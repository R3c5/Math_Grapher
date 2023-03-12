package com.example1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class Tg extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tg);
        Button GoBackButton = (Button) findViewById(R.id.FuckGoBack);
        GoBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImDone();
            }
        });
        Button Compute = (Button) findViewById(R.id.ComputeTg);
        Compute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ComputeGraph();
            }
        });
    }

    private void ImDone() {
        finish();
    }

    private double my_function(double x) {
        return Math.tan(x);
    }

    private void ComputeGraph() {
        final int how_many = 10;
        final double start = Math.PI * -how_many;
        final double end = Math.PI * how_many;
        final int multe = 1000000000;
        final double pas = 0.0001;
        GraphView Graph = (GraphView) findViewById(R.id.TgGraph);
        LineGraphSeries<DataPoint> puncte = new LineGraphSeries<>();
        double dif = end - start + 2;
        dif /= pas;
        int pct_cnt = (int) dif;
        double x, y;
        x = start - pas;
        double discontinuitate = Math.PI / 2;
        for (int i = 0; i < pct_cnt; i++) {
            y = my_function(x);
            x += pas;
            puncte.appendData(new DataPoint(x, y), true, multe);
            double check = x;
            while (check < 0)
                check += Math.PI;
            while (check > Math.PI)
                check -= Math.PI;
            if (Math.abs(check - discontinuitate) < 0.001) {
                Graph.addSeries(puncte);
                puncte = new LineGraphSeries<>();
            }
        }
        Graph.addSeries(puncte);
        FixGraph();

    }

    private void FixGraph() {
        GraphView Graph = (GraphView) findViewById(R.id.TgGraph);
        final double Minx, Maxx, Miny, Maxy;
        Minx = -Math.PI;
        Maxx = Math.PI;
        Miny = -20;
        Maxy = 20;
        //Graph.getViewport().setYAxisBoundsManual(true);

        Graph.getViewport().setYAxisBoundsManual(true);
        Graph.getViewport().setXAxisBoundsManual(true);
        Graph.getViewport().setMinX(Minx);
        Graph.getViewport().setMaxX(Maxx);
        Graph.getViewport().setMinY(Miny);
        Graph.getViewport().setMaxY(Maxy);
        Graph.getViewport().setScalable(true);
        Graph.getViewport().setScalableY(true);
        Graph.getViewport().setScrollable(true);
        Graph.getViewport().setScrollableY(true);
    }
}