package io.github.jlrods.gisescookingunitconverter;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by rodjose1 on 17/07/2018.
 */

//Activity to handle the About app info
public class AboutActivity extends Activity {
    @Override public void onCreate(Bundle savedInstanceState) {
        //call super method
        super.onCreate(savedInstanceState);
        //set the layout
        setContentView(R.layout.about);
    }//End of onCreate method
}//End of AboutActivity
