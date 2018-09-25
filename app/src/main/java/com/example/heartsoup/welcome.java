package com.example.heartsoup;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class welcome extends AppCompatActivity {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private Animation animation;
    private ImageView wel_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);

        animation = AnimationUtils.loadAnimation(this, R.anim.alpha_anim);
        wel_img = (ImageView) findViewById(R.id.imageView3);
        wel_img.startAnimation(animation);
        TextView wel = (TextView) findViewById(R.id.textView10);
        String we="你知道吗？心理亚健康已经成为一种常态。\n 据调查，95%的人都有不同程度的心理亚健康。\n"+
                "症状："+
                "1.记忆力明显下降\n2.反应迟钝\n3.抑郁\n4.强迫\n5.烦躁不安\n6.心神不宁7.强烈的嫉妒心\n8.恐惧心理";
        wel.setText(we);
        wel.startAnimation(animation);
        wel_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent welcome = new Intent(welcome.this,menu.class);
                jumpToNextActivity(welcome);
            }
        });
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent welcome = new Intent(welcome.this,menu.class);
                jumpToNextActivity(welcome);
            }
        },2000);

    }

    private void jumpToNextActivity(Intent intent){
        startActivity(intent);
        welcome.this.finish();
    }
//    @Override
//    protected void onPostCreate(Bundle savedInstanceState) {
//        super.onPostCreate(savedInstanceState);
//
//        // Trigger the initial hide() shortly after the activity has been
//        // created, to briefly hint to the user that UI controls
//        // are available.
//        delayedHide(100);
//    }
//
//    private void toggle() {
//        if (mVisible) {
//            hide();
//        } else {
//            show();

}
