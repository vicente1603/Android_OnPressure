package br.com.onpressure.projeto.onpressure.Fragmentos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.onpressure.projeto.onpressure.R;

public class SegundoFragment  extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_historico, container, false);


        return view;
    }

}