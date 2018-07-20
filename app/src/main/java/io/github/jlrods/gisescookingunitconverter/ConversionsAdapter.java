package io.github.jlrods.gisescookingunitconverter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.RelativeSizeSpan;
import android.text.style.SuperscriptSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by rodjose1 on 04/06/2018.
 */

//Class to handle the adapter objects to link spinners UI and data
public class ConversionsAdapter extends CursorAdapter {
    //Adapter constructor method
    public ConversionsAdapter(Context context, Cursor c,int flags) {
        super(context, c, flags);
        Log.d("Adapter_Constructor","Leaves ConversionsAdapter constructor ");
    }//End of constructor method

    //Method to create a new view
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_spinner,parent,false);
    }

    //Method to bind the view and the data via a cursor
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        Log.d("Ent_Populate_Spinners","Enter bindView method to populate spinners in ConversionsAdapter class.");
        //Declare and instantiate a TextView object to hold the unit name and symbol
        TextView tvUnit = (TextView) view.findViewById(R.id.tvUnit);
        //Declare and instantiate a String to hold the name by stracting data from cursor, where the column name is Name
        String unitText = cursor.getString(cursor.getColumnIndexOrThrow("Name"));
        //Declare and instantiate a String to hold the name by stracting data from cursor, where the column name is Symbol
        String unitSymbol = "("+cursor.getString(cursor.getColumnIndexOrThrow("Symbol"))+")";
        //Declare s Span object and point to null. This span object will allow to use superscript format for power expressions
        SpannableStringBuilder superScriptString = null;
        //Check if the symbol string contains the ^ symbol to represent exponential values
        if (unitSymbol.contains("^")){
            //If it does have it, find the index of the ^ symbol within the symbol string
            int index = unitSymbol.indexOf("^");
            //Redefine the result string by concatenating with the modified symbol string
            //The new result is made of the old result value, a blank space, and the modified symbol string
            unitText = unitText + " " + unitSymbol.substring(0,index) + unitSymbol.substring(index+1,unitSymbol.length());
            //Instantiate the the span text for formating the String that hold the final result text
            superScriptString = new SpannableStringBuilder(unitText);
            //Include the superscript format to the spannable text which will be applicable to the result text
            superScriptString.setSpan(new SuperscriptSpan(), unitText.length()-2, unitText.length()-1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            superScriptString.setSpan(new RelativeSizeSpan(0.75f), unitText.length()-2, unitText.length()-1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }else {
            //If the ^ symbol is not present in the symbol string, just declare and instantiate the span object
            superScriptString= new SpannableStringBuilder (unitText+ " " + unitSymbol);
        }//End of if else statement that checks if the ^ is present in the symbol text
        //set the text of the TextView with the final result text, which is formatted to show superscipt text
        tvUnit.setText(superScriptString);
        Log.d("Ext_Pop_Spinners","Exit bindView method to populate spinners in ConversionsAdapter class.");
    }


    //Method to get Cursor with a list of Units based on the property required
}
