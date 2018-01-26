package com.example.ehsun.showingandhidinguielements;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void hide(View view) {
        TextView myTextView = (TextView) findViewById(R.id.myTextView);
        myTextView.setVisibility(View.GONE);
    }

    public void show(View view) {
        TextView myTextView = (TextView) findViewById(R.id.myTextView);
        myTextView.setVisibility(View.VISIBLE);
    }

}
