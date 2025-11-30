package com.example.angelastore;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MeuAdaptador extends RecyclerView.Adapter<MeuAdaptador.ViewHolder> {
    ArrayList<Product> produtos;

    public MeuAdaptador(ArrayList<Product> produtos) {
        this.produtos = produtos;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView txtNomeProduto;
        final TextView txtDescricaoProduto;
        final TextView txtMarcaProduto;
        final TextView txtPrecoAtual;
        final ShapeableImageView ivProduto;

        public ViewHolder(View view) {
            super(view);
            txtNomeProduto = view.findViewById(R.id.txtNomeProduto);
            txtDescricaoProduto = view.findViewById(R.id.txtDescricaoProduto);
            txtMarcaProduto = view.findViewById(R.id.txtMarcaProduto);
            txtPrecoAtual = view.findViewById(R.id.txtPrecoAtual);
            ivProduto = view.findViewById(R.id.ivProduto);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_produto, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product produto = produtos.get(position);
        holder.txtNomeProduto.setText(produto.getProduct_name());
        holder.txtDescricaoProduto.setText(produto.getProduct_description());
        holder.txtMarcaProduto.setText(produto.getBrand_description());
        holder.txtPrecoAtual.setText( "R$ " + String.format("%.2f", produto.getPrice()) );

        String url = "http://192.168.1.224/Dona-Angela-Store-";
        Picasso.get().load(url + produto.getProduct_photo()) // para pegar a primeira foto
                .placeholder(R.drawable.placeholder_pdm_correto).error(R.drawable.error_placeholder_pdm).into(holder.ivProduto);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), UnidadeProduto.class);

            intent.putExtra("nome", produto.getProduct_name());
            intent.putExtra("descricao", produto.getProduct_description());
            intent.putExtra("preco", produto.getPrice());
            intent.putExtra("marca", produto.getBrand_description());
            intent.putExtra("foto", produto.getProduct_photo());

            v.getContext().startActivity(intent);
        });
    }
    @Override
    public int getItemCount() {
        return produtos.size();
    }
}
