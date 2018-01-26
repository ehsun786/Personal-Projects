package com.example.ehsun.toastdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    protected void clickFunction(View view) {
        EditText name = (EditText) findViewById(R.id.name);
        Log.i("Name", name.getText().toString());
        Toast.makeText(MainActivity.this, "Hi there, " + name.getText().toString() + "!", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
