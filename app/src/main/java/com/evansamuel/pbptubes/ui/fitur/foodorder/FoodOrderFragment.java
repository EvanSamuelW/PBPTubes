package com.evansamuel.pbptubes.ui.fitur.foodorder;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.evansamuel.pbptubes.R;
import com.evansamuel.pbptubes.ui.fitur.menu.ApiClient;
import com.evansamuel.pbptubes.ui.fitur.menu.ApiInterface;
import com.evansamuel.pbptubes.ui.fitur.menu.MenuDao;
import com.evansamuel.pbptubes.ui.fitur.transaksi.TransaksiResponse;

import java.text.ParseException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FoodOrderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FoodOrderFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private MenuDao menu;
    private TextView twName, twPrice;
    private NumberPicker amount;
    private ImageView ivMenu;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FoodOrderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FoodOrderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FoodOrderFragment newInstance(String param1, String param2) {
        FoodOrderFragment fragment = new FoodOrderFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_food_order, container, false);
        twName = v.findViewById(R.id.foodname);
        twPrice = v.findViewById(R.id.foodprice);
        ivMenu = v.findViewById(R.id.ivFood);
        amount = v.findViewById(R.id.amount);

        amount.setValue(1);
        amount.setMinValue(1);
        amount.setMaxValue(100);
        menu = (MenuDao) getArguments().getSerializable("menu");
        twName.setText(menu.getNama());
        twPrice.setText("Rp" + menu.getPrice().toString());
        Glide.with(getContext())
                .load(menu.getPhoto())
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(ivMenu);



        return v;
    }

    private void saveBooking(Double finalPrice, String email) throws ParseException {

//        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
//        Call<TransaksiResponse> add = apiService.createBooking(emailUser, Name, Room, CheckInDate, CheckOutDate, Price);
//
//
//        add.enqueue(new Callback<TransaksiResponse>() {
//            @Override
//            public void onResponse(Call<TransaksiResponse> call, Response<TransaksiResponse> response) {
//                Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                progressDialog.dismiss();
//            }
//
//            @Override
//            public void onFailure(Call<TransaksiResponse> call, Throwable t) {
//                progressDialog.dismiss();
//
//            }
//        });
    }
}