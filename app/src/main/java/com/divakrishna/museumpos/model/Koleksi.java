package com.divakrishna.museumpos.model;

import com.divakrishna.museumpos.adapter.KoleksiAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Koleksi {
    @SerializedName("no_inventaris")
    private String no_inventaris;
    //@SerializedName("tanggal_masuk")
    //private String tanggal_masuk;
    @SerializedName("jenis")
    private String jenis;
    @SerializedName("nama_koleksi")
    private String nama_koleksi;
    @SerializedName("daerah_asal")
    private String daerah_asal;
    @SerializedName("tanggal_pembuatan")
    private String tanggal_pembuatan;
    //@SerializedName("tanggal_pemakaian")
    //private String tanggal_pemakaian;
    //@SerializedName("bentuk")
    //private String bentuk;
    //@SerializedName("banyaknya")
    //private String banyaknya;
    //@SerializedName("nilai")
    //private String nilai;
    //@SerializedName("ukuran")
    //private String ukuran;
    @SerializedName("bahan")
    private String bahan;
    //@SerializedName("warna")
    //private String warna;
    @SerializedName("teknik_pembuatannya")
    private String teknik_pembuatannya;
    @SerializedName("deskripsi")
    private String deskripsi;
    @SerializedName("sejarah")
    private String sejarah;
    @SerializedName("foto")
    private String foto;
    //@SerializedName("kondisi")
    //private String kondisi;
    @SerializedName("audio")
    private String audio;

    public Koleksi() {
    }

    public Koleksi(String no_inventaris, String jenis, String nama_koleksi, String daerah_asal, String tanggal_pembuatan, String bahan, String teknik_pembuatannya, String deskripsi, String sejarah, String foto, String audio) {
        this.no_inventaris = no_inventaris;
        this.jenis = jenis;
        this.nama_koleksi = nama_koleksi;
        this.daerah_asal = daerah_asal;
        this.tanggal_pembuatan = tanggal_pembuatan;
        this.bahan = bahan;
        this.teknik_pembuatannya = teknik_pembuatannya;
        this.deskripsi = deskripsi;
        this.sejarah = sejarah;
        this.foto = foto;
        this.audio = audio;
    }

    public String getNoInventaris() {
        return no_inventaris;
    }

    public void setNoInventaris(String no_inventaris) {
        this.no_inventaris = no_inventaris;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getNamaKoleksi() {
        return nama_koleksi;
    }

    public void setNamaKoleksi(String nama_koleksi) {
        this.nama_koleksi = nama_koleksi;
    }

    public String getDaerahAsal() {
        return daerah_asal;
    }

    public void setDaerahAsal(String daerah_asal) {
        this.daerah_asal = daerah_asal;
    }

    public String getTanggalPembuatan() {
        return tanggal_pembuatan;
    }

    public void setTanggalPembuatan(String tanggal_pembuatan) {
        this.tanggal_pembuatan = tanggal_pembuatan;
    }

    public String getBahan() {
        return bahan;
    }

    public void setBahan(String bahan) {
        this.bahan = bahan;
    }

    public String getTeknikPembuatanya() {
        return teknik_pembuatannya;
    }

    public void setTeknikPembuatanya(String teknik_pembuatannya) {
        this.teknik_pembuatannya = teknik_pembuatannya;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getSejarah() {
        return sejarah;
    }

    public void setSejarah(String sejarah) {
        this.sejarah = sejarah;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }
}
