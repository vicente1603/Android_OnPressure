package br.com.onpressure.projeto.onpressure.Activities;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import br.com.onpressure.projeto.onpressure.Componentes.IMC.IMCAdapter;
import br.com.onpressure.projeto.onpressure.Componentes.PressaoArterial.PressaoArterialAdapter;
import br.com.onpressure.projeto.onpressure.Model.IMC.IMCDAO;
import br.com.onpressure.projeto.onpressure.Model.PressaoArterial.PressaoArterialDAO;
import br.com.onpressure.projeto.onpressure.R;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class HistoricoActivity extends AppCompatActivity {

    Button btnPA;
    Button btnIMC;
    IMCAdapter adapterImc;
    PressaoArterialAdapter adapterPa;
    RecyclerView recyclerView, PA, IMC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnPA = findViewById(R.id.btnPA);

        btnIMC = findViewById(R.id.btnIMC);

        PA = findViewById(R.id.recyclerViewPressao);

        IMC = findViewById(R.id.recyclerViewImc);

        btnIMC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IMC.setVisibility(View.VISIBLE);
                PA.setVisibility(View.GONE);
                configurarRecyclerImc();
            }
        });

        btnPA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PA.setVisibility(View.VISIBLE);
                IMC.setVisibility(View.GONE);
                configurarRecyclerPA();
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void configurarRecyclerImc(){
        recyclerView = (RecyclerView)findViewById(R.id.recyclerViewImc);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        IMCDAO dao = new IMCDAO(this);
        adapterImc = new IMCAdapter(dao.retornarTodos());
        recyclerView.setAdapter(adapterImc);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    private void configurarRecyclerPA(){
        recyclerView = (RecyclerView)findViewById(R.id.recyclerViewPressao);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        PressaoArterialDAO dao = new PressaoArterialDAO(this);
        adapterPa = new PressaoArterialAdapter(dao.retornarTodas());
        recyclerView.setAdapter(adapterPa);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }
}