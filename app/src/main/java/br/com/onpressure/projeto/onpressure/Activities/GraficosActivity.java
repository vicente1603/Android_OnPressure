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
import android.widget.ListAdapter;
import android.widget.ListView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
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

    GraphView graph;
    DbHelper myHelper;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graficos);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myHelper = new DbHelper(this);
        sqLiteDatabase = myHelper.getWritableDatabase();

        desenharChart();

    }

    private void desenharChart() {

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(carregarDados());

        series.setThickness(10);
        series.setDrawDataPoints(true);
        series.setDataPointsRadius(15);

        graph = findViewById(R.id.graph);
        graph.setTitle("teste");

        // set manual X bounds
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(100);

        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(20);

        // enable scaling and scrolling
        graph.getViewport().setScalable(true);
        graph.getViewport().setScalableY(true);
       // graph.getGridLabelRenderer().setNumHorizontalLabels(9);

//        graph.getViewport().setScrollable(true); // enables horizontal scrolling
//        graph.getViewport().setScrollableY(true); // enables vertical scrolling
//        graph.getViewport().setScalable(true); // enables horizontal zooming and scrolling
//        graph.getViewport().setScalableY(true); // enables vertical zooming and scrolling

        graph.addSeries(series);

//        BarGraphSeries<DataPoint> series2 = new BarGraphSeries<>(carregarDados());
//
//        graph.addSeries(series2);

    }

    private void desenharChartDate() {

        Calendar calendar = Calendar.getInstance();
        Date d1 = calendar.getTime();

        GraphView graph = (GraphView) findViewById(R.id.graph);

// you can directly pass Date objects to DataPoint-Constructor
// this will convert the Date to double via Date#getTime()
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[]{
                new DataPoint(d1, 5),
        });

        graph.addSeries(series);

        graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(this));
        graph.getGridLabelRenderer().setNumHorizontalLabels(2); // only 4 because of the space

// set manual x bounds to have nice steps
        graph.getViewport().setMinX(d1.getTime());
        graph.getViewport().setXAxisBoundsManual(true);

// as we use dates as labels, the human rounding to nice readable numbers
// is not necessary
        graph.getGridLabelRenderer().setHumanRounding(false);

    }

    private DataPoint[] carregarDados() {

        Cursor result = myHelper.loadData();

        DataPoint[] dp = new DataPoint[result.getCount()];

        for (int i = 0; i < result.getCount(); i++) {

            result.moveToNext();

            dp[i] = new DataPoint(result.getInt(0), result.getInt(2)); //  _x_ e |y|
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
