package com.example.produtofinal.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

//Aqui montamos o modelo do dado que vamos inserir na API. Nesse caso será um produto
public class Produto{
    String nome;
    Integer id;
    String categoria;

    public Produto() {
    }

    public Produto(String nome, Integer id, String categoria) {
        this.nome = nome;
        this.id = id;
        this.categoria = categoria;
    }

    public Produto(String nome, String categoria) {
        this.nome = nome;
        this.categoria = categoria;
    }

    //Com esse Método convertemos para jSon o Objeto.
    public static Produto parseObject(String json){
        Produto produto = new Produto();
        try{
            JSONObject obj = new JSONObject(json);
            produto.setNome(obj.getString("nome"));
            produto.setCategoria(obj.getString("categoria"));

            return produto;
        }
        catch (Exception ex){
            return produto;
        }
    }

    public static String parseJson(Produto produto){
        JSONObject obj = new JSONObject();
        try{
            obj.put("id",""+produto.getId());
            obj.put("nome",produto.getNome());
            obj.put("categoria",produto.getCategoria());
            return obj.toString();
        }
        catch (Exception ex){
            return "";
        }
    }

    //Montamos uma lista de objetos que tem na API
    public static ArrayList<Produto> parseArrayList(String json){
        ArrayList<Produto> usuarios = new ArrayList<>();
        try{
            JSONArray array = new JSONArray(json);

            //Geramos um for com o tamanho da lista que recuperamos da API.
            for(int i = 0; i < array.length(); i++){
                Produto produto = new Produto();
                JSONObject obj = array.getJSONObject(i);
                produto.setNome(obj.getString("nome"));
                produto.setId(obj.getInt("id"));
                produto.setCategoria(obj.getString("categoria"));
                usuarios.add(produto);
            }
            return usuarios;
        }
        catch (Exception ex){
            return null;
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}