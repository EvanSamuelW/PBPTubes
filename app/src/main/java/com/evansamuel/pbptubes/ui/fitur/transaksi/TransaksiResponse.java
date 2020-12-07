package com.evansamuel.pbptubes.ui.fitur.transaksi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TransaksiResponse {
    @SerializedName("data")
    @Expose
    private List<TransaksiDAO> transactions = null;

    @SerializedName("message")
    @Expose
    private String message;

    public List<TransaksiDAO> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransaksiDAO> transactions) {
        this.transactions = transactions;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
