package com.example.android.musicalapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PlayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        String title = getIntent().getStringExtra("title");
        String subtitle = getIntent().getStringExtra("artist") + " - " + getIntent().getStringExtra("album");

        TextView titleTv = findViewById(R.id.player_title);
        titleTv.setText(title);
        TextView subtitleTv = findViewById(R.id.player_subtitle);
        subtitleTv.setText(subtitle);
    }

    public void goBack(View view){
        finish();
    }
}
