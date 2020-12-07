package com.evansamuel.pbptubes.ui.fitur.menu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MenuResponse {
    @SerializedName("data")
    @Expose
    private List<MenuDao> menus = null;

    @SerializedName("message")
    @Expose
    private String message;

    public List<MenuDao> getMenus() {
        return menus;
    }

    public void setMenus(List<MenuDao> menus) {
        this.menus = menus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
