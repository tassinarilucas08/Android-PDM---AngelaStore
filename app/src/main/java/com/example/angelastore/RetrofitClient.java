package com.example.angelastore;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static RetrofitClient instance = null;
    private Api api;

    private RetrofitClient() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL) // vamos criar isso no Api.java
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(Api.class);
    }

    public static synchronized RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }

    public Api getApi() {
        return api;
    }
}
