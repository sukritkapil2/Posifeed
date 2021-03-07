package com.posifeed.posifeed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button enterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enterButton = findViewById(R.id.enterButton);

        SharedPreferences sharedPreferences = getSharedPreferences("SharedPrefs", Context.MODE_PRIVATE);
        Boolean firstTime = sharedPreferences.getBoolean("firstTime", true);

        if(!firstTime) {
            Intent enterFeedIntent = new Intent(MainActivity.this, FeedListActivity.class);
            startActivity(enterFeedIntent);
        }

        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putBoolean("firstTime", false);
                myEdit.commit();

                Intent enterFeedIntent = new Intent(MainActivity.this, FeedListActivity.class);
                startActivity(enterFeedIntent);
            }
        });
    }
}