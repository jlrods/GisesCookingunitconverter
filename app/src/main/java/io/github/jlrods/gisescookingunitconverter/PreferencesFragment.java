package io.github.jlrods.gisescookingunitconverter;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import io.github.jlrods.gisescookingunitconverter.R;

/**
 * Created by rodjose1 on 18/07/2018.
 */

public class PreferencesFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}