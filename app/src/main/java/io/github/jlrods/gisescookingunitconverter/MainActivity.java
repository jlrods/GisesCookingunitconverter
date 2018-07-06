package io.github.jlrods.gisescookingunitconverter;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    public enum Operation{
        Addition, Multiplication, Subtraction, Division, Convined;
        //Method to increase the ordinal value
        public int increaseOrdinal(){

            return this.ordinal()+1;
        }
        public Operation getOpByName(String op){
            Operation operation;
            switch(op){
                case "Addition":
                    operation = Addition;
                    break;
                case "Multiplication":
                    operation = Multiplication;
                    break;
                case "Subtraction":
                    operation = Subtraction;
                    break;
                case "Division":
                    operation = Division;
                    break;
                case "Convined":
                    operation = Convined;
                    break;
                default:
                    operation= null;
            }
            return operation;
        }
    }//End of Operation Enum
    private static DecimalFormat df2 = new DecimalFormat(".##");
    //Conversion DB object to handle the database
    ConversionsDB conversions = new ConversionsDB(this,"conversions",null,1);
    //Cursor to receive result cursor from DB queries
    Cursor cursor;
    //Variable to save the current selected property in order to populate the units in the spinners
    Property currentProperty = Property.WEIGHT;
    //Variables to control the ImageViews with the property images
    ImageView imgWeight;
    ImageView imgVolume;
    ImageView imgTemperature;
    //List of current current Units available in the Spinners which will be used for doing the conversions
    List<Unit> currentUnits;
    //int i;
    //NoKeyboardEditText tvInput;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Log.d("Ent_main","Enter onCreate on MainActivity.");
        //Set layout
        setContentView(R.layout.activity_main);
        //Create and instantiate imageViews for each available properties
        imgWeight = (ImageView) this.findViewById(R.id.imgWeight);
        imgVolume = (ImageView) this.findViewById(R.id.imgVolume);
        imgTemperature = (ImageView) this.findViewById(R.id.imgTemperature);
        //Declaration of two spinners
        final NoDefaultSpinner spUnitFrom = this.findViewById(R.id.spUnitFrom);
        final NoDefaultSpinner spUnitTo = this.findViewById(R.id.spUnitTo);
        //Assign onClickListeners for each property image
        imgWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Call method to select weight as the current property to populate the correct units
                weightSelected(spUnitFrom,spUnitTo);
            }
        });
        imgVolume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Method to select volume as the current property to populate the correct units
                volumeSelected(spUnitFrom,spUnitTo);
            }
        });
        imgTemperature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Method to select temperature as the current property to populate the correct units
                temperatureSelected(spUnitFrom,spUnitTo);
            }
        });
        //Create and instantiate a TextView object to handle the result text view
        final TextView tvResult = this.findViewById(R.id.tvResult);
        //Create and instantiate buttons from number pad
        Button btnNo1 = (Button) this.findViewById(R.id.btn1);
        Button btnNo2 = (Button) this.findViewById(R.id.btn2);
        Button btnNo3 = (Button) this.findViewById(R.id.btn3);
        Button btnNo4 = (Button) this.findViewById(R.id.btn4);
        Button btnNo5 = (Button) this.findViewById(R.id.btn5);
        Button btnNo6 = (Button) this.findViewById(R.id.btn6);
        Button btnNo7 = (Button) this.findViewById(R.id.btn7);
        Button btnNo8 = (Button) this.findViewById(R.id.btn8);
        Button btnNo9 = (Button) this.findViewById(R.id.btn9);
        Button btnNo0 = (Button) this.findViewById(R.id.btn0);
        Button btnDecimal = (Button) this.findViewById(R.id.btnDecimal);
        Button btnDivision = (Button) this.findViewById(R.id.btnDivision);
        Button btnDel = (Button) this.findViewById(R.id.btnDel);
        Button btnClear = (Button) this.findViewById(R.id.btnClear);
        //Create and instantiate Convert button
        Button btnConvert = (Button) this.findViewById(R.id.btnConvert);
        //Create and instantiate the input field
        final NoKeyboardEditText tvInput = (NoKeyboardEditText) this.findViewById(R.id.intxtUnitFrom);
        //Assign onClickListeners for each button view in the number pad + convert button
        //Button[] buttons = {btnNo0,btnNo1,btnNo2,btnNo3,btnNo4,btnNo5,btnNo6,btnNo7,btnNo8,btnNo9};
        btnNo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                inputNumber(tvInput,"1");
            }
        });
        btnNo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                inputNumber(tvInput,"2");
            }
        });
        btnNo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                inputNumber(tvInput,"3");
            }
        });
        btnNo4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                inputNumber(tvInput,"4");
            }
        });
        btnNo5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                inputNumber(tvInput,"5");
            }
        });
        btnNo6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                inputNumber(tvInput,"6");
            }
        });
        btnNo7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                inputNumber(tvInput,"7");
            }
        });
        btnNo8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                inputNumber(tvInput,"8");
            }
        });
        btnNo9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                inputNumber(tvInput,"9");
            }
        });
        btnNo0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                inputNumber(tvInput,"0");
            }
        });
        //this.assignOnClickEventListenerNumPadButtons(buttons);
        btnDecimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                inputNumber(tvInput,".");
            }
        });
        btnDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                inputNumber(tvInput,"/");
            }
        });
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                deleteDigit(tvInput);
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                clearInput   (tvInput,tvResult);
            }
        });
        //Set OnClickListner for convert button
        btnConvert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Call function to make the conversion
                convert(tvResult,tvInput,spUnitFrom, spUnitTo);
            }
        });
        //Populate Spinners with initial data based on default property = weight
        this.changeSpinnersContent(spUnitFrom,spUnitTo,currentProperty);
        //Get a list of units from the Spinner
        currentUnits = updateUnitList();
        
        Log.d("Ext_main","Exit onCreate on MainActivity.");
    }//End of onCreate method

    //Method to populate a spinner passed as parameter based on the property selected
    protected void populateSpinner(Spinner spinner, Property property){
        Log.d("Ent_populateSpinner","Enter populateSpinner method.");
        //Get the cursor from database query
        cursor = conversions.getUnitsAsPerProperty(property);
        //Create new CursorAdapter
        ConversionsAdapter adapter = new ConversionsAdapter(this,cursor,CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        //Set the new adapter
        spinner.setAdapter(adapter);
        //cursor = null;
        Log.d("Ext_populateSpinner","Exit populate Spinner method.");
    }// End of populateSpinner method

    public void setSpinnersPrompts(Spinner unitFrom, Spinner unitTo){
        Log.d("Ent_setPrompt","Enter setSpinnersPrompts method.");
        unitFrom.setPrompt("From");
        unitTo.setPrompt("To");
        Log.d("Ext_setPrompt","Exit setSpinnersPrompts method.");
    }//End of setSpinnersPrompts method

    //Method to be called within OnClick event listener assigned to the weight img
    private void weightSelected(NoDefaultSpinner spUnitFrom, NoDefaultSpinner spUnitTo){
        Log.d("Ent_wgtSelect","Enter weightSelected method.");
        this.changeProperty(spUnitFrom,spUnitTo,currentProperty,Property.WEIGHT);
        Log.d("Ext_wgtSelect","Exit weightSelected method.");
    }//End of weightSelected

    //Method to be called within OnClick event listener assigned to the volume img
    private void volumeSelected(NoDefaultSpinner spUnitFrom, NoDefaultSpinner spUnitTo){
        Log.d("Ent_volSelect","Enter volumeSelected method.");
        this.changeProperty(spUnitFrom,spUnitTo,currentProperty,Property.VOLUME);
        Log.d("Ext_volSelect","Exit volumeSelected method.");
    }//End of weightSelected

    //Method to be called within OnClick event listener assigned to the temperature img
    private void temperatureSelected(NoDefaultSpinner spUnitFrom, NoDefaultSpinner spUnitTo){
        Log.d("Ent_tempSelect","Enter temperatureSelected method.");
        this.changeProperty(spUnitFrom,spUnitTo,currentProperty,Property.TEMPERATURE);
        Log.d("Ext_tempSelect","Exit temperatureSelected method.");
    }//End of weightSelected

    //General method to change units contained within the spinners based on the property selected
    private void changeSpinnersContent(NoDefaultSpinner spUnitFrom, NoDefaultSpinner spUnitTo, Property p){
        Log.d("Ent_chgSpinners","Enter changeSpinnersContent method.");
        //Create and instantiate UnitFrom spinner
        spUnitFrom = (NoDefaultSpinner)this.findViewById(R.id.spUnitFrom);
        //Create and instantiate UnitTo spinner
        spUnitTo = (NoDefaultSpinner)this.findViewById(R.id.spUnitTo);
        //Call method to populate both spinners
        populateSpinner(spUnitFrom,p);
        populateSpinner(spUnitTo,p);
        //Call method to populate spinner's prompt
        this.setSpinnersPrompts(spUnitFrom,spUnitTo);
        Log.d("Ext_chgSpinners","Exit changeSpinnersContent method.");
    }// End of changeSpinnersContent method

    //Method to change from one property to another based on user selections.
    private void changeProperty(NoDefaultSpinner spUnitFrom, NoDefaultSpinner spUnitTo,Property oldProperty, Property newProperty){
        Log.d("Ent_changePrpty","Enter changeProperty method.");
        if(oldProperty!= newProperty){
            int selectedColor = getResources().getColor(R.color.colorAccent);
            //int notSelectedColor = getResources().getColor(R.color.colorDivider);
            int notSelectedColor = Color.parseColor("#fafafa");
            //Change current property background to white
            if(newProperty == Property.WEIGHT){
                imgWeight.setBackgroundColor(selectedColor);
                imgVolume.setBackgroundColor(notSelectedColor);
                imgTemperature.setBackgroundColor(notSelectedColor);
            }else if(newProperty == Property.VOLUME){
                imgVolume.setBackgroundColor(selectedColor);
                imgTemperature.setBackgroundColor(notSelectedColor);
                imgWeight.setBackgroundColor(notSelectedColor);
            }else{
                imgTemperature.setBackgroundColor(selectedColor);
                imgWeight.setBackgroundColor(notSelectedColor);
                imgVolume.setBackgroundColor(notSelectedColor);
            }// End of inner if else statements
            //Change currentProperty variable to the one selected
            currentProperty = newProperty;
            //Get a list of units from the Spinner
            currentUnits = updateUnitList();
        }// End of outer if statement
        //Populate spinners with proper units based on the propertS
        this.changeSpinnersContent(spUnitFrom,spUnitTo,currentProperty);
        Log.d("Ext_changePrpty","Exit changeProperty method.");
    }//End of changeProperty method

    //Method to enable and disable the "." and "/" buttons when one of them is pressed
    private boolean isUsingSymbol(NoKeyboardEditText inputField){
        Log.d("Ent_isUsingSymbol","Enter isUsingSymbol method.");
        //Define inner variable to be used within this context
        //Decimal and Division variables
        String decimal = ".";
        String division = "/";
        //Boolean value to be returned based on the current text analysis
        boolean isUsingSymbol = false;
        //Get the current text from the Text field
        String text = inputField.getText().toString();
        //Check if "." or "/" characters has been already used
        if (text.contains(decimal) || text.contains(division)){
            isUsingSymbol = true;
        }
        Log.d("Ext_isUsingSymbol","Exit isUsingSymbol method.");
        return isUsingSymbol;
    }// End of switchDecimalAndDivisionState method

    private void inputNumber(NoKeyboardEditText inputField, String digit){
        Log.d("Ent_input","Enter inputNumber method.");
        switch (digit){
            case "1":
                inputField.setText(inputField.getText().append("1"));
                break;
            case "2":
                inputField.setText(inputField.getText().append("2"));
                break;
            case "3":
                inputField.setText(inputField.getText().append("3"));
                break;
            case "4":
                inputField.setText(inputField.getText().append("4"));
                break;
            case "5":
                inputField.setText(inputField.getText().append("5"));
                break;
            case "6":
                inputField.setText(inputField.getText().append("6"));
                break;
            case "7":
                inputField.setText(inputField.getText().append("7"));
                break;
            case "8":
                inputField.setText(inputField.getText().append("8"));
                break;
            case "9":
                inputField.setText(inputField.getText().append("9"));
                break;
            case "0":
                inputField.setText(inputField.getText().append("0"));
                break;
            case ".":
                //Check if there is not a symbol already in the text before appending the "." sysmbol
                if(!isUsingSymbol(inputField)){
                    inputField.setText(inputField.getText().append("."));
                }
                break;
            case "/":
                //Check if there is not a symbol already in the text before appending the "/" sysmbol
                if(!isUsingSymbol(inputField)){
                    inputField.setText(inputField.getText().append("/"));
                }
                break;
            default:
                break;
        }
        Log.d("Ext_input","Exit inputNumber method.");
    }//End of inputNumber method

    //Method to delete last digit displayed on the input text view
    private void deleteDigit(NoKeyboardEditText tvInput){
        Log.d("Ent_Del","Enter deleteDigit method.");
        tvInput.setText(tvInput.getText().delete(tvInput.length()-1 ,tvInput.length()));
        Log.d("Ext_Del","Exit deleteDigit method.");
    }

    //Method to clear the whole text in on the input text view and display the hint message
    private void clearInput(NoKeyboardEditText tvInput,TextView tvResult){
        Log.d("Ent_Clear","Enter clearInput method.");
        //Set the empty strings on both Text Views
        tvInput.setText("");
        tvResult.setText("");
        //Display the hints on both views
        tvInput.setHint(R.string.UnitFromHint);
        tvResult.setText(R.string.UnitToResult);
        Log.d("Ext_Clear","Exit clearInput method.");
    }

    //Method to convert from one unit to another
    private void convert(TextView tvresult,NoKeyboardEditText tvInput,NoDefaultSpinner spUnitFrom,NoDefaultSpinner spUnitTo){
        Log.d("Ent_Convert","Enter convert method.");
        //Define inner varialbles to be used with   method
        Unit unitFrom = new Unit();
        Unit unitTo = new Unit();

        //Perform verification to check current data is OK for creating a new Unit_Converter object
        if(spUnitFrom.getSelectedItem() != null && spUnitTo.getSelectedItem()!= null){
            if(tvInput.getText().toString().equals("")){
                Toast.makeText(this,R.string.EmptyInutFieldMessage,Toast.LENGTH_LONG).show();
            }else{

                //CursorAdapter a = (CursorAdapter) spUnitFrom.getAdapter();
                //Cursor c = conversions.getUnitsAsPerProperty(currentProperty);
                //c.moveToPosition(spUnitFrom.getSelectedItemPosition());
                //Extract tvInput text and store it for future uses
                String unitFromValue = tvInput.getText().toString();
                //Find the two selected units and assign correct attributes to uniFrom and unitTo objects
                String unitFromName =extractSelectedName(spUnitFrom);
                String unitToName =extractSelectedName(spUnitTo);
                //Check the same unit has not been selected
                if(unitFromName.toString().toLowerCase().equals(unitToName.toString().toLowerCase())){
                    //Display message saying the same unit has been selected to convert to
                    Toast.makeText(this,"You have selected the same unit.",Toast.LENGTH_SHORT).show();
                }else{
                    //If units selected are different extract unit objects form currentUnits list
                    unitFrom = this.findUnitByName(unitFromName);
                    unitTo = this.findUnitByName(unitToName);
                    //Try to find a directed link conversion between the two units
                    cursor = this.conversions.getConversion(unitFrom,unitTo);
                    //Check the cursor does not return null or is empty. (This covers conversions between units and its reference within its own system)
                    if(cursor != null && cursor.getCount() > 0){
                        //Create new converter object to calculate the conversion between unitFrom and unitTo
                        Unit_Converter converter = this.createUnitConverter(unitFrom,unitTo,unitFromValue);
                        //Display result on the text view designated for that
                        tvresult.setText(String.valueOf(df2.format(converter.convertUnit()))+" " +unitTo.getSymbol());
                    }else{
                        //If cursor returned null or empty, then look for an intermediate conversion with a reference unit
                        //First, check if the current unitFrom is reference for its system
                        if(unitFrom.isReference()){
                            //Declare and instantiate a new unit object to keep the reference in the unitTo system
                            Unit referenceUnitTo = this.conversions.getReference(unitTo);
                            //Try to find the direct conversion between unitFrom and the unitTo reference
                            cursor = this.conversions.getConversion(unitFrom,referenceUnitTo);
                            //Check the cursor does not return null or empty. (This covers conversion between reference units in different systems)
                            if(cursor!=null && cursor.getCount()>0){
                                //Create converter object to calculate conversion from UnitFrom to referenceUnitTo
                                Unit_Converter converterUnitToRef= this.createUnitConverter(unitFrom,referenceUnitTo,unitFromValue);
                                //Try to find the direct conversion between unitTo and its reference
                                cursor = this.conversions.getConversion(referenceUnitTo,unitTo);
                                if(cursor!= null && cursor.getCount()>0){
                                    //Create new converter object to calculate the conversion from reference to unitTo
                                    Unit_Converter converter = this.createUnitConverter(referenceUnitTo,unitTo,String.valueOf(converterUnitToRef.convertUnit()));
                                    //Display result on the text view designated for that
                                    tvresult.setText(String.valueOf(df2.format(converter.convertUnit()))+" " +unitTo.getSymbol());
                                }else{
                                    Toast.makeText(this,"No conversion from reference to UnitTo",Toast.LENGTH_LONG).show();
                                }

                            }else{
                                Toast.makeText(this,"No conversion from UnitFrom to UnitToRef: "+unitFrom.getName()+"and "+unitTo.getName() ,Toast.LENGTH_LONG).show();
                            }
                        }else{
                            //Declare and instansiate a new unit object to keep the reference in the unitFrom system
                            Unit referenceUnitFrom = this.conversions.getReference(unitFrom);
                            //Try to find the direct conversion between unitFrom and the reference
                            cursor = this.conversions.getConversion(unitFrom,referenceUnitFrom);
                            //Check the cursor does not return null or empty. (This covers conversion between units in the same system)
                            if(cursor != null && cursor.getCount() > 0){
                                //Create new converter object to calculate the conversion between unitFrom to reference
                                Unit_Converter converterUnitFromRef = this.createUnitConverter(unitFrom,referenceUnitFrom,unitFromValue);
                                //Try to find the direct conversion between reference and unitTo
                                cursor = this.conversions.getConversion(referenceUnitFrom,unitTo);
                                //Check the cursor does not return null or is empty
                                if (cursor != null && cursor.getCount() > 0){
                                    //Create new converter object to calculate the conversion from reference to unitTo
                                    Unit_Converter converter = this.createUnitConverter(referenceUnitFrom,unitTo,String.valueOf(converterUnitFromRef.convertUnit()));
                                    //Display result on the text view designated for that
                                    tvresult.setText(String.valueOf(df2.format(converter.convertUnit()))+" " +unitTo.getSymbol());
                                }else{
                                    //If cursor is empty or returns null would mean that converision between systems is required and extra intermediate conversion has to be done
                                    //This covers conversions between different systems.
                                    //Declare and instantiate a new unit object to keep the reference in th unitTo system
                                    Unit referenceUnitTo = this.conversions.getReference(unitTo);
                                    //Try to find the direct conversion between the unitFrom reference and unitToReference
                                    cursor = this.conversions.getConversion(referenceUnitFrom,referenceUnitTo);
                                    //Check the cursor does not return null or empty. (This covers conversion between units in different systems)
                                    if(cursor != null && cursor.getCount() >0){
                                        //Create new converter object to calculate the conversion from referenceUnitFrom to referenceUnitTo
                                        Unit_Converter converterUnitToRef =this.createUnitConverter(referenceUnitFrom,referenceUnitTo,String.valueOf(converterUnitFromRef.convertUnit()));
                                        //Try to find the direct conversion between the referenceUnitTo and unitTo units
                                        cursor = this.conversions.getConversion(referenceUnitTo,unitTo);
                                        //Check the cursor does not return null or is empty.
                                        if(cursor != null && cursor.getCount() >0){
                                            //Create new converter object to calculate the conversion from reference to unitTo
                                            Unit_Converter converter = this.createUnitConverter(referenceUnitTo,unitTo,String.valueOf(converterUnitToRef.convertUnit()));
                                            //Display result on the text view designated for that
                                            tvresult.setText(String.valueOf(df2.format(converter.convertUnit()))+" " +unitTo.getSymbol());
                                        }else{
                                            Toast.makeText(this,"No conversion found for theses units: "+unitFrom.getName()+"and "+unitTo.getName() ,Toast.LENGTH_LONG).show();
                                        }
                                    }

                                }
                            }else{
                                //If cursor is empty or returns null or is empty throw an error message
                                Toast.makeText(this,"No conversion found for this unit: "+unitFrom.getName()+"and its reference: "+referenceUnitFrom.getName() ,Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                    //Create Unit_Converter object which will be able to calculate the convertion and return proper result
                }//End of inner if else statement

            }
        }else{
            //Display error message for wrong selection
            Toast.makeText(this,R.string.WrongUnitMessage,Toast.LENGTH_LONG).show();
            //Check if the input field is valid

        }//End of if else statement
        //Create the new Unit_Converter object and retrieve the result

        //Display the correct value on the UI
        Log.d("Ext_Convert","Exit convert method.");
    }//End of convert method


    //Method to create a Unit_Converter object by using the global cursor object
    private Unit_Converter createUnitConverter(Unit unit1, Unit unit2, String unitFromValue){
        Unit_Converter converter = null;
        //cursor = this.conversions.getConversion(unit1,unit2);
        //Extract data from cursor
        String equation = cursor.getString(1);
        String op = cursor.getString(2);
        //Declare and initialize an Operation variable to hold the operation enum. Use random Operation as initial value, so inner methods are accessible.
        Operation operation = Operation.Addition;
        //Call the operation method to find the proper operation by passing the name of it
        operation = operation.getOpByName(op);
        //Create Unit_Converter object which will be able to calculate the convertion and return proper result
        converter = new Unit_Converter(unit1,unit2,equation,operation,unitFromValue);
        return converter;
    }// End of createUnitConverter method

    //Method that update the list of current units avaialble in the spinners
    private ArrayList<Unit> updateUnitList(){
        ArrayList<Unit> listOfUnits= new ArrayList<Unit>();
        Log.d("Ent_UnitList","Enter updateUnitList method.");
        cursor = conversions.getUnitsAsPerProperty(currentProperty);
        listOfUnits = conversions.listUnits(cursor);
        Log.d("Ext_UnitList","Exit updateUnitLIst method.");
        return listOfUnits;
    }//End of updateUnitList method

    //Method to find a unit within the current list of units
    private Unit findUnitByName(String unitName){
        Log.d("Ent_findUnit","Enter findUnitByName method.");
        //Declare and initialize unit object to store the unit when found
        Unit unitFound = null;
        //Iterator for the while loop
        int i =0;
        //Boolean flag to stop while loop when unit has been found
        boolean found = false;
        while (i<currentUnits.size()&& !found){
            //Check current unit name value against the name provided via parameter
            if(currentUnits.get(i).getName().toLowerCase().equals(unitName.toLowerCase())){
                unitFound = new Unit(
                        currentUnits.get(i).getId(),
                        currentUnits.get(i).getName(),
                        currentUnits.get(i).getSymbol(),
                        currentUnits.get(i).isReference(),
                        currentUnits.get(i).getProperty(),
                        currentUnits.get(i).getSystem()
                );
                found = true;
            }//End of if statemet that checks current Unit item
            //Increase the iterator
            i++;
        }//End of while loop
        Log.d("Ext_findUnit","Exit findUnitByname method.");
        return unitFound;
    }//End of findUnitByName method

    //Method to extract name from selected item in spinner
    private String extractSelectedName(NoDefaultSpinner spinner){
        Log.d("Ent_ExtractName","Enter extractSelectedName method.");
        String itemName = "";
        Cursor c = (Cursor) spinner.getSelectedItem();
        itemName = c.getString(c.getColumnIndexOrThrow("Name"));
        Log.d("Ext_ExtractName","Exit extractSelectedName method.");
        return itemName;
    }//End of extractSelectedName method
    /*private void assignOnClickEventListenerNumPadButtons(Button[] buttons){
        for(i=0;i<buttons.length;i++){
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    inputNumber(tvInput,String.valueOf(i));
                }
            });
        }
    }*/

}//End of Main_Activity methi
