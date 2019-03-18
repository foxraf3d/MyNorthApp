package Intermediate;

public class Util {

    public static boolean validaSenha(String senha, String confirmaSenha){
        if (!senha.equalsIgnoreCase(confirmaSenha)) {
            return false;
        }
        return true;
    }

}
