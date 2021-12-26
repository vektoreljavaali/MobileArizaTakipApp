package com.example.deneme.entity;

public class ArizaBildirim {
    long id;
    long personelid;
    String personelad;
    String tarih;
    String arizabildirimsekli;
    String arizatanimi;
    String oncelik;
    long makineid;
    String makinead;


    public ArizaBildirim(long id, long personelid, String personelad, String tarih,
                         String arizabildirimsekli, String arizatanimi, String oncelik,
                         long makineid, String makinead) {
        this.id = id;
        this.personelid = personelid;
        this.personelad = personelad;
        this.tarih = tarih;
        this.arizabildirimsekli = arizabildirimsekli;
        this.arizatanimi = arizatanimi;
        this.oncelik = oncelik;
        this.makineid = makineid;
        this.makinead = makinead;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPersonelid() {
        return personelid;
    }

    public void setPersonelid(long personelid) {
        this.personelid = personelid;
    }

    public String getPersonelad() {
        return personelad;
    }

    public void setPersonelad(String personelad) {
        this.personelad = personelad;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public String getArizabildirimsekli() {
        return arizabildirimsekli;
    }

    public void setArizabildirimsekli(String arizabildirimsekli) {
        this.arizabildirimsekli = arizabildirimsekli;
    }

    public String getArizatanimi() {
        return arizatanimi;
    }

    public void setArizatanimi(String arizatanimi) {
        this.arizatanimi = arizatanimi;
    }

    public String getOncelik() {
        return oncelik;
    }

    public void setOncelik(String oncelik) {
        this.oncelik = oncelik;
    }

    public long getMakineid() {
        return makineid;
    }

    public void setMakineid(long makineid) {
        this.makineid = makineid;
    }

    public String getMakinead() {
        return makinead;
    }

    public void setMakinead(String makinead) {
        this.makinead = makinead;
    }
}
