package com.hadesfranklyn.uber.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.hadesfranklyn.uber.config.ConfiguracaoFirebase;

import java.io.Serializable;

public class Usuario implements Serializable {

    private String id;
    private String nome;
    private String email;
    private String senha;
    private String tipo;

    private String latitude;
    private String longitude;

    //construtor
    public Usuario() {
    }

    //get e set

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Exclude
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    //métodos
    public void salvar() {

        DatabaseReference firebaseReference = ConfiguracaoFirebase.getFirebaseDatabase();
        DatabaseReference usuarios = firebaseReference.child("usuarios").child(getId());

        usuarios.setValue(this);
    }
}
