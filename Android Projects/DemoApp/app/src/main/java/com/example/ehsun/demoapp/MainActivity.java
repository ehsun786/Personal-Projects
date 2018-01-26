package com.example.ehsun.demoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static int x = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    protected void changeCat(View view) {
        ImageView imageView = (ImageView) findViewById(R.id.catImageView);
        if (x % 2 == 0) {
            imageView.setImageResource(R.drawable.cat2);
        } else {
            imageView.setImageResource(R.drawable.cat);
        }
        x++;
    }
}
