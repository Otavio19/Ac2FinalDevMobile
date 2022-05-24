package com.example.produtofinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.produtofinal.Adapter.ProdutoAdapter;
import com.example.produtofinal.Service.ListaProdutoAsync;
import com.example.produtofinal.model.Produto;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Produto> listaProdutos;
    RecyclerView produtoRecycler;
    ProdutoAdapter produtoAdapter;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaProdutos = new ArrayList<>();
        produtoRecycler = (RecyclerView) findViewById(R.id.RecyclerProduto);
        progressDialog = new ProgressDialog(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        buscarProdutos();
    }

    public void buscarProdutos() {
        new ListaProdutoAsync("GET",MainActivity.this).execute("Produtos","");
    }
    public void excluirProduto(String id){
        new ListaProdutoAsync("DELETE",MainActivity.this)
                .execute("Produtos"+id,"");
    }

    public void addProduto(View v){
        Intent i = new Intent(MainActivity.this, CadProduto.class);
        startActivity(i);
    }

    public void setListaProdutos(ArrayList<Produto> produtos){
        this.listaProdutos = produtos;
    }


    public void setupRecyclerUsuario(){
        LinearLayoutManager layout = new LinearLayoutManager(this);
        produtoRecycler.setLayoutManager(layout);
        produtoAdapter = new ProdutoAdapter(listaProdutos);
        produtoRecycler.setAdapter(produtoAdapter);

        produtoRecycler.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
    }

}