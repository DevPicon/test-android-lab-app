package pe.devpicon.android.mytestinglabapp.charts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

import pe.devpicon.android.mytestinglabapp.R;

public class ChartActivity extends AppCompatActivity {

    private LineChart chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        chart = (LineChart) findViewById(R.id.chart);
        List<Entry> entries = new ArrayList<>();
        entries.add(new Entry(1,2));
        entries.add(new Entry(2,3));
        entries.add(new Entry(4,2));
        entries.add(new Entry(5,2));
        LineDataSet dataSet = new LineDataSet(entries, "This is a label");
        LineData lineData = new LineData(dataSet);
        chart.setData(lineData);
        chart.invalidate();
    }
}
