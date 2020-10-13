package com.evansamuel.pbptubes;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.evansamuel.pbptubes.databinding.FragmentBookBinding;
import com.evansamuel.pbptubes.ui.fitur.BookFragment;
import com.evansamuel.pbptubes.ui.fitur.DaftarKamar;
import com.evansamuel.pbptubes.ui.fitur.Kamar;
import com.evansamuel.pbptubes.ui.fitur.RecyclerViewAdapter;
import com.evansamuel.pbptubes.ui.fitur.transaksi.DatabaseClient;
import com.evansamuel.pbptubes.ui.fitur.transaksi.Transaksi;
import com.google.android.material.navigation.NavigationView;
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

import javax.annotation.Nullable;

public class ViewTransaksi extends Fragment {
    private ArrayList<Transaksi> ListTransaksi;
    private RecyclerView recyclerView;
    private UserRecyclerViewAdapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;
    FragmentBookBinding binding;
    FirebaseAuth fAuth;
    String userId,username="Evan Samuel";
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




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_transaksi, container, false);

        recyclerView = root.findViewById(R.id.user_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        adapter = new UserRecyclerViewAdapter(getActivity(), ListTransaksi);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();

        userId = fAuth.getCurrentUser().getUid();
        user = fAuth.getCurrentUser();

        final DocumentReference documentReference = fStore.collection("users").document(userId);
        documentReference.addSnapshotListener(getActivity(), new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if (documentSnapshot.exists()) {
                    username = documentSnapshot.getString("fName");
                } else {
                    Log.d("tag", "onEvent: Document do not exists");
                }
            }
        });
        Toast.makeText(getActivity(),username,Toast.LENGTH_LONG).show();
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
                        .loadAllUserTransaction(username);
                return pegawaiList;
            }

            @Override
            protected void onPostExecute(List<Transaksi> users) {
                super.onPostExecute(users);
                adapter = new UserRecyclerViewAdapter(getActivity(), users);
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
