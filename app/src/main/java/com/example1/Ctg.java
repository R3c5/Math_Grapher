package com.example1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.style.TabStopSpan;
import android.view.View;
import android.widget.Button;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class Ctg extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ctg);
        Button GoBackButton = (Button) findViewById(R.id.FuckGoBack);
        GoBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImDone();
            }
        });
        Button Compute = (Button) findViewById(R.id.ComputeCtg);
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
        return 1 / Math.tan(x);
    }

    private void ComputeGraph() {
        final int how_many = 10;
        final double start = Math.PI * -how_many;
        final double end = Math.PI * how_many;
        final int multe = 1000000000;
        final double pas = 0.001;
        GraphView Graph = (GraphView) findViewById(R.id.CtgGraph);
        LineGraphSeries<DataPoint> puncte = new LineGraphSeries<>();
        double dif = end - start + 2;
        dif /= pas;
        int pct_cnt = (int) dif;
        double x, y;
        x = start - pas;
        for (int i = 0; i < pct_cnt; i++) {
            y = my_function(x);
            x += pas;
            puncte.appendData(new DataPoint(x, y), true, multe);
            double check = x;
            double pi = Math.PI;
            while (check < -pi / 2)
                check += pi;
            while (check > pi / 2)
                check -= pi;
            if (Math.abs(check) < 0.001) {
                Graph.addSeries(puncte);
                puncte = new LineGraphSeries<>();
            }
        }
        Graph.addSeries(puncte);
        FixGraph();

    }

    private void FixGraph() {
        GraphView Graph = (GraphView) findViewById(R.id.CtgGraph);
        final double Minx, Maxx, Miny, Maxy;
        Minx = -Math.PI;
        Maxx = Math.PI;
        Miny = -20;
        Maxy = 20;
        // Graph.getViewport().setYAxisBoundsManual(true);

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