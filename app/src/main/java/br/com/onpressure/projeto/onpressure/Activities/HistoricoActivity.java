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
import android.widget.LinearLayout;
import android.widget.Toast;

public class HistoricoActivity extends AppCompatActivity {

    Button btnPA;
    Button btnIMC;
    IMCAdapter adapterImc;
    PressaoArterialAdapter adapterPa;
    RecyclerView recyclerView, PA, IMC;
    LinearLayout layoutNaoCadastradoPA,layoutNaoCadastradoIMC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnPA = findViewById(R.id.btnPA);

        btnIMC = findViewById(R.id.btnIMC);

        PA = findViewById(R.id.recyclerViewPressao);
        IMC = findViewById(R.id.recyclerViewImc);

        layoutNaoCadastradoIMC = findViewById(R.id.layoutNaoCadastradoIMC);
        layoutNaoCadastradoPA = findViewById(R.id.layoutNaoCadastradoPA);

        configurarRecyclerPA();

        btnIMC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutNaoCadastradoIMC.setVisibility(View.VISIBLE);
                layoutNaoCadastradoPA.setVisibility(View.GONE);
                IMC.setVisibility(View.GONE);
                PA.setVisibility(View.GONE);
                configurarRecyclerImc();
            }
        });

        btnPA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutNaoCadastradoPA.setVisibility(View.VISIBLE);
                layoutNaoCadastradoIMC.setVisibility(View.GONE);
                PA.setVisibility(View.GONE);
                IMC.setVisibility(View.GONE);
                configurarRecyclerPA();
            }
        });


    }

    private void configurarRecyclerImc(){
        recyclerView = (RecyclerView)findViewById(R.id.recyclerViewImc);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        IMCDAO dao = new IMCDAO(this);
        adapterImc = new IMCAdapter(dao.retornarTodos());
        recyclerView.setAdapter(adapterImc);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        if (dao.retornarTodos().size() > 0){
            IMC.setVisibility(View.VISIBLE);
            layoutNaoCadastradoIMC.setVisibility(View.GONE);
        }
    }

    private void configurarRecyclerPA(){
        recyclerView = (RecyclerView)findViewById(R.id.recyclerViewPressao);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        PressaoArterialDAO dao = new PressaoArterialDAO(this);
        adapterPa = new PressaoArterialAdapter(dao.retornarTodas());
        recyclerView.setAdapter(adapterPa);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        if (dao.retornarTodas().size() > 0){
            PA.setVisibility(View.VISIBLE);
            layoutNaoCadastradoPA.setVisibility(View.GONE);
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