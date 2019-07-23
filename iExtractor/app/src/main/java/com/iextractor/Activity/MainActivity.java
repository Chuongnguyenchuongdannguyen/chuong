package com.iextractor.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.iextractor.R;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
    }

    private void initUI() {
        RelativeLayout video_to_jpg_layout = findViewById(R.id.video_to_jpg_layout);
        video_to_jpg_layout.setOnClickListener(v -> startActivity(new Intent(MainActivity.this,ImportVideoActivity.class)));
    }
}
