package com.evansamuel.pbptubes.ui.fitur.transaksi;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.evansamuel.pbptubes.R;
import com.evansamuel.pbptubes.ui.fitur.menu.ApiClient;
import com.evansamuel.pbptubes.ui.fitur.menu.ApiInterface;
import com.evansamuel.pbptubes.ui.fitur.menu.MenuResponse;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRecyclerViewAdapter extends RecyclerView.Adapter<UserRecyclerViewAdapter.UserViewHolder> {
    public static final String TAG = "TAG";
    private Context context;
    private List<TransaksiDAO> transaksiList;
    private DatePickerDialog.OnDateSetListener mDateListener;
    private ProgressDialog progressDialog;


    public UserRecyclerViewAdapter(Context context, List<TransaksiDAO> transaksiList) {
        this.context = context;
        this.transaksiList = transaksiList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_list, parent, false);
        return new UserViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        TransaksiDAO transaksi = transaksiList.get(position);
        holder.userName.setText(transaksi.getName());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String dateIn = sdf.format(transaksi.getCheck_in_date());
        String dateOut = sdf.format(transaksi.getCheck_out_date());
        holder.CheckInDate.setText(dateIn);
        holder.CheckOutDate.setText(dateOut);
        holder.room.setText(transaksi.getRoom());
        holder.PriceTag.setText("Rp. " + Math.round(transaksi.getPrice()) );

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Delete Transaction")
                        .setMessage("Are you sure to delete this transaction?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                deleteBooking(transaksi.getId());
                            }
                        }).setNegativeButton("No", null)
                        .create().show();
            }
        });

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Edit Your Transaction")
                        .setMessage("You can only change your check out date. Proceed?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Calendar cal = Calendar.getInstance();
                                int year = cal.get(Calendar.YEAR);
                                int month = cal.get(Calendar.MONTH);
                                int day = cal.get(Calendar.DAY_OF_MONTH);

                                DatePickerDialog dialog = new DatePickerDialog(
                                        context,
                                        android.R.style.Theme_Material_Light_Dialog_MinWidth,
                                        mDateListener,
                                        year, month, day);
                                dialog.getWindow();
                                dialog.show();
                                dialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
                                dialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);


                            }
                        }).setNegativeButton("No", null)
                        .create().show();

                mDateListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month + 1;
                        Log.d(TAG, "onDateSet:yyyy-mm-dd: " + year + "-" + month + "-" + day);
                        DecimalFormat mFormat = new DecimalFormat("00");
                        String date2 = mFormat.format(Double.valueOf(year)) + "-" + mFormat.format(Double.valueOf(month)) + "-" + mFormat.format(Double.valueOf(day));

                        String dateMasuk = dateIn;
                        String dateKeluar = date2;
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        String dateTampung1 = dateOut;
                        Toast.makeText(context, dateMasuk, Toast.LENGTH_SHORT).show();
                        Toast.makeText(context, dateTampung1, Toast.LENGTH_SHORT).show();

                        try {
                            Date dateIn = sdf.parse(dateMasuk);
                            Date dateOut = sdf.parse(dateKeluar);
                            Date dateTampung2 = sdf.parse(dateTampung1);
                            long difference = dateOut.getTime() - dateIn.getTime();
                            long difference2 = dateTampung2.getTime() - dateIn.getTime();

                            long days = TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS);
                            long days2 = TimeUnit.DAYS.convert(difference2, TimeUnit.MILLISECONDS);


                            if (days > 0) {

                                long finalPrice = days * (transaksi.getPrice()).longValue()/days2;
                                Double d = Long.valueOf(finalPrice).doubleValue();
                                transaksi.setCheck_out_date(dateOut);
                                transaksi.setPrice(d);
                                updateTransaksi(transaksi);

                            } else if (days == 0) {
                                Toast.makeText(context, "This hotel has 1 night minimum stay", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Check out date is invalid", Toast.LENGTH_SHORT).show();

                            }

                        } catch (ParseException e) {
                            e.printStackTrace();
                        }


                    }
                };
            }
        });


    }

    @Override
    public int getItemCount() {
        return transaksiList.size();
    }


    public class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView userName, CheckOutDate, PriceTag, CheckInDate, room;
        Button edit, delete;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            progressDialog = new ProgressDialog(context);
            room = itemView.findViewById(R.id.room_text);
            userName = itemView.findViewById(R.id.name_text);
            CheckOutDate = itemView.findViewById(R.id.check_out_text);
            PriceTag = itemView.findViewById(R.id.harga);
            edit = itemView.findViewById(R.id.btn_edit);
            delete = itemView.findViewById(R.id.btn_delete);
            CheckInDate = itemView.findViewById(R.id.check_in_text);


            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {

        }
    }

    private void updateTransaksi(TransaksiDAO transaksiDAO) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String dateIn = sdf.format(transaksiDAO.getCheck_in_date());
        String dateOut = sdf.format(transaksiDAO.getCheck_out_date());
        Call<TransaksiResponse> update = apiService.updateBooking(transaksiDAO.getId(), transaksiDAO.getEmail(), transaksiDAO.getName(), transaksiDAO.getRoom(), dateIn, dateOut, transaksiDAO.getPrice());


        update.enqueue(new Callback<TransaksiResponse>() {
            @Override
            public void onResponse(Call<TransaksiResponse> call, Response<TransaksiResponse> response) {
                Toast.makeText(context, "Update Berhasil", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<TransaksiResponse> call, Throwable t) {
                Toast.makeText(context, "Kesalahan Jaringan", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        });
    }

    private void deleteBooking(String id) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<TransaksiResponse> delete = apiService.deleteBooking(id);


        delete.enqueue(new Callback<TransaksiResponse>() {
            @Override
            public void onResponse(Call<TransaksiResponse> call, Response<TransaksiResponse> response) {
                Toast.makeText(context, "Update Berhasil", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<TransaksiResponse> call, Throwable t) {
                Toast.makeText(context, "Kesalahan Jaringan", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        });
    }

}
