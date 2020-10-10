package com.evansamuel.pbptubes.ui.fitur;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.evansamuel.pbptubes.BR;
import com.evansamuel.pbptubes.R;
import com.evansamuel.pbptubes.databinding.AdapterRecyclerViewBinding;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private Context context;
    private List<Kamar> result;

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