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

public class ConversionsAdapter extends CursorAdapter {
    public ConversionsAdapter(Context context, Cursor c,int flags) {
        super(context, c, flags);
        Log.d("Adapter_Constructor","Leaves ConversionsAdapter constructor ");
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_spinner,parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        Log.d("Ent_Populate_Spinners","Enters method to populate spinners. ConversionsAdapter.bindView.");
        TextView tvUnit = (TextView) view.findViewById(R.id.tvUnit);
        //TextView tvSymb= (TextView) view.findViewById(R.id.tvSymbol);
        String unitText = cursor.getString(cursor.getColumnIndexOrThrow("Name"));
        String unitSymbol = "("+cursor.getString(cursor.getColumnIndexOrThrow("Symbol"))+")";
        //Declare s Span object and point to null
        SpannableStringBuilder superScriptString = null;
        if (unitSymbol.contains("^")){
            //Find the index of the ^ symbol
            int index = unitSymbol.indexOf("^");
            //Redefine the result string by concatenating with the modified symbol string
            unitText = unitText + " " + unitSymbol.substring(0,index) + unitSymbol.substring(index+1,unitSymbol.length());
            //Instantiate the the span text
            superScriptString = new SpannableStringBuilder(unitText);
            //Include the superscript format to the spannable text
            superScriptString.setSpan(new SuperscriptSpan(), unitText.length()-2, unitText.length()-1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            superScriptString.setSpan(new RelativeSizeSpan(0.75f), unitText.length()-2, unitText.length()-1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }else {
            superScriptString= new SpannableStringBuilder (unitText+ " " + unitSymbol);
        }
        tvUnit.setText(superScriptString);
        //tvSymb.setText(unitSymbol);
        Log.d("Ext_Pop_Spinners","Exit method to populate spinners. ConversionsAdapter.bindView.");
    }


    //Method to get Cursor with a list of Units based on the property required
}
