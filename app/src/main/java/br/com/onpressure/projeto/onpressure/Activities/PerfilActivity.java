package br.com.onpressure.projeto.onpressure.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;

import br.com.onpressure.projeto.onpressure.Componentes.Usuario.UsuarioAdapter;
import br.com.onpressure.projeto.onpressure.Model.Usuario.Usuario;
import br.com.onpressure.projeto.onpressure.Model.Usuario.UsuarioDAO;
import br.com.onpressure.projeto.onpressure.R;

public class PerfilActivity extends AppCompatActivity {

    Usuario usuarioEditado = null;

    RecyclerView recyclerView;
    UsuarioAdapter adapter;

    TextView txtIdade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        configurarRecycler();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void configurarRecycler(){
        recyclerView = (RecyclerView)findViewById(R.id.recyclerViewUsuario);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        UsuarioDAO dao = new UsuarioDAO(this);
        adapter = new UsuarioAdapter(dao.retornarTodos());
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

}
