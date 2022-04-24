package com.example.myfirstapp;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ContentActivity extends AppCompatActivity {

    private int n = 0;
    private TextView counterView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        counterView = (TextView) findViewById(R.id.counter_id);
    }

    public void countInc(View view) {
        n++;
        if (counterView != null)
            counterView.setText(Integer.toString(n));
    }

    public void showTaeus(View view) {
        Toast toast = Toast.makeText(this, R.string.button_label, Toast.LENGTH_LONG);
        toast.show();
    }
}