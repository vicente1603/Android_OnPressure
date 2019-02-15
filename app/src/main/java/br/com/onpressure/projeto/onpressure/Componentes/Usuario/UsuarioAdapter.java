package br.com.onpressure.projeto.onpressure.Componentes.Usuario;


import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import br.com.onpressure.projeto.onpressure.Activities.CadastroActivity;
import br.com.onpressure.projeto.onpressure.Model.Usuario.Usuario;
import br.com.onpressure.projeto.onpressure.R;

public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioHolder> {


    private final List<Usuario> usuarios;

    public UsuarioAdapter(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public void atualizarUsuario(Usuario usuario){
        usuarios.set(usuarios.indexOf(usuario), usuario);
        notifyItemChanged(usuarios.indexOf(usuario));
    }

    public void adicionarUsuario(Usuario usuario){
        usuarios.add(usuario);
        notifyItemInserted(getItemCount());
    }

    @Override
    public UsuarioHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new UsuarioHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_usuario, parent, false));
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
        holder.txtNomeContato.setText(String.valueOf(usuarios.get(position).getNomeContato()));
        holder.txtTelefoneContato.setText(String.valueOf(usuarios.get(position).getTelefoneContato()));
        final Usuario usuario = usuarios.get(position);

        holder.btnEditar.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent upit = new Intent(getActivity(v), CadastroActivity.class);
                Activity activity = getActivity(v);
                Intent intent = upit;
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("usuario", usuario);
                activity.finish();
                activity.startActivity(intent);
            }
        });

    }

    private Activity getActivity(View view) {
        Context context = view.getContext();
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity)context;
            }
            context = ((ContextWrapper)context).getBaseContext();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return usuarios != null ? usuarios.size() : 0;
    }
}
