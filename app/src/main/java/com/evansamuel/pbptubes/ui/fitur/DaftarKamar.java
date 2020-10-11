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

    public static final Kamar STANDARD = new Kamar("Standard Room","1 kasur king size",400000,
            "https://ecs7.tokopedia.net/blog-tokopedia-com/uploads/2020/02/1.-standard-room-sumber-gambar-Pixabay.jpg");

    public static final Kamar SUPERIOR = new Kamar("Superior Room","1 kasur king size",700000,
            "https://ecs7.tokopedia.net/blog-tokopedia-com/uploads/2020/02/2.-Superior-Room-sumber-gambar-Pixabay.jpg");

    public static final Kamar DELUXE = new Kamar("Deluxe Room","Kasur double bed",900000,
            "https://ecs7.tokopedia.net/blog-tokopedia-com/uploads/2020/02/3.-Deluxe-Room-sumber-gambar-Pixabay.jpg");
    public static final Kamar JUNIOR = new Kamar("Junior Suite","1 kasur king size, ruang tamu",1500000, "https://ecs7.tokopedia.net/blog-tokopedia-com/uploads/2020/02/4.-Junior-Suite-Room-sumber-gambar-Pixabay.jpg");

    public static final Kamar SUITE = new Kamar("Suite Room","1 kasur king size, ruang tamu, dapur",2000000,
            "https://ecs7.tokopedia.net/blog-tokopedia-com/uploads/2020/02/5.-Suite-Room-sumber-gambar-Pixabay.jpg");



}

