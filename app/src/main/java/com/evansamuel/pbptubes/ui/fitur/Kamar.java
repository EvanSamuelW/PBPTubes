package com.evansamuel.pbptubes.ui.fitur;


import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

public class Kamar {

    public String nama;
    public String fasilitas;
    public Integer harga;
    public String imgURL;

    public Kamar(String nama, String fasilitas, Integer harga, String imgURL) {
        this.nama = nama;
        this.fasilitas = fasilitas;
        this.harga = harga;
        this.imgURL = imgURL;
    }

    public String getFasilitas() {
        return fasilitas;
    }

    public void setFasilitas(String fasilitas) {
        this.fasilitas = fasilitas;
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

    public void setHarga(Integer harga) {
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
