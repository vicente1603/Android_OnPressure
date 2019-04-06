package br.com.onpressure.projeto.onpressure.Componentes.PressaoArterial;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.com.onpressure.projeto.onpressure.R;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import br.com.onpressure.projeto.onpressure.R;

public class PressaoArterialHolder extends RecyclerView.ViewHolder{


    public TextView txtPressaoDiastolica;
    public TextView txtPressaoSistolica;
    public TextView txtFrequenciaCardiaca;
    public TextView txtInfoPressao;
    public TextView txtData;
    public ImageButton btnExcluir;



    public PressaoArterialHolder(View itemView) {
        super(itemView);
        txtPressaoDiastolica = (TextView) itemView.findViewById(R.id.txtPressaoDiastolica);
        txtPressaoSistolica = (TextView) itemView.findViewById(R.id.txtPressaoSistolica);
        txtFrequenciaCardiaca = itemView.findViewById(R.id.txtFrequenciaCardiaca);
        txtInfoPressao = itemView.findViewById(R.id.txtInfoPressao);
        txtData = itemView.findViewById(R.id.data);
        btnExcluir = itemView.findViewById(R.id.btnExcluir);
    }
}
