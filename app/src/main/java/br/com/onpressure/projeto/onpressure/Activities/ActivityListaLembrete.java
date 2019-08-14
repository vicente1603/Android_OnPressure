package br.com.onpressure.projeto.onpressure.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import br.com.onpressure.projeto.onpressure.Componentes.Lembrete.LembreteAdapter;
import br.com.onpressure.projeto.onpressure.Model.Lembrete.LembreteDAO;
import br.com.onpressure.projeto.onpressure.R;

public class ActivityListaLembrete extends AppCompatActivity {

    LembreteAdapter adapterLembrete;
    LinearLayout layoutNaoCadastrado;
    RecyclerView recyclerView, lembrete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_lembretes);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        layoutNaoCadastrado = findViewById(R.id.layoutNaoCadastrado);
        lembrete = findViewById(R.id.recyclerViewLembrete);
        //lembrete.setVisibility(View.VISIBLE);

        configurarRecyclerLembretes();

        FloatingActionButton fab = findViewById(R.id.fab_lembrete);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityListaLembrete.this, LembretesActivity.class));
            }
        });

    }

    private void configurarRecyclerLembretes() {
        recyclerView = findViewById(R.id.recyclerViewLembrete);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        LembreteDAO dao = new LembreteDAO(this);
        adapterLembrete = new LembreteAdapter(dao.retornarTodos());
        recyclerView.setAdapter(adapterLembrete);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        if (dao.retornarTodos().size() > 0){
            lembrete.setVisibility(View.VISIBLE);
            layoutNaoCadastrado.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}