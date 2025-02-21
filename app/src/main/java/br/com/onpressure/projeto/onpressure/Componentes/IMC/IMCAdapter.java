package br.com.onpressure.projeto.onpressure.Componentes.IMC;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import br.com.onpressure.projeto.onpressure.Model.IMC.IMC;
import br.com.onpressure.projeto.onpressure.Model.IMC.IMCDAO;
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
    public void onBindViewHolder(IMCHolder holder, final int position) {

        holder.txtPeso.setText(String.valueOf(imcs.get(position).getPeso()));
        holder.txtAltura.setText(String.valueOf(imcs.get(position).getAltura()));
        holder.txtResultado.setText(String.valueOf(imcs.get(position).getResultadoImc()));
        holder.txtInfo.setText(imcs.get(position).getInfoImc());
        holder.txtData.setText(imcs.get(position).getData());

        final IMC imc = imcs.get(position);

        holder.btnExcluir.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View view = v;
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Confirmação")
                        .setMessage("Tem certeza que deseja excluir este registro de imc?")
                        .setPositiveButton("Excluir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                IMC imc = imcs.get(position);
                                IMCDAO dao = new IMCDAO(view.getContext());
                                boolean sucesso = dao.excluir(imc.getId());
                                if (sucesso) {
                                    removerIMC(imc);
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

    public void removerIMC(IMC imc) {
        int position = imcs.indexOf(imc);
        imcs.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount(){ return imcs != null ? imcs.size() : 0; }

}
