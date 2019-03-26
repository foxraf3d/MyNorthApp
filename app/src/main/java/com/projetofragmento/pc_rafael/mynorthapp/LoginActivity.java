package com.projetofragmento.pc_rafael.mynorthapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import Controller.UsuarioController;

public class LoginActivity extends AppCompatActivity {

    private EditText login;
    private EditText senha;
    private Button entrar;
    private TextView cadastrar;
    private UsuarioController crudUser;
    private static String [][] listaUser;
    public static final String PREFS_MYNORTH = "MyPrefsMyNorth";
    private SharedPreferences sPref;

    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.editLoginId);
        senha = findViewById(R.id.editSenhaId);
        entrar = findViewById(R.id.btnEntrarId);
        cadastrar = findViewById(R.id.txtCadastroId);


        user = verificarUsuarioLogado();
        if (user != null){
            Intent intent = new Intent(LoginActivity.this, PrincipalActivity.class);
            intent.putExtra("USUARIO", user);
            startActivity(intent);
        }


        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crudUser = new UsuarioController(getBaseContext());
                String loginString = login.getText().toString();
                String senhaString = senha.getText().toString();
                user = login.getText().toString();

                boolean usuarioValido = usuarioExiste(loginString, senhaString);
                if (usuarioValido){
                    //Recuperando arquivo de preferencias:
                    salvarUsuarioLogado(loginString);
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


    }

    private String verificarUsuarioLogado() {
        sPref = getSharedPreferences(PREFS_MYNORTH, MODE_PRIVATE);
        String usuarioLogado = sPref.getString("loginUser", null);
        return usuarioLogado;
    }

    private void salvarUsuarioLogado(String login) {
        sPref = getSharedPreferences(PREFS_MYNORTH, MODE_PRIVATE);
        SharedPreferences.Editor editor = sPref.edit();
        editor.putString("loginUser", login);
        editor.commit();
    }

    public boolean usuarioExiste(String login, String senha){

        Cursor cursor = crudUser.carregaDados();
        int campoLogin = cursor.getColumnIndex("login");
        int campoSenha = cursor.getColumnIndex("senha");
        int size = cursor.getCount();

        if (size > 0) {
            listaUser = new String[cursor.getCount()][2];
            for (int i = 0; i < cursor.getCount(); i++) {
                listaUser[i][0] = cursor.getString(campoLogin);
                listaUser[i][1] = cursor.getString(campoSenha);
                if (i <= cursor.getCount())
                    cursor.moveToNext();
            }

            if (listaUser.length > 0) {
                for (int i = 0; i < listaUser.length; i++) {
                    String loginString = listaUser[i][0];
                    String senhaString = listaUser[i][1];
                    if (loginString.equalsIgnoreCase(login) && senhaString.equalsIgnoreCase(senha)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
