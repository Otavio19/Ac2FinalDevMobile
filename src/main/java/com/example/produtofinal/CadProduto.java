package com.example.produtofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.produtofinal.Service.CadProdutoAsync;
import com.example.produtofinal.model.Produto;


public class CadProduto extends AppCompatActivity {

    int id = 0;
    Produto produto;
    TextView txtProduto, txtCategoria;

    public Produto getUsuario(){
        return  produto;
    }
    public void setProduto(Produto usuario){
        this.produto = usuario;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_produto);

        txtCategoria = findViewById(R.id.txtCategoria);
        txtProduto = findViewById(R.id.txtNome);

        if(getIntent().hasExtra("id")){
            id = getIntent().getIntExtra("id",0);
            new CadProdutoAsync("GET",CadProduto.this).execute("https://628c09203df57e983ec89d6c.mockapi.io/" +id,"");
        }
        else
            produto = new Produto();
    }

    public void carregarProdutos(){
        txtProduto.setText(produto.getNome());
        txtCategoria.setText(produto.getCategoria());
    }

    public void btnSalvar(View v){
        produto.setNome(txtProduto.getText().toString());
        produto.setCategoria(txtCategoria.getText().toString());

        new CadProdutoAsync("POST",CadProduto.this)
                .execute("Produtos",Produto.parseJson(produto));

    }
}