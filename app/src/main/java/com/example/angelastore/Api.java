package com.example.angelastore;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface Api {

    String BASE_URL = "http://192.168.1.224/Dona-Angela-Store-/api/";

    // USERS
    @POST("Users/login")
    Call<ApiEnvelope<LoginData>> login(@Body User body);

    @POST("Users/add")
    Call<ApiEnvelope<UserRegisterData>> register(@Body User body);


    @GET("Products/")
    Call<ApiEnvelopeProducts> listProducts();

    @GET("Products/id/{id}")
    Call<ApiEnvelopeProducts> getProductById(@Path("id") int id);
}
