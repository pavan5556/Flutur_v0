package com.sailstudios.apps.flutur_v0;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.sailstudios.apps.flutur_v0.Activities.Activity1;
import com.sailstudios.apps.flutur_v0.Activities.Activity10;
import com.sailstudios.apps.flutur_v0.Activities.Activity2;
import com.sailstudios.apps.flutur_v0.Activities.Activity3;
import com.sailstudios.apps.flutur_v0.Activities.Activity4;
import com.sailstudios.apps.flutur_v0.Activities.Activity5;
import com.sailstudios.apps.flutur_v0.Activities.Activity6;
import com.sailstudios.apps.flutur_v0.Activities.Activity7;
import com.sailstudios.apps.flutur_v0.Activities.Activity8;
import com.sailstudios.apps.flutur_v0.Activities.Activity9;

public class PageFragment extends Fragment implements View.OnClickListener {

    private int position;
    private SharedPreferences preferences;
    private SharedPreferences.OnSharedPreferenceChangeListener prefListener;
    private Button button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_page, container, false);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle args = getArguments();
        position = args.getInt(Utils.PAGE_POSITION);

        TextView tv = view.findViewById(R.id.position_tv);
        tv.setText(Integer.toString(position + 1));

        button = view.findViewById(R.id.enter_activity_button);
        button.setOnClickListener(this);

       setStatusForEntryButton();

        preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        prefListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                if (key.equals(Utils.HIGH_SCORE)) {
                    setStatusForEntryButton();
                }
            }
        };

        preferences.registerOnSharedPreferenceChangeListener(prefListener);

    }

    void setStatusForEntryButton(){
        final int highscore = Utils.getHighScoreFromPreferences(getActivity());
        if (position > highscore)
            button.setEnabled(false);
        else
            button.setEnabled(true);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
     preferences.unregisterOnSharedPreferenceChangeListener(prefListener);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.enter_activity_button) {
            Intent intent = new Intent();
            switch (position) {

                case 0:
                    intent = new Intent(getActivity(), Activity1.class);
                    break;
                case 1:
                    intent = new Intent(getActivity(), Activity2.class);
                    break;
                case 2:
                    intent = new Intent(getActivity(), Activity3.class);
                    break;

                case 3:
                    intent = new Intent(getActivity(), Activity4.class);
                    break;
                case 4:
                    intent = new Intent(getActivity(), Activity5.class);
                    break;
                case 5:
                    intent = new Intent(getActivity(), Activity6.class);
                    break;

                case 6:
                    intent = new Intent(getActivity(), Activity7.class);
                    break;
                case 7:
                    intent = new Intent(getActivity(), Activity8.class);
                    break;
                case 8:
                    intent = new Intent(getActivity(), Activity9.class);
                    break;

                case 9:
                    intent = new Intent(getActivity(), Activity10.class);
                    break;
            }
            if (intent != null) {
                intent.putExtra(Utils.PAGE_POSITION, position);

                startActivity(intent);
            }
        }
    }
}


