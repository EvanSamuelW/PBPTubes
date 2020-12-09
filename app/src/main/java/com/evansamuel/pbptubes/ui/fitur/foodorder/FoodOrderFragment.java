package com.evansamuel.pbptubes.ui.fitur.foodorder;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.evansamuel.pbptubes.ui.fitur.menu.MenuResponse;
import com.evansamuel.pbptubes.ui.fitur.transaksi.TransaksiResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.ParseException;

import javax.annotation.Nullable;

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
    String userId, email,customer;
    FirebaseFirestore fStore;
    FirebaseAuth fAuth;
    FirebaseUser user;
    StorageReference storageReference;
    Button btn_order;
    private ProgressDialog progressDialog;
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
        btn_order = v.findViewById(R.id.btn_order);
        progressDialog = new ProgressDialog(getContext());

        amount.setValue(1);
        amount.setMinValue(1);
        amount.setMaxValue(100);
        menu = (MenuDao) getArguments().getSerializable("menu");
        twName.setText(menu.getNama());
        twPrice.setText( menu.getPrice().toString());
        Glide.with(getContext())
                .load(menu.getPhoto())
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(ivMenu);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        userId = fAuth.getCurrentUser().getUid();
        user = fAuth.getCurrentUser();


        btn_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DocumentReference documentReference = fStore.collection("users").document(userId);
                documentReference.addSnapshotListener(getActivity(), new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                        if (documentSnapshot.exists()) {
                            email = documentSnapshot.getString("email");
                            customer = documentSnapshot.getString("fName");

                                saveFood(customer, email);
                        } else {
                            Log.d("tag", "onEvent: Document do not exists");
                        }
                    }
                });

            }
        });
        return v;
    }

    private void saveFood(String customer, String email)  {
        final String Menu = twName.getText().toString();
        final Double Price = Double.parseDouble(twPrice.getText().toString()) * amount.getValue();
        final String Photo = menu.getPhoto();
        final String emailUser = email;
        final String customer_name = customer;
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<TransaksiFoodResponse> add  = apiService.createFoodTransaksi(twName.getText().toString(),Price,amount.getValue(), emailUser, customer_name, menu.getPhoto());

        add.enqueue(new Callback<TransaksiFoodResponse>() {
            @Override
            public void onResponse(Call<TransaksiFoodResponse> call, Response<TransaksiFoodResponse> response) {
                Toast.makeText(getContext(), "Insert Transaction Food Failed", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<TransaksiFoodResponse> call, Throwable t) {
                Toast.makeText(getContext(),"Insert Transaction Food Success", Toast.LENGTH_SHORT).show();

                progressDialog.dismiss();

            }
        });
    }


}