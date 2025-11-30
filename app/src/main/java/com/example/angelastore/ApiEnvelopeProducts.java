package com.example.angelastore;

import java.util.List;

public class ApiEnvelopeProducts {
    private int code;
    private String type;
    private String status;
    private String message;
    private Data data;

    public static class Data {
        private List<Product> products;

        public List<Product> getProducts() {
            return products;
        }
    }   
    public Data getData() {
        return data;
    }
}


