package com.evansamuel.pbptubes.ui.fitur.foodorder;

import com.evansamuel.pbptubes.ui.fitur.transaksi.TransaksiDAO;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TransaksiFoodResponse {
    @SerializedName("data")
    @Expose
    private List<TransaksiFoodDAO> transactions = null;

    @SerializedName("message")
    @Expose
    private String message;

    public List<TransaksiFoodDAO> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransaksiFoodDAO> transactions) {
        this.transactions = transactions;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
