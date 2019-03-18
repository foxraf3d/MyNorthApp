package com.projetofragmento.pc_rafael.mynorthapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import Entities.UsuarioEntity;

public class PrincipalActivity extends AppCompatActivity {

    TextView user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        user = findViewById(R.id.userID);

        UsuarioEntity usuarioEntity = new UsuarioEntity();
        user.setText(usuarioEntity.getLogin());
    }
}
