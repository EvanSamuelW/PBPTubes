package com.evansamuel.pbptubes.ui.fitur.transaksi;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.evansamuel.pbptubes.ui.fitur.transaksi.Transaksi;

import java.util.List;

@Dao
public interface TransaksiDAO {

    @Query("SELECT * FROM transaksi")
    List<Transaksi> getAll();

    @Insert
    void insert(Transaksi transaksi);

    @Update
    void update(Transaksi transaksi);

    @Delete
    void delete(Transaksi transaksi);
}
