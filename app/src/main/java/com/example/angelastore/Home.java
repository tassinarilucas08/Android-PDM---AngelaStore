package com.example.angelastore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home extends AppCompatActivity {

    ArrayList<Product> produtos = new ArrayList<>();
    MeuAdaptador adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button btnCarrinho = findViewById(R.id.btnCarrinho);
        btnCarrinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Carrinho.class);
                startActivity(intent);
            }
        });

        RecyclerView rvProdutos = findViewById(R.id.rvProdutos);
        adaptador = new MeuAdaptador(produtos);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        rvProdutos.setLayoutManager(layoutManager);
        rvProdutos.setAdapter(adaptador);

        carregarProdutos();
    }
        private void carregarProdutos(){
            Call<ApiEnvelopeProducts> call =  RetrofitClient.getInstance().getApi().listProducts();

        call.enqueue(new Callback<ApiEnvelopeProducts>() {
            @Override
            public void onResponse(Call<ApiEnvelopeProducts> call, Response<ApiEnvelopeProducts> response) {
                if (response.body() != null){
                    produtos.clear();
                    produtos.addAll(response.body().getData().getProducts());
                    adaptador.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<ApiEnvelopeProducts> call, Throwable t) {
                t.printStackTrace();
            }
        });
        }
}