package br.com.onpressure.projeto.onpressure.Fragmentos;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import br.com.onpressure.projeto.onpressure.R;

public class Ajuda_Frag extends DialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_frag_ajuda, container, false);
        getDialog().setTitle("Simple Dialog");

        Button btn_fechar = (Button) rootView.findViewById(R.id.btn_fechar);
        btn_fechar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return rootView;
    }

}
