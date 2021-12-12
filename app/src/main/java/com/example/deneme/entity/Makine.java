package com.example.deneme.entity;

public class Makine {

    long id;
    String ad;
    long parentid;

    public Makine() {
    }

    public Makine(long id, String ad, long parentid) {
        this.id = id;
        this.ad = ad;
        this.parentid = parentid;
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

    public long getParentid() {
        return parentid;
    }

    public void setParentid(long parentid) {
        this.parentid = parentid;
    }
}
