package com.example.heartsoup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class friend extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);
        Button yes = (Button) findViewById(R.id.button10);
        Button no = (Button) findViewById(R.id.button11);
        final TextView frd = (TextView) findViewById(R.id.textView22);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = "最好的爱情，是势均力敌。把握平衡，努力付出，努力守候，祝福你们长久";
                frd.setText(s);
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s="永远不要轻易放弃，你不会知道，一旦错过，会留下多久的遗憾";
                frd.setText(s);
            }
        });
    }
}
