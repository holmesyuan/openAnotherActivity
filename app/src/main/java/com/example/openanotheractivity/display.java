package com.example.openanotheractivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

public class display extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        Intent intent_wait = getIntent();
        String message = intent_wait.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView textView = new TextView(this);
        textView.setText(message);
        textView.setTextSize(50);

        ViewGroup layout = (ViewGroup)findViewById(R.id.activity_display);
        layout.addView(textView);
    }
}
