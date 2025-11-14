package com.example.angelastore;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class MeuAdaptador extends RecyclerView.Adapter<MeuAdaptador.ViewHolder> {
    ArrayList<Produto> produtos;

    public MeuAdaptador(ArrayList<Produto> produtos) {
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
            txtNomeProduto = (TextView) view.findViewById(R.id.txtNomeProduto);
            txtDescricaoProduto = (TextView) view.findViewById(R.id.txtDescricaoProduto);
            txtMarcaProduto = (TextView) view.findViewById(R.id.txtMarcaProduto);
            txtPrecoAtual = (TextView) view.findViewById(R.id.txtPrecoAtual);
            ivProduto = (ShapeableImageView) view.findViewById(R.id.ivProduto);
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
        Produto produto = produtos.get(position);
        holder.txtNomeProduto.setText(produto.nome);
        holder.txtDescricaoProduto.setText(produto.descricao);
        holder.txtMarcaProduto.setText(produto.marca);
        holder.txtPrecoAtual.setText( "R$ " + String.format("%.2f", produto.preco) );
        holder.ivProduto.setImageResource(produto.foto);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), Product.class);

            intent.putExtra("nome", produto.getNome());
            intent.putExtra("descricao", produto.getDescricao());
            intent.putExtra("marca", produto.getMarca());
            intent.putExtra("preco", produto.getPreco());
            intent.putExtra("foto", produto.getFoto());

            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return produtos.size();
    }
}
