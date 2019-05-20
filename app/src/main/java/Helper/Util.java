package helper;

import android.widget.Toast;

import com.projetofragmento.pc_rafael.mynorthapp.PrincipalActivity;

import java.util.ArrayList;

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

    public static String[] listaMeses (){
        String[] listaMes = new String[]{
                "Selecione um mês...",
                "JANEIRO",
                "FEVEREIRO",
                "MARÇO",
                "ABRIL",
                "MAIO",
                "JUNHO",
                "JULHO",
                "AGOSTO",
                "SETEMBRO",
                "OUTUBRO",
                "NOVEMBRO",
                "DEZEMBRO"
        };
        return listaMes;
    }

    public static boolean validaConta(String dropTipoConta,
                                      String anoEdit,
                                      String dropMes,
                                      String numeroParcelaedit,
                                      String qtdParcelaEdit,
                                      String valorEdit,
                                      String dataVencimentoEdit,
                                      String dataPagamentoEdit){

        ArrayList<String> campos = new ArrayList<>();
        campos.add(dropTipoConta);
        campos.add(anoEdit);
        campos.add(dropMes);
        campos.add(numeroParcelaedit);
        campos.add(qtdParcelaEdit);
        campos.add(valorEdit);
        campos.add(dataVencimentoEdit);
        campos.add(dataPagamentoEdit);

        for (String valorCampo: campos) {
            if (valorCampo.isEmpty()){
                if (valorCampo == dataPagamentoEdit)
                    return true;
                return false;
            }
        }
        return true;
    }

}
