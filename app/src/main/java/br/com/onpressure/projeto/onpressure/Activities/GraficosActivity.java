package br.com.onpressure.projeto.onpressure.Activities;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import br.com.onpressure.projeto.onpressure.Componentes.DbHelper;
import br.com.onpressure.projeto.onpressure.Componentes.IMC.IMCAdapter;
import br.com.onpressure.projeto.onpressure.Componentes.PressaoArterial.PressaoArterialAdapter;
import br.com.onpressure.projeto.onpressure.Model.IMC.IMCDAO;
import br.com.onpressure.projeto.onpressure.Model.PressaoArterial.PressaoArterialDAO;
import br.com.onpressure.projeto.onpressure.R;

public class GraficosActivity extends AppCompatActivity {

    BarChart barChart;
    ArrayList<String> dates;
    Random random;
    ArrayList<BarEntry> barEntries;
    EditText txtDataFim;
    EditText txtDataInicio;
    Button btn_pesquisar;
    Button btn_limpar;
    ListView listViewItens;
    GraphView graph;
    LineGraphSeries<DataPoint> series;
    DbHelper myHelper;
    SQLiteDatabase sqLiteDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graficos);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        graph = findViewById(R.id.graph);

        myHelper = new DbHelper(this);
        sqLiteDatabase = myHelper.getWritableDatabase();


        //        barChart = (BarChart) findViewById(R.id.bargraph);
//        txtDataInicio = findViewById(R.id.txtDataInicio);
//        txtDataFim = findViewById(R.id.txtDataFim);
//        btn_pesquisar = findViewById(R.id.btn_pesquisar);
//        btn_limpar = findViewById(R.id.btn_limpar);
//        listViewItens = findViewById(R.id.listViewGraph);
//
//
//        btn_pesquisar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                String dataInicio = txtDataInicio.getText().toString();
//                String dataFim = txtDataFim.getText().toString();
//
//                createRandomBarGraph(dataInicio, dataFim);
//                barChart.setVisibility(View.VISIBLE);
//            }
//        });
//
//        btn_limpar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                txtDataFim.setText("");
//                txtDataInicio.setText("");
//                barChart.setVisibility(View.GONE);
//
//            }
//        });

        exqButton();
        //loadList();


    }

    private void exqButton() {


        series = new LineGraphSeries<DataPoint>(loadList());
        graph.addSeries(series);

    }

//    public void createRandomBarGraph(String Date1, String Date2) {
//
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
//
//        try {
//            Date date1 = simpleDateFormat.parse(Date1);
//            Date date2 = simpleDateFormat.parse(Date2);
//
//            Calendar mDate1 = Calendar.getInstance();
//            Calendar mDate2 = Calendar.getInstance();
//            mDate1.clear();
//            mDate2.clear();
//
//            mDate1.setTime(date1);
//            mDate2.setTime(date2);
//
//            dates = new ArrayList<>();
//            dates = getList(mDate1, mDate2);
//
//            barEntries = new ArrayList<>();
//            float max;
//            float value;
//            random = new Random();
//            for (int j = 0; j < dates.size(); j++) {
//                max = 5f;
//                value = random.nextFloat() * max;
//                barEntries.add(new BarEntry(value, j));
//            }
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        BarDataSet barDataSet = new BarDataSet(barEntries, "Datas");
//        BarData barData = new BarData(dates, barDataSet);
//        barChart.setData(barData);
//    }

//    public ArrayList<String> getList(Calendar startDate, Calendar endDate) {
//        ArrayList<String> list = new ArrayList<String>();
//        while (startDate.compareTo(endDate) <= 0) {
//            list.add(getDate(startDate));
//            startDate.add(Calendar.DAY_OF_MONTH, 1);
//        }
//        return list;
//    }

//    public String getDate(Calendar cld) {
//        String curDate = cld.get(Calendar.DAY_OF_MONTH) + "/" + (cld.get(Calendar.MONTH) + 1) + "/"
//                + cld.get(Calendar.YEAR);
//        try {
//            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(curDate);
//            curDate = new SimpleDateFormat("dd/MM/yyyy").format(date);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return curDate;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private DataPoint[] loadList() {
        //ArrayList<String> array = new ArrayList<>();

        //String[] columns = {"PressaoDiastolica", "PressaoSistolica"};
//        Cursor cursor = sqLiteDatabase.query("PressoesArterial", columns, null, null, null, null, null);

        Cursor result = myHelper.loadData();

        DataPoint[] dp = new DataPoint[result.getCount()];


        for (int i = 0; i < result.getCount(); i++) {

            result.moveToNext();

            dp[i] = new DataPoint(result.getInt(1), result.getInt(2));

//            array.add(result.getString(1));
//            array.add(result.getString(2));
//            array.add(result.getString(5));
//            ListAdapter listAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, array);
//            listViewItens.setAdapter(listAdapter);
        }
        return dp;
    }

}
