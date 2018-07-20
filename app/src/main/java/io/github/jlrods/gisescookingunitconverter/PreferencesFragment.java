package io.github.jlrods.gisescookingunitconverter;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import io.github.jlrods.gisescookingunitconverter.R;

/**
 * Created by rodjose1 on 18/07/2018.
 */

//Class to handle the fragment to be set into the PreferencesActivity
public class PreferencesFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        //Call the super method
        super.onCreate(savedInstanceState);
        //Set the layout
        addPreferencesFromResource(R.xml.preferences);
    }// End of constructor method
}// End of PreferencesFragment class