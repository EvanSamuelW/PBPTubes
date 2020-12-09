package com.evansamuel.pbptubes.ui.fitur.foodorder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TransaksiFoodResponse {
    @SerializedName("data")
    @Expose
    private List<TransaksiFoodDAO> transactionsfood = null;

    @SerializedName("message")
    @Expose
    private String message;

    public List<TransaksiFoodDAO> getTransactionsFood() {
        return transactionsfood;
    }

    public void setTransactions(List<TransaksiFoodDAO> transactions) {
        this.transactionsfood = transactions;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
