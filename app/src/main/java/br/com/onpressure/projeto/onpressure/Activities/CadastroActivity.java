package br.com.onpressure.projeto.onpressure.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;

import br.com.onpressure.projeto.onpressure.Model.Usuario.Usuario;
import br.com.onpressure.projeto.onpressure.Model.Usuario.UsuarioDAO;
import br.com.onpressure.projeto.onpressure.R;

public class CadastroActivity extends AppCompatActivity {

    Usuario usuarioEditado = null;

    private int getIndex(Spinner spinner, String myString)
    {
        int index = 0;

        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                index = i;
                break;
            }
        }
        return index;
    }

    private EditText txtEmail;
    private EditText txtNome;
    private EditText txtDataNascimento;
    private EditText txtOcupacao;
    private Spinner spnTipoSanguineo;
    private Spinner spnGrauHipertensao;
    private EditText txtTelefone;
    private RadioGroup radioSexo;
    private EditText txtNomeContato;
    private EditText txtTelefoneContato;
    private RadioGroup radioTratamento;
    private Button btnEntrar;
    private Button btnSair;

    boolean editar = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        Intent intent = getIntent();
        if(intent.hasExtra("usuario")){

            editar = true;

            usuarioEditado = (Usuario) intent.getSerializableExtra("usuario");

            txtDataNascimento = findViewById(R.id.txtDataNascimento);
            txtNome = findViewById(R.id.txtNomeCompleto);
            txtEmail = findViewById(R.id.txtEmail);
            spnGrauHipertensao = findViewById(R.id.spnGrauHipertensao);
            spnTipoSanguineo = findViewById(R.id.spnTipoSanguineo);
            txtOcupacao = findViewById(R.id.txtOcupacao);
            txtTelefone = findViewById(R.id.txtNumeroTelefone);
            radioSexo = findViewById(R.id.radioSex);
            radioTratamento = findViewById(R.id.radioTratamento);
            txtNomeContato = findViewById(R.id.txtNomeContato);
            txtTelefoneContato = findViewById(R.id.txtTelefoneContato);

            txtDataNascimento.setText(usuarioEditado.getDataNascimento());
            txtNome.setText(usuarioEditado.getNome());
            txtEmail.setText(usuarioEditado.getEmail());
            spnGrauHipertensao.setSelection(getIndex(spnGrauHipertensao, usuarioEditado.getGrauHipertensao()));
            spnTipoSanguineo.setSelection(getIndex(spnTipoSanguineo, usuarioEditado.getTipoSanguineo()));
            txtOcupacao.setText(usuarioEditado.getOcupacao());
            txtTelefone.setText(usuarioEditado.getTelefone());
            txtNomeContato.setText(usuarioEditado.getNomeContato());
            txtTelefoneContato.setText(usuarioEditado.getTelefoneContato());

            if(usuarioEditado.getTratamento() != null){
                RadioButton rb;
                if (usuarioEditado.getTratamento().equals("Sim")){
                    rb = findViewById(R.id.radioSim);
                    rb.setChecked(true);
                }
                else{
                    rb = findViewById(R.id.radioNão);
                    rb.setChecked(true);
                }
            }

            if(usuarioEditado.getSexo() != null){
                RadioButton rb;
                if (usuarioEditado.getSexo().equals("Masculino")){
                    rb = findViewById(R.id.radioMale);
                }
                else{
                    rb = findViewById(R.id.radioFemale);
                    rb.setChecked(true);
                }
            }

        }

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
        radioTratamento = findViewById(R.id.radioTratamento);
        txtNomeContato = findViewById(R.id.txtNomeContato);
        txtTelefoneContato = findViewById(R.id.txtTelefoneContato);


        if(editar==false){
        SharedPreferences settings = getSharedPreferences("PREFS_NAME", 0);
        if (settings.getString("PrefUsuario", "").length() > 0) {
            Intent it = new Intent(this, HomeActivity.class);
            startActivity(it);
            finish();
        }}

        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CadastroActivity.this, "Saindo..", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");//formating according to my need

                        if (TextUtils.isEmpty(txtNome.getText())) {
                            txtNome.setError("Campo usuário é obrigatório");
                        } else if (TextUtils.isEmpty(txtEmail.getText())) {
                            txtEmail.setError("Campo e-mail é obrigatório");
                        } else if (TextUtils.isEmpty(txtTelefone.getText())) {
                            txtTelefone.setError("Campo telefone é obrigatório");
                        } else if (TextUtils.isEmpty(txtOcupacao.getText())) {
                            txtOcupacao.setError("Campo ocupação é obrigatório");
                        } else if (txtDataNascimento.getText().toString() == null) {
                            txtDataNascimento.setError("Campo data de nascimento é obrigatório");
                        } else if (spnGrauHipertensao.getSelectedItem().toString().equals("Selecione")) {
                            TextView errorText = (TextView)spnGrauHipertensao.getSelectedView();
                            errorText.setError("");
                            errorText.setTextColor(Color.RED);
                            errorText.setText("Campo obrigatório");
                        } else if (spnTipoSanguineo.getSelectedItem().toString().equals("Selecione")) {
                            TextView errorText = (TextView)spnTipoSanguineo.getSelectedView();
                            errorText.setError("");
                            errorText.setTextColor(Color.RED);
                            errorText.setText("Campo obrigatório");
                        } else {

                            String email = txtEmail.getText().toString();
                            String nome = txtNome.getText().toString();
                            String dataNascimento = txtDataNascimento.getText().toString();
                            String telefone = txtTelefone.getText().toString();
                            String ocupacao = txtOcupacao.getText().toString();
                            String tipoSanguineo = spnTipoSanguineo.getSelectedItem().toString();
                            String grauHipertensao = spnGrauHipertensao.getSelectedItem().toString();
                            String sexo = radioSexo.getCheckedRadioButtonId() == R.id.radioMale ? "Masculino" : "Feminino";
                            String tratamento = radioTratamento.getCheckedRadioButtonId() == R.id.radioSim ? "Sim" : "Não";
                            String nomeContato = txtNomeContato.getText().toString();
                            String telefoneContato = txtTelefoneContato.getText().toString();

                            UsuarioDAO dao = new UsuarioDAO(getBaseContext());
                            boolean sucesso;
                            if(usuarioEditado != null)
                                sucesso = dao.salvar(usuarioEditado.getId(), email, nome, dataNascimento, ocupacao, grauHipertensao, tipoSanguineo, telefone, sexo, nomeContato, telefoneContato, tratamento);
                            else
                                sucesso = dao.salvar(email, nome, dataNascimento, ocupacao, grauHipertensao, tipoSanguineo, telefone, sexo, nomeContato, telefoneContato, tratamento);

                            if (sucesso) {

                                Usuario usuario = dao.retornarUltimo();

                                txtEmail.setText("");
                                txtNome.setText("");
                                txtDataNascimento.setText("");
                                txtTelefone.setText("");
                                txtOcupacao.setText("");
                                spnTipoSanguineo.setSelection(0);
                                spnGrauHipertensao.setSelection(0);
                                radioSexo.setSelected(false);
                                txtNomeContato.setText("");
                                txtTelefoneContato.setText("");
                                radioTratamento.setSelected(false);

                                Snackbar.make(view, "Salvando...", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();


                                Toast.makeText(CadastroActivity.this, "Cadastro realizado!", Toast.LENGTH_SHORT).show();
                                Snackbar.make(view, "Cadastro realizado!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                                Intent it = new Intent(CadastroActivity.this, PerfilActivity.class);
                                startActivity(it);

                            } else {
                                Snackbar.make(view, "Erro ao salvar.", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                            }

                            SharedPreferences settings = getSharedPreferences("PREFS_NAME", 0);
                            SharedPreferences.Editor editor = settings.edit();
                            editor.putString("PrefUsuario", "nao_exibir_outra_vez");

                            editor.commit();

                            Intent intent = new Intent(CadastroActivity.this, HomeActivity.class);
                            startActivity(intent);
                            finish();
                        }

                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

}
