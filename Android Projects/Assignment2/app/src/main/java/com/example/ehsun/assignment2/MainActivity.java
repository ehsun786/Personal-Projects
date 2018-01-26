package com.example.ehsun.assignment2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    static int i = 0;
    protected void clickFunction(View view) {
        Button button = (Button)findViewById(R.id.hello);

        int x = i % 2;
        if(x==0) {
            button.setText("It is even!");
        } else {
            button.setText("It is odd!");
        }
        i++;
        Log.i("Info","Hi There! How may I help you?");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
