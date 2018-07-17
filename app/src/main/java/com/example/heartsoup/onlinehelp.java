package com.example.heartsoup;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class onlinehelp extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onlinehelp);
        PackageManager packageManager = getPackageManager();
        Intent intent=new Intent();
        intent = packageManager.getLaunchIntentForPackage("com.neurosky.unitythinkgear");
        if(intent==null){
            Toast.makeText(onlinehelp.this, "未安装", Toast.LENGTH_LONG).show();
        }else{
            startActivity(intent);
        }
        Button game = (Button)findViewById(R.id.game);
        game.setVisibility(View.INVISIBLE);
//        game.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                PackageManager packageManager = getPackageManager();
//                Intent intent=new Intent();
//                intent = packageManager.getLaunchIntentForPackage("com.neurosky.unitythinkgear");
//                if(intent==null){
//                    Toast.makeText(onlinehelp.this, "未安装", Toast.LENGTH_LONG).show();
//                }else{
//                    startActivity(intent);
//                }
//
//            }
//        });
    }
}
