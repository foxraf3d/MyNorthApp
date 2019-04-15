package com.projetofragmento.pc_rafael.mynorthapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Controller.UsuarioController;
import Entities.UsuarioEntity;
import helper.Util;

public class CadastrarActivity extends AppCompatActivity {

    private EditText login;
    private EditText senha;
    private EditText confirmarSenha;
    private Button cadastrar;
    private UsuarioController crudUser;
    private UsuarioEntity usuario;

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
                usuario = new UsuarioEntity(null,login.getText().toString(),senha.getText().toString());
//                usuario.setLogin(login.getText().toString());
//                usuario.setSenha(senha.getText().toString());
                String confirmarSenhaString = confirmarSenha.getText().toString();
                String resultado;

                String campoNaoPreenchido = Util.validaCadastroUsuario(usuario.getLogin(), usuario.getSenha(), confirmarSenhaString);

                if (campoNaoPreenchido == ""){
                    boolean senhaValida = Util.validaSenha(usuario.getSenha(), confirmarSenhaString);

                    if (senhaValida) {
                        resultado = crudUser.inserirUsuario(usuario.getLogin(), usuario.getSenha());
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                        Toast.makeText(getBaseContext(), resultado, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getBaseContext(), "Senhas não coincidem!", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getBaseContext(), "Favor preencher o campo "+campoNaoPreenchido+"!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    /*private Cursor ConsultarDados() {
        Cursor cursor = crudUser.carregaDados();
        System.out.println("DADOS: "+cursor);
        return cursor;
    }*/
}
