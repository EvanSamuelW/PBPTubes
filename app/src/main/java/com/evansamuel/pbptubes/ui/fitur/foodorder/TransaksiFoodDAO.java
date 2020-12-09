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

    public TransaksiFoodDAO(String id, String menu, Double price, String photo, String amount, String email, String customer_name) {
        this.id = id;
        this.menu = menu;
        this.price = price;
        this.photo = photo;
        this.amount = amount;
        this.email = email;
        this.customer_name = customer_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }
}
