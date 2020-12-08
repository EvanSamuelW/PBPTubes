package com.evansamuel.pbptubes.ui.fitur.menu;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MenuDao implements Serializable {

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String nama;

    @SerializedName("price")
    private Double price;

    @SerializedName("photo")
    private String photo;

    public MenuDao(String id, String nama, Double price, String photo) {
        this.id = id;
        this.nama = nama;
        this.price = price;
        this.photo = photo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
