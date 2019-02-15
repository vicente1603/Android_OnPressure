package br.com.onpressure.projeto.onpressure.Activities;

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

                double pesoImc = Double.parseDouble(txtPeso.getText().toString());
                double alturaImc = Double.parseDouble(txtAltura.getText().toString());
                double res = (pesoImc / (alturaImc * alturaImc));

                String informacao = "Ainda não calculado";

                if (res < 15) {
                    informacao = "Abaixo do Peso I.";
                }
                if ((res >= 15) && (res < 18.5)) {
                    informacao = "Abaixo do Peso.";
                }
                if ((res >= 18.6) && (res < 24.9)) {
                    informacao = "Peso Normal.";
                }
                if ((res >= 25) && (res < 29.4)) {
                    informacao = "Acima do Peso.";
                }
                if ((res >= 30) && (res < 39.4)) {
                    informacao = "Obesidade I.";
                }
                if (res >= 40) {
                    informacao = "Obesidade II.";
                }

                BigDecimal bd = new BigDecimal(res).setScale(2, RoundingMode.CEILING.HALF_EVEN);

                txtResultado.setText(String.valueOf(bd.doubleValue()));
                txtInfo.setText(String.valueOf(informacao));

                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");//formating according to my need

                if (TextUtils.isEmpty(txtPeso.getText())){
                    txtPeso.setError("Campo Obrigatório");
                }else if(TextUtils.isEmpty(txtAltura.getText())){
                    txtAltura.setError("Campo obrigatório");
                }else{

                    float peso = Float.parseFloat(txtPeso.getText().toString());
                    float altura = Float.parseFloat(txtAltura.getText().toString());
                    float resultado = Float.parseFloat(txtResultado.getText().toString());
                    String infoImc = informacao;
                    String data = formatter.format(Calendar.getInstance().getTime());

                    IMCDAO dao = new IMCDAO(getBaseContext());
                    boolean sucesso = dao.salvar(peso, altura, resultado, infoImc, data);

                    if (sucesso){


                        txtPeso.setText("");
                        txtAltura.setText("");
                        txtResultado.setText("");
                        txtInfo.setText("");

                        Toast.makeText(CalculoImcActivity.this, "Dados salvos com sucesso!", Toast.LENGTH_LONG).show();

                        Intent it = new Intent(CalculoImcActivity.this, HomeActivity.class);
                        startActivity(it);
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
