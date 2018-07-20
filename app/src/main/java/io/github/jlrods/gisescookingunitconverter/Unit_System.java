package io.github.jlrods.gisescookingunitconverter;

import android.util.Log;

/**
 * Created by rodjose1 on 21/05/2018.
 */

// Enum to define the multiple systems available on this app
public enum Unit_System {
    //Define the possible systems in this app
    INTERNATIONAL_SYSTEM("International System","SI"),
    IMPERIAL_SYSTEM("Imperial System",""),
    KITCHEN_SYSTEM("Kitchen System","KS");
    //Declare the system's attributes
    private String name;
    private String abbrevaiation;

    //System constructor
    Unit_System(String name,String abbrevaiation){
        this.name = name;
        this.abbrevaiation = abbrevaiation;
    }//End of constructor

    //Getter methods
    //Method that returns the Unit system' name
    public String getName(){
        return this.name;
    }

    //Method that returns the Unit system' abbreviation
    public String getAbbrevaiation(){
        return this.abbrevaiation;
    }

    //Method that returns a full list of the systems available on the app
    public String[] getSystems(){
        Log.d("Ent_getSystems","Enter getSystems method in the Unit_System enum.");
        //Creates an array to store the systems' names
        String[] systems = new String[Unit_System.values().length];
        //Iterate through the list of systems defined on this enum
        for (Unit_System system: Unit_System.values()) {
            //Include the name of current system into the system array
            systems[system.ordinal()] = system.getName();
        }//End of for each loop
        Log.d("Ext_getSystems","Exit getSystems method in the Unit_System enum.");
        return systems;
    }//End of getSystems method

    //Method to increase the System ordinal
    public int increaseOrdinal(){
        return this.ordinal()+1;
    }

    //Find a system by receiving the ordinal
    public static Unit_System findSystem(int ordinal){
        Log.d("Ent_findSystem","Ent findSystem method in the Unit_System enum.");
        //Declare and instantiate Unit_System object to be returned by method
        Unit_System system = Unit_System.INTERNATIONAL_SYSTEM;
        //Check the ordinal against the possible ordinals corresponding to each enum item
        switch (ordinal){
            case 0:
                system = Unit_System.INTERNATIONAL_SYSTEM;
                break;
            case 1:
                system = Unit_System.IMPERIAL_SYSTEM;
                break;
            case 2:
                system = Unit_System.KITCHEN_SYSTEM;
                break;
        }//End of switch statement
        Log.d("Ent_findSystem","Ent findSystem method in the Unit_System enum.");
        return system;
    }// End of findProperty
}//End of Unit_System enum
