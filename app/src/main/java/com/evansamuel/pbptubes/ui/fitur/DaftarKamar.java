package com.evansamuel.pbptubes.ui.fitur;

import java.util.ArrayList;

public class DaftarKamar {
    public ArrayList<Kamar> KAMAR;

    public DaftarKamar(){
        KAMAR = new ArrayList();
        KAMAR.add(STANDARD);
        KAMAR.add(SUPERIOR);
        KAMAR.add(DELUXE);
        KAMAR.add(JUNIOR);
        KAMAR.add(SUITE);
    }

    public static final Kamar STANDARD = new Kamar("Standard Room","Available",400000,"Jenis kamar yang menawarkan fasilitas kasur king size atau dua queen size namun dengan harga yang miring",
            "https://ecs7.tokopedia.net/blog-tokopedia-com/uploads/2020/02/1.-standard-room-sumber-gambar-Pixabay.jpg");

    public static final Kamar SUPERIOR = new Kamar("Superior Room","Available",700000,"Jenis kamar yang menawarkan fasilitas kasur king size atau dua queen size namun dengan harga yang miring",
            "https://ecs7.tokopedia.net/blog-tokopedia-com/uploads/2020/02/2.-Superior-Room-sumber-gambar-Pixabay.jpg");

    public static final Kamar DELUXE = new Kamar("Deluxe Room","Available",900000,"Jenis kamar yang menawarkan fasilitas kasur king size atau dua queen size namun dengan harga yang miring",
            "https://ecs7.tokopedia.net/blog-tokopedia-com/uploads/2020/02/3.-Deluxe-Room-sumber-gambar-Pixabay.jpg");
    public static final Kamar JUNIOR = new Kamar("Junior Suite","Available",1500000,"Jenis kamar yang menawarkan fasilitas kasur king size atau dua queen size namun dengan harga yang miring",
            "https://ecs7.tokopedia.net/blog-tokopedia-com/uploads/2020/02/4.-Junior-Suite-Room-sumber-gambar-Pixabay.jpg");

    public static final Kamar SUITE = new Kamar("Suite Room","Available",2000000,"Jenis kamar yang menawarkan fasilitas kasur king size atau dua queen size namun dengan harga yang miring",
            "https://ecs7.tokopedia.net/blog-tokopedia-com/uploads/2020/02/5.-Suite-Room-sumber-gambar-Pixabay.jpg");



}

