package br.com.onpressure.projeto.onpressure.Activities;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import br.com.onpressure.projeto.onpressure.Componentes.AlarmReceiver;
import br.com.onpressure.projeto.onpressure.R;

public class LembretesActivity extends AppCompatActivity {

    private EditText dataEscolhida;
    private EditText materia;
   // private EditText horarioEscolhido;
    private ImageButton calendarioData;
    private Button botaoAgendar;

    SimpleDateFormat formatarData = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    SimpleDateFormat formatarHora = new SimpleDateFormat("HH:mm", Locale.getDefault());
    Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lembretes);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        dataEscolhida = (EditText) findViewById(R.id.edt_dataEscolhida);
        materia = (EditText) findViewById(R.id.edt_medicamentos);
        botaoAgendar = (Button) findViewById(R.id.btn_agendarNotificacao);
        calendarioData = findViewById(R.id.btn_calendárioDatePicker);
       // horarioEscolhido = (EditText) findViewById(R.id.edt_horario);


        botaoAgendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String dataAgendada = dataEscolhida.getText().toString();
                Log.d("Data agendada", String.valueOf(dataAgendada));
                //String horarioAgendado = horarioEscolhido.getText().toString();
                //Log.d("Horário agendado", String.valueOf(horarioAgendado));
                String materiaAgendada = materia.getText().toString();

                agendarNotificacao(getNotification(materiaAgendada),formatarData.parse(dataAgendada, new ParsePosition(0)));

                Log.d("Agendamento", "Agendou");

            }
        });

        calendarioData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizaHorario();
                atualizarData();
            }
        });
    }

    private void exibeDataNoCampo() {
        dataEscolhida.setText(formatarData.format(calendar.getTime()));
        Log.d("Data escolhida: ", String.valueOf(dataEscolhida));
    }

//    private void exibeHorarioNoCampo() {
//
//        horarioEscolhido.setText(formatarHora.format(calendar.getTime()));
//
//        Log.d("Hora escolhida: ", String.valueOf(horarioEscolhido));
//    }

    private void atualizarData() {

        new DatePickerDialog(this, data, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    //Salva a data que o usuário configurou
    DatePickerDialog.OnDateSetListener data = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            exibeDataNoCampo();
        }
    };

    private void atualizaHorario() {
        new TimePickerDialog(this, horario, calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE), true).show();
    }

    //Salva o horário que o usuário configurou
    TimePickerDialog.OnTimeSetListener horario = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calendar.set(Calendar.MINUTE, minute);
            //exibeHorarioNoCampo();

        }
    };

    private void agendarNotificacao(Notification notification, Date dataAgendada){

        Intent intentNotificacao = new Intent(this, AlarmReceiver.class);
        calendar.setTime(dataAgendada);

        //Irá gerar as id aleatorias para cada notificação
        int id = (int) (Math.random() * 1000);

        intentNotificacao.putExtra(AlarmReceiver.NOTIFICATION_ID, id);
        intentNotificacao.putExtra(AlarmReceiver.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, id, intentNotificacao,
                PendingIntent.FLAG_UPDATE_CURRENT);

        //disparo de notificacao
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC , calendar.getTimeInMillis(),
                pendingIntent);

        Log.d("AgendarNOtificacao", "agendarnotificacao");
    }

    private Notification getNotification(String content) {
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle("Lembrete: Tomar o medicamento");
        builder.setContentText(content);
        builder.setSmallIcon(R.mipmap.ic_launcher);

        Toast.makeText(LembretesActivity.this, "Medicamento "+content+" adicionado!", Toast.LENGTH_LONG).show();

        Log.d("Construindo Notificacao", "Construindo Notificacao");

        return builder.build();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}









