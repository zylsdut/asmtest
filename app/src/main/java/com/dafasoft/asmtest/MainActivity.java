package com.dafasoft.asmtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*findViewById(R.id.test).setOnClickListener(v -> {
            Log.d("zyl", "onCLick");
        });*/
        findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ReClickHelper.clickEnable()) {
                    return;
                }
                Log.d("zyl", "onCLick");
            }
        });
    }

    public void
}
