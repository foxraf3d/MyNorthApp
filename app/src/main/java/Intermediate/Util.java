package Intermediate;

import android.database.Cursor;

import Controller.UsuarioController;

public class Util {

    public static boolean validaSenha(String senha, String confirmaSenha){
        if (!senha.equalsIgnoreCase(confirmaSenha)) {
            return false;
        }
        return true;
    }

}
