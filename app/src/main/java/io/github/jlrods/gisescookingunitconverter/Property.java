package io.github.jlrods.gisescookingunitconverter;

import java.util.List;

/**
 * Created by rodjose1 on 21/05/2018.
 */

public enum Property {
     WEIGHT("Weight",R.drawable.weight),
     VOLUME("Volume",R.drawable.volume),
     TEMPERATURE("Temperature",R.drawable.temperature),
     LENGTH("Length",R.drawable.length),
     AREA("Area",R.drawable.area),
     TIME("Time",R.drawable.time);
    private final String name;
    private final int imageId;
    
    //Constructor method for the enum class
    Property(String name, int imageId){
        this.name = name;
        this.imageId = imageId;
    }
    
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
        //Creates an array to store the properties' names
        String[] properties = new String[Property.values().length];
        //Iterate through the list of properties defined on this enum
        for (Property property: Property.values()) {
            //Include the name of current property into the array
            properties[property.ordinal()]= property.getName();
        }
        return properties;
    }

    //Find a porperty by receiving the ordinal
    public static Property findProperty(int ordinal){
        Property property = Property.WEIGHT;
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
        }
        return property;
    }// End of findProperty

    @Override
    public String toString() {

        return this.getName();
    }

    public int increaseOrdinal(){
        return this.ordinal()+1;
    }
}
