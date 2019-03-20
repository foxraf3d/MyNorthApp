package com.projetofragmento.pc_rafael.mynorthapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import Controller.UsuarioController;
import Intermediate.Util;

public class LoginActivity extends AppCompatActivity {

    private EditText login;
    private EditText senha;
    private Button entrar;
    private TextView cadastrar;
    private TextView consultar;
    private UsuarioController crudUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.editLoginId);
        senha = findViewById(R.id.editSenhaId);
        entrar = findViewById(R.id.btnEntrarId);
        cadastrar = findViewById(R.id.txtCadastroId);
        consultar = findViewById(R.id.consultarID);

        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crudUser = new UsuarioController(getBaseContext());
                String loginString = login.getText().toString();
                String senhaString = senha.getText().toString();

                boolean usuarioValido = Util.usuarioExiste(loginString, senhaString);
                if (usuarioValido){
                    Intent intent = new Intent(LoginActivity.this, PrincipalActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Usuário ou senha inválidos!", Toast.LENGTH_LONG).show();
                }
            }
        });

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, CadastrarActivity.class);
                startActivity(intent);
            }
        });

        consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, PrincipalActivity.class);
                startActivity(intent);
            }
        });

    }
}
