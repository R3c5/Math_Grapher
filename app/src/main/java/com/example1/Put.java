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

public class Put extends AppCompatActivity {
    private EditText valoare_a;
    private TextView text_fct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_putere);

        Button GoBackButton = (Button) findViewById(R.id.FuckGoBack);
        GoBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImDone();
            }
        });

        Button HelpPut = (Button) findViewById(R.id.helper_putere);
        HelpPut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SwitchHelpPut();
            }
        });

        Button Compute = (Button) findViewById(R.id.ComputePut);
        Compute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ComputeGraph();
                CoutFunction();
            }
        });
        valoare_a = (EditText) findViewById(R.id.val_put_a);
        text_fct = (TextView) findViewById(R.id.text_fct_put);
    }
    private  void ImDone(){
        finish();
    }

    private double my_function(double x){
        if(valoare_a.getText().length() == 0){
            valoare_a.setText("1");
        }
        double a= Double.parseDouble(valoare_a.getText().toString());

        double y=pow(x,a);
        return y;
    }


    private void CoutFunction(){
        double a= Double.parseDouble(valoare_a.getText().toString());
        text_fct.setText("f(x)=x");
        if(a != 1){
            SpannableStringBuilder cs = new SpannableStringBuilder(valoare_a.getText().toString());
            cs.setSpan(new SuperscriptSpan(), 0, cs.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            cs.setSpan(new RelativeSizeSpan(0.75f), 0, cs.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            text_fct.append(cs);
        }

    }

    private void ComputeGraph(){

        //reset graph
        GraphView Graph = (GraphView) findViewById(R.id.PutGraph);
        Graph.removeAllSeries();
        //draw graph
        final int how_many  = 100;
        final double start =-how_many;
        final double end =how_many;
        final int multe = 1000000000;
        final double pas =  0.001;

        LineGraphSeries<DataPoint> puncte = new LineGraphSeries<>();
        double dif = end - start + 2;
        dif /= pas;
        int pct_cnt = (int)dif;
        double x, y;
        x = start - pas;
        for(int i = 0 ; i < pct_cnt ; i ++ ) {
            y = my_function(x);
            x += pas;
            puncte.appendData(new DataPoint(x,y),true, multe);
        }
        Graph.addSeries(puncte);
        FixGraph();

    }
    private  void FixGraph()
    {
        GraphView Graph = (GraphView) findViewById(R.id.PutGraph);
        final double Minx,Maxx,Miny,Maxy;
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

    private void SwitchHelpPut() {
        Intent intent_hput = new Intent(this,HPut.class);
        startActivity(intent_hput);
    }
}