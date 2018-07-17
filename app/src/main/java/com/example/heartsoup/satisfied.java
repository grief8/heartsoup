package com.example.heartsoup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class satisfied extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_satisfied);

         Button yes= (Button) findViewById(R.id.button6);
        Button no= (Button) findViewById(R.id.button7);
         Button many= (Button) findViewById(R.id.button4);
        Button less= (Button) findViewById(R.id.button8);
         Button inhv= (Button) findViewById(R.id.button5);
         Button ihv= (Button) findViewById(R.id.button9);
        final TextView xue = (TextView) findViewById(R.id.textView18);
        final TextView cun = (TextView) findViewById(R.id.textView19);
        final TextView friend = (TextView) findViewById(R.id.textView20);
        ihv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent frd = new Intent();
                frd.setClass(satisfied.this,friend.class);
                startActivity(frd);
            }
        });
        inhv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = "好好锻炼，好好打扮。外在太糟糕，就很少有人想了解你的内在的哦.";
                friend.setText(s);
            }
        });
        less.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s =  "滚去挣钱。明天是我们的!!";
                cun.setText(s);
            }
        });
        many.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s="人外有人，天外有天。脚踏实地才是正道嘛";
                cun.setText(s);
            }
        });
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s="其实，比你优秀的人比你更努力。\nElon Musk能连续15年每周工作100个小时以上.库克每天早上3点45分起床，一直工作到深夜。";
                xue.setText(s);
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = "滚去学习，想这么多干嘛？";
                xue.setText(s);
            }
        });
    }
}
