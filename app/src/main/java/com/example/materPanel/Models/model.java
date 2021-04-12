package com.example.materPanel.Models;

public class model {
    String title, category, price, pImage;

    model() {

    }


    public model(String title, String category, String price, String pImage) {
        this.title = title;
        this.category = category;
        this.price = price;
        this.pImage = pImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPImage() {
        return pImage;
    }

    public void setPImage(String pImage) {
        this.pImage = pImage;
    }
}
