package com.example.angelastore;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Api {

    String BASE_URL = "http://192.168.1.224/Dona-Angela-Store-/api/";

    // USERS
    @POST("Users/login")
    Call<ApiEnvelope<LoginData>> login(@Body User body);

    @POST("Users/add")
    Call<ApiEnvelope<UserRegisterData>> register(@Body User body);

    // PRODUCTS
//    @GET("Products/")
//    Call<ProductsEnvelopeResponse> listProducts();
//
//    @GET("Products/id/{id}")
//    Call<ProductByIdEnvelopeResponse> getProductById(@Path("id") int id);
}
