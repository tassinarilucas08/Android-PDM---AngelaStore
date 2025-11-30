package com.example.angelastore;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.squareup.picasso.Picasso;

public class UnidadeProduto extends AppCompatActivity {

    ImageView imgProduto;
    TextView txtNome;
    TextView txtDescription;
    TextView txtPreco;
    Button btnAddCarrinho;
    Button btnReturn;
    TextView txtMarca;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_unidade_produto);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        imgProduto = findViewById(R.id.imgProduto);
        txtNome = findViewById(R.id.txtNome);
        txtDescription = findViewById(R.id.txtDescricao);
        txtPreco = findViewById(R.id.txtPreco);
        btnAddCarrinho = findViewById(R.id.btnAddCarrinho);
        btnReturn = findViewById(R.id.btnReturn);
        txtMarca = findViewById(R.id.txtMarca);

        String nome = getIntent().getStringExtra("nome");
        String desc = getIntent().getStringExtra("descricao");
        String marca = getIntent().getStringExtra("marca");
        double preco = getIntent().getDoubleExtra("preco", 0.0);
        String foto = getIntent().getStringExtra("foto");

        txtNome.setText(nome);
        txtDescription.setText(desc);
        txtMarca.setText(marca);
        txtPreco.setText("R$ " + String.format("%.2f", preco));

        String url = "http://192.168.1.224/Dona-Angela-Store-" + foto;
        Picasso.get().load(url).placeholder(R.drawable.placeholder_pdm_correto).error(R.drawable.error_placeholder_pdm).into(imgProduto);

    btnReturn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            onBackPressed();
        }
    });
    }
}