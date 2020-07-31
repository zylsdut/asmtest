package com.dafasoft.asmtest;

import androidx.appcompat.app.AppCompatActivity;

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
        findViewById(R.id.test).setOnClickListener(v -> {
            Log.d("zyl", "onCLick");
        });

        TestModel model = new TestModel();
        model.setListener(view -> {

        });
    }



    @Override
    public void onClick(View v) {
        Log.d("zyl", "距离上次点击 =" + (System.currentTimeMillis() - lastTime));
        Log.d("zyl", "onCLick");
        lastTime = System.currentTimeMillis();
    }
}
