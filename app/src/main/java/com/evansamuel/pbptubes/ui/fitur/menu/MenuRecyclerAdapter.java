package com.evansamuel.pbptubes.ui.fitur.menu;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.evansamuel.pbptubes.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuRecyclerAdapter extends RecyclerView.Adapter<MenuRecyclerAdapter.RoomViewHolder> {


    private List<MenuDao> dataList;
    private List<MenuDao> filteredDataList;
    private Context context;
    private ProgressDialog progressDialog;
    private Button deletebtn,orderbtn,editbtn;
    FirebaseAuth fAuth;
    FirebaseUser usercek;


    public MenuRecyclerAdapter(Context context, List<MenuDao> dataList) {
        this.dataList = dataList; //d
        this.filteredDataList = dataList;
        this.context = context;
    }



    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_adapter_menu, parent, false);
        return new RoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuRecyclerAdapter.RoomViewHolder holder, int position) {
        final MenuDao brg = filteredDataList.get(position);




        holder.twName.setText(brg.getNama());
        holder.twPrice.setText("Rp" + brg.getPrice().toString());
        Glide.with(context)
                .load(brg.getPhoto())
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(holder.ivMenu);
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Delete Menu")
                        .setMessage("Are you sure to delete this menu?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String id = brg.getId();
                                deleteMenu(id);
                            }
                        }).setNegativeButton("No", null)
                        .create().show();
            }
        });

        holder.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putSerializable("menu",brg);
                bundle.putString("status", "edit");

                AddFoodFragment fragment1 = new AddFoodFragment();
                fragment1.setArguments(bundle);
                FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.view, fragment1);
                fragmentTransaction.commit();
            }
        });


    }

    @Override
    public int getItemCount() {
        return filteredDataList.size();
    }

    public class RoomViewHolder extends RecyclerView.ViewHolder {
        private TextView twName, twPrice;
        private ImageView ivMenu;
        private LinearLayout mParent;
        private Button editBtn, deleteBtn,orderBtn;


        public RoomViewHolder(@NonNull View itemView) {
            super(itemView);
            fAuth = FirebaseAuth.getInstance();
            usercek = fAuth.getCurrentUser();
            progressDialog = new ProgressDialog(context);

            twName = itemView.findViewById(R.id.twName);
            twPrice = itemView.findViewById(R.id.twPrice);
            ivMenu = itemView.findViewById(R.id.ivMenu);
            mParent = itemView.findViewById(R.id.linearLayout);
            editBtn = itemView.findViewById(R.id.edit);
            deleteBtn = itemView.findViewById(R.id.delete);
            orderBtn = itemView.findViewById(R.id.order);

            if(usercek!=null){
                deleteBtn.setVisibility(View.GONE);
                editBtn.setVisibility(View.GONE);
            }
            else{
                orderBtn.setVisibility(View.GONE);
            }
        }
    }

    private void deleteMenu(String id) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<MenuResponse> delete = apiService.deleteMenu(id);


        delete.enqueue(new Callback<MenuResponse>() {
            @Override
            public void onResponse(Call<MenuResponse> call, Response<MenuResponse> response) {
                Toast.makeText(context, "Kesalahan Jaringan", Toast.LENGTH_SHORT).show();

                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<MenuResponse> call, Throwable t) {
                Toast.makeText(context, "Delete Berhasil", Toast.LENGTH_SHORT).show();

                progressDialog.dismiss();

            }
        });
    }

}
