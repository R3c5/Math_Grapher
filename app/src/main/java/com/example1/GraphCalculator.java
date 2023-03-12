package com.example1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.ValidationResult;
import net.objecthunter.exp4j.function.Function;

public class GraphCalculator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_calculator);
        Button Start = (Button) findViewById(R.id.ComputeAllGraphButton);
        Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                and_so_it_begins();
            }
        });
        Button Help = (Button) findViewById(R.id.help_graph);
        Help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch_help();
            }
        });
        Button Go_back = (Button) findViewById(R.id.inapoi_graph);
        Go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImDone();
            }
        });
    }

    private void ImDone() {
        finish();
    }

    private void switch_help() {
        Intent intent_hgraph = new Intent(this, Hgraph.class);
        startActivity(intent_hgraph);
    }

    Function log_natural = new Function("ln", 1) {

        @Override
        public double apply(double... x) {
            return Math.log(x[0]);
        }
    };
    Function tangenta = new Function("tg", 1) {

        @Override
        public double apply(double... x) {
            return Math.tan(x[0]);
        }
    };
    Function cotangenta = new Function("ctg", 1) {

        @Override
        public double apply(double... x) {
            return 1 / Math.tan(x[0]);
        }
    };
    Function cotangentaeng = new Function("ctan", 1) {

        @Override
        public double apply(double... x) {
            return 1 / Math.tan(x[0]);
        }
    };
    Function logb = new Function("logb", 2) {
        //log baza b din x
        @Override
        public double apply(double... args) {
            return Math.log(args[0]) / Math.log(args[1]);
        }
    };
    Function arctangenta = new Function("arctg", 1) {

        @Override
        public double apply(double... x) {
            return Math.atan(x[0]);
        }
    };
    Function arccotangenta = new Function("arcctg", 1) {

        @Override
        public double apply(double... x) {
            return Math.atan(1 / x[0]);
        }
    };
    Function arccotangentaeng = new Function("actan", 1) {

        @Override
        public double apply(double... x) {
            return Math.atan(1 / x[0]);
        }
    };
    Function arcsin = new Function("arcsin", 1) {

        @Override
        public double apply(double... x) {
            return Math.asin(x[0]);
        }
    };
    Function arccos = new Function("arccos", 1) {

        @Override
        public double apply(double... x) {
            return Math.acos(x[0]);
        }
    };

    private void and_so_it_begins() {

        EditText Input = (EditText) findViewById(R.id.InputFunction);
        String InputStr = Input.getText().toString();
        if (InputStr.isEmpty()) {
            input_gresit();
            return;
        }
        // System.out.println(InputStr);
        ExpressionBuilder BobTheBuilder = new ExpressionBuilder(InputStr).variable("x");

        BobTheBuilder.function(log_natural);
        BobTheBuilder.function(logb);
        BobTheBuilder.function(tangenta);
        BobTheBuilder.function(cotangenta);
        BobTheBuilder.function(cotangentaeng);
        BobTheBuilder.function(arctangenta);
        BobTheBuilder.function(arccotangenta);
        BobTheBuilder.function(arccotangentaeng);
        BobTheBuilder.function(arcsin);
        BobTheBuilder.function(arccos);

        Expression exp;
        try {
            exp = BobTheBuilder.build();
        } catch (Exception e) {
            input_gresit();
            return;
        }
        GraphView Graph = (GraphView) findViewById(R.id.AllGraph);
        Graph.removeAllSeries();
        final double start = -100;
        final double end = 100;
        final int multe = 1000000000;
        final double pas = 0.001;
        double dif = end - start + 2;
        dif /= pas;
        int pct_cnt = (int) dif;
        double x, y;
        x = start - pas;
        LineGraphSeries<DataPoint> puncte = new LineGraphSeries<>();
        double anterior = 0;
        for (int i = 0; i < pct_cnt; i++) {
            exp.setVariable("x", x);
            y = exp.evaluate();
            if (anterior * y < 0 && Math.abs(y - anterior) > 2) {
                Graph.addSeries(puncte);
                puncte = new LineGraphSeries<>();
            }
            anterior = y;
            x += pas;
            puncte.appendData(new DataPoint(x, y), true, multe);
        }
        Graph.addSeries(puncte);
        FixGraph();
    }

    private void input_gresit() {
        Toast.makeText(GraphCalculator.this, "Input invalid",
                Toast.LENGTH_SHORT).show();
    }

    private void FixGraph() {
        GraphView Graph = (GraphView) findViewById(R.id.AllGraph);
        final double Minx, Maxx, Miny, Maxy;
        Minx = -10;
        Maxx = 10;
        Miny = -10;
        Maxy = 10;
        //Graph.getViewport().setYAxisBoundsManual(true);

        Graph.getViewport().setYAxisBoundsManual(true);
        Graph.getViewport().setXAxisBoundsManual(true);
        Graph.getViewport().setMinX(Minx);
        Graph.getViewport().setMaxX(Maxx);
        Graph.getViewport().setMinY(Miny);
        Graph.getViewport().setMaxY(Maxy);
        Graph.getViewport().setScalable(true);
        Graph.getViewport().setScrollable(true);
        Graph.getViewport().setScalableY(true);
        Graph.getViewport().setScrollableY(true);
    }
}