package com.dafasoft.asmtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, TestModel.TestListener {


    private long lastTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        asdf();
    }

    private void asdf() {
        TestModel model = new TestModel();
        findViewById(R.id.test).setOnClickListener(v -> {
            this.startActivity(new Intent());
        });

        findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    private void setData() {
        findViewById(R.id.test).setOnClickListener(v4 -> {
        });
    }
}
