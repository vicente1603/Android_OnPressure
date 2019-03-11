package br.com.onpressure.projeto.onpressure.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import br.com.onpressure.projeto.onpressure.R;

public class AberturaActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                mostrarCadastro();
            }
        }, 2000);
    }

    private void mostrarCadastro() {
        Intent intent = new Intent(AberturaActivity.this,
                CadastroActivity.class);
        startActivity(intent);
        finish();
    }

}
