package io.github.jlrods.gisescookingunitconverter;

import android.util.Log;

import java.util.List;

/**
 * Created by rodjose1 on 21/05/2018.
 */

//Enum to define the possible properties to work with in this app
public enum Property {
    //Define the possible properties and assign an image from the drawable folder
     WEIGHT("Weight",R.drawable.weight),
     VOLUME("Volume",R.drawable.volume),
     TEMPERATURE("Temperature",R.drawable.temperature),
     LENGTH("Length",R.drawable.length),
     AREA("Area",R.drawable.area),
     TIME("Time",R.drawable.time);
     //Declare the attributes of each property enum item
    //One attribute to hold the property name
    private final String name;
    //One attribute to hold the image corresponding to it
    private final int imageId;
    
    //Constructor method for the enum class
    Property(String name, int imageId){
        Log.d("Ent_PropertyConstructor","Enter the constructor method of Property enum.");
        //Set the property name
        this.name = name;
        //Set the image id
        this.imageId = imageId;
        Log.d("Ext_PropertyConstructor","Exit the constructor method of Property enum.");
    }//End of constructor method
    
    //Getter methods
    //Method that returns specific enum object's name
    public String getName(){
        return this.name;
    }
    //Method that returns specific enum object's image id number
    public int getImageId(){
        return this.imageId;
    }
    
    //Method that returns a string array of property names
    public String[] getProperties(){
        Log.d("Ent_getProperties","Enter getProperties method in the Property enum.");
        //Creates an array to store the properties' names
        String[] properties = new String[Property.values().length];
        //Iterate through the list of properties defined on this enum
        for (Property property: Property.values()) {
            //Include the name of current property into the array
            properties[property.ordinal()]= property.getName();
        }
        Log.d("Ext_getProperties","Exit getProperties method in the Property enum.");
        //Return all the properties
        return properties;
    }//End of getProperties method

    //Find a porperty by receiving the ordinal
    public static Property findProperty(int ordinal){
        Log.d("Ent_findProperty","Enter findProperty method in the Property enum.");
        //Declare and isntantiate a property enum item with a default value: Weight
        Property property = Property.WEIGHT;
        //Check the current property's ordinal value and compare against the different ordinal values available to represent a property
        //When a match is found, assign the property corresponding to the matching ordinal
        switch (ordinal){
            case 0:
                property = Property.WEIGHT;
                break;
            case 1:
                property = Property.VOLUME;
                break;
            case 2:
                property = Property.TEMPERATURE;
                break;
            case 3:
                property = Property.LENGTH;
                break;
            case 4:
                property = Property.AREA;
                break;
            case 5:
                property = Property.TIME;
                break;
        }// End of switch statment
        Log.d("Ext_findProperty","Exit findProperty method in the Property enum.");
        return property;
    }// End of findProperty


    //Override the toStringMethod
    @Override
    public String toString() {
        //Return the name attribute
        return this.getName();
    }//End of toString method

    //Method to increase the property's ordinal value
    public int increaseOrdinal(){
        return this.ordinal()+1;
    }
}//End of Property enum
