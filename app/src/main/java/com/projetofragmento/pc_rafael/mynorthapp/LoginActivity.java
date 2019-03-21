package com.projetofragmento.pc_rafael.mynorthapp;

import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
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
    private static String [][] listaUser;

    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.editLoginId);
        senha = findViewById(R.id.editSenhaId);
        entrar = findViewById(R.id.btnEntrarId);
        cadastrar = findViewById(R.id.txtCadastroId);
        consultar = findViewById(R.id.consultarID);


        user = login.getText().toString();

        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crudUser = new UsuarioController(getBaseContext());
                String loginString = login.getText().toString();
                String senhaString = senha.getText().toString();

                boolean usuarioValido = usuarioExiste(loginString, senhaString);
                if (usuarioValido){
                    Intent intent = new Intent(LoginActivity.this, PrincipalActivity.class);
                    intent.putExtra("USUARIO", user);
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
                intent.putExtra("USUARIO", user);
                startActivity(intent);
            }
        });

    }

    public boolean usuarioExiste(String login, String senha){

        Cursor cursor = crudUser.carregaDados();
        int campoLogin = cursor.getColumnIndex("login");
        int campoSenha = cursor.getColumnIndex("senha");
        int size = cursor.getCount();
        for (int i = 0; i < cursor.getCount(); i++){
            listaUser = new String[cursor.getCount()][2];
            listaUser[i][0] = cursor.getString(campoLogin);
            listaUser[i][1] = cursor.getString(campoSenha);
            if (i <= cursor.getCount())
                cursor.moveToNext();
        }
        for (int i = 0; i < listaUser.length; i++){
            if (listaUser[i][0].equalsIgnoreCase(login) && listaUser[i][1].equalsIgnoreCase(senha)){
                return true;
            }
        }

        return false;
    }
}
