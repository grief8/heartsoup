package com.example.heartsoup;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Button s = (Button) findViewById(R.id.solution);
        Button home = (Button) findViewById(R.id.home);
        Button goal = (Button) findViewById(R.id.goal);
        Button article = (Button) findViewById(R.id.article);
        Button eeg = (Button) findViewById(R.id.eeg);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home = new Intent();
                home.setClass(menu.this,home.class);
                startActivity(home);
            }
        });
        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("来玩个游戏吧~", 3000);
                Intent online = new Intent();
                online.setClass(menu.this,onlinehelp.class);
                startActivity(online);
            }
        });
        goal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent MainActivity=new Intent();
                MainActivity.setClass(menu.this,MainActivity.class);
                startActivity(MainActivity);
            }
        });
        article.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent article = new Intent();
                article.setClass(menu.this,article.class);
                startActivity(article);
            }
        });
        eeg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent eeg = new Intent();
//                eeg.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                ComponentName com = new ComponentName("com.android.Algo_SDK_Sample", "com.android.calendar.MainActivity");
//                eeg.setComponent(com);
//                eeg.setAction("android.intent.action.VIEW");
//                startActivity(eeg);
                PackageManager packageManager = getPackageManager();
                Intent intent=new Intent();
                intent = packageManager.getLaunchIntentForPackage("com.neurosky.algo_sdk_sample");
                if(intent==null){
                    Toast.makeText(menu.this, "未安装", Toast.LENGTH_LONG).show();
                }else{
                    startActivity(intent);
                }
//                Intent intent=new Intent();
//                intent.setComponent(new ComponentName("com.android.calendar", "com.android.calendar.LaunchActivity"));
//                startActivity(intent);
            }
        });
        TextView tip = (TextView) findViewById(R.id.textView14);
        String sm = "心理健康的标准\n" +
                "①有适度的安全感，有自尊心，对自我的成就有价值感。\n" +
                "②适度地自我批评，不过分夸耀自己也不过分苛责自己。\n" +
                "③在日常生活中，具有适度的主动性，不为环境所左右。\n" +
                "④理智，现实，客观，与现实有良好的接触，能容忍生活中挫折的打击，无过度的幻想。\n" +
                "⑤适度地接受个人的需要，并具有满足此种需要的能力。\n" +
                "⑥有自知之明，了解自己的动机和目的，能对自己的能力作客观的估计。\n" +
                "⑦能保持人格的完整与和谐，个人的价值观能适应社会的标准，对自己的工作能集中注意力。\n" +
                "⑧有切合实际的生活目标。\n" +
                "⑨具有从经验中学习的能力，能适应环境的需要改变自己。\n" +
                "⑩有良好的人际关系，有爱人的能力和被爱的能力。在不违背社会标准的前提下，能保持自己的个性，既不过分阿谀，也不过分寻求社会赞许，有个人独立的意见，有判断是非的标准";
        tip.setText(sm);
    }

    public void showToast(final String msg, final int timeStyle) {
        menu.this.runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(getApplicationContext(), msg, timeStyle).show();
            }

        });
    }
}
