package com.evansamuel.pbptubes.ui.fitur.menu;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.evansamuel.pbptubes.R;

import java.util.ArrayList;
import java.util.List;

public class MenuRecyclerAdapter extends RecyclerView.Adapter<MenuRecyclerAdapter.RoomViewHolder> implements Filterable {


    private List<MenuDao> dataList;
    private List<MenuDao> filteredDataList;
    private Context context;

    public MenuRecyclerAdapter(Context context, List<MenuDao> dataList) {
        this.dataList = dataList;
        this.filteredDataList = dataList;
        this.context = context;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charSequenceString = constraint.toString();
                if(charSequenceString.isEmpty()) {
                    filteredDataList = dataList;
                } else {
                    List<MenuDao> filteredList = new ArrayList<>();
                    for (MenuDao UserDAO : dataList) {
                        if(UserDAO.getNama().toLowerCase().contains(charSequenceString.toLowerCase())) {
                            filteredList.add(UserDAO);
                        }
                        filteredDataList = filteredList;
                    }
                }
                FilterResults results = new FilterResults();
                results.values = filteredDataList;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredDataList = (List<MenuDao>) results.values;
                notifyDataSetChanged();
            }
        };
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


    }

    @Override
    public int getItemCount() {
        return filteredDataList.size();
    }

    public class RoomViewHolder extends RecyclerView.ViewHolder{
        private TextView twName, twPrice;
        private ImageView ivMenu;
        private LinearLayout mParent;

        public RoomViewHolder(@NonNull View itemView) {
            super(itemView);
            twName = itemView.findViewById(R.id.twName);
            twPrice = itemView.findViewById(R.id.twPrice);
            ivMenu = itemView.findViewById(R.id.ivMenu);
            mParent = itemView.findViewById(R.id.linearLayout);
        }
    }
}
