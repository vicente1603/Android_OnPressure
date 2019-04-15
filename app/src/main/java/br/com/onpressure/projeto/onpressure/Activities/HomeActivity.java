package br.com.onpressure.projeto.onpressure.Activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import br.com.onpressure.projeto.onpressure.Componentes.PressaoArterial.PressaoArterialAdapter;
import br.com.onpressure.projeto.onpressure.R;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView txtComoSeSente;
    private TextView txtResposta;
    private RecyclerView recyclerView;
    private PressaoArterialAdapter adapter;
    private LinearLayout layoutComoEsta;
    private LinearLayout layoutResposta;
    private static final String PREFERENCE = "Preferencia";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listarDicas();


        txtComoSeSente = findViewById(R.id.txtComoSeSente);
        txtResposta = findViewById(R.id.txtResposta);
        layoutComoEsta = findViewById(R.id.layoutComoEsta);
        layoutResposta = findViewById(R.id.layoutResposta);

        Bundle extra = getIntent().getExtras();

        if (extra != null) {

            layoutComoEsta.setVisibility(View.GONE);
            layoutResposta.setVisibility(View.VISIBLE);
            String textoPassado = extra.getString("txtEscolhido");
            txtResposta.setText(textoPassado);

            SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCE, 0);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            String txtEscolhido = txtResposta.getText().toString();
            editor.putString("txtEscolhido", txtEscolhido);
            editor.commit();

        }

        SharedPreferences sharedPreferences =  getSharedPreferences(PREFERENCE, 0);
        if (sharedPreferences.contains("txtEscolhido")){
            String txtRecuperado = sharedPreferences.getString("txtEscolhido", "Default");
            txtResposta.setText(txtRecuperado);
            layoutComoEsta.setVisibility(View.GONE);
            layoutResposta.setVisibility(View.VISIBLE);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, PressaoArterialActivity.class));
            }
        });

        FloatingActionButton fab_emergencia = findViewById(R.id.fab_emergencia);
        fab_emergencia.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View view) {
                String telefone_emergencia = "192";
                Uri uri = Uri.parse("tel:" + telefone_emergencia);

                Intent intent = new Intent(Intent.ACTION_CALL, uri);
                if (ActivityCompat.checkSelfPermission(HomeActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(HomeActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                    return;
                }
                startActivity(intent);
            }
        });

    }

    public void onClick(View v) {
        Intent it = new Intent(HomeActivity.this, FeedbackActivity.class);
        startActivity(it);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            onDestroy();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.sair) {

            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_perfil) {
            Intent intent = new Intent(HomeActivity.this, PerfilActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_imc) {
            Intent intent = new Intent(HomeActivity.this, CalculoImcActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_pressao) {
            Intent intent = new Intent(HomeActivity.this, PressaoArterialActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_alimentos) {
            Intent intent = new Intent(HomeActivity.this, AlimentosActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_sintomas) {
            Intent intent = new Intent(HomeActivity.this, SintomasActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_lembretes) {
            Intent intent = new Intent(HomeActivity.this, ActivityListaLembrete.class);
            startActivity(intent);
        } else if (id == R.id.nav_grafico) {
            Intent intent = new Intent(HomeActivity.this, GraficosActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_menu_historico) {
            Intent it = new Intent(HomeActivity.this, HistoricoActivity.class);
            startActivity(it);
        } else if (id == R.id.nav_fale_conosco) {
            Intent it = new Intent(HomeActivity.this, FaleConoscoActivity.class);
            startActivity(it);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void listarDicas() {
        String[] dicas = {
                "A Sociedade Brasileira de Cardiologia recomenda o máximo de 5g de Sal por dia!",
                "1g Sal: 400mg de Sódio ou seja, o máximo de sódio por dia é 2.000mg",
                "Pratique 30 minutos de qualquer exercício, 3X na semana! Sua saúde agradece!",
                "Observe as porções de alimentos, prato colorido sempre é mais saudável!",
                "Observe a tabela de alimentos, vegetais e frutas também tem sódio! Consulte a tabela e consuma menos sódio na dieta!",
                "Evite alimentos industrializados, pois o sódio é usado como conservante!",
                "Leia o rótulo, onde diz sódio! Adoçantes e conservantes tem sódio!",
                "Verifique sua pressão, no mínimo ao acordar e ao dormir!",
                "Mantenha sua pressão normal! Cuide a dieta e a medicação!",
                "Anote no aplicativo os valores da pressão e mostre ao seu médico ou farmacêutico!",
                "Não existe Sal bom, sal tem sódio! Leia o rótulo!",
                "Cuidado com o sódio dos alimentos doces!",
                "Pressão alta pode lhe adoecer! Cuide da sua!",
                "Beba água! Sua pressão agradece!",
                "Pernas inchadas podem indicar alterações na pressão! Verifique a mesma e procure seu médico!",
                "Dor de cabeça por mais de duas semanas pode indicar pressão alta! Não espere doer pra tratar!",
                "Urinando pouco? Verifique sua pressão!",
                "Tomou seu medicamento hoje? Lembre-se de usar todo dia, no horário recomendado!",
                "Não use medicamentos sem receita! Procure seu farmacêutico ou médico!",
                "76% dos hipertensos não se cuidam! Saia desta estatística! A vida vale mais!",
                "Pressão alta pode matar ou diminuir a qualidade de vida! Viva bem e cuide-se!",
                "O medicamento para pressão pode aumentar sua vontade de ir ao banheiro, sinal que sua pressão está baixando!",
                "Evite alimentos com muito sódio! Sua saúde agradece!",
                "Compre saches de sal nos atacados! Use 5 saches por dia! Veja o sal nos rótulos!",
                "Bolacha doce ou chocolate tem sódio! Leia o rótulo!",
                "Não use sal no preparo do alimento! Use 5 saches de sal por dia e use o sal no prato já pronto!",
                "Prefira temperos naturais e reduza o sal aos poucos!",
                "O paladar se acostuma ao pouco sal! Tente e aos poucos irá reduzir!",
                "Só o remédio não adianta! Exercício e dieta ajudam muito!",
                "Cafeína e energéticos aumentam a pressão! Não misture com bebida alcoólica!",
                "Bebida alcoólica pode diminuir a ação do medicamento!",
                "Não troque ou pare de usar o remédio por conta! Procure seu médico ou farmacêutico!",
                "O medicamento por gerar efeitos no organismo que você não gosta, fale com seu farmacêutico para lhe orientar o que fazer!",
                "Quantos comprimidos ainda restam, não deixe acabar para pegar!",
                "Pressão em Dia! Vida saudável!",
                "Você é o que come! Coma alimentos saudáveis!",
                "Movimente-se, seu corpo agradece!",};

        Random randomico = new Random();
        int numeroAleatorio = randomico.nextInt(dicas.length);

        AlertDialog alertDialog = new AlertDialog.Builder(HomeActivity.this).create();
        alertDialog.setTitle("Dicas:");
        alertDialog.setMessage(dicas[numeroAleatorio]);
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Fechar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }


}
