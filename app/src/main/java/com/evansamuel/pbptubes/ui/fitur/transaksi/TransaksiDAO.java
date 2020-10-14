package com.evansamuel.pbptubes.ui.fitur.transaksi;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.evansamuel.pbptubes.ui.fitur.transaksi.Transaksi;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

@Dao
public interface TransaksiDAO {

    @Query("SELECT * FROM transaksi WHERE email= :user")
    List<Transaksi> loadAllUserTransaction(String user);

    @Insert
    void insert(Transaksi transaksi);

    @Update
    void update(Transaksi transaksi);

    @Delete
    void delete(Transaksi transaksi);
}
