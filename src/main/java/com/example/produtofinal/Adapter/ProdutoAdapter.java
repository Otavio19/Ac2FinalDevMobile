package com.example.produtofinal.Adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.produtofinal.CadProduto;
import com.example.produtofinal.MainActivity;
import com.example.produtofinal.R;
import com.example.produtofinal.holder.ProdutoHolder;
import com.example.produtofinal.model.Produto;

import java.util.ArrayList;

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoHolder>
{
    private final ArrayList<Produto> produtos;
    public ProdutoAdapter(ArrayList<Produto>produtos){
        this.produtos = produtos;
    }

    @NonNull
    @Override
    public ProdutoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProdutoHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.listagem_item_produtos,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProdutoHolder holder, int position) {
        holder.txtNome.setText(produtos.get(position).getNome());
        holder.txtCategoria.setText(produtos.get(position).getCategoria());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(holder.itemView.getContext(), CadProduto.class);
                i.putExtra("id",produtos.get(holder.getAdapterPosition()).getId());
                holder.itemView.getContext().startActivity(i);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());
                alert.setMessage("Deseja Excluir esse item?");
                alert.setTitle("Atenção");
                alert.setNegativeButton("Não",null);
                alert.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((MainActivity)holder.itemView.getContext())
                                .excluirProduto(produtos.get(
                                        holder.getAdapterPosition()).getId()+"");
                    }
                });
                alert.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return produtos != null ? produtos.size() : 0;
    }
}