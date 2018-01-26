package com.example.ehsun.higherorlower;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    static Integer random = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    protected void setRandom() {
        Random generator = new Random();
        random = generator.nextInt(20) + 1;
    }

    protected void guessNumber(View view) {
        EditText numberField = (EditText) findViewById(R.id.numberField);
        int guessed = Integer.parseInt(numberField.getText().toString());
        if (random == null) {
            setRandom();
        }
        if (guessed == random) {
            Toast.makeText(MainActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
            setRandom();
        } else if (random > guessed) {
            Toast.makeText(MainActivity.this, "Higher!", Toast.LENGTH_SHORT).show();
        } else if (random < guessed) {
            Toast.makeText(MainActivity.this, "Lower!", Toast.LENGTH_SHORT).show();
        }
    }

}
