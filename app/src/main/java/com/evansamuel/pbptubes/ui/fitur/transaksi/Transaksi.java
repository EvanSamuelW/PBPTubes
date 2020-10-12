package com.evansamuel.pbptubes.ui.fitur.transaksi;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Transaksi implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "checkIn")
    public String checkInDate;

    @ColumnInfo(name = "checkOut")
    public String checkOutDate;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "Room")
    public String room;

    @ColumnInfo(name = "price")
    public String price;

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public String getRoom(){
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}

