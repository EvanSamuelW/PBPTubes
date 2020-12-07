package com.evansamuel.pbptubes.ui.fitur.transaksi;


import com.google.gson.annotations.SerializedName;

import java.util.Date;


public class TransaksiDAO {

    @SerializedName("id")
    private String id;

    @SerializedName("check_in_date")
    private Date check_in_date;

    @SerializedName("check_out_date")
    private Date check_out_date;

    @SerializedName("room")
    private String room;

    @SerializedName("email")
    private String email;

    @SerializedName("name")
    private String name;

    @SerializedName("price")
    private Double price;

    public TransaksiDAO(String id, Date check_in_date, Date check_out_date, String room, String email, String name, Double price) {
        this.id = id;
        this.check_in_date = check_in_date;
        this.check_out_date = check_out_date;
        this.room = room;
        this.email = email;
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCheck_in_date() {
        return check_in_date;
    }

    public void setCheck_in_date(Date check_in_date) {
        this.check_in_date = check_in_date;
    }

    public Date getCheck_out_date() {
        return check_out_date;
    }

    public void setCheck_out_date(Date check_out_date) {
        this.check_out_date = check_out_date;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
