package br.com.onpressure.projeto.onpressure.Activities;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
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
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.PointsGraphSeries;
import com.jjoe64.graphview.series.Series;

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

import static android.widget.GridLayout.VERTICAL;

public class GraficosActivity extends AppCompatActivity {

    GraphView graph;
    GraphView graph2;
    DbHelper myHelper;
    LinearLayout layout_graph1, layout_graph2;
    SQLiteDatabase sqLiteDatabase;
    int id, pas;
    Button btnPAS, btnPAD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graficos);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        layout_graph1 = findViewById(R.id.layout_graph1);
        layout_graph2 = findViewById(R.id.layout_graph2);
        btnPAS = findViewById(R.id.btnPAS);
        btnPAD = findViewById(R.id.btnPAD);

        myHelper = new DbHelper(this);
        sqLiteDatabase = myHelper.getWritableDatabase();

        btnPAD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_graph2.setVisibility(View.VISIBLE);
                layout_graph1.setVisibility(View.GONE);
            }
        });

        btnPAS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_graph1.setVisibility(View.VISIBLE);
                layout_graph2.setVisibility(View.GONE);
            }
        });

        desenharChatPointPAS();
        desenharChatPointPAD();

    }

    private void desenharChatPointPAS (){
        graph = findViewById(R.id.graph);
        PointsGraphSeries<DataPoint> series = new PointsGraphSeries<>(carregarDadosPAS());

        series.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                Toast.makeText(GraficosActivity.this, ""+dataPoint, Toast.LENGTH_SHORT).show();
            }
        });

        graph.setTitle("Nível de Pressão Sistólica");
        graph.setTitleTextSize(50);
        GridLabelRenderer gridLabel = graph.getGridLabelRenderer();
        gridLabel.setHorizontalAxisTitle("Nº Registro");
        gridLabel.setVerticalAxisTitle("Pressão Sistólica");

        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMaxY(200);

        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMaxX(10);

        graph.getViewport().setScalable(true);
        graph.getViewport().setScalableY(true);

        graph.addSeries(series);
        series.setShape(PointsGraphSeries.Shape.POINT);

        LineGraphSeries<DataPoint> series2 = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 140),
                new DataPoint(id, 140),
        });
        series2.setColor(Color.RED);
        series2.setThickness(10);
        graph.addSeries(series2);

        LineGraphSeries<DataPoint> series3 = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 120),
                new DataPoint(id, 120),
        });
        series3.setColor(Color.GREEN);
        series3.setThickness(10);
        graph.addSeries(series3);

//        LineGraphSeries<DataPoint> series4 = new LineGraphSeries<>(carregarDadosPAS());
//        graph.addSeries(series4);
    }

    private void desenharChatPointPAD (){
        graph2 = findViewById(R.id.graph2);
        PointsGraphSeries<DataPoint> series = new PointsGraphSeries<>(carregarDadosPAD());

        series.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                Toast.makeText(GraficosActivity.this, ""+dataPoint, Toast.LENGTH_SHORT).show();
            }
        });

        graph2.setTitle("Nível de Pressão Diastólica");
        graph2.setTitleTextSize(50);
        GridLabelRenderer gridLabel = graph2.getGridLabelRenderer();
        gridLabel.setHorizontalAxisTitle("Nº Registro");
        gridLabel.setVerticalAxisTitle("Pressão Diastólica");

        graph2.getViewport().setYAxisBoundsManual(true);
        graph2.getViewport().setMaxY(200);

        graph2.getViewport().setXAxisBoundsManual(true);
        graph2.getViewport().setMaxX(10);

        graph2.getViewport().setScalable(true);
        graph2.getViewport().setScalableY(true);

        graph2.addSeries(series);
        series.setShape(PointsGraphSeries.Shape.POINT);

        LineGraphSeries<DataPoint> series2 = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 90),
                new DataPoint(id, 90),
        });
        series2.setColor(Color.RED);
        series2.setThickness(10);
        graph2.addSeries(series2);

        LineGraphSeries<DataPoint> series3 = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 80),
                new DataPoint(id, 80),
        });
        series3.setColor(Color.GREEN);
        series3.setThickness(10);
        graph2.addSeries(series3);

//        LineGraphSeries<DataPoint> series4 = new LineGraphSeries<>(carregarDadosPAS());
//        graph.addSeries(series4);
    }

    private DataPoint[] carregarDadosPAS() {

        Cursor result = myHelper.loadData();

        DataPoint[] dp = new DataPoint[result.getCount()];

        for (int i = 0; i < result.getCount(); i++) {

            result.moveToNext();

            id = result.getInt(0) + 10;
            pas = result.getInt(2) + 5;

            dp[i] = new DataPoint(result.getInt(0), result.getInt(2)); //  _x_ e |y|
        }
        return dp;
    }

    private DataPoint[] carregarDadosPAD() {

        Cursor result = myHelper.loadData();

        DataPoint[] dp = new DataPoint[result.getCount()];

        for (int i = 0; i < result.getCount(); i++) {

            result.moveToNext();

            id = result.getInt(0) + 10;
            pas = result.getInt(2) + 5;

            dp[i] = new DataPoint(result.getInt(0), result.getInt(1)); //  _x_ e |y|
        }
        return dp;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}
