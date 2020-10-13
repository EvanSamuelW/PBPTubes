package com.evansamuel.pbptubes;



import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;


import com.evansamuel.pbptubes.ui.fitur.transaksi.Transaksi;

import java.util.ArrayList;
import java.util.List;

public class  UserRecyclerViewAdapter extends RecyclerView.Adapter<UserRecyclerViewAdapter.UserViewHolder> {

    private Context context;
    private List<Transaksi> transaksiList;


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
//        holder.textViewNumber.setText(transaksi.getId());
        holder.textViewName.setText(transaksi.getName());
        holder.textViewPrice.setText("Rp. " + transaksi.getPrice());
    }

    @Override
    public int getItemCount() {
        return transaksiList.size();
    }


    public class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewName, textViewNumber, textViewPrice;
        Button edit,delete;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.name_text);
            textViewNumber = itemView.findViewById(R.id.number_text);
            textViewPrice = itemView.findViewById(R.id.price_text);
            edit = itemView.findViewById(R.id.btn_edit);
            delete = itemView.findViewById(R.id.btn_delete);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            AppCompatActivity activity = (AppCompatActivity) v.getContext();
            Transaksi transaksi = transaksiList.get(getAdapterPosition());
            Bundle data = new Bundle();
            data.putSerializable("transaksi", transaksi);

        }
    }







}