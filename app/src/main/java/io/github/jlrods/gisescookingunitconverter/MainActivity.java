package io.github.jlrods.gisescookingunitconverter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.text.TextUtilsCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.RelativeSizeSpan;
import android.text.style.SuperscriptSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Public Enum to define the type of operation that will govern the unit conversion
    public enum Operation{
        Addition, Multiplication, Subtraction, Division, Convined;
        //Method to increase the ordinal value
        public int increaseOrdinal(){
            Log.d("Ent_OpIncreaseOrd","Enter Operation Enum's increaseOrdinal method.");
            //Get ordinal and add 1
            Log.d("Ext_OpIncreaseOrd","Exit Operation Enum's increaseOrdinal method.");
            return this.ordinal()+1;

        }// End of increaseOrdinal method
        //Method to get an Operation enum object by passing the operation name as argument
        public static Operation getOpByName(String op){
            Log.d("Ent_getOperationByName","Enter getOpByName method.");
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
            Log.d("Ext_getOperationByName","Exit getOpByName method.");
            return operation;
        }//End of getOpByName method
    }//End of Operation Enum

    //Declare and instantiate a decimal format object to format result output
    private static DecimalFormat df5 = new DecimalFormat(".#####");
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
    //Declare UI global varaibles to be accessed from different methods within the MainActivity class
    //Input EditText for UnitFrom value
    NoKeyboardEditText tvInput;
    //TextView to display the Result
    TextView tvResult;
    //Both Spinners
    NoDefaultSpinner spUnitFrom;
    NoDefaultSpinner spUnitTo;
    //Button to change input value sign
    Button btnChangeSign;
    //Define constants to represent physical limits (Temperature minimun valid values for each scale that respect the Absolute zero concept)
    double ABSOLUTE_ZERO = 0.0;
    double ABSOLUTE_ZERO_CELSIUS = -273.15;
    double ABSOLUTE_ZERO_FAHRENHEIT = -459.67;
    //On create method
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Log.d("Ent_main","Enter onCreate on MainActivity.");
        //Set layout
        setContentView(R.layout.activity_main);
        //Create and instantiate imageViews for each available proprerty
        imgWeight = (ImageView) this.findViewById(R.id.imgWeight);
        imgVolume = (ImageView) this.findViewById(R.id.imgVolume);
        imgTemperature = (ImageView) this.findViewById(R.id.imgTemperature);
        //Instantiate the two spinners
        spUnitFrom = this.findViewById(R.id.spUnitFrom);
        spUnitTo = this.findViewById(R.id.spUnitTo);
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
        //Instantiate a TextView object to handle the result text view
        tvResult = this.findViewById(R.id.tvResult);
        //Instantiate the input field for the Unit From value
        tvInput = (NoKeyboardEditText) this.findViewById(R.id.intxtUnitFrom);
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
        Button btnDel = (Button) this.findViewById(R.id.btnDel);
        Button btnClear = (Button) this.findViewById(R.id.btnClear);
        //Instantiate the +/- button
        btnChangeSign = (Button) this.findViewById(R.id.btnChangeSign);
        //Create and instantiate Convert button
        Button btnConvert = (Button) this.findViewById(R.id.btnConvert);
        //Assign onClickListeners for each button view in the number pad + convert button
        //Button[] buttons = {btnNo0,btnNo1,btnNo2,btnNo3,btnNo4,btnNo5,btnNo6,btnNo7,btnNo8,btnNo9};
        btnNo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Call function to appen the number 1 to the input text
                inputNumber(tvInput,"1");
            }
        });
        btnNo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Call function to appen the number 2 to the input text
                inputNumber(tvInput,"2");
            }
        });
        btnNo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Call function to appen the number 3 to the input text
                inputNumber(tvInput,"3");
            }
        });
        btnNo4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Call function to appen the number 4 to the input text
                inputNumber(tvInput,"4");
            }
        });
        btnNo5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Call function to appen the number 5 to the input text
                inputNumber(tvInput,"5");
            }
        });
        btnNo6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Call function to appen the number 6 to the input text
                inputNumber(tvInput,"6");
            }
        });
        btnNo7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Call function to appen the number 7 to the input text
                inputNumber(tvInput,"7");
            }
        });
        btnNo8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Call function to appen the number 8 to the input text
                inputNumber(tvInput,"8");
            }
        });
        btnNo9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Call function to appen the number 9 to the input text
                inputNumber(tvInput,"9");
            }
        });
        btnNo0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Call function to appen the number 0 to the input text
                inputNumber(tvInput,"0");
            }
        });
        //this.assignOnClickEventListenerNumPadButtons(buttons);
        btnDecimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Call function to appen the the "." symbol to the input text
                inputNumber(tvInput,".");
            }
        });
        btnChangeSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Call the method to  toggle between positive and negative sign
                changeSign(tvInput);
            }
        });
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Call the method to delete the last digit displayed in the input box
                deleteDigit(tvInput);
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Call method to clear both input and result boxes
                clearInput   (tvInput);
            }
        });
        //Set OnClickListner for convert button
        btnConvert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Call function to make the conversion
                convert(tvInput,spUnitFrom, spUnitTo);
            }
        });
        //Set the +/- to be disabled by default (To be enabled on the changeProperty method if property is set to Temperature)
        btnChangeSign.setClickable(false);
        //Get default current property from preferences
        SharedPreferences pref =  PreferenceManager.getDefaultSharedPreferences(this);
        String preferedPropertyID = pref.getString("intitalProperty","0");
        //Create a new property object to hold the prefered property
        Property  preferenceProperty = Property.findProperty(Integer.parseInt(preferedPropertyID));
        //Check the current property and the prefered  are not the same
        if(this.currentProperty != preferenceProperty){
            this.changeProperty(spUnitFrom,spUnitTo,currentProperty,preferenceProperty);
        }else{
            //If the property is the same as the default one (Weight), just populate the spinners and update the currentUnits list
            //Populate Spinners with initial data based on default property = weight
            this.changeSpinnersContent(spUnitFrom,spUnitTo,currentProperty);
            //Get a list of units from the Spinner
            currentUnits = updateUnitList();
        }// End of if else statement
        Log.d("Ext_main","Exit onCreate on MainActivity.");
    }//End of onCreate method

    @Override
    protected void onSaveInstanceState(Bundle saveState) {
        //Call super method
        super.onSaveInstanceState(saveState);
        Log.d("Ent_onSaveInstance","Enter the orverriden section of onSaveInstanceSate method on MainActivity.");
        //Declare and instantiate a number to hold the current property ordinal
        int property = this.currentProperty.ordinal();
        //Save the orfinal in the current state
        saveState.putInt("property",property);
        //Declare and instantiate a number to hold the current selection on the Unit From spinner
        int unitFromSelectedItem = spUnitFrom.getSelectedItemPosition();
        //Save the current position on the unit from spinner in the current app state
        saveState.putInt("unitFromPosition",unitFromSelectedItem);
        //Decalre and instantiate a number to hold the current selection on the unit to spinner
        int unitToSelectedItem = spUnitTo.getSelectedItemPosition();
        //Save the current position on the unit to spinner in the current app state
        saveState.putInt("unitToPosition",unitToSelectedItem);
        //Decalre and instantiate string to stote the unit from value input in the input box
        String unitFromValue = this.tvInput.getText().toString();
        //Save the current app state the unit from value input in the input box
        saveState.putString("unitFromValue",unitFromValue);
        //Declare and instantiate String to hold the result displayed in the Result box
        String result = this.tvResult.getText().toString();
        //Save in the current app state the result displayed
        saveState.putString("result",result);
        Log.d("Ext_onSaveInstance","Exit the orverriden section of onSaveInstanceSate method on MainActivity.");
    }// End of onSaveInstanceState method

    @Override
    protected void onRestoreInstanceState(Bundle restoreState) {
        //Call the super method
        super.onRestoreInstanceState(restoreState);
        Log.d("Ent_onRestoreInstance","Enter the orverriden section of onRestroeInstanceState method on MainActivity.");
        //Check the stored state is not null
        if (restoreState != null){
            //Retrieve the number that stores the property ordinal
            int propertyID = restoreState.getInt("property");
            //Call the changeProperty method that will check the current property and the default one (from onCreate method) so the proper changes can be made on the UI if they are different
            this.changeProperty(spUnitFrom,spUnitTo,currentProperty,Property.findProperty(propertyID));
            //Set the text in the input box by retrievin the value stored for that purpose
            this.tvInput.setText(restoreState.getString("unitFromValue"));
            //Select the selected items on each spinner
            int unitFromPosition = restoreState.getInt("unitFromPosition");
            int unitToPostion = restoreState.getInt("unitToPosition");
            spUnitFrom.setSelection(unitFromPosition);
            spUnitTo.setSelection(unitToPostion);
            //Display correct result by retrieving the value stored on the state for that purpose
            this.tvResult.setText(restoreState.getString("result"));
        }// End of if statement to check the state is not null
        Log.d("Ext_onRestoreInstance","Exit the orverriden section of onRestroeInstanceState method on MainActivity.");
    }// End of onRestoreInstanceState method

    //Method to make Action bar menu visible
    @Override public boolean onCreateOptionsMenu(Menu menu) {
        Log.d("Ent_CreateMenu","Enter the onCreateOptionsMenu method in the MainActivity.");
        getMenuInflater().inflate(R.menu.menu_main, menu);
        Log.d("EExt_CreateMenu","Exit the onCreateOptionsMenu method in the MainActivity.");
        return true;
    }// End of onCreateOptionsMenu method

    //Method to define menu functionalities
    @Override public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("Ent_OptItemSelected","Enter the onOptionsItemSelected method in the MainActivity.");
        //Declare and isntantiate the id of the selected item
        int id = item.getItemId();
        //Check the id and verify against the possble id values
        //Check first if the id corresponds to the settings menu item
        if (id == R.id.settings) {
            //Call the method to start the preferences activity
            this.callPrefernces(null);
            return true;
         //If not, check if id corresponds to the about menu item
        }else if (id == R.id.about) {
            //Call method to start about activity
            this.callAboutActivity(null);
            return true;
        }// End of if else statement to check the item id
        Log.d("Ext_OptItemSelected","Exit the onOptionsItemSelected method in the MainActivity.");
        return super.onOptionsItemSelected(item);
    }// End of the onOptionsItemSelected method

    //Method to populate a spinner passed as parameter based on the property selected
    protected void populateSpinner(Spinner spinner, Property property){
        Log.d("Ent_populateSpinner","Enter populateSpinner method in the MainActivity.");
        //Get the cursor from database query
        cursor = conversions.getUnitsAsPerProperty(property);
        //Create new CursorAdapter
        ConversionsAdapter adapter = new ConversionsAdapter(this,cursor,CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        //Set the new adapter
        spinner.setAdapter(adapter);
        //cursor = null;
        Log.d("Ext_populateSpinner","Exit populate Spinner method in the MainActility.");
    }// End of populateSpinner method

    //Method to set both spinner prompts ("From" and "To")
    public void setSpinnersPrompts(Spinner unitFrom, Spinner unitTo){
        Log.d("Ent_setPrompt","Enter setSpinnersPrompts method in MainActivity.");
        //Set prompt for the unitFrom spinner
        unitFrom.setPrompt("From");
        //Set promtp for the unitTo spinner
        unitTo.setPrompt("To");
        Log.d("Ext_setPrompt","Exit setSpinnersPrompts method in MainActivity.");
    }//End of setSpinnersPrompts method

    //Method to be called within OnClick event listener assigned to the weight img
    private void weightSelected(NoDefaultSpinner spUnitFrom, NoDefaultSpinner spUnitTo){
        Log.d("Ent_wgtSelect","Enter weightSelected method in MainActivity.");
        //Call the changeProperty method to do proper changes on the UI and logic
        this.changeProperty(spUnitFrom,spUnitTo,currentProperty,Property.WEIGHT);
        Log.d("Ext_wgtSelect","Exit weightSelected method in MainActivity.");
    }//End of weightSelected

    //Method to be called within OnClick event listener assigned to the volume img
    private void volumeSelected(NoDefaultSpinner spUnitFrom, NoDefaultSpinner spUnitTo){
        Log.d("Ent_volSelect","Enter volumeSelected method in MainActivity.");
        //Call the changeProperty method to do proper changes on the UI and logic
        this.changeProperty(spUnitFrom,spUnitTo,currentProperty,Property.VOLUME);
        Log.d("Ext_volSelect","Exit volumeSelected method in MainActivity.");
    }//End of weightSelected

    //Method to be called within OnClick event listener assigned to the temperature img
    private void temperatureSelected(NoDefaultSpinner spUnitFrom, NoDefaultSpinner spUnitTo){
        Log.d("Ent_tempSelect","Enter temperatureSelected method in MainActivity.");
        //Call the changeProperty method to do proper changes on the UI and logic
        this.changeProperty(spUnitFrom,spUnitTo,currentProperty,Property.TEMPERATURE);
        Log.d("Ext_tempSelect","Exit temperatureSelected method in MainActivity.");
    }//End of weightSelected

    //General method to change units contained within the spinners based on the property selected
    private void changeSpinnersContent(NoDefaultSpinner spUnitFrom, NoDefaultSpinner spUnitTo, Property p){
        Log.d("Ent_chgSpinners","Enter changeSpinnersContent method in MainActivity.");
        //Create and instantiate UnitFrom spinner
        spUnitFrom = (NoDefaultSpinner)this.findViewById(R.id.spUnitFrom);
        //Create and instantiate UnitTo spinner
        spUnitTo = (NoDefaultSpinner)this.findViewById(R.id.spUnitTo);
        //Call method to populate both spinners
        populateSpinner(spUnitFrom,p);
        populateSpinner(spUnitTo,p);
        //Call method to populate spinner's prompt
        this.setSpinnersPrompts(spUnitFrom,spUnitTo);
        Log.d("Ext_chgSpinners","Exit changeSpinnersContent method in MainActivity.");
    }// End of changeSpinnersContent method

    //Method to change from one property to another based on user selections.
    private void changeProperty(NoDefaultSpinner spUnitFrom, NoDefaultSpinner spUnitTo,Property oldProperty, Property newProperty){
        Log.d("Ent_changePrpty","Enter changeProperty method in MainActivity.");
        //Check the old property and the new one are not the same
        if(oldProperty!= newProperty){
            //Declare and instantiate a number to hold the Accent color number
            int selectedColor = getResources().getColor(R.color.colorAccent);
            //Declare and instantiate a number to store the not selected item color (graish color)
            int notSelectedColor = Color.parseColor("#fafafa");
            //Change new property background to the the accent color and the not selected properties to color defined above
            if(newProperty == Property.WEIGHT){
                //Weight with accent color, the other two with white color
                imgWeight.setBackgroundColor(selectedColor);
                imgVolume.setBackgroundColor(notSelectedColor);
                imgTemperature.setBackgroundColor(notSelectedColor);
            }else if(newProperty == Property.VOLUME){
                //Volume with accent color, the other two with wihte color
                imgVolume.setBackgroundColor(selectedColor);
                imgTemperature.setBackgroundColor(notSelectedColor);
                imgWeight.setBackgroundColor(notSelectedColor);
            }else{
                //Temperature with accent color, the other two with white color
                imgTemperature.setBackgroundColor(selectedColor);
                imgWeight.setBackgroundColor(notSelectedColor);
                imgVolume.setBackgroundColor(notSelectedColor);
            }// End of inner if else statements
            //Change currentProperty variable to the one selected
            currentProperty = newProperty;
            //Disable or enable the +/- button depending on the property
            if(currentProperty == Property.TEMPERATURE){
                //Enable the +/- button
                btnChangeSign.setClickable(true);
            }else{
                //Otherwise disable the +/- button
                btnChangeSign.setClickable(false);
            }
            //Get a list of units from the Spinner
            currentUnits = updateUnitList();
            //Populate spinners with proper units based on the property
            this.changeSpinnersContent(spUnitFrom,spUnitTo,currentProperty);
            //Clear the input and result boxes so hints are displayed instead
            this.clearInput(tvInput);
        }// End of outer if statement
        Log.d("Ext_changePrpty","Exit changeProperty method in MainActivity.");
    }//End of changeProperty method

    //Method to enable and disable the "." and "/" buttons when one of them is pressed
    private boolean isUsingSymbol(NoKeyboardEditText inputField){
        Log.d("Ent_isUsingSymbol","Enter isUsingSymbol method in MainActivity.");
        //Define inner variable to be used within this context
        //Define the Decimal variable as a String
        String decimal = ".";
        //Boolean value to be returned based on the current text analysis
        boolean isUsingSymbol = false;
        //Get the current text from the Text field
        String text = inputField.getText().toString();
        //Check if "." character has been already used
        if (text.contains(decimal)){
            isUsingSymbol = true;
        }
        Log.d("Ext_isUsingSymbol","Exit isUsingSymbol method in MainActivity.");
        return isUsingSymbol;
    }// End of switchDecimalAndDivisionState method

    private void inputNumber(NoKeyboardEditText inputField, String digit){
        Log.d("Ent_input","Enter inputNumber method in MainActility.");
        //Check the digit passed as agument so the correct number is appended to the current input text
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
            default:
                break;
        }// End of switch statement to check the digit value
        Log.d("Ext_input","Exit inputNumber method in MainActivity.");
    }//End of inputNumber method

    //Method to change sign of the unit from value
    private void changeSign(NoKeyboardEditText inputField){
        Log.d("Ent_changeSign","Enter the changeSign method in MainActivity.");
        //Check the input field is not empty to avoid Null exception
        if (inputField !=null) {
            //Define and instantiate variable to store the current value in the input box
            String currentValue = inputField.getText().toString();
            //Define String value for representing the negative value
            String negativeSign = "-";
            //Check if the current string is empty
            if (TextUtils.isEmpty(inputField.getText())){
                //If empty add the - sign
                inputField.setText(negativeSign);
            }//End of if statement to check the input field was empty
            //Check if current sign is negative and invert if required
            //Trim the whole string and check the first character in the text is equal to the negative sign
            else if(currentValue.substring(0,1).equals(negativeSign)){
                //Check the current values holds more digits, not only th negative sign (-)
                if (currentValue.length()>1){
                    //If value in the input text is longer than one, change the sign with no - symbol
                    inputField.setText(currentValue.substring(1,currentValue.length()));
                }
            }else{
                // The sign is positve so invert to negative value
                inputField.setText(negativeSign.concat(currentValue));
            }
        }Log.d("Ext_changeSign","Exit the changeSign method in MainActivity.");
    }// End of chageSign method

    //Method to delete last digit displayed on the input text view
    private void deleteDigit(NoKeyboardEditText tvInput){
        Log.d("Ent_Del","Enter deleteDigit method in MainActivity.");
        //Check the input field is not empty so the last character can be deleted
        if (tvInput !=null && !TextUtils.isEmpty(tvInput.getText())){
            tvInput.setText(tvInput.getText().delete(tvInput.length()-1 ,tvInput.length()));
        }// End of if statement to check the input box is not  null or empty
        Log.d("Ext_Del","Exit deleteDigit method in MainActivity.");
    }// End of deleteDigit method

    //Method to clear the whole text in on the input text view and display the hint message
    private void clearInput(NoKeyboardEditText tvInput){
        Log.d("Ent_Clear","Enter clearInput method in MainActivity.");
        //Set the empty strings on both Text Views
        tvInput.setText("");
        tvResult.setText("");
        //Display the hints on both views
        tvInput.setHint(R.string.UnitFromHint);
        tvResult.setText(R.string.UnitToResult);
        Log.d("Ext_Clear","Exit clearInput method in MainActivity.");
    }// End of clearInput method

    //Method to convert from one unit to another
    private void convert(NoKeyboardEditText tvInput,NoDefaultSpinner spUnitFrom,NoDefaultSpinner spUnitTo){
        Log.d("Ent_Convert","Enter convert method in MainActivity.");
        //Define inner varialbles to be used within the method
        Unit unitFrom = new Unit();
        Unit unitTo = new Unit();
        //Perform verification to check current data is OK for creating a new Unit_Converter object
        //Check the unit from and to have been selected from both spinners
        if(spUnitFrom.getSelectedItem() != null && spUnitTo.getSelectedItem()!= null){
            //Check the input box is not empty
            if(tvInput.getText().toString().equals("")){
                Toast.makeText(this,R.string.EmptyInutFieldMessage,Toast.LENGTH_LONG).show();
            }else{
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
                    //If units selected are different extract unit objects form currentUnits list by passing their names in
                    unitFrom = this.findUnitByName(unitFromName);
                    unitTo = this.findUnitByName(unitToName);
                    //Check the unit and the value are coherent and represent physical magnitudes (Kelvin not negavitive and Celsues not lower than -273.15)
                    if (!this.isValid(unitFrom,unitFromValue)){
                        Toast.makeText(this,R.string.InvalidTemp,Toast.LENGTH_LONG).show();
                    }else{
                        //Try to find a directed link conversion between the two units
                        cursor = this.conversions.getConversion(unitFrom,unitTo);
                        //Check the cursor does not return null or is empty. (This covers conversions between units and its reference within its own system)
                        if(cursor != null && cursor.getCount() > 0){
                            //Create new converter object to calculate the conversion between unitFrom and unitTo
                            Unit_Converter converter = this.createUnitConverter(unitFrom,unitTo,unitFromValue);
                            //Call method to display result on the text view designated for that
                            this.displayResult(converter);
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
                                        //Call the method to display result on the text view designated for that
                                        this.displayResult(converter);
                                    }else{
                                        //Display error message in case the conversion has not been found
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
                                        this.displayResult(converter);
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
                                                this.displayResult(converter);
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
                    }

                }//End of inner if else statement
            }
        }else{
            //Display error message for wrong selection
            Toast.makeText(this,R.string.WrongUnitMessage,Toast.LENGTH_LONG).show();
            //Check if the input field is valid
        }//End of if else statements
        Log.d("Ext_Convert","Exit convert method in MainActivity.");
    }//End of convert method

    //Method to create a Unit_Converter object by using the global cursor object
    private Unit_Converter createUnitConverter(Unit unit1, Unit unit2, String unitFromValue){
        Log.d("Ent_createUnitConv","Enter the createUnitConverter method in MainActivity.");
        //Declare and instantiate null UnitConverter object, this will be the one to be returned
        Unit_Converter converter = null;
        //cursor = this.conversions.getConversion(unit1,unit2);
        //Extract data from cursor (the equation and operation for the conversion)
        String equation = cursor.getString(1);
        String op = cursor.getString(2);
        //Declare and initialize an Operation object to hold the operation enum. Use static method to find the proper operation by passing the name of it
        Operation operation = Operation.getOpByName(op);
        //Create Unit_Converter object which will be able to calculate the convertion and return proper result
        converter = new Unit_Converter(unit1,unit2,equation,operation,unitFromValue);
        Log.d("Ext_createUnitConv","Exit the createUnitConverter method in MainActivity.");
        return converter;
    }// End of createUnitConverter method

    //Method that update the list of current units avaialble in the spinners
    private ArrayList<Unit> updateUnitList(){
        Log.d("Ent_UnitList","Enter updateUnitList method in MainActivity.");
        //Declare and instantiate an ArrayLlist object, the one to be populated and returned by method
        ArrayList<Unit> listOfUnits= new ArrayList<Unit>();
        //Assign new value to global variable cursor by queryin database by passing current property variable as criteria for the quesry
        this.cursor = this.conversions.getUnitsAsPerProperty(this.currentProperty);
        //Call method that extract all the units from cursor and returns an ArrayList with the cursor elements (units) in it
        listOfUnits = this.conversions.listUnits(this.cursor);
        Log.d("Ext_UnitList","Exit updateUnitLIst method in MainActivity.");
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
        //While loop to look for the unit within the unit list
        while (i<this.currentUnits.size()&& !found){
            //Check current unit name value against the name provided via parameter
            if(this.currentUnits.get(i).getName().toLowerCase().equals(unitName.toLowerCase())){
                //If unit names are the same, create a new unit object
                unitFound = new Unit(
                        this.currentUnits.get(i).getId(),
                        this.currentUnits.get(i).getName(),
                        this.currentUnits.get(i).getSymbol(),
                        this.currentUnits.get(i).isReference(),
                        this.currentUnits.get(i).getProperty(),
                        this.currentUnits.get(i).getSystem()
                );
                //Set boolean flag to true
                found = true;
            }//End of if statemet that checks current Unit item
            //Increase the iterator
            i++;
        }//End of while loop
        Log.d("Ext_findUnit","Exit findUnitByname method in MainActivity.");
        return unitFound;
    }//End of findUnitByName method

    //Method to extract name from selected item in spinner
    private String extractSelectedName(NoDefaultSpinner spinner){
        Log.d("Ent_ExtractName","Enter extractSelectedName method in MainActivity.");
        //Declare and instantiate a null string variable to hold the item name, this will be returned by the method
        String itemName = "";
        //Declare and instantiate a cursor object to hold current selected item in the spinner
        Cursor c = (Cursor) spinner.getSelectedItem();
        // Assign to itemName variable the value stored in the selected item in cursor, which column name is "Name"
        itemName = c.getString(c.getColumnIndexOrThrow("Name"));
        Log.d("Ext_ExtractName","Exit extractSelectedName method in MainActivity.");
        return itemName;
    }//End of extractSelectedName method

    //Method to update UI and display result from unit conversion
    private void displayResult(Unit_Converter converter){
        Log.d("Ent_displayResult","Enter displayResult method in MainActivity.");
        //Create strings for the numeric result (already formated)
        String result = df5.format(converter.convertUnit())+" ";
        //Create string for the symbol coming from DB
        String symbol = converter.getUnitTo().getSymbol();
        //Declare s Span object and point to null
        SpannableStringBuilder superScriptString = null;
        //Check if the symbol retrieved form DB has the ^ symbol so it can be removed from symbol string
        if (symbol.contains("^")){
            //Find the index of the ^ symbol
            int index = symbol.indexOf("^");
            //Redefine the result string by concatenating with the modified symbol string
            result = result + symbol.substring(0,index) + symbol.substring(index+1,symbol.length());
            //Instantiate the the span text
            superScriptString = new SpannableStringBuilder(result);
            //Include the superscript format to the spannable text
            superScriptString.setSpan(new SuperscriptSpan(), result.length()-1, result.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            superScriptString.setSpan(new RelativeSizeSpan(0.75f), result.length()-1, result.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }else {
            //If no superscript indicator symbol is defined int he unit's symbol string
            //simply concatenate the result and the correspondin symbol
            result += symbol;
            //Instantiate the span text with current result String
            superScriptString = new SpannableStringBuilder(result);
        }
        //Change Result TextView's text attribute
        this.tvResult.setText(superScriptString);
        Log.d("Ext_displayResult","Exit displayResult method in MainActivity.");
    }//  End of displayResult method.

    //Method to call the AboutActivity
    private void callAboutActivity(View view){
        Log.d("Ent_callAbout","Enter the callAboutActivity method in MainActivity.");
        //Declare and instantiate a new Intent object, passing the AboutActivity class as argument
        Intent i = new Intent(this, AboutActivity.class);
        //Start the activity by passin in the intent
        startActivity(i);
        Log.d("Ext_callAbout","Exit the callAboutActivity method in MainActivity.");
    }//End of callAboutActivity

    //Methos to call the Preferences screen
    private void callPrefernces(View view){
        Log.d("Ent_callPrefernce","Enter the callPreferences method in MainActivity.");
        //Declare and instantiate a new Intent object, passing the PreferencesActivity class as argument
        Intent i = new Intent(this, PreferencesActivity.class);
        //Start the activity by passin in the intent
        startActivity(i);
        Log.d("Ext_callPrefernce","Exit the callPreferences method in MainActivity.");
    }// End of callPreferences method

    //Method to check the input value and the unit seleted respect the physical limits (Absolute zero)
    private boolean isValid(Unit unitFrom, String unitFromValue){
        Log.d("Ent_isValid","Enter isValid method in MainActivity.");
        //Declare a boolean flag to be returned by method
        boolean isValid = true;
        //Perform the check for temperature. At this moment this is the only check done, but more can be added within this method later on
        if ((unitFrom.getName().toLowerCase().equals("kelvin")&& Double.parseDouble(unitFromValue)< ABSOLUTE_ZERO) || (unitFrom.getName().toLowerCase().equals("celsius")&& Double.parseDouble(unitFromValue)< ABSOLUTE_ZERO_CELSIUS)
                || (unitFrom.getName().toLowerCase().equals("fahrenheit")&& Double.parseDouble(unitFromValue)< ABSOLUTE_ZERO_FAHRENHEIT)){
            isValid = false;
        }
        Log.d("Ext_isValid","Exit isValid method in MainActivity.");
        return isValid;
    }//End of isValid method

}//End of MainActivity class
