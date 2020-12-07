package com.evansamuel.pbptubes.ui.fitur.menu;

import android.view.Menu;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("menu")
    Call<MenuResponse> getAllMenu(@Query("data") String data);



    @POST("menu/insert")
    @FormUrlEncoded
    Call<MenuResponse> createMenu(@Field("name") String nama,
                                  @Field("price") Double price,
                                  @Field("photo") String photo);

    @PUT("menu/update/{id}")
    @FormUrlEncoded
    Call<MenuResponse> updateMenu(@Path("id") String id,
                                  @Field("name") String nama,
                                  @Field("price") Double price,
                                  @Field("photo") String photo);


    @POST("menu/delete/{id}")
    @FormUrlEncoded
    Call<MenuResponse> deleteMenu(@Path("id") String id);



}
