package com.hadesfranklyn.uber.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.hadesfranklyn.uber.R;
import com.hadesfranklyn.uber.helper.UsuarioFirebase;

public class MainActivity extends AppCompatActivity {

    private Button buttonEntrar;
    private Button buttonCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar componentes
        buttonEntrar = findViewById(R.id.buttonEntrar);
        buttonCadastrar = findViewById(R.id.buttonCadastrar);

        // Esconde a toolbar
        getSupportActionBar().hide();
    }


    public void abrirTelaLogin(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void abrirTelaCadastro(View view) {
        startActivity(new Intent(this, CadastroActivity.class));
    }

    @Override
    protected void onStart() {
        super.onStart();
        UsuarioFirebase.redirecionaUsuarioLogado(MainActivity.this);
    }
}