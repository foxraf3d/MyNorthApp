package Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.projetofragmento.pc_rafael.mynorthapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TipoDeContasFragment extends Fragment {


    public TipoDeContasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_tipo_de_contas, container, false);
        return view;
    }

}
