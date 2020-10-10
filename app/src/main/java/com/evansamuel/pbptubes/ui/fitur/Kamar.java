package com.evansamuel.pbptubes.ui.fitur;


import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

public class Kamar {

    public String nama;
    public String ketersediaan;
    public Integer harga;
    public String deskripsi;
    public String imgURL;

    public Kamar(String nama, String ketersediaan, Integer harga, String deskripsi, String imgURL) {
        this.nama = nama;
        this.ketersediaan = ketersediaan;
        this.harga = harga;
        this.deskripsi = deskripsi;
        this.imgURL = imgURL;
    }

    public String getKetersediaan() {
        return ketersediaan;
    }

    public void setKetersediaan(String ketersediaan) {
        this.ketersediaan = ketersediaan;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }



    public int getHarga() {
        return harga;
    }

    public void setIpk(Integer harga) {
        this.harga=harga;
    }



    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }


    @BindingAdapter("android:loadImage")
    public static void loadImage(ImageView imageView, String imgURL){
        Glide.with(imageView)
                .load(imgURL)
                .into(imageView);
    }
}
