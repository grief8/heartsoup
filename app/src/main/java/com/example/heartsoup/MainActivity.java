package com.example.heartsoup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //TextView answer = (TextView) findViewById(R.id.textView24);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView t= (TextView) findViewById(R.id.textView);
        Button b= (Button) findViewById(R.id.button);
        Button b2= (Button) findViewById(R.id.b2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent satisfied = new Intent();
                satisfied.setClass(MainActivity.this,satisfied.class);
                startActivity(satisfied);
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent unsatisfied = new Intent();
                unsatisfied.setClass(MainActivity.this,unsatisfied.class);
                startActivity(unsatisfied);
            }
        });
    }

    String statisfied = "你很幸运，恭喜你";
    String unstatisfied = "你想改变吗？";
    String i_wanna = "想";
    String i_dont = "不想";
    String if_wanna = "或许，你的问题在于想得太多，而读书太少。";
    String if_not = "你不应该在奋斗的年纪选择安逸";
//    BinTree question = new BinTree();
//    question.createTree();
}
