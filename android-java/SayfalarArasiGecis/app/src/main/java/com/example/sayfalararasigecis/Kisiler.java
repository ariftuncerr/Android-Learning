package com.example.sayfalararasigecis;

import java.io.Serializable;

public class Kisiler implements Serializable {
    private String kisiName;
    private int kisiSifre;

    public Kisiler(String kisiName,int kisiSifre) {
        this.kisiSifre = kisiSifre;
        this.kisiName = kisiName;
    }

        public int getKisiSifre() {
        return kisiSifre;
    }

    public void setKisiSifre(int kisiSifre) {
        this.kisiSifre = kisiSifre;
    }

    public String getKisiName() {
        return kisiName;
    }

    public void setKisiName(String kisiName) {
        this.kisiName = kisiName;
    }
}
