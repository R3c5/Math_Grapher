package com.example1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.RelativeSizeSpan;
import android.text.style.SuperscriptSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import static java.lang.Math.pow;

public class Exp extends AppCompatActivity {
    private EditText valoare_a;
    private TextView text_fct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exponentiala);
        Button GoBackButton = (Button) findViewById(R.id.FuckGoBack);
        GoBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImDone();
            }
        });

        Button HelpExp = (Button) findViewById(R.id.helper_exponentiala);
        HelpExp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SwitchHelpExp();
            }
        });

        Button Compute = (Button) findViewById(R.id.ComputeExp);
        Compute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ComputeGraph();
                CoutFunction();
            }
        });
        valoare_a = (EditText) findViewById(R.id.val_exp_a);
        text_fct = (TextView) findViewById(R.id.text_fct_exp);
    }

    private void ImDone() {
        finish();
    }

    private double my_function(double x) {
        if (valoare_a.getText().length() == 0) {
            valoare_a.setText("1");
        }
        double a = Double.parseDouble(valoare_a.getText().toString());

        return pow(a, x);
    }


    private void CoutFunction() {
        double a = Double.parseDouble(valoare_a.getText().toString());
        text_fct.setText("f(x)=");
        text_fct.append(valoare_a.getText().toString());
        SpannableStringBuilder cs = new SpannableStringBuilder("x");
        cs.setSpan(new SuperscriptSpan(), 0, cs.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        cs.setSpan(new RelativeSizeSpan(0.75f), 0, cs.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        text_fct.append(cs);

    }

    private void ComputeGraph() {

        //reset graph
        GraphView Graph = (GraphView) findViewById(R.id.ExpGraph);
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
        GraphView Graph = (GraphView) findViewById(R.id.ExpGraph);
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

    private void SwitchHelpExp() {
        Intent intent_hexp = new Intent(this, HExp.class);
        startActivity(intent_hexp);
    }
}