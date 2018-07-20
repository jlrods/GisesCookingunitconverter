package io.github.jlrods.gisescookingunitconverter;

import android.util.Log;

/**
 * Created by rodjose1 on 19/06/2018.
 */

//Class to handle the abtraction of a unit conversion
public class Unit_Converter {
    //Declare the class attributes
    //Unit objects to hold the unitFrom and unitTo units
    private Unit unitFrom;
    private Unit unitTo;
    //Double variable to hold the value of unit to convert from
    private double unitFromValue;
    //String to hold the relationship between units as per the database
    private String equation;
    // Enum to define different operations
    private MainActivity.Operation operation;
    //Double variable to hold the final result aster applaying the conversion to unitTo
    private double result;

    //Default constructor
    public Unit_Converter(){
        Log.d("Ent_defaultConstConv","Enter default constructor in the UnitConverter class.");
        this.unitFrom = new Unit();
        this.unitTo = new Unit();
        this.unitFromValue  = 1.0;
        this.equation = "1";
        this.operation = MainActivity.Operation.Multiplication;
        this.result = 1.0;
        Log.d("Ext_defaultConstConv","Exit default constructor in the UnitConverter class.");
    }// End of default constructor

    //Full constructor
    public Unit_Converter(Unit unitFrom, Unit unitTo,String equation, MainActivity.Operation operation,String unitFromValue){
        Log.d("Ent_FullConstConv","Enter full constructor in the UnitConverter class.");
        this.unitFrom = unitFrom;
        this.unitTo = unitTo;
        this.equation = equation;
        this.operation = operation;
        this.unitFromValue = Double.parseDouble(unitFromValue);
        this.result = 1.0;//Set result to 1 by default
        Log.d("Ext_FullConstConv","Exit full constructor in the UnitConverter class.");
    }//End of full constructor

    //Method to perform conversion
    public double convertUnit(){
        Log.d("Ent_convertUnit","Enter converUnit method within the Unit_Converter class.");
        //Declare double variable to hold the numerical relationship between units
        double factor;
        //Check what kind of operation is going to be performed
        //Check if it is a sum
        if(this.operation == MainActivity.Operation.Addition){
            factor = Double.parseDouble(this.equation);
            this.setResult( this.unitFromValue + factor);
            //Check if it is a multiplication
        }else if(this.operation == MainActivity.Operation.Multiplication){
            factor = Double.parseDouble(this.equation);
            this.setResult(this.unitFromValue * factor);
            //Check if it is a substraction
        }else if(this.operation == MainActivity.Operation.Subtraction){
            factor = Double.parseDouble(this.equation);
            this.setResult(this.unitFromValue - factor);
            //Check if it is a division
        }else if(this.operation == MainActivity.Operation.Division){
            factor = Double.parseDouble(this.equation);
            this.setResult(this.unitFromValue/factor);
        }else{
            //All the Convined operation conversion end here
            //Dedicated expressions to transform from Kelvin to Fahrenhit and vice versa UnitFrom + 459.67)/1.8 //(UnitFrom*1.8)-459.67
            //If the unit belongs to Temperature property do the conversion as follows
            if(this.unitFrom.getProperty() == Property.TEMPERATURE){
                double factor1 = 459.67;
                double factor2 = 1.8;
                //Double check the conversion to be done is from Kelvin to F or vise versa
                if(this.unitFrom.getId()== 44 && this.getUnitTo().getId()==22){
                    this.setResult((this.unitFromValue + factor1)/factor2);
                }else if(this.unitFrom.getId() == 22 && this.unitTo.getId()==44){
                    this.setResult((this.unitFromValue*factor2)-factor1);
                }//End of if else statemet
            }//End of if that checks the property
            //A fuction is required to parse the convined expression and represent that in terms of variables, numbers and math operations.
        }// End of if else statements
        Log.d("Ext_convertUnit","Exit convertUnit method within the Unit_Converter class.");
        return this.getResult();
    }//End of converUnit method

    //Getter and Setter methods
    public Unit getUnitFrom() {
        return unitFrom;
    }

    public void setUnitFrom(Unit unitFrom) {
        this.unitFrom = unitFrom;
    }

    public Unit getUnitTo() {
        return unitTo;
    }

    public void setUnitTo(Unit unitTo) {
        this.unitTo = unitTo;
    }

    public double getUnitFromValue() {
        return unitFromValue;
    }

    public void setUnitFromValue(double unitFromValue) {
        this.unitFromValue = unitFromValue;
    }

    public String getEquation() {
        return equation;
    }

    public void setEquation(String equation) {
        this.equation = equation;
    }

    public MainActivity.Operation getOperation() {
        return operation;
    }

    public void setOperation(MainActivity.Operation operation) {
        this.operation = operation;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }
}// End of Unit_Converter class