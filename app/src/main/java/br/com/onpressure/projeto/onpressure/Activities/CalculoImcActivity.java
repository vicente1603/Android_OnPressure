package br.com.onpressure.projeto.onpressure.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import org.w3c.dom.Text;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import br.com.onpressure.projeto.onpressure.Componentes.PressaoArterial.PressaoArterialAdapter;
import br.com.onpressure.projeto.onpressure.Model.IMC.IMCDAO;
import br.com.onpressure.projeto.onpressure.R;

public class CalculoImcActivity extends AppCompatActivity {

    private EditText txtPeso, txtAltura;
    private TextView txtResultado, txtInfo;
    private Button btnCalcular;

    RecyclerView recyclerView;
    PressaoArterialAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculo_imc);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtPeso = findViewById(R.id.txtPeso);
        txtAltura = findViewById(R.id.txtAltura);
        btnCalcular = findViewById(R.id.btnCalcular);
        txtResultado = findViewById(R.id.txtResultado);
        txtInfo = findViewById(R.id.txtInfo);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // double pesoImc = 0;
                // pesoImc = Double.parseDouble(txtPeso.getText().toString());
                double pesoImc = Double.parseDouble(txtPeso.getText().toString().replace(",", "."));
                double alturaImc = Double.parseDouble(txtAltura.getText().toString().replace(",", "."));
                double res = (pesoImc / (alturaImc * alturaImc));

                String informacao = "Sem informação";
                String dica = "";

                if (res < 15) {
                    informacao = "Abaixo do Peso I.";
                    dica = "Alimentos ricos em proteína são grandes aliados: " +
                            "dê preferência às carnes magras (alcatra, filé mignon, maminha, fraldinha) " +
                            ", frango e principalmente peixes e ovos, além de leite e queijos brancos como ricota e minas.";
                }
                if ((res >= 15) && (res < 18.5)) {
                    informacao = "Abaixo do Peso.";
                    dica = "Aumente o consumo de pães, bolos, massas, mandioca, batata, milho e cereais " +
                            "(arroz, farinha de trigo, fubá, aveia), lembrando sempre de optar pelas versões integrais";
                }
                if ((res >= 18.6) && (res < 24.9)) {
                    informacao = "Peso Normal";
                    dica = "Não existe alimento 100% bom ou 100% ruim. Varie ao máximo o seu cardápio " +
                            "e não elimine completamente nenhum tipo de alimento. O equilíbrio entre a quantidade e a freqüência com a" +
                            " qual você consome refeições mais calóricas é a garantia do seu sucesso.";
                }
                if ((res >= 25) && (res < 29.4)) {
                    informacao = "Acima do Peso.";
                    dica = "Manter hábitos alimentares saudáveis e praticar atividades físicas são bons aliados contra o excesso de peso.";
                }
                if ((res >= 30) && (res < 39.4)) {
                    informacao = "Obesidade I.";
                    dica = "Procure tratamento através de dieta apropriada com avaliação médica em conjunto " +
                            "com a prática de exercícios, desde que o paciente seja avaliado e liberado pelo médico.";
                }
                if (res >= 40) {
                    informacao = "Obesidade II.";
                    dica = "Em alguns casos avaliados pelo médico, pode-se fazer o uso de remédios para emagrecer para ajudar no controle do peso.";
                }

                BigDecimal bd = new BigDecimal(res).setScale(2, RoundingMode.CEILING.HALF_EVEN);

                txtResultado.setText(String.valueOf(bd.doubleValue()));
                txtInfo.setText(String.valueOf(informacao));

                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");//formating according to my need

                if (TextUtils.isEmpty(txtPeso.getText())) {
                    txtPeso.setError("Campo Obrigatório");
                } else if (TextUtils.isEmpty(txtAltura.getText())) {
                    txtAltura.setError("Campo obrigatório");
                } else {

                    float peso = Float.parseFloat(txtPeso.getText().toString().replace(",", "."));
                    float altura = Float.parseFloat(txtAltura.getText().toString().replace(",", "."));
                    float resultado = Float.parseFloat(txtResultado.getText().toString());
                    String infoImc = informacao;
                    String data = formatter.format(Calendar.getInstance().getTime());

                    IMCDAO dao = new IMCDAO(getBaseContext());
                    boolean sucesso = dao.salvar(peso, altura, resultado, infoImc, data);

                    if (sucesso) {

                        txtPeso.setText("");
                        txtAltura.setText("");
                        txtResultado.setText("");
                        txtInfo.setText("");

                        Toast.makeText(CalculoImcActivity.this, "Dados salvos com sucesso!", Toast.LENGTH_LONG).show();

                        AlertDialog alertDialog = new AlertDialog.Builder(CalculoImcActivity.this).create();
                        alertDialog.setTitle("Informação:");
                        alertDialog.setMessage(informacao + " - " + dica);
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();

                        //Intent it = new Intent(CalculoImcActivity.this, HomeActivity.class);
                        //startActivity(it);
                    }
                }

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
}
