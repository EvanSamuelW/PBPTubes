package com.evansamuel.pbptubes.ui.fitur.transaksi;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

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
    String userId,email;
    FirebaseFirestore fStore;
    FirebaseUser user;
    StorageReference storageReference;
    TextView nameView, fasilitasView, hargaView, roomView, checkInDate, checkOutDate;
    private DatePickerDialog.OnDateSetListener mDateListener, mDateListener2;
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





        nameView = root.findViewById(R.id.nameView);
        fasilitasView = root.findViewById(R.id.FasilitasView);
        hargaView = root.findViewById(R.id.HargaView);
        roomView = root.findViewById(R.id.roomView);

        hargaView.setText("Rp" + harga + " Per Malam");
        roomView.setText(jenis);
        fasilitasView.setText(fasilitas);

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
                        year, month, day);
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
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();


            }
        });

        mDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet:dd/mm/yyyy: " + day + "/" + month + "/" + year);

                String date2 = day + "/" + month + "/" + year;
                checkInDate.setText(date2);
            }
        };

        mDateListener2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: dd/mm/yyyy: " + day + "/" + month + "/" + year);

                String date1 = day + "/" + month + "/" + year;
                checkOutDate.setText(date1);
            }
        };

        btn_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date1 = checkInDate.getText().toString();
                String date2 = checkOutDate.getText().toString();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");


                try {
                    Date dateIn = sdf.parse(date1);
                    Date dateOut = sdf.parse(date2);
                    long difference = dateOut.getTime() - dateIn.getTime();
                    long seconds = difference / 1000;
                    long minutes = seconds / 60;
                    long hours = minutes / 60;
                    long days = hours / 24;

                    if (days > 0) {

                        long finalPrice = days * Integer.parseInt(harga);

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
                                    nameView.setText(documentSnapshot.getString("fName"));
                                    email = documentSnapshot.getString("email");
                                    add(finalPrice,email);



                                } else {
                                    Log.d("tag", "onEvent: Document do not exists");
                                }

                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                builder.setTitle("Thank you for your order")
                                        .setMessage("Please check your transaction history to see detailed data of your transaction")
                                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                Navigation.findNavController(view).navigate(R.id.action_nav_order_to_nav_dashboard);

                                            }
                                        }).create().show();

                            }
                        });




                    } else if (days == 0) {
                        Toast.makeText(getActivity(), "Pemesanan minimal satu malam", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "Tanggal check out tidak bisa lebih lama dari check in", Toast.LENGTH_SHORT).show();

                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }


            }
        });

        return root;
    }


    private void add(Long finalPrice,String email) {
        final String Room = roomView.getText().toString();
        final String Name = nameView.getText().toString();
        final String Price = Long.toString(finalPrice);
        final String CheckInDate = checkInDate.getText().toString();
        final String CheckOutDate = checkOutDate.getText().toString();
        final String emailUser = email;



        class AddTransaksi extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                Transaksi transaksi = new Transaksi();
                transaksi.setName(Name);
                transaksi.setRoom(Room);
                transaksi.setPrice(Price);
                transaksi.setCheckInDate(CheckInDate);
                transaksi.setCheckOutDate(CheckOutDate);
                transaksi.setEmail(emailUser);

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