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

public class Home extends AppCompatActivity {

    ArrayList<Produto> produtos;

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
        Button btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Login.class);
                startActivity(intent);
            }
        });

        gerarProdutos();

        RecyclerView rvProdutos = findViewById(R.id.rvProdutos);
        MeuAdaptador adaptador = new MeuAdaptador(produtos);
        RecyclerView.LayoutManager layout =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvProdutos.setLayoutManager(layout);
        rvProdutos.setAdapter(adaptador);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        rvProdutos.setLayoutManager(layoutManager);



    }
    private void gerarProdutos() {
        produtos = new ArrayList<Produto>();
        criarProduto("Essencial", "Perfume floral com toque cítrico", "Natura", 89.90, R.drawable.img_essencial);
        criarProduto("Ekos", "Creme hidratante para o corpo", "Natura", 39.90, R.drawable.img_ekos);
        criarProduto("Ekos", "Creme hidratante para o corpo", "Natura", 39.90, R.drawable.img_ekos);
        criarProduto("Ekos", "Creme hidratante para o corpo", "Natura", 39.90, R.drawable.img_ekos);
        criarProduto("Ekos", "Creme hidratante para o corpo", "Natura", 39.90, R.drawable.img_ekos);
        criarProduto("Ekos", "Creme hidratante para o corpo", "Natura", 39.90, R.drawable.img_ekos);
        criarProduto("Ekos", "Creme hidratante para o corpo", "Natura", 39.90, R.drawable.img_ekos);
        criarProduto("Ekos", "Creme hidratante para o corpo", "Natura", 39.90, R.drawable.img_ekos);
        criarProduto("Ekos", "Creme hidratante para o corpo", "Natura", 39.90, R.drawable.img_ekos);
        criarProduto("Ekos", "Creme hidratante para o corpo", "Natura", 39.90, R.drawable.img_ekos);

    }

    private void criarProduto(String nome, String descricao, String marca, double preco, int foto) {
        Produto produto = new Produto(nome, descricao, marca, preco, foto);
        produtos.add(produto);
    }
}