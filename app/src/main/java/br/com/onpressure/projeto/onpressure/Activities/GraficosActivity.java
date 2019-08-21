package br.com.onpressure.projeto.onpressure.Activities;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.PointsGraphSeries;
import com.jjoe64.graphview.series.Series;

import br.com.onpressure.projeto.onpressure.Componentes.DbHelper;
import br.com.onpressure.projeto.onpressure.R;

public class GraficosActivity extends AppCompatActivity {

    GraphView graph;
    GraphView graph2;
    DbHelper myHelper;
    LinearLayout layout_graphPAS, layout_graphPAD, layout_graphPADePAD;
    SQLiteDatabase sqLiteDatabase;
    int id, pas;
    Button btnPAS, btnPAD, btnPASePAD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graficos);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        layout_graphPAS = findViewById(R.id.layout_graphPAS);
        layout_graphPAD = findViewById(R.id.layout_graphPAD);
        layout_graphPADePAD = findViewById(R.id.layout_graphPADePAD);
        btnPAS = findViewById(R.id.btnPAS);
        btnPAD = findViewById(R.id.btnPAD);
        btnPASePAD = findViewById(R.id.btnPASePAD);

        myHelper = new DbHelper(this);
        sqLiteDatabase = myHelper.getWritableDatabase();

        btnPAD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_graphPAD.setVisibility(View.VISIBLE);
                layout_graphPAS.setVisibility(View.GONE);
                layout_graphPADePAD.setVisibility(View.GONE);
            }
        });

        btnPAS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_graphPAS.setVisibility(View.VISIBLE);
                layout_graphPAD.setVisibility(View.GONE);
                layout_graphPADePAD.setVisibility(View.GONE);
            }
        });

        btnPASePAD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_graphPADePAD.setVisibility(View.VISIBLE);
                layout_graphPAS.setVisibility(View.GONE);
                layout_graphPAD.setVisibility(View.GONE);
            }
        });

        desenharChatPointPAS();
        desenharChatPointPAD();
        desenharChatPointPASePAD();

    }

    private void desenharChatPointPASePAD() {

        graph = findViewById(R.id.graphPASePAD);
        PointsGraphSeries<DataPoint> seriesPAS = new PointsGraphSeries<>(carregarDadosPAS());
        PointsGraphSeries<DataPoint> seriesPAD = new PointsGraphSeries<>(carregarDadosPAD());

        seriesPAS.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                Toast.makeText(GraficosActivity.this, ""+dataPoint, Toast.LENGTH_SHORT).show();
            }
        });

        seriesPAD.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                Toast.makeText(GraficosActivity.this, ""+dataPoint, Toast.LENGTH_SHORT).show();
            }
        });

        GridLabelRenderer gridLabel = graph.getGridLabelRenderer();
        gridLabel.setHorizontalAxisTitle("Nº Registro");
        gridLabel.setVerticalAxisTitle("Pressão Sistólica e Diastólica");

        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMaxY(200);

        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMaxX(10);

        graph.getViewport().setScalable(true);
        graph.getViewport().setScalableY(true);

        graph.addSeries(seriesPAS);
        graph.addSeries(seriesPAD);
        seriesPAS.setShape(PointsGraphSeries.Shape.POINT);
        seriesPAS.setColor(Color.MAGENTA);
        seriesPAS.setTitle("PAS");
        seriesPAD.setShape(PointsGraphSeries.Shape.POINT);
        seriesPAD.setColor(Color.BLUE);
        seriesPAD.setTitle("PAD");

        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);

//        LineGraphSeries<DataPoint> series2 = new LineGraphSeries<>(new DataPoint[] {
//                new DataPoint(0, 140),
//                new DataPoint(id, 140),
//        });
//        series2.setColor(Color.RED);
//        series2.setThickness(10);
//        graph.addSeries(series2);
//
//        LineGraphSeries<DataPoint> series3 = new LineGraphSeries<>(new DataPoint[] {
//                new DataPoint(0, 120),
//                new DataPoint(id, 120),
//        });
//        series3.setColor(Color.GREEN);
//        series3.setThickness(10);
//        graph.addSeries(series3);

    }

    private void desenharChatPointPAS (){
        graph = findViewById(R.id.graphPAS);
        PointsGraphSeries<DataPoint> series = new PointsGraphSeries<>(carregarDadosPAS());

        series.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                Toast.makeText(GraficosActivity.this, ""+dataPoint, Toast.LENGTH_SHORT).show();
            }
        });

//        graph.setTitle("Nível de Pressão Sistólica");
//        graph.setTitleTextSize(50);
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
        graph2 = findViewById(R.id.graphPAD);
        PointsGraphSeries<DataPoint> series = new PointsGraphSeries<>(carregarDadosPAD());

        series.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                Toast.makeText(GraficosActivity.this, ""+dataPoint, Toast.LENGTH_SHORT).show();
            }
        });

//        graph2.setTitle("Nível de Pressão Diastólica");
//        graph2.setTitleTextSize(50);
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
