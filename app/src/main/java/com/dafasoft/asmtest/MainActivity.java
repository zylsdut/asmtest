package com.dafasoft.asmtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private long lastTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        asdf();
    }

    private void asdf() {
        findViewById(R.id.test1).setOnClickListener(v -> {
            long current = System.currentTimeMillis();
            long interval = current - lastTime;
            Log.d("zyl", "test1 点击间隔 ： " + interval);
            lastTime = current;
        });

        findViewById(R.id.test2).setOnClickListener(v -> {
            long current = System.currentTimeMillis();
            long interval = current - lastTime;
            Log.d("zyl", "test2 点击间隔 ： " + interval);
            lastTime = current;
        });

        findViewById(R.id.test3).setOnClickListener(v -> {
            long current = System.currentTimeMillis();
            long interval = current - lastTime;
            Log.d("zyl", "test3 点击间隔 ： " + interval);
            lastTime = current;
        });

        /*findViewById(R.id.test2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long current = System.currentTimeMillis();
                long interval = current - lastTime;
                Log.d("zyl", "test2 点击间隔 ： " + interval);
                lastTime = current;
            }
        });
        findViewById(R.id.test3).setOnClickListener(this);*/
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.test3:
                long current = System.currentTimeMillis();
                long interval = current - lastTime;
                Log.d("zyl", "test3 点击间隔 ： " + interval);
                lastTime = current;
                break;

                default:
                    break;
        }
    }
}
