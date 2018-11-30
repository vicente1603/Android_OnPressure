package br.com.onpressure.projeto.onpressure.Componentes.Usuario;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.List;

import br.com.onpressure.projeto.onpressure.Model.Usuario.Usuario;
import br.com.onpressure.projeto.onpressure.R;

public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioHolder> {


    private final List<Usuario> usuarios;

    public UsuarioAdapter(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public UsuarioHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new UsuarioHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lista, parent, false));
    }

    @Override
    public void onBindViewHolder(UsuarioHolder holder, int position) {
        holder.txtNome.setText(usuarios.get(position).getNome());
        holder.txtEmail.setText(usuarios.get(position).getEmail());
        holder.txtOcupacao.setText(usuarios.get(position).getOcupacao());
        holder.txtTipoSanguineo.setText(usuarios.get(position).getTipoSanguineo());
        holder.txtDataNascimento.setText(usuarios.get(position).getDataNascimento());
        holder.txtGenero.setText(usuarios.get(position).getSexo());
        holder.txtNumeroTelefone.setText(String.valueOf(usuarios.get(position).getTelefone()));
        holder.txtGrauHipertensao.setText(String.valueOf(usuarios.get(position).getGrauHipertensao()));
    }

    @Override
    public int getItemCount() {
        return usuarios != null ? usuarios.size() : 0;
    }
}
