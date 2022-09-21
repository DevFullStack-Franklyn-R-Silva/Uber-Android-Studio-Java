package com.hadesfranklyn.uber.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hadesfranklyn.uber.R;

public class MainActivity extends AppCompatActivity {

    private Button buttonEntrar;
    private Button buttonCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonEntrar = findViewById(R.id.buttonEntrar);
        buttonCadastrar = findViewById(R.id.buttonCadastrar);

        getSupportActionBar().hide();
    }


    public void abrirTelaLogin(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }
    public void abrirTelaCadastro(View view) {
        startActivity(new Intent(this, CadastroActivity.class));
    }
}