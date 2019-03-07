# GisesCookingunitconverter

## Project AIM
This project had as main objective to work as first experience and introduction into developing my first Android app, which involved defining  the required layouts for the user interface and programming the code behing to control the app's behaviour.
## Description
This app is a unit converter to transform most common units usded in the cooking environment in terms of the following properties: mass, volume and temperature.

This app is made of three basic activities, the MainActivity, AboutActivity and PreferencesActivity. Most of operations are handled in the MainActivity, where the a property can be selected at the top of the app. The selected property image (mass, volume or temperature) will be highlighted in blue and depending on the selection, the two dropdown menus (From and To) will display the proper units to select the desired units to convert from and to.

An input field is available, where the number to convert the From unit can be input by using the app's numerical pad (this means the default os' keyboard cannot be used as an input method). This was designed this way to minimize the risk of inputting invalid text. Finally, a Convert button is avilable to perform the operation of transforming from one unit to the other. The result is displayed in a text fiel located between the input field and the num pad.

## Installation
### Minimun requirements
- Android device running Android Ice Cream Sandwich - Version 4.03 - AP1 15 or higher
### How to install
Instructions only valid for Android device:
1. Go to this [link](https://jlrods.github.io/Downloads.html)
2. Look for Cooking Unit Converter(V1.2) software in the list and click on it to start the download.
3. Open the gisesCookingUnitConverterV1.2.zip file by tapping the file.
4. Follow the on screen instructions and click on Install.
5. That's all! you can now test the Gise's Cooking Unit Converter software and transform some units from one system of measurement to another.

## Lessons Learnt
- Use of intents and call different activities.
- Apply saved app preferences within the onCreate method, so user customizations can be applied every time the MainActivity object gets created.
- Familiarize with SQLite3 to create and populate a DB as well as retrieve data from it (Use of SQLiteOpenHelper class).
- Populate Spinner objects (dropdown menus) and use CursorAdapter to populate data coming from the DB (the units for each property)into this elements.
- Define portrait and landscape layouts.
- Save and restore app state via onSaveInstanceState and onRestoreInstanceState methods.
- Use of SpannableStringBuilder to display formatted text in TextView objects.
- Use of Logcat and the Log class.

