package io.github.jlrods.gisescookingunitconverter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
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
        TextView tvSymb= (TextView) view.findViewById(R.id.tvSymbol);
        String unitText = cursor.getString(cursor.getColumnIndexOrThrow("Name"));
        String unitSymbol = "("+cursor.getString(cursor.getColumnIndexOrThrow("Symbol"))+")";
        tvUnit.setText(unitText);
        tvSymb.setText(unitSymbol);
        Log.d("Ext_Pop_Spinners","Exit method to populate spinners. ConversionsAdapter.bindView.");
    }


    //Method to get Cursor with a list of Units based on the property required
}
