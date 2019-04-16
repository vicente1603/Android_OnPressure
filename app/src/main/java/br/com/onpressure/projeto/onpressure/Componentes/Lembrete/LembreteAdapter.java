package br.com.onpressure.projeto.onpressure.Componentes.Lembrete;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import br.com.onpressure.projeto.onpressure.Model.IMC.IMC;
import br.com.onpressure.projeto.onpressure.Model.IMC.IMCDAO;
import br.com.onpressure.projeto.onpressure.Model.Lembrete.Lembrete;
import br.com.onpressure.projeto.onpressure.Model.Lembrete.LembreteDAO;
import br.com.onpressure.projeto.onpressure.R;

public class LembreteAdapter extends RecyclerView.Adapter<LembreteHolder>{

    private final List<Lembrete> lembretes;

    public LembreteAdapter(List<Lembrete> lembretes){
        this.lembretes = lembretes;
    }

    public void adicionarLembrete(Lembrete lembrete){
        lembretes.add(lembrete);
        notifyItemInserted(getItemCount());
    }


    @Override
    public LembreteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new LembreteHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lembrete, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LembreteHolder holder, final int position) {

        holder.txtMedicamento.setText(lembretes.get(position).getMedicamento());
        holder.txtPosologia.setText(lembretes.get(position).getPosologia());
        holder.txtDataHora.setText(lembretes.get(position).getDataHora());

        final Lembrete lembrete = lembretes.get(position);

        holder.btnExcluir.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View view = v;
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Confirmação")
                        .setMessage("Tem certeza que deseja excluir este lembrete?")
                        .setPositiveButton("Excluir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Lembrete lembrete = lembretes.get(position);
                                LembreteDAO dao = new LembreteDAO(view.getContext());
                                boolean sucesso = dao.excluir(lembrete.getId());
                                if (sucesso) {
                                    removerLembrete(lembrete);
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

    public void removerLembrete(Lembrete lembrete) {
        int position = lembretes.indexOf(lembrete);
        lembretes.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return lembretes != null ? lembretes.size() : 0;
    }
}
