package com.sailstudios.apps.flutur_v0.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sailstudios.apps.flutur_v0.R;
import com.sailstudios.apps.flutur_v0.Utils;

public class Activity5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_5);

        final int position = getIntent().getIntExtra(Utils.PAGE_POSITION, 0);

        Button button=findViewById(R.id.activity5_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.saveHighScoreToPreferences(Activity5.this,position);
                finish();
            }
        });
    }
}
