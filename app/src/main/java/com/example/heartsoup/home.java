package com.example.heartsoup;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ImageButton yi = (ImageButton) findViewById(R.id.imageButton2);
        ImageButton tie = (ImageButton) findViewById(R.id.imageButton3);
        ImageButton zixun = (ImageButton) findViewById(R.id.imageButton5);
        ImageButton i525 = (ImageButton) findViewById(R.id.imageButton6);
        ImageButton xjtu = (ImageButton) findViewById(R.id.imageButton7);
        xjtu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://zq.xjtu.edu.cn/xinliv5/");
                Intent it= new Intent(Intent.ACTION_VIEW,uri);
                startActivity(it);
            }
        });
        i525.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://www.psy525.cn/525");
                Intent it= new Intent(Intent.ACTION_VIEW,uri);
                startActivity(it);
            }
        });
        zixun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://www.xlzx.com/Index.html");
                Intent it= new Intent(Intent.ACTION_VIEW,uri);
                startActivity(it);
            }
        });
        tie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://tieba.baidu.com/f?kw=%D0%C4%C0%ED&fr=ala0&loc=rec&red_tag=1814803522&pn=0&");
                Intent it= new Intent(Intent.ACTION_VIEW,uri);
                startActivity(it);
            }
        });
        yi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://www.xinli001.com/");
                Intent it= new Intent(Intent.ACTION_VIEW,uri);
                startActivity(it);
            }
        });
    }
}
