package com.ehsunhanif.eventsavvy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
    }

    // Method to handle going back to main screen
    public void back(View view) {
        Intent intent = new Intent(getApplicationContext(), ToolbarActivity.class);
        startActivity(intent);
    }
}
