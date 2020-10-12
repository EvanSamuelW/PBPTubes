package com.evansamuel.pbptubes.ui.fitur.transaksi;

import androidx.room.Database;
import androidx.room.RoomDatabase;



@Database(entities = {Transaksi.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TransaksiDAO transaksiDAO();

}
