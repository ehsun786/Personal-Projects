package com.example.ehsun.currencyconvertor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    protected void convertDollars(View view) {
        EditText numberField = (EditText) findViewById(R.id.numberField);
        String dollars = numberField.getText().toString();
        double num = Double.parseDouble(dollars);
        DecimalFormat df = new DecimalFormat("###.##");
        double pounds = num * 0.74;
        Toast.makeText(MainActivity.this, "£" + df.format(pounds), Toast.LENGTH_LONG).show();
    }
}
