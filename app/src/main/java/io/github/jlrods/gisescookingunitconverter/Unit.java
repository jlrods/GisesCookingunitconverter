package io.github.jlrods.gisescookingunitconverter;

import android.util.Log;

/**
 * Created by rodjose1 on 21/05/2018.
 */

//Class to handle the Unit abstraction
public class Unit {
    //Unit class Attributes
    private int id;
    private String name;
    private String symbol;
    private boolean isReference;
    private Property property;
    private Unit_System system;

    //Constructors
    //Default constructor
    public Unit(){
        Log.d("Ent_DefaultConstUnit","Enter default constructor in Unit class.");
        this.name ="";
        this.symbol="";
        this.isReference = false;
        Log.d("Ext_DefaultConstUnit","Enxit default constructor in Unit class.");
    }//End of unit default constructor

    //Full constructor
    public Unit(int id,String name, String symbol, boolean isReference, Property property, Unit_System system){
        Log.d("Ent_FullConstUnit","Enter full constructor in Unit class.");
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.isReference = isReference;
        this.property = property;
        this.system = system;
        Log.d("Ext_FullConstUnit","Exit full constructor in Unit class.");
    }//End of unit full constructor

    //Getter and Setter methods

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() { return symbol; }

    public boolean isReference() {
        return isReference;
    }

    public Property getProperty() {
        return property;
    }

    public Unit_System getSystem() {
        return system;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setReference(boolean reference) {
        isReference = reference;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public void setSystem(Unit_System system) {
        this.system = system;
    }
}// End of Unit class
