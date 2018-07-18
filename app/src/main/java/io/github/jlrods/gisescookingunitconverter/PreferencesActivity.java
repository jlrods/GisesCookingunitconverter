package io.github.jlrods.gisescookingunitconverter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by rodjose1 on 18/07/2018.
 */

public class PreferencesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new PreferencesFragment())
                .commit();
    }
}