package com.example.ehsun.numbertype;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.jetbrains.annotations.Contract;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void makeToast(String text) {
        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
    }

    protected void checkNumber(View view) {
        EditText numberField = (EditText) findViewById(R.id.numberField);
        long number = Long.parseLong(numberField.getText().toString());
        if (isTriangularNumber(number) && checkIfSquare(number)) {
            makeToast("Triangular and Square");
        } else if (isTriangularNumber(number)) {
            makeToast("Triangular Only");
        } else if (checkIfSquare(number)) {
            makeToast("Square Only");
        } else {
            makeToast("Neither");
        }
    }

    // Returns true if 'num' is triangular, else false
    @Contract(pure = true)
    private boolean isTriangularNumber(long num) {
        // Base case
        if (num < 0) {
            return false;
        }
        int sum = 0;
        for (int n = 1; sum <= num; n++) {
            sum = sum + n;
            if (sum == num)
                return true;
        }
        return false;
    }



    private static boolean checkIfSquare(long number) {
        long sqrt = (long) Math.sqrt(number);
        if (sqrt * sqrt == number) {
            return true;
        } else {
            return false;
        }

    }


}
