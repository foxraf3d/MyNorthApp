package com.projetofragmento.pc_rafael.mynorthapp;

import android.app.Application;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Controller.UsuarioController;
import Intermediate.Util;

public class CadastrarActivity extends AppCompatActivity {

    private EditText login;
    private EditText senha;
    private EditText confirmarSenha;
    private Button cadastrar;
    private UsuarioController crudUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        login = findViewById(R.id.editLoginId);
        senha = findViewById(R.id.editSenhaId);
        confirmarSenha = findViewById(R.id.editConfirmarSenhaId);
        cadastrar = findViewById(R.id.btnCadastrarId);

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crudUser = new UsuarioController(getBaseContext());
                String loginString = login.getText().toString();
                String senhaString = senha.getText().toString();
                String confirmarSenhaString = confirmarSenha.getText().toString();
                String resultado;

                boolean senhaValida = Util.validaSenha(senhaString, confirmarSenhaString);

                if (senhaValida) {
                    resultado = crudUser.inserirUsuario(loginString, senhaString);
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    Toast.makeText(getBaseContext(), resultado, Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getBaseContext(), "Erro ao cadastrar usuário!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private Cursor ConsultarDados() {
        Cursor cursor = crudUser.carregaDados();
        System.out.println("DADOS: "+cursor);
        return cursor;
    }
}
