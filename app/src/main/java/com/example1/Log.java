package com.example1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.RelativeSizeSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import static java.lang.Math.log;
import static java.lang.Math.pow;

public class Log extends AppCompatActivity {
    private EditText valoare_a;
    private TextView text_fct;
    private boolean ok = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logaritm);

        Button GoBackButton = (Button) findViewById(R.id.FuckGoBack);
        GoBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImDone();
            }
        });

        Button HelpLog = (Button) findViewById(R.id.helper_logaritm);
        HelpLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SwitchHelpLog();
            }
        });

        Button Compute = (Button) findViewById(R.id.ComputeLog);
        Compute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ok = false;
                CoutFunction();
                ComputeGraph();
            }
        });
        valoare_a = (EditText) findViewById(R.id.val_log_a);
        text_fct = (TextView) findViewById(R.id.text_fct_log);

    }

    private void ImDone() {
        finish();
    }

    private double my_function(double x) {
        double a, y, baza = 1;
        if (ok == true) {
            return log(x);
        } else {

            a = Double.parseDouble(valoare_a.getText().toString());
            baza = log(a);
            y = log(x) / baza;
            return y;
        }
    }


    private void CoutFunction() {
        if (valoare_a.getText().length() == 0) {
            text_fct.setText("f(x)=ln x");
            ok = true;
        } else {
            double a = Double.parseDouble(valoare_a.getText().toString());
            //eroare
            if (a == 1)
                text_fct.setText("Functia log nu este definita cu baza 1. Incearca alta valoare.");
            else {
                if (a == 10) text_fct.setText("f(x)=lg x");
                else {
                    text_fct.setText("f(x)=log");
                    SpannableStringBuilder cs = new SpannableStringBuilder(valoare_a.getText().toString());
                    cs.setSpan(new SubscriptSpan(), 0, cs.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    cs.setSpan(new RelativeSizeSpan(0.75f), 0, cs.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    text_fct.append(cs);
                    text_fct.append("x");
                }
            }
        }
    }

    private void ComputeGraph() {

        //reset graph
        GraphView Graph = (GraphView) findViewById(R.id.LogGraph);
        Graph.removeAllSeries();
        //draw graph
        final int how_many = 100;
        final double start = -how_many;
        final double end = how_many;
        final int multe = 1000000000;
        final double pas = 0.001;

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
        }
        Graph.addSeries(puncte);
        FixGraph();

    }

    private void FixGraph() {
        GraphView Graph = (GraphView) findViewById(R.id.LogGraph);
        final double Minx, Maxx, Miny, Maxy;
        Minx = -3;
        Maxx = 3;
        Miny = -3;
        Maxy = 3;
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

    private void SwitchHelpLog() {
        Intent intent_hlog = new Intent(this, HLog.class);
        startActivity(intent_hlog);
    }
}