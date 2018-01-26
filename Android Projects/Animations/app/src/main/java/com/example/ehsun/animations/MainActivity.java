package com.example.ehsun.animations;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView bart = (ImageView) findViewById(R.id.bart);
        bart.setTranslationX(-1000f);
        bart.setTranslationY(-1000f);
    }

    public void fade(View view) {
        ImageView bart = (ImageView) findViewById(R.id.bart);
       ImageView homer = (ImageView) findViewById(R.id.homer);
//        if (homer.getAlpha() == 0f) {
//            bart.animate().alpha(0f).setDuration(2000);
//            homer.animate().alpha(1f).setDuration(2000);
//        } else {
//            homer.animate().alpha(0f).setDuration(2000);
//            bart.animate().alpha(1f).setDuration(2000);
//        }
        bart.animate()
                .translationXBy(1000f)
                .translationYBy(1000f)
                .rotationBy(3600)
                .scaleX(0.5f)
                .scaleY(0.5f)
                .setDuration(2000);

        homer.animate().alpha(0f).setDuration(2000);
    }
}
