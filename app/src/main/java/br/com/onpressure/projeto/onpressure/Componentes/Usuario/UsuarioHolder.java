package br.com.onpressure.projeto.onpressure.Componentes.Usuario;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import br.com.onpressure.projeto.onpressure.R;

public class UsuarioHolder extends RecyclerView.ViewHolder{


    public TextView txtNome;
    public TextView txtEmail;
    public TextView txtOcupacao;
    public TextView txtTipoSanguineo;
    public TextView txtDataNascimento;
    public TextView txtGenero;
    public TextView txtNumeroTelefone;
    public TextView txtGrauHipertensao;

    public UsuarioHolder(View itemView) {
        super(itemView);
        txtNome = (TextView) itemView.findViewById(R.id.txtNomeCompleto);
        txtEmail = (TextView) itemView.findViewById(R.id.txtEmail);
        txtOcupacao = (TextView) itemView.findViewById(R.id.txtOcupacao);
        txtTipoSanguineo = (TextView) itemView.findViewById(R.id.txtTipoSanguineo);
        txtDataNascimento = (TextView) itemView.findViewById(R.id.txtDataNascimento);
        txtGenero = (TextView) itemView.findViewById(R.id.txtGenero);
        txtNumeroTelefone = (TextView) itemView.findViewById(R.id.txtNumeroTelefone);
        txtGrauHipertensao = (TextView) itemView.findViewById(R.id.txtGrauHipertensao);
    }
}
