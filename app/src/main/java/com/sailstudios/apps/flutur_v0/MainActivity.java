package com.sailstudios.apps.flutur_v0;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private PagerAdapter adapter;
    private TextView highscoreTV, levelTV, resetTV;
    private ProgressBar progressBar;
    private int highscore;
    private SharedPreferences preferences;
    private SharedPreferences.OnSharedPreferenceChangeListener prefListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.view_pager);
        highscoreTV = findViewById(R.id.highscore_tv);
        levelTV = findViewById(R.id.level_tv);
        resetTV = findViewById(R.id.reset_tv);
        progressBar = findViewById(R.id.progressBar);

        viewPager.setClipToPadding(false);
        viewPager.setPageMargin(36);

        adapter = new PagerAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(adapter);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        prefListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                if (key.equals(Utils.HIGH_SCORE)) {
                    updateHighScore();
                }
            }
        };

        preferences.registerOnSharedPreferenceChangeListener(prefListener);

        resetTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.saveHighScoreToPreferences(MainActivity.this, -1);
                updateHighScore();
            }
        });

        updateHighScore();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        preferences.unregisterOnSharedPreferenceChangeListener(prefListener);
    }

    void updateHighScore() {

        highscore = Utils.getHighScoreFromPreferences(this);

        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            public void run() {
                viewPager.setCurrentItem(highscore, true);
                if (Build.VERSION.SDK_INT > 24)
                    progressBar.setProgress(highscore, true);
                else
                    progressBar.setProgress(highscore);
                highscoreTV.setText("High Score: " + highscore);
                if (highscore == 10)
                    levelTV.setText("Level: " + highscore);
                else
                    levelTV.setText("Level: " + (highscore + 1));
            }
        }, 700);
    }
}
