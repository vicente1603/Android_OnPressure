package br.com.onpressure.projeto.onpressure.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import br.com.onpressure.projeto.onpressure.Model.Usuario.UsuarioDAO;
import br.com.onpressure.projeto.onpressure.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private EditText txtEmail;
    private EditText txtNome;
    private EditText txtDataNascimento;
    private EditText txtOcupacao;
    private Spinner spnTipoSanguineo;
    private Spinner spnGrauHipertensao;
    private EditText txtTelefone;
    private RadioGroup radioSexo;

    private Button btnEntrar;
    private Button btnSair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnEntrar = findViewById(R.id.btnEntrar);
        btnSair = findViewById(R.id.btnSair);

        txtDataNascimento = findViewById(R.id.txtDataNascimento);
        txtNome = findViewById(R.id.txtNomeCompleto);
        txtEmail = findViewById(R.id.txtEmail);
        spnGrauHipertensao = findViewById(R.id.spnGrauHipertensao);
        spnTipoSanguineo = findViewById(R.id.spnTipoSanguineo);
        txtOcupacao = findViewById(R.id.txtOcupacao);
        txtTelefone = findViewById(R.id.txtNumeroTelefone);
        radioSexo = findViewById(R.id.radioSex);

        SharedPreferences settings = getSharedPreferences("PREFS_NAME", 0);
        if (settings.getString("PrefUsuario", "").length() > 0) {
            Intent it = new Intent(this, HomeActivity.class);
            startActivity(it);
            finish();
        }

        btnEntrar.setOnClickListener(this);

        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Cancelou!!!!!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {

        try {

            if (v.getId() == btnEntrar.getId()) {

                Snackbar.make(v, "Salvando...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");//formating according to my need

                if (TextUtils.isEmpty(txtNome.getText())) {
                    txtNome.setError("Campo usuário é obrigatório");
                } else if (TextUtils.isEmpty(txtEmail.getText())) {
                    txtEmail.setError("Campo e-mail é obrigatório");
                } else if (TextUtils.isEmpty(txtTelefone.getText())) {
                    txtTelefone.setError("Campo telefone é obrigatório");
                } else if (TextUtils.isEmpty(txtOcupacao.getText())) {
                    txtOcupacao.setError("Campo ocupação é obrigatório");
                } else if (TextUtils.isEmpty(txtDataNascimento.getText())) {
                    txtDataNascimento.setError("Campo data de nascimento é obrigatório");
                } else {


                    String email = txtEmail.getText().toString();
                    String nome = txtNome.getText().toString();
                    String dataNascimento = formatter.format(Calendar.getInstance().getTime());
                    String telefone = txtTelefone.getText().toString();
                    String ocupacao = txtOcupacao.getText().toString();
                    String tipoSanguineo = spnTipoSanguineo.getSelectedItem().toString();
                    String grauHipertensao = spnGrauHipertensao.getSelectedItem().toString();
                    String sexo = radioSexo.getCheckedRadioButtonId() == R.id.radioMale ? "Masculino" : "Feminino";

                    UsuarioDAO dao = new UsuarioDAO(getBaseContext());
                    boolean sucesso = dao.salvar(email, nome, dataNascimento, ocupacao, grauHipertensao, tipoSanguineo, telefone, sexo);
                    if (sucesso) {

                        txtEmail.setText("");
                        txtNome.setText("");
                        txtDataNascimento.setText("");
                        txtTelefone.setText("");
                        txtOcupacao.setText("");
                        spnTipoSanguineo.setSelection(0);
                        spnGrauHipertensao.setSelection(0);
                        radioSexo.setSelected(false);

                        Toast.makeText(MainActivity.this, "Salvou!!!!!", Toast.LENGTH_SHORT).show();
                        Snackbar.make(v, "Salvou!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    } else {
                        Snackbar.make(v, "Erro ao salvar.", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    }

                    SharedPreferences settings = getSharedPreferences("PREFS_NAME", 0);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("PrefUsuario", "nao_exibir_outra_vez");

                    editor.commit();

                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }
}
