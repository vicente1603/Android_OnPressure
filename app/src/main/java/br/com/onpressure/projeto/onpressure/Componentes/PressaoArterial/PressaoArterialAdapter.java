package br.com.onpressure.projeto.onpressure.Componentes.PressaoArterial;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import br.com.onpressure.projeto.onpressure.Componentes.Usuario.UsuarioHolder;
import br.com.onpressure.projeto.onpressure.Model.PressaoArterial.PressaoArterial;
import br.com.onpressure.projeto.onpressure.Model.PressaoArterial.PressaoArterialDAO;
import br.com.onpressure.projeto.onpressure.R;

public class PressaoArterialAdapter extends RecyclerView.Adapter<PressaoArterialHolder> {

    private final List<PressaoArterial> pressoesArterial;

    public PressaoArterialAdapter(List<PressaoArterial> pressoesArterial) {
        this.pressoesArterial = pressoesArterial;
    }

    public void adicionarPressaoArterial(PressaoArterial pressaoArterial) {
        pressoesArterial.add(pressaoArterial);
        notifyItemInserted(getItemCount());
    }

    @Override
    public PressaoArterialHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PressaoArterialHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pressao_arterial, parent, false));
    }

    @Override
    public void onBindViewHolder(PressaoArterialHolder holder, final int position) {

        holder.txtPressaoDiastolica.setText(String.valueOf(pressoesArterial.get(position).getPressaoDiastolica()));
        holder.txtPressaoSistolica.setText(String.valueOf(pressoesArterial.get(position).getPressaoSistolica()));
        holder.txtFrequenciaCardiaca.setText(String.valueOf(pressoesArterial.get(position).getFrequenciaCardiaca()));
        holder.txtInfoPressao.setText(pressoesArterial.get(position).getInfoPressao());
        holder.txtData.setText(pressoesArterial.get(position).getData());

        holder.btnExcluir.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View view = v;
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Confirmação")
                        .setMessage("Tem certeza que deseja excluir este registro de pressão arterial?")
                        .setPositiveButton("Excluir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                PressaoArterial pressaoArterial = pressoesArterial.get(position);
                                PressaoArterialDAO dao = new PressaoArterialDAO(view.getContext());
                                boolean sucesso = dao.excluir(pressaoArterial.getId());
                                if (sucesso) {
                                    removerPA(pressaoArterial);
                                    Snackbar.make(view, "Item removido com sucesso!", Snackbar.LENGTH_LONG).setAction("Action", null).show();

                                } else {
                                    Snackbar.make(view, "Erro ao remover o item", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                                }
                            }
                        })
                        .setNegativeButton("Cancelar", null)
                        .create()
                        .show();
            }
        });

    }

    public void removerPA(PressaoArterial pressaoArterial) {
        int position = pressoesArterial.indexOf(pressaoArterial);
        pressoesArterial.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return pressoesArterial != null ? pressoesArterial.size() : 0;
    }

}
