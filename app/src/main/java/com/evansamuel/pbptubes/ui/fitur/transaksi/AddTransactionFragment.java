package com.evansamuel.pbptubes.ui.fitur.transaksi;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.evansamuel.pbptubes.R;
import com.evansamuel.pbptubes.ui.fitur.RecyclerViewAdapter;
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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Nullable;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddTransactionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddTransactionFragment extends Fragment {
    public static final String TAG = "TAG";
    FirebaseAuth fAuth;
    String userId;
    FirebaseFirestore fStore;
    FirebaseUser user;
    StorageReference storageReference;
    TextView nameView,fasilitasView,hargaView,roomView,checkInDate,checkOutDate;
    private DatePickerDialog.OnDateSetListener mDateListener,mDateListener2;
    Button btn_order;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddTransactionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddTransactionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddTransactionFragment newInstance(String param1, String param2) {
        AddTransactionFragment fragment = new AddTransactionFragment();
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

        View root = inflater.inflate(R.layout.fragment_add_transaction, container, false);
       String jenis = getArguments().getString(RecyclerViewAdapter.Jenis);
        String harga = getArguments().getString(RecyclerViewAdapter.Harga);
        String fasilitas = getArguments().getString(RecyclerViewAdapter.Fasilitas);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();


        nameView = root.findViewById(R.id.nameView);
        fasilitasView = root.findViewById(R.id.FasilitasView);
        hargaView = root.findViewById(R.id.HargaView);
        roomView = root.findViewById(R.id.roomView);

        hargaView.setText("Rp"+ harga + " Per Malam");
        roomView.setText(jenis);
        fasilitasView.setText(fasilitas);
        userId = fAuth.getCurrentUser().getUid();
        user = fAuth.getCurrentUser();

        checkInDate = root.findViewById(R.id.checkInDate);
        checkOutDate = root.findViewById(R.id.checkOutDate);
        btn_order = root.findViewById(R.id.btn_order);

        checkInDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        getActivity(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();


            }
        });

        checkOutDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(
                        getActivity(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateListener2,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();


            }
        });

        mDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month +1;
                Log.d(TAG, "onDateSet: mm/dd/yyyy: " + month + "/" + day + "/" + year  );

                String date2 = month + "/" + day + "/" + year;
                checkInDate.setText(date2);
            }
        };

        mDateListener2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month +1;
                Log.d(TAG, "onDateSet: mm/dd/yyyy: " + month + "/" + day + "/" + year  );

                String date1 = month + "/" + day + "/" + year;
                checkOutDate.setText(date1);
            }
        };

        btn_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date1 = checkInDate.getText().toString();
                String date2 = checkOutDate.getText().toString();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");


                try {
                    Date dateIn = sdf.parse(date1);
                    Date dateOut = sdf.parse(date2);

                    if(dateOut.getTime()>dateIn.getTime())
                    {
                        long difference = dateOut.getTime()- dateIn.getTime();
                        long difference_In_Days
                                = (difference
                                / (1000 * 60 * 60 * 24))
                                % 365;

                        long finalPrice = difference_In_Days*Integer.parseInt(harga);

                        Toast.makeText(getActivity(),"Total Harga :" +Long.toString(finalPrice),Toast.LENGTH_SHORT).show();
                        add();

                    }else if(dateOut.getTime()==dateIn.getTime())
                    {
                        Toast.makeText(getActivity(),"Pemesanan kamar minimal satu malam",Toast.LENGTH_SHORT).show();
                    }else
                    {
                        Toast.makeText(getActivity(),"Tanggal Check In tidak bisa lebih kecil dari tanggal Check In",Toast.LENGTH_SHORT).show();

                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }


            }
        });
        final DocumentReference documentReference = fStore.collection("users").document(userId);
        documentReference.addSnapshotListener(getActivity(), new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if (documentSnapshot.exists()) {
                    nameView.setText(documentSnapshot.getString("fName"));
                } else {
                    Log.d("tag", "onEvent: Document do not exists");
                }
            }
        });

        return root;
    }


    private void add(){
        final String Room = roomView.getText().toString();
        final String Name = nameView.getText().toString();
        final String Price = hargaView.getText().toString();
        final String CheckInDate = checkInDate.getText().toString();
        final String CheckOutDate = checkOutDate.getText().toString();

        class AddTransaksi extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                Transaksi transaksi = new Transaksi();
                transaksi.setName(Name);
                transaksi.setRoom(Room);
                transaksi.setPrice(Price);
                transaksi.setCheckInDate(CheckInDate);
                transaksi.setCheckOutDate(CheckOutDate);

                DatabaseClient.getInstance(getActivity().getApplicationContext()).getDatabase()
                        .transaksiDAO()
                        .insert(transaksi);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getActivity().getApplicationContext(), "Transaction Saved", Toast.LENGTH_SHORT).show();
                roomView.setText("");
                nameView.setText("");
                hargaView.setText("");


            }
        }

        AddTransaksi add = new AddTransaksi();
        add.execute();
    }
}