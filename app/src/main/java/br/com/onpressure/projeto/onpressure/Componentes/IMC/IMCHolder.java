package br.com.onpressure.projeto.onpressure.Componentes.IMC;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.com.onpressure.projeto.onpressure.R;

public class IMCHolder extends RecyclerView.ViewHolder{

    public TextView txtPeso;
    public TextView txtAltura;
    public TextView txtResultado;
    public TextView txtInfo;
    public TextView txtData;

    public IMCHolder(View itemView){
        super(itemView);
        txtPeso = itemView.findViewById(R.id.txtPeso);
        txtAltura = itemView.findViewById(R.id.txtAltura);
        txtResultado = itemView.findViewById(R.id.txtResultado);
        txtInfo = itemView.findViewById(R.id.txtInfo);
        txtData = itemView.findViewById(R.id.data);
    }

}
