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

public class AlegeGraficFunctieElem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alege);
        //go back
        Button GoBackButton = (Button) findViewById(R.id.GoBackButton);
        GoBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImDone();
            }
        });

        //liniara (=lin)
        Button SwitchLinButton = (Button) findViewById(R.id.switchliniara);
        SwitchLinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SwitchLin();
            }
        });
        //putere (=put)
        Button SwitchPutButton = (Button) findViewById(R.id.switchputere);

        SpannableStringBuilder cs = new SpannableStringBuilder("F(X)=XA");
        cs.setSpan(new SuperscriptSpan(), 6, 7, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        cs.setSpan(new RelativeSizeSpan(0.75f), 6, 7, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        SwitchPutButton.setText(cs);

        SwitchPutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SwitchPut();
            }
        });
        //logaritm (=log)
        Button SwitchLogButton = (Button) findViewById(R.id.switchlogaritm);

        cs = new SpannableStringBuilder("F(X)=LOGAX");
        cs.setSpan(new SubscriptSpan(), 8, 9, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        cs.setSpan(new RelativeSizeSpan(0.75f), 8, 9, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        SwitchLogButton.setText(cs);

        SwitchLogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SwitchLog();
            }
        });
        //exponentiala (=exp)
        Button SwitchExpButton = (Button) findViewById(R.id.switchexponentiala);

        cs = new SpannableStringBuilder("F(X)=AX");
        cs.setSpan(new SuperscriptSpan(), 6, 7, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        cs.setSpan(new RelativeSizeSpan(0.75f), 6, 7, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        SwitchExpButton.setText(cs);

        SwitchExpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SwitchExp();
            }
        });
        //sin
        Button SwitchSinButton = (Button) findViewById(R.id.switchsin);
        SwitchSinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SwitchSin();
            }
        });
        //cos
        Button SwitchCosButton = (Button) findViewById(R.id.switchcos);
        SwitchCosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SwitchCos();
            }
        });
        //tg
        Button SwitchTgButton = (Button) findViewById(R.id.switchtg);
        SwitchTgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SwitchTg();
            }
        });
        //ctg
        Button SwitchCtgButton = (Button) findViewById(R.id.switchctg);
        SwitchCtgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SwitchCtg();
            }
        });
    }

    private void SwitchLin() {
        Intent intent_lin = new Intent(this, Lin.class);
        startActivity(intent_lin);
    }

    private void SwitchPut() {
        Intent intent_put = new Intent(this, Put.class);
        startActivity(intent_put);
    }

    private void SwitchLog() {
        Intent intent_log = new Intent(this, Log.class);
        startActivity(intent_log);
    }

    private void SwitchExp() {
        Intent intent_exp = new Intent(this, Exp.class);
        startActivity(intent_exp);
    }

    private void SwitchSin() {
        Intent intent_sin = new Intent(this, Sin.class);
        startActivity(intent_sin);
    }

    private void SwitchCos() {
        Intent intent_cos = new Intent(this, Cos.class);
        startActivity(intent_cos);
    }

    private void SwitchTg() {
        Intent intent_tg = new Intent(this, Tg.class);
        startActivity(intent_tg);
    }

    private void SwitchCtg() {
        Intent intent_ctg = new Intent(this, Ctg.class);
        startActivity(intent_ctg);
    }

    private void ImDone() {
        finish();
    }
}