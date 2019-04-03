package Helper;

public class Util {

    public static boolean validaSenha(String senha, String confirmaSenha){
        if (!senha.equalsIgnoreCase(confirmaSenha)) {
            return false;
        }
        return true;
    }

    public static String validaCadastroUsuario(String login, String senha, String confirmarSenha){
        String resultado = "";
        String [][] listaCampos = new String[3][2];
        listaCampos[0][0] = "Login";
        listaCampos[0][1] = login;
        listaCampos[1][0] = "Senha";
        listaCampos[1][1] = senha;
        listaCampos[2][0] = "Confirmar Senha";
        listaCampos[2][1] = confirmarSenha;

        for (int i=0; i < listaCampos.length; i++){
            if (listaCampos[i][1].isEmpty()){
                return resultado = listaCampos[i][0];
            }
        }
        return resultado;
    }

}
