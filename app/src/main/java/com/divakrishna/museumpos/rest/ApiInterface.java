package com.divakrishna.museumpos.rest;

import com.divakrishna.museumpos.model.GetKoleksi;
import com.divakrishna.museumpos.model.PostKritikSaran;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("museum_pos_android")
    Call<GetKoleksi> getKoleksi();

    @GET("museum_pos_android")
    Call<GetKoleksi> getKoleksiNama(@Query("nama_koleksi") String namaKoleksi);

    @GET("museum_pos_android")
    Call<GetKoleksi> getKoleksiDetail(@Query("no_inventaris") String noInventaris);

    @FormUrlEncoded
    @POST("museum_pos_android")
    Call<PostKritikSaran> postKritikSaran(@Field("nama") String nama,
                                          @Field("email") String email,
                                          @Field("no_hp") String no_hp,
                                          @Field("kritik_saran") String kritik_saran);

}
