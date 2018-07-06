package io.github.jlrods.gisescookingunitconverter;

/**
 * Created by rodjose1 on 21/05/2018.
 */

// Enum to define the multiple systems available on this app
public enum Unit_System {
    INTERNATIONAL_SYSTEM("International System","SI"),
    IMPERIAL_SYSTEM("Imperial System",""),
    KITCHEN_SYSTEM("Kitchen System","KS");
    private String name;
    private String abbrevaiation;

    Unit_System(String name,String abbrevaiation){
        this.name = name;
        this.abbrevaiation = abbrevaiation;
    }

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
        //Creates an array to store the systems' names
        String[] systems = new String[Unit_System.values().length];
        //Iterate through the list of systems defined on this enum
        for (Unit_System system: Unit_System.values()) {
            //Include the name of current system into the system array
            systems[system.ordinal()] = system.getName();
        }
        return systems;
    }

    public int increaseOrdinal(){
        return this.ordinal()+1;
    }

    //Find a system by receiving the ordinal
    public static Unit_System findSystem(int ordinal){
        Unit_System system = Unit_System.INTERNATIONAL_SYSTEM;
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
        }
        return system;
    }// End of findProperty
}
