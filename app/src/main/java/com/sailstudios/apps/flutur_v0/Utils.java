package com.sailstudios.apps.flutur_v0;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.sailstudios.apps.flutur_v0.Activities.Activity1;

public class Utils {

    public static final String PAGE_POSITION = "page_position";
    public static final String HIGH_SCORE = "high_score";

    public static void saveHighScoreToPreferences(Context context,int position){

        int highscore=getHighScoreFromPreferences(context);
        if(position>=highscore||position==-1) {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt(HIGH_SCORE, position + 1);
            editor.apply();
        }
    }

    public static int getHighScoreFromPreferences(Context context){

        SharedPreferences preferences=PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getInt(HIGH_SCORE,0);
    }

}
