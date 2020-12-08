package com.evansamuel.pbptubes.ui.fitur.transaksi;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.evansamuel.pbptubes.R;
import com.evansamuel.pbptubes.ui.fitur.menu.ApiClient;
import com.evansamuel.pbptubes.ui.fitur.menu.ApiInterface;

import java.text.SimpleDateFormat;
import java.util.List;

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
            holder.PriceTag.setText("Rp. " + transaksi.getPrice().toString());

    }

    @Override
    public int getItemCount() {
        return transaksiList.size();
    }


    public class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewName, userName, CheckOutDate, PriceTag, CheckInDate, room;
        Button edit, delete;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            progressDialog = new ProgressDialog(context);
            room = itemView.findViewById(R.id.room_text);
            textViewName = itemView.findViewById(R.id.name_text);
            userName = itemView.findViewById(R.id.number_text);
            CheckOutDate = itemView.findViewById(R.id.price_text);
            PriceTag = itemView.findViewById(R.id.harga);
            edit = itemView.findViewById(R.id.btn_edit);
            delete = itemView.findViewById(R.id.btn_delete);
            CheckInDate = itemView.findViewById(R.id.name_text);

//            edit.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                    builder.setTitle("Edit Your Transaction")
//                            .setMessage("You can only change your check out date. Proceed?")
//                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialogInterface, int i) {
//                                    Calendar cal = Calendar.getInstance();
//                                    int year = cal.get(Calendar.YEAR);
//                                    int month = cal.get(Calendar.MONTH);
//                                    int day = cal.get(Calendar.DAY_OF_MONTH);
//
//                                    DatePickerDialog dialog = new DatePickerDialog(
//                                            context,
//                                            android.R.style.Theme_Material_Light_Dialog_MinWidth,
//                                            mDateListener,
//                                            year, month, day);
//                                    dialog.getWindow();
//                                    dialog.show();
//                                    dialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
//                                    dialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
//
//
//                                }
//                            }).setNegativeButton("No", null)
//                            .create().show();
//                }
//            });

//            mDateListener = new DatePickerDialog.OnDateSetListener() {
//                @Override
//                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
//                    month = month + 1;
//                    Log.d(TAG, "onDateSet:dd/mm/yyyy: " + day + "/" + month + "/" + year);
//
//                    String date2 = day + "/" + month + "/" + year;
//                    TransaksiDAO transaksi = transaksiList.get(getAdapterPosition());
//
//                    String dateMasuk = CheckInDate.getText().toString();
//                    String dateKeluar = date2;
//                    SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
//                    Date dateTampung = transaksi.getCheck_out_date();
//
//                    try {
//                        Date dateIn = sdf.parse(dateMasuk);
//                        Date dateOut = sdf.parse(dateKeluar);
//                        Date dateT = sdf.parse(dateTampung);
//                        long difference = dateOut.getTime() - dateIn.getTime();
//                        long difference2 = dateT.getTime() - dateIn.getTime();
//                        long seconds = difference / 1000;
//                        long minutes = seconds / 60;
//                        long hours = minutes / 60;
//                        long days = hours / 24;
//
//                        long seconds2 = difference2 / 1000;
//                        long minutes2 = seconds2 / 60;
//                        long hours2 = minutes2 / 60;
//                        long days2 = hours2 / 24;
//
//                        if (days > 0) {
//
//                            long finalPrice = days * ((Integer.parseInt(transaksi.getPrice()))/days2);
//                            transaksi.setPrice(String.valueOf(finalPrice));
//                            transaksi.setCheckOutDate(date2);
//                            update(transaksi);
//
//                        } else if (days == 0) {
//                            Toast.makeText(context, "This hotel has 1 night minimum stay", Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(context, "Check out date is invalid", Toast.LENGTH_SHORT).show();
//
//                        }
//
//                    } catch (ParseException e) {
//                        e.printStackTrace();
//                    }
//
//
//                }
//            };
//
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Delete Transaction")
                            .setMessage("Are you sure to delete this transaction?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    TransaksiDAO transaksi = transaksiList.get(getAdapterPosition());
                                    deleteBooking(transaksi.getId());
                                }
                            }).setNegativeButton("No", null)
                            .create().show();
                }
            });
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {

        }
    }


    //    private void update(final Transaksi transaksi) {
//        class UpdateUser extends AsyncTask<Void, Void, Void> {
//
//            @Override
//            protected Void doInBackground(Void... voids) {
//
//                DatabaseClient.getInstance(context.getApplicationContext()).getDatabase()
//                        .transaksiDAO()
//                        .update(transaksi);
//                return null;
//            }
//
//            @Override
//            protected void onPostExecute(Void aVoid) {
//                super.onPostExecute(aVoid);
//                Toast.makeText(context.getApplicationContext(), "Transaction updated", Toast.LENGTH_SHORT).show();
//
//            }
//        }
//
//        UpdateUser update = new UpdateUser();
//        update.execute();
//    }
//
    private void deleteBooking(String id) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<TransaksiResponse> delete = apiService.deleteBooking(id);


        delete.enqueue(new Callback<TransaksiResponse>() {
            @Override
            public void onResponse(Call<TransaksiResponse> call, Response<TransaksiResponse> response) {
                Toast.makeText(context, "Delete Berhasil", Toast.LENGTH_SHORT).show();
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