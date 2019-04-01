package br.com.onpressure.projeto.onpressure.Activities;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graficos);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        barChart = (BarChart) findViewById(R.id.bargraph);

        createRandomBarGraph("2016/05/05", "2016/06/01");

    }

    public void createRandomBarGraph(String Date1, String Date2){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");

        try {
            Date date1 = simpleDateFormat.parse(Date1);
            Date date2 = simpleDateFormat.parse(Date2);

            Calendar mDate1 = Calendar.getInstance();
            Calendar mDate2 = Calendar.getInstance();
            mDate1.clear();
            mDate2.clear();

            mDate1.setTime(date1);
            mDate2.setTime(date2);

            dates = new ArrayList<>();
            dates = getList(mDate1,mDate2);

            barEntries = new ArrayList<>();
            float max = 0f;
            float value = 0f;
            random = new Random();
            for(int j = 0; j< dates.size();j++){
                max = 5f;
                value = random.nextFloat() * max;
                barEntries.add(new BarEntry(value,j));
            }

        }catch(ParseException e){
            e.printStackTrace();
        }

        BarDataSet barDataSet = new BarDataSet(barEntries,"Datas");
        BarData barData = new BarData(dates,barDataSet);
        barChart.setData(barData);
    }

    public ArrayList<String> getList(Calendar startDate, Calendar endDate){
        ArrayList<String> list = new ArrayList<String>();
        while(startDate.compareTo(endDate)<=0){
            list.add(getDate(startDate));
            startDate.add(Calendar.DAY_OF_MONTH,1);
        }
        return list;
    }

    public String getDate(Calendar cld){
        String curDate = cld.get(Calendar.YEAR) + "/" + (cld.get(Calendar.MONTH) + 1) + "/"
                +cld.get(Calendar.DAY_OF_MONTH);
        try{
            Date date = new SimpleDateFormat("yyyy/MM/dd").parse(curDate);
            curDate =  new SimpleDateFormat("yyy/MM/dd").format(date);
        }catch(ParseException e){
            e.printStackTrace();
        }
        return curDate;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}
