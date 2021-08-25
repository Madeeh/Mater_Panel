package com.example.materPanel.Models;

public class PriceListModel {
    String pTitle, pPrice;

    PriceListModel() {

    }

    public PriceListModel(String pTitle, String pPrice) {
        this.pTitle = pTitle;
        this.pPrice = pPrice;
    }

    public String getpTitle() {
        return pTitle;
    }

    public void setpTitle(String pTitle) {
        this.pTitle = pTitle;
    }

    public String getpPrice() {
        return pPrice;
    }

    public void setpPrice(String pPrice) {
        this.pPrice = pPrice;
    }
}
