package com.evansamuel.pbptubes;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.Transliterator;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;


import com.evansamuel.pbptubes.ui.fitur.transaksi.DatabaseClient;
import com.evansamuel.pbptubes.ui.fitur.transaksi.Transaksi;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class UserRecyclerViewAdapter extends RecyclerView.Adapter<UserRecyclerViewAdapter.UserViewHolder> {
    public static final String TAG = "TAG";
    private Context context;
    private List<Transaksi> transaksiList;
    private DatePickerDialog.OnDateSetListener mDateListener;


    public UserRecyclerViewAdapter(Context context, List<Transaksi> transaksiList) {
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
        Transaksi transaksi = transaksiList.get(position);
        holder.userName.setText(transaksi.getName());
        holder.CheckInDate.setText(transaksi.getCheckInDate());
        holder.CheckOutDate.setText(transaksi.getCheckOutDate());
        holder.room.setText(transaksi.getRoom());
        holder.PriceTag.setText("Rp. " + transaksi.getPrice());
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
            room = itemView.findViewById(R.id.room_text);
            textViewName = itemView.findViewById(R.id.name_text);
            userName = itemView.findViewById(R.id.number_text);
            CheckOutDate = itemView.findViewById(R.id.price_text);
            PriceTag = itemView.findViewById(R.id.harga);
            edit = itemView.findViewById(R.id.btn_edit);
            delete = itemView.findViewById(R.id.btn_delete);
            CheckInDate = itemView.findViewById(R.id.name_text);

            edit.setOnClickListener(new View.OnClickListener() {
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
                }
            });

            mDateListener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                    month = month + 1;
                    Log.d(TAG, "onDateSet:dd/mm/yyyy: " + day + "/" + month + "/" + year);

                    String date2 = day + "/" + month + "/" + year;
                    Transaksi transaksi = transaksiList.get(getAdapterPosition());

                    String dateMasuk = transaksi.getCheckInDate();
                    String dateKeluar = date2;
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");


                    try {
                        Date dateIn = sdf.parse(dateMasuk);
                        Date dateOut = sdf.parse(dateKeluar);
                        long difference = dateOut.getTime() - dateIn.getTime();
                        long seconds = difference / 1000;
                        long minutes = seconds / 60;
                        long hours = minutes / 60;
                        long days = hours / 24;

                        if (days > 0) {

                            long finalPrice = days * Integer.parseInt(transaksi.getPrice());
                            transaksi.setPrice(String.valueOf(finalPrice));
                            transaksi.setCheckOutDate(date2);
                            update(transaksi);

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

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Delete Transaction")
                            .setMessage("Are you sure to delete this transaction?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Transaksi transaksi = transaksiList.get(getAdapterPosition());
                                    delete(transaksi);
                                }
                            }).setNegativeButton("No", null)
                            .create().show();
                }
            });
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {


        }
    }


    private void update(final Transaksi transaksi) {
        class UpdateUser extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                DatabaseClient.getInstance(context.getApplicationContext()).getDatabase()
                        .transaksiDAO()
                        .update(transaksi);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(context.getApplicationContext(), "Transaction updated", Toast.LENGTH_SHORT).show();

            }
        }

        UpdateUser update = new UpdateUser();
        update.execute();
    }

    private void delete(final Transaksi user) {
        class DeleteUser extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                DatabaseClient.getInstance(context.getApplicationContext()).getDatabase()
                        .transaksiDAO()
                        .delete(user);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(context.getApplicationContext(), "Transaction deleted", Toast.LENGTH_SHORT).show();

            }
        }

        DeleteUser delete = new DeleteUser();
        delete.execute();
    }


}