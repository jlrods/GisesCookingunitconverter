package io.github.jlrods.gisescookingunitconverter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by rodjose1 on 18/07/2018.
 */

//Activity to handle the About app info
public class PreferencesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Call super method
        super.onCreate(savedInstanceState);
        //set fragment for preferences
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new PreferencesFragment())
                .commit();
    }// End of constructor method
}//End of PreferencesActivity class