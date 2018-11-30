package br.com.onpressure.projeto.onpressure.Componentes.PressaoArterial;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import br.com.onpressure.projeto.onpressure.Model.PressaoArterial.PressaoArterial;
import br.com.onpressure.projeto.onpressure.R;

public class PressaoArterialAdapter extends RecyclerView.Adapter<PressaoArterialHolder>{

    private final List<PressaoArterial> pressoesArterial;

    public PressaoArterialAdapter(List<PressaoArterial> pressoesArterial) {
        this.pressoesArterial = pressoesArterial;
    }

    public void adicionarPressaoArterial(PressaoArterial pressaoArterial){
        pressoesArterial.add(pressaoArterial);
        notifyItemInserted(getItemCount());
    }

    @Override
    public PressaoArterialHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PressaoArterialHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pressao_arterial, parent, false));
    }

    @Override
    public void onBindViewHolder(PressaoArterialHolder holder, int position) {
        holder.txtPressaoDiastolica.setText(String.valueOf(pressoesArterial.get(position).getPressaoDiastolica()));
        holder.txtPressaoSistolica.setText(String.valueOf(pressoesArterial.get(position).getPressaoSistolica()));
        holder.txtFrequenciaCardiaca.setText(String.valueOf(pressoesArterial.get(position).getFrequenciaCardiaca()));
        holder.txtData.setText(pressoesArterial.get(position).getData());
    }

    @Override
    public int getItemCount() {
        return pressoesArterial != null ? pressoesArterial.size() : 0;
    }

}
