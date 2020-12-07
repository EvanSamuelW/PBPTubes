package com.evansamuel.pbptubes.ui.fitur.transaksi;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.evansamuel.pbptubes.R;
import com.evansamuel.pbptubes.UserRecyclerViewAdapter;
import com.evansamuel.pbptubes.databinding.FragmentBookBinding;
import com.evansamuel.pbptubes.ui.fitur.menu.ApiClient;
import com.evansamuel.pbptubes.ui.fitur.menu.ApiInterface;
import com.evansamuel.pbptubes.ui.fitur.menu.MenuDao;
import com.evansamuel.pbptubes.ui.fitur.menu.MenuRecyclerAdapter;
import com.evansamuel.pbptubes.ui.fitur.menu.MenuResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TransaksiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TransaksiFragment extends Fragment {
    private ArrayList<TransaksiDAO> ListTransaksi = new ArrayList<>();
    private UserRecyclerViewAdapter recyclerAdapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    FragmentBookBinding binding;
    FirebaseAuth fAuth;
    SwipeRefreshLayout refreshLayout;
    FirebaseFirestore fStore;
    FirebaseUser user;
    StorageReference storageReference;
    String userId;
    private String email;
    View v;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TransaksiFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TransaksiFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TransaksiFragment newInstance(String param1, String param2) {
        TransaksiFragment fragment = new TransaksiFragment();
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

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_transaksi, container, false);

        refreshLayout = v.findViewById(R.id.swipe_refresh);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        recyclerView = v.findViewById(R.id.user_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        userId = fAuth.getCurrentUser().getUid();
        user = fAuth.getCurrentUser();

        final DocumentReference documentReference = fStore.collection("users").document(userId);
        documentReference.addSnapshotListener(getActivity(), new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if (documentSnapshot.exists()) {
                    email = documentSnapshot.getString("email");
                    loadBooking(email);

                    refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                        @Override
                        public void onRefresh() {
                            loadBooking(email);
                            refreshLayout.setRefreshing(false);
                        }
                    });

                } else {
                    Log.d("tag", "onEvent: Document do not exists");
                }
            }
        });


        return v;


    }


    public void loadBooking(String email) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<TransaksiResponse> call = apiService.getBookingByEmail(email, "data");

        call.enqueue(new Callback<TransaksiResponse>() {
            @Override
            public void onResponse(Call<TransaksiResponse> call, Response<TransaksiResponse> response) {
                generateDataList(response.body().getTransactions());
                refreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<TransaksiResponse> call, Throwable t) {
//                Toast.makeText(getContext(), "Kesalahan Jaringan", Toast.LENGTH_SHORT).show();
                refreshLayout.setRefreshing(false);
            }
        });
    }


    private void generateDataList(List<TransaksiDAO> customerList) {

        recyclerView = v.findViewById(R.id.user_rv);
        recyclerAdapter = new UserRecyclerViewAdapter(getContext(), customerList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerAdapter);

    }
}