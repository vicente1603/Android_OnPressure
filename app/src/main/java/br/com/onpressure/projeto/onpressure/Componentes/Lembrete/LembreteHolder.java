package br.com.onpressure.projeto.onpressure.Componentes.Lembrete;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import br.com.onpressure.projeto.onpressure.R;

public class LembreteHolder extends RecyclerView.ViewHolder {

    public TextView txtMedicamento;
    public TextView txtPosologia;
    public TextView txtDataHora;
    public ImageButton btnExcluir;

    public LembreteHolder(View itemView) {
        super(itemView);
        txtMedicamento = itemView.findViewById(R.id.txtMedicamento);
        txtPosologia = itemView.findViewById(R.id.txtPosologia);
        txtDataHora = itemView.findViewById(R.id.txtDataHora);
        btnExcluir = itemView.findViewById(R.id.btnExcluir);
    }
}
