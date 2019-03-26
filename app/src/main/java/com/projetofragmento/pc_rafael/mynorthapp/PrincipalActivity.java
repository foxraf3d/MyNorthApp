package com.projetofragmento.pc_rafael.mynorthapp;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import Controller.UsuarioController;

public class PrincipalActivity extends AppCompatActivity {

    private TextView user;
    private Button sair;
    private UsuarioController crudUser;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        user = findViewById(R.id.txtUserID);
        sair = findViewById(R.id.btnLogOUT);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            String nomeUsuario = extras.getString("USUARIO");
            user.setText(nomeUsuario);
        }

        sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getSharedPreferences(LoginActivity.PREFS_MYNORTH, MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.clear();
                editor.commit();
                finish();
            }
        });

       /* try {
            crudUser = new UsuarioController(getBaseContext());
            Cursor cursor = ConsultarDados();

            int indiceColunaID = cursor.getColumnIndex("id");
            int indiceColunaLogin = cursor.getColumnIndex("login");

            itens = new ArrayList<String>();
            ids = new ArrayList<Integer>();
            itensAdaptador = new ArrayAdapter<String>(getBaseContext(),
                    android.R.layout.simple_list_item_1,
                    android.R.id.text1,
                    itens);
            listUser.setAdapter(itensAdaptador);
            cursor.moveToFirst();

            for (int i = 1; i <= cursor.getCount(); i++){
                itens.add(cursor.getString(indiceColunaLogin));
                ids.add(Integer.parseInt(cursor.getString(indiceColunaID)));
                Log.i("Resultado ", "ID: " + cursor.getString(indiceColunaID) + " LOGIN: " + cursor.getString(indiceColunaLogin));
                if (i <= cursor.getCount())
                cursor.moveToNext();
            }

        }catch (Exception e){
            e.printStackTrace();
        }*/
    }

    private Cursor ConsultarDados() {
        Cursor cursor = crudUser.carregaDados();
        return cursor;
    }
}
