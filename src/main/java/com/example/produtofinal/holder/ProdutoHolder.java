package com.example.produtofinal.holder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.produtofinal.R;

public class ProdutoHolder extends RecyclerView.ViewHolder {
    public TextView txtNome;
    public TextView txtCategoria;

    public ProdutoHolder(@NonNull View itemView) {
        super(itemView);
        txtNome = (TextView)itemView.findViewById(R.id.txtNome);
        txtCategoria = (TextView) itemView.findViewById(R.id.txtCategoria);
    }
}
