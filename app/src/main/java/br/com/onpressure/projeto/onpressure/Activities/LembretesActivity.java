package br.com.onpressure.projeto.onpressure.Activities;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
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

import br.com.onpressure.projeto.onpressure.Componentes.AlarmReceiver;
import br.com.onpressure.projeto.onpressure.Model.Lembrete.LembreteDAO;
import br.com.onpressure.projeto.onpressure.R;

public class LembretesActivity extends AppCompatActivity {

    private EditText dataEscolhida;
    private EditText medicamento;
    private EditText posologia;
    private ImageButton calendarioData;
    private Button botaoAgendar;
    public static final String NOTIFICATION_CHANNEL_ID = "10001";
    private NotificationManager mNotificationManager;

    SimpleDateFormat formatarData = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lembretes);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        dataEscolhida = findViewById(R.id.txtDataHora);
        medicamento = findViewById(R.id.txtMedicamento);
        posologia = findViewById(R.id.txtPosologia);
        botaoAgendar = findViewById(R.id.btn_agendarNotificacao);
        calendarioData = findViewById(R.id.btn_calendárioDatePicker);

        botaoAgendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String dataAgendada = dataEscolhida.getText().toString();
                String medicamentoAgendado = medicamento.getText().toString();
                String posologiaAgendada = posologia.getText().toString();
                agendarNotificacao(getNotification(medicamentoAgendado, posologiaAgendada), formatarData.parse(dataAgendada, new ParsePosition(0)));

                LembreteDAO dao = new LembreteDAO(getBaseContext());
                boolean sucesso = dao.salvar(medicamentoAgendado, posologiaAgendada, dataAgendada);

                if (sucesso) {

                    medicamento.setText("");
                    posologia.setText("");
                    dataEscolhida.setText("");

                }

                Intent i = new Intent(LembretesActivity.this, ActivityListaLembrete.class);
                startActivity(i);


            }
        });

        calendarioData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizarData();
            }
        });

    }

    private void exibeDataNoCampo() {
        dataEscolhida.setText(formatarData.format(calendar.getTime()));
    }

    private void atualizarData() {

        new DatePickerDialog(this, data, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show();
        new TimePickerDialog(this, horario, calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE), true).show();
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

        }
    };

    private void agendarNotificacao(Notification notification, Date dataAgendada) {

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
        alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(),
                pendingIntent);
    }

    @TargetApi(16)
    private Notification getNotification(String medicamento, String posologia) {

        Notification.Builder mBuilder = new Notification.Builder(this);
        mBuilder.setSmallIcon(R.mipmap.ic_launcher);
        mBuilder.setContentTitle("Lembrete: Tomar o medicamento");
        mBuilder.setContentText(medicamento + " - " + posologia);

        Toast.makeText(LembretesActivity.this, "Medicamento " + medicamento + " adicionado!", Toast.LENGTH_LONG).show();

        mNotificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "NOTIFICATION_CHANNEL_NAME", importance);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            assert mNotificationManager != null;
            mBuilder.setChannelId(NOTIFICATION_CHANNEL_ID);
            mNotificationManager.createNotificationChannel(notificationChannel);
        }

        return mBuilder.build();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}









