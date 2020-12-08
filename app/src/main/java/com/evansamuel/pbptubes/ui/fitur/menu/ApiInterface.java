package com.evansamuel.pbptubes.ui.fitur.menu;

import android.view.Menu;

import com.evansamuel.pbptubes.ui.fitur.transaksi.TransaksiResponse;

import java.util.Date;

import retrofit2.Call;
import retrofit2.http.DELETE;
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


    @DELETE("menu/delete/{id}")
    Call<MenuResponse> deleteMenu(@Path("id") String id);


    @GET("room")
    Call<TransaksiResponse> getAllBooking(@Query("data") String data);



    @POST("room/insert")
    @FormUrlEncoded
    Call<TransaksiResponse> createBooking(
                                    @Field("email") String email,
                                  @Field("name") String name,
                                  @Field("room") String room,
                                    @Field("check_in_date") String check_in_date,
                                    @Field("check_out_date") String check_out_date,
                                    @Field("price") Double price );

    @PUT("room/update/{id}")
    @FormUrlEncoded
    Call<TransaksiResponse> updateBooking(@Path("id") String id,
                                          @Field("email") String email,
                                          @Field("name") Double name,
                                          @Field("room") String room,
                                          @Field("check_in_date") Date check_in_date,
                                          @Field("check_out_date") Date check_out_date,
                                          @Field("price") Double price);


    @DELETE("room/delete/{id}")
    Call<TransaksiResponse> deleteBooking(@Path("id") String id);


    @GET("room/{email}")
    Call<TransaksiResponse> getBookingByEmail(
            @Path("email") String email,
            @Query("data") String data);


}
