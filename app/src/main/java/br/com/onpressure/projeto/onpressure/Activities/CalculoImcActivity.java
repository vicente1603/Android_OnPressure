package br.com.onpressure.projeto.onpressure.Activities;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import org.w3c.dom.Text;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;

import br.com.onpressure.projeto.onpressure.R;

public class CalculoImcActivity extends AppCompatActivity {

    EditText txtPeso, txtAltura;
    TextView txtResultado, txtInfo;
    Button btnCalcular;

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

                    double peso = Double.parseDouble(txtPeso.getText().toString());
                    double altura = Double.parseDouble(txtAltura.getText().toString());
                    double res = (peso / (altura * altura));

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

                    txtResultado.setText(String.valueOf(bd.doubleValue() + " kg/m2"));
                    txtInfo.setText(String.valueOf(informacao));
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
