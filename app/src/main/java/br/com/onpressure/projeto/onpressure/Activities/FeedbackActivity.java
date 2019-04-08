package br.com.onpressure.projeto.onpressure.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import br.com.onpressure.projeto.onpressure.R;

public class FeedbackActivity extends AppCompatActivity {

    ImageButton feliz;
    ImageButton regular;
    ImageButton naotaobem;
    ImageButton mal;
    TextView txtStatus;
    Button btnConfirmar;
//    private static final String PREFERENCE = "Preferencia";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        feliz = findViewById(R.id.feliz);
        regular = findViewById(R.id.regular);
        naotaobem = findViewById(R.id.naotaobem);
        mal = findViewById(R.id.mal);
        txtStatus = findViewById(R.id.txtStatus);
        btnConfirmar = findViewById(R.id.btn_confirmar);

        feliz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtStatus.setText("Muito bem");
            }
        });

        regular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtStatus.setText("Regular");
            }
        });

        naotaobem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtStatus.setText("Não tão bem");
            }
        });

        mal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtStatus.setText("Mal");
            }
        });

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!txtStatus.equals("")) {

//                    SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCE, 0);
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    String txtEscolhido = txtStatus.getText().toString();
//                    editor.putString("txtEscolhido", txtEscolhido);
//                    editor.commit();

                    Intent intent = new Intent(FeedbackActivity.this, HomeActivity.class);
                    intent.putExtra("txtEscolhido", txtEscolhido);
                    startActivity(intent);

                }
            }
        });

//        SharedPreferences sharedPreferences =  getSharedPreferences(PREFERENCE, 0);
//        if (sharedPreferences.contains("txtEscolhido")){
//            String txtRecuperado = sharedPreferences.getString("txtEscolhido", "Default");
//            txtStatus.setText(txtRecuperado);
//        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}