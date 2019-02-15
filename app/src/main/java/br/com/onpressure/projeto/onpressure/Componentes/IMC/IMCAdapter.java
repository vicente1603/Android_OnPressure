package br.com.onpressure.projeto.onpressure.Componentes.IMC;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import br.com.onpressure.projeto.onpressure.Model.IMC.IMC;
import br.com.onpressure.projeto.onpressure.R;

public class IMCAdapter extends RecyclerView.Adapter<IMCHolder>{

    private final List<IMC> imcs;

    public IMCAdapter(List<IMC> imcs){
        this.imcs = imcs;
    }

    public void adicionarImc(IMC imc){
        imcs.add(imc);
        notifyItemInserted(getItemCount());
    }

    @Override
    public IMCHolder onCreateViewHolder(ViewGroup parent, int viewType){
        return new IMCHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_imc, parent, false));
    }

    @Override
    public void onBindViewHolder(IMCHolder holder, int position) {
        holder.txtPeso.setText(String.valueOf(imcs.get(position).getPeso()));
        holder.txtAltura.setText(String.valueOf(imcs.get(position).getAltura()));
        holder.txtResultado.setText(String.valueOf(imcs.get(position).getResultadoImc()));
        holder.txtInfo.setText(imcs.get(position).getInfoImc());
        holder.txtData.setText(imcs.get(position).getData());
    }

    @Override
    public int getItemCount(){ return imcs != null ? imcs.size() : 0; }

}
