package io.github.jlrods.gisescookingunitconverter;

import android.util.Log;

/**
 * Created by rodjose1 on 19/06/2018.
 */

public class Unit_Converter {
    private Unit unitFrom;
    private Unit unitTo;
    private double unitFromValue;
    private String equation;
    // Enum to define different operations
    private MainActivity.Operation operation;
    private double result;
    //Default constructor
    public Unit_Converter(){
        this.unitFrom = new Unit();
        this.unitTo = new Unit();
        this.unitFromValue  = 1.0;
        this.equation = "1";
        this.operation = MainActivity.Operation.Multiplication;
        this.result = 1.0;
    }// End of default constructor
    //Full constructor
    public Unit_Converter(Unit unitFrom, Unit unitTo,String equation, MainActivity.Operation operation,String unitFromValue){
        this.unitFrom = unitFrom;
        this.unitTo = unitTo;
        this.equation = equation;
        this.operation = operation;
        //Check the type of unit form value was input (decimal or fraction expression)
        if (unitFromValue.contains("/")){
            this.unitFromValue = Double.parseDouble(unitFromValue);
        }else{
            this.unitFromValue = Double.parseDouble(unitFromValue);
        }
        this.result = 1.0;// End of if else statement
    }//End of full constructor

    //Method to perform conversion
    public double convertUnit(){
        //result = 0.0;
        double factor;
        Log.d("Ent_convertUnit","Enter converUnit method within the Unit_Converter class.");
        //Check what kind of operation is going to be performed
        if(this.operation == MainActivity.Operation.Addition){
            factor = Double.parseDouble(this.equation);
            this.setResult( this.unitFromValue + factor);
        }else if(this.operation == MainActivity.Operation.Multiplication){
            factor = Double.parseDouble(this.equation);
            this.setResult(this.unitFromValue * factor);
        }else if(this.operation == MainActivity.Operation.Subtraction){
            factor = Double.parseDouble(this.equation);
            this.setResult(this.unitFromValue - factor);
        }else if(this.operation == MainActivity.Operation.Division){
            factor = Double.parseDouble(this.equation);
            this.setResult(this.unitFromValue/factor);
        }else{
            //All the Convined operation conversion end here
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