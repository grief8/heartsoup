package com.example.heartsoup;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class unsatisfied extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unsatisfied);
        Button sat = (Button) findViewById(R.id.button2);
        Button uns = (Button) findViewById(R.id.button3);
        final TextView te = (TextView) findViewById(R.id.textView3);
        sat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
         String sat ="或许，你的问题在于想得太多，而读书太少。";
                 te.setText(sat);

            }
        });
       uns.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String uns="你不应该在奋斗的年纪选择安逸";
               te.setText(uns);
           }
       });

    }

}
