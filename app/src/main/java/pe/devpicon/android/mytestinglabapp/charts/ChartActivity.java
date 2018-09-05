package pe.devpicon.android.mytestinglabapp.charts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
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

        final String[] quarters = new String[]{"Q1", "Q2", "Q3", "Q4"};

        IAxisValueFormatter formatter = new IAxisValueFormatter() {

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return quarters[(int) value];
            }
        };

        XAxis xAxis = chart.getXAxis();
        xAxis.setGranularity(1f); // minimum axis-step (interval) is 1
        xAxis.setValueFormatter(formatter);

        List<Entry> entries1 = new ArrayList<>();
        entries1.add(new Entry(0f, 100000f));
        entries1.add(new Entry(1f, 140000f));
        entries1.add(new Entry(2f, 180000f));
        entries1.add(new Entry(3f, 220000f));

        List<Entry> entries2 = new ArrayList<>();
        entries2.add(new Entry(0f, 200000f));
        entries2.add(new Entry(1f, 300000f));
        entries2.add(new Entry(2f, 400000f));
        entries2.add(new Entry(3f, 500000f));

        LineDataSet dataSet1 = new LineDataSet(entries1, "This is a label");
        dataSet1.setMode(LineDataSet.Mode.LINEAR);
        dataSet1.setAxisDependency(YAxis.AxisDependency.LEFT);

        LineDataSet dataSet2 = new LineDataSet(entries2, "This is another label");
        dataSet2.setMode(LineDataSet.Mode.LINEAR);
        dataSet2.setAxisDependency(YAxis.AxisDependency.LEFT);

        List<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(dataSet1);
        dataSets.add(dataSet2);

        LineData lineData = new LineData(dataSets);
        chart.setData(lineData);
        chart.invalidate();
    }
}
