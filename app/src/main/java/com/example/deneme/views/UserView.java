package com.example.deneme.views;

public class UserView {

    long id;
    String ad;
    String soyad;

    public UserView(long id, String ad, String soyad) {
        this.id = id;
        this.ad = ad;
        this.soyad = soyad;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }
}
