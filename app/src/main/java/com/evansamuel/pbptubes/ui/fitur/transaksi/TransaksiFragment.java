package com.evansamuel.pbptubes.ui.fitur.transaksi;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.evansamuel.pbptubes.R;
import com.evansamuel.pbptubes.UserRecyclerViewAdapter;
import com.evansamuel.pbptubes.databinding.FragmentBookBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TransaksiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TransaksiFragment extends Fragment {
    private ArrayList<Transaksi> ListTransaksi;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    FragmentBookBinding binding;
    FirebaseAuth fAuth;
    private SwipeRefreshLayout refreshLayout;
    //    String userId,username="Evan Samuel";
    FirebaseFirestore fStore;
    FirebaseUser user;
    StorageReference storageReference;
    Button order;
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
        View root = inflater.inflate(R.layout.fragment_transaksi, container, false);


        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        recyclerView = root.findViewById(R.id.user_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

//        userId = fAuth.getCurrentUser().getUid();
//        user = fAuth.getCurrentUser();
//
//        final DocumentReference documentReference = fStore.collection("users").document(userId);
//        documentReference.addSnapshotListener(getActivity(), new EventListener<DocumentSnapshot>() {
//            @Override
//            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
//                if (documentSnapshot.exists()) {
//                    username = documentSnapshot.getString("fName");
//                } else {
//                    Log.d("tag", "onEvent: Document do not exists");
//                }
//            }
//        });
//        Toast.makeText(getActivity(),username,Toast.LENGTH_LONG).show();
        getUsers();


        return root;



    }



    private void getUsers(){
        class GetUsers extends AsyncTask<Void, Void, List<Transaksi>> {

            @Override
            protected List  <Transaksi> doInBackground(Void... voids) {
                List<Transaksi> pegawaiList = DatabaseClient
                        .getInstance(getActivity())
                        .getDatabase()
                        .transaksiDAO()
                        .loadAllUserTransaction();
                return pegawaiList;
            }

            @Override
            protected void onPostExecute(List<Transaksi> users) {
                super.onPostExecute(users);
                UserRecyclerViewAdapter adapter = new UserRecyclerViewAdapter(getContext(), users);
                recyclerView.setAdapter(adapter);
                if (users.isEmpty()){
                    Toast.makeText(getActivity(), "Empty List", Toast.LENGTH_SHORT).show();
                }
            }
        }

        GetUsers get = new GetUsers();
        get.execute();
    }

}