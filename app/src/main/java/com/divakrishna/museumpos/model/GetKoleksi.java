package com.divakrishna.museumpos.model;

import com.divakrishna.museumpos.adapter.KoleksiAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetKoleksi {

    @SerializedName("status")
    String status;
    @SerializedName("result")
    List<Koleksi> listDataKoleksi;
    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Koleksi> getListDataKoleksi() {
        return listDataKoleksi;
    }

    public void setListDataKoleksi(List<Koleksi> listDataKoleksi) {
        this.listDataKoleksi = listDataKoleksi;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
