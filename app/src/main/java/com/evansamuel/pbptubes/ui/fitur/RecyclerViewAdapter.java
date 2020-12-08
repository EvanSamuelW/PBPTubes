package com.evansamuel.pbptubes.ui.fitur;



import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.evansamuel.pbptubes.BR;
import com.evansamuel.pbptubes.R;
import com.evansamuel.pbptubes.databinding.AdapterRecyclerViewBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private Context context;
    private List<Kamar> result;
    public static final String Jenis="Room type is not found";
    public static final String Harga="Room price is not found";
    public static final String Fasilitas="Room facilities is not found";
    public static final Double harga2=0.0;




    public RecyclerViewAdapter(){}

    public RecyclerViewAdapter(Context context, List<Kamar> result){
        this.context = context;
        this.result = result;
    }



   @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterRecyclerViewBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.adapter_recycler_view,parent,false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {
        Kamar daftarKamar = result.get(position);
        holder.bind(daftarKamar);

        String nama = holder.adapterRecyclerViewBinding.tvNpm.getText().toString();
        String harga = holder.adapterRecyclerViewBinding.tvFakultas.getText().toString();
        String fasilitas = holder.adapterRecyclerViewBinding.tvNama.getText().toString();
        Double harga2 = Double.parseDouble(harga);


        holder.adapterRecyclerViewBinding.order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString(Jenis,nama);
                bundle.putDouble(Harga,harga2);
                bundle.putString(Fasilitas,fasilitas);


                Navigation.findNavController(view).navigate(R.id.action_nav_book_to_nav_order,bundle);



            }
        });
    }





    @Override
    public int getItemCount() {
        return result.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public AdapterRecyclerViewBinding adapterRecyclerViewBinding;
        public MyViewHolder(AdapterRecyclerViewBinding adapterRecyclerViewBinding)
        {
            super(adapterRecyclerViewBinding.getRoot());
            this.adapterRecyclerViewBinding = adapterRecyclerViewBinding;
        }

        public void bind(Object obj){
            adapterRecyclerViewBinding.setVariable(BR.kmr, obj);
            adapterRecyclerViewBinding.executePendingBindings();
        }


    }


}