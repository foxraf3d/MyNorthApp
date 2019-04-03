package Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import Fragments.ContasPagasFragment;
import Fragments.ContasPendentesFragment;
import Fragments.TipoDeContasFragment;

public class TabAdapter extends FragmentStatePagerAdapter {

    private String[] tituloAbas = {"Pendentes", "Pagas", "Tipo de Contas"};

    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;

        switch (position){
            case 0:
                fragment = new ContasPendentesFragment();
                break;
            case 1:
                fragment = new ContasPagasFragment();
                break;
            case 2:
                fragment = new TipoDeContasFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return tituloAbas.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position){
        return tituloAbas[position];
    }


}
