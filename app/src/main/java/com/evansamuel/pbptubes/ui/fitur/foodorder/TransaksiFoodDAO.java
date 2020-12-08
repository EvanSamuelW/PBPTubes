package com.evansamuel.pbptubes.ui.fitur.foodorder;

import com.google.gson.annotations.SerializedName;

public class TransaksiFoodDAO {
    @SerializedName("id")
    private String id;

    @SerializedName("menu")
    private String menu;

    @SerializedName("price")
    private Double price;

    @SerializedName("photo")
    private String photo;

    @SerializedName("amount")
    private String amount;

    @SerializedName("email")
    private String email;

    @SerializedName("customer_name")
    private String customer_name;
}
