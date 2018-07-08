package io.github.jlrods.gisescookingunitconverter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by rodjose1 on 21/05/2018.
 */

import java.util.ArrayList;
import java.util.List;

import io.github.jlrods.gisescookingunitconverter.Property;

public class ConversionsDB extends SQLiteOpenHelper {
    Context context;
    //Default constructor
    public ConversionsDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create Property table
        db.execSQL("CREATE TABLE PROPERTY (_id INTEGER PRIMARY KEY AUTOINCREMENT,Name TEXT)");
        //Populate Property table
        db.execSQL("INSERT INTO PROPERTY VALUES(null,'"+Property.WEIGHT.toString()+"')");
        db.execSQL("INSERT INTO PROPERTY VALUES(null,'"+Property.VOLUME.toString()+"')");
        db.execSQL("INSERT INTO PROPERTY VALUES(null,'"+Property.TEMPERATURE.toString()+"')");
        db.execSQL("INSERT INTO PROPERTY VALUES(null,'"+Property.LENGTH.toString()+"')");
        db.execSQL("INSERT INTO PROPERTY VALUES(null,'"+Property.AREA.toString()+"')");
        db.execSQL("INSERT INTO PROPERTY VALUES(null,'"+Property.TIME.toString()+"')");

        //Create UNIT_SYSTEM table
        db.execSQL("CREATE TABLE UNIT_SYSTEM (_id INTEGER PRIMARY KEY AUTOINCREMENT,Name TEXT,Abbrevaiation TEXT)");
        //Populate UNIT_SYSTEM  table
        db.execSQL("INSERT INTO UNIT_SYSTEM VALUES(null,'"+
                Unit_System.INTERNATIONAL_SYSTEM.toString()+"','"+
                Unit_System.INTERNATIONAL_SYSTEM.getAbbrevaiation()+"')");
        db.execSQL("INSERT INTO UNIT_SYSTEM VALUES(null,'"+
                Unit_System.IMPERIAL_SYSTEM.toString()+"','"+
                Unit_System.IMPERIAL_SYSTEM.getAbbrevaiation()+"')");
        db.execSQL("INSERT INTO UNIT_SYSTEM VALUES(null,'"+
                Unit_System.KITCHEN_SYSTEM.toString()+"','"+
                Unit_System.KITCHEN_SYSTEM.getAbbrevaiation()+"')");

        //Create UNIT Table
        db.execSQL("CREATE TABLE UNIT(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Name TEXT, Symbol TEXT, " +
                "IsReference INTEGER, " +
                "PROPERTY_ID INTEGER," +
                "SYSTEM_ID INTEGER)");
        //Populate the UNIT table with different unit instances
        db.execSQL("INSERT INTO UNIT VALUES(null,'grams','g',1,"+Property.WEIGHT.increaseOrdinal()+","+Unit_System.INTERNATIONAL_SYSTEM.increaseOrdinal()+")");//1
        db.execSQL("INSERT INTO UNIT VALUES(null, 'miligrams','mg',0,1,1)");//2
        db.execSQL("INSERT INTO UNIT VALUES(null, 'centigrams','cg',0,1,1)");//3
        db.execSQL("INSERT INTO UNIT VALUES(null, 'decigrams','dg',0,1,1)");//4
        db.execSQL("INSERT INTO UNIT VALUES(null, 'decagrams','Dg',0,1,1)");//5
        db.execSQL("INSERT INTO UNIT VALUES(null, 'hectograms','Hg',0,1,1)");//6
        db.execSQL("INSERT INTO UNIT VALUES(null, 'kilograms','Kg',0,1,1)");//7
        db.execSQL("INSERT INTO UNIT VALUES(null, 'litre','l',1,2,1)");//8
        db.execSQL("INSERT INTO UNIT VALUES(null, 'mililitres','ml',0,2,1)");//9
        db.execSQL("INSERT INTO UNIT VALUES(null, 'centilitres','cl',0,2,1)");//10
        db.execSQL("INSERT INTO UNIT VALUES(null, 'decilitres','dl',0,2,1)");//11
        db.execSQL("INSERT INTO UNIT VALUES(null, 'decalitres','Dl',0,2,1)");//12
        db.execSQL("INSERT INTO UNIT VALUES(null, 'hectolitres','Hl',0,2,1)");//13
        db.execSQL("INSERT INTO UNIT VALUES(null, 'kilolitres','Kl',0,2,1)");//14
        db.execSQL("INSERT INTO UNIT VALUES(null, 'cubic milimetres','mm^3',0,2,1)");//15
        db.execSQL("INSERT INTO UNIT VALUES(null, 'cubic centimetres','cm^3',0,2,1)");//16
        db.execSQL("INSERT INTO UNIT VALUES(null, 'cubic decimetres','dm^3',0,2,1)");//17
        db.execSQL("INSERT INTO UNIT VALUES(null, 'cubic metre','m^3',1,2,1)");//18
        db.execSQL("INSERT INTO UNIT VALUES(null, 'cubic decametres','Dm^3',0,2,1)");//19
        db.execSQL("INSERT INTO UNIT VALUES(null, 'cubic hectometres','Hm^3',0,2,1)");//20
        db.execSQL("INSERT INTO UNIT VALUES(null, 'cubic kilometres','Km^3',0,2,1)");//21
        db.execSQL("INSERT INTO UNIT VALUES(null, 'kelvin','K',1,3,1)");//22
        db.execSQL("INSERT INTO UNIT VALUES(null, 'celsius','°C',0,3,1)");//23
        db.execSQL("INSERT INTO UNIT VALUES(null, 'metre','m',1,4,1)");//24
        db.execSQL("INSERT INTO UNIT VALUES(null, 'milimetres','mm',0,4,1)");//25
        db.execSQL("INSERT INTO UNIT VALUES(null, 'centimetres','cm',0,4,1)");//26
        db.execSQL("INSERT INTO UNIT VALUES(null, 'decimetres','dm',0,4,1)");//27
        db.execSQL("INSERT INTO UNIT VALUES(null, 'decametres','Dm',0,4,1)");//28
        db.execSQL("INSERT INTO UNIT VALUES(null, 'hectometres','Hm',0,4,1)");//29
        db.execSQL("INSERT INTO UNIT VALUES(null, 'kilometres','Km',0,4,1)");//30
        db.execSQL("INSERT INTO UNIT VALUES(null, 'grain','gr',0,1,2)");//31
        db.execSQL("INSERT INTO UNIT VALUES(null, 'drachm','dr',0,1,2)");//32
        db.execSQL("INSERT INTO UNIT VALUES(null, 'ounce','oz',0,1,2)");//33
        db.execSQL("INSERT INTO UNIT VALUES(null, 'pound','lb',1,1,2)");//34
        db.execSQL("INSERT INTO UNIT VALUES(null, 'stone','st',0,1,2)");//35
        db.execSQL("INSERT INTO UNIT VALUES(null, 'quarter','qr',0,1,2)");//36
        db.execSQL("INSERT INTO UNIT VALUES(null, 'hundredweight','cwt',0,1,2)");//37
        db.execSQL("INSERT INTO UNIT VALUES(null, 'ton','t',0,1,2)");//38
        db.execSQL("INSERT INTO UNIT VALUES(null, 'fluid ounce','fl oz',1,2,2)");//39
        db.execSQL("INSERT INTO UNIT VALUES(null, 'gill','gi',0,2,2)");//40
        db.execSQL("INSERT INTO UNIT VALUES(null, 'pint','pt',0,2,2)");//41
        db.execSQL("INSERT INTO UNIT VALUES(null, 'quart','qt',0,2,2)");//42
        db.execSQL("INSERT INTO UNIT VALUES(null, 'gallon','gal',0,2,2)");//43
        db.execSQL("INSERT INTO UNIT VALUES(null, 'fahrenheit','°F',1,3,2)");//44
        db.execSQL("INSERT INTO UNIT VALUES(null, 'inch','in',0,4,2)");//45
        db.execSQL("INSERT INTO UNIT VALUES(null, 'foot','ft',1,4,2)");//46
        db.execSQL("INSERT INTO UNIT VALUES(null, 'yard','yd',0,4,2)");//47
        db.execSQL("INSERT INTO UNIT VALUES(null, 'chain','ch',0,4,2)");//48
        db.execSQL("INSERT INTO UNIT VALUES(null, 'furlong','fur',0,4,2)");//49
        db.execSQL("INSERT INTO UNIT VALUES(null, 'mile','mi',0,4,2)");//50
        db.execSQL("INSERT INTO UNIT VALUES(null, 'league','lea',0,4,2)");//51
        db.execSQL("INSERT INTO UNIT VALUES(null, 'square metre','m^2',1,5,1)");//52
        db.execSQL("INSERT INTO UNIT VALUES(null, 'square milimetres','mm^2',0,5,1)");//53
        db.execSQL("INSERT INTO UNIT VALUES(null, 'square centimetres','cm^2',0,5,1)");//54
        db.execSQL("INSERT INTO UNIT VALUES(null, 'square decimetres','dm^2',0,5,1)");//55
        db.execSQL("INSERT INTO UNIT VALUES(null, 'square decametres','Dm^2',0,5,1)");//56
        db.execSQL("INSERT INTO UNIT VALUES(null, 'square hectometres','Hm^2',0,5,1)");//57
        db.execSQL("INSERT INTO UNIT VALUES(null, 'square kilometres','Km^2',0,5,1)");//58
        db.execSQL("INSERT INTO UNIT VALUES(null, 'hectare','ha',0,5,1)");//59
        db.execSQL("INSERT INTO UNIT VALUES(null, 'perch',null,0,5,2)");//60
        db.execSQL("INSERT INTO UNIT VALUES(null, 'rood',null,0,5,2)");//61
        db.execSQL("INSERT INTO UNIT VALUES(null, 'acre',null,0,5,2)");//62
        db.execSQL("INSERT INTO UNIT VALUES(null, 'square yards','yd^2',0,5,2)");//63
        db.execSQL("INSERT INTO UNIT VALUES(null, 'square inch','in^2',0,5,2)");//64
        db.execSQL("INSERT INTO UNIT VALUES(null, 'square miles','mi^2',0,5,2)");//65
        db.execSQL("INSERT INTO UNIT VALUES(null, 'square foot','ft^2',1,5,2)");//66
        db.execSQL("INSERT INTO UNIT VALUES(null, 'tablespoon','Tbsp',0,2,3)");//67
        db.execSQL("INSERT INTO UNIT VALUES(null, 'teaspoon','tsp',0,2,3)");//68
        db.execSQL("INSERT INTO UNIT VALUES(null, 'cup','cup',1,2,3)");//69

        //Create OPERATIONS table
        db.execSQL("CREATE TABLE OPERATIONS(_id INTEGER PRIMARY KEY AUTOINCREMENT,Name TEXT)");
        //Populate the OPERATIONS table with different math operations to determine the type of relationship between units
        db.execSQL("INSERT INTO OPERATIONS VALUES(null,'"+ MainActivity.Operation.Addition.toString()+"')");
        db.execSQL("INSERT INTO OPERATIONS VALUES(null,'"+ MainActivity.Operation.Multiplication.toString()+"')");
        db.execSQL("INSERT INTO OPERATIONS VALUES(null,'"+ MainActivity.Operation.Subtraction.toString()+"')");
        db.execSQL("INSERT INTO OPERATIONS VALUES(null,'"+ MainActivity.Operation.Division.toString()+"')");
        db.execSQL("INSERT INTO OPERATIONS VALUES(null,'"+ MainActivity.Operation.Convined.toString()+"')");

        //Create CONVERSION table
        db.execSQL("CREATE TABLE CONVERSION(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "UnitFrom INTEGER, " +
                "UnitTo INTEGER, " +
                "Equation TEXT, " +
                "OPERATION_ID INTEGER)");
        //Populate the CONVERSION table with different relationships between units
        //Property: Weight (SI)
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 1,2,'1000',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 2,1,'0.001',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 1,3,'100',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 3,1,'0.01',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 1,4,'10',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 4,1,'0.1',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 1,5,'0.1',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 5,1,'10',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 1,6,'0.01',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 6,1,'100',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 1,7,'0.001',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 7,1,'1000',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        //Property: Volume (SI)
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 8,9,'1000',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 9,8,'0.001',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 8,10,'100',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 10,8,'0.01',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 8,11,'10',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 11,8,'0.1',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 8,12,'0.1',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 12,8,'10',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 8,13,'0.01',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 13,8,'100',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 8,14,'0.001',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 14,8,'1000',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 8,18,'0.001',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 18,8,'1000',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 18,15,'1000000000',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 15,18,'1000000000',"+MainActivity.Operation.Division.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 18,16,'1000000',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 16,18,'1000000',"+MainActivity.Operation.Division.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 18,17,'1000',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 17,18,'0.001',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 18,19,'0.001',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 19,18,'1000',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 18,20,'0.000001',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 20,18,'1000000',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 18,21,'0.000000001',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 21,18,'1000000000',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        //Property: Temperature (SI)
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 22,23,'273.15',"+MainActivity.Operation.Subtraction.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 23,22,'273.15',"+MainActivity.Operation.Addition.increaseOrdinal()+")");
        //Property: Length (SI)
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 24,25,'1000',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 25,24,'0.001',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 24,26,'100',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 26,24,'0.01',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 24,27,'10',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 27,24,'0.1',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 24,28,'0.1',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 28,24,'10',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 24,29,'0.01',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 29,24,'100',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 24,30,'0.001',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 30,24,'1000',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        //Property: Weight (Imperial system)
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 34,31,'7000',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 31,34,'7000',"+MainActivity.Operation.Division.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 34,32,'256',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 32,34,'256',"+MainActivity.Operation.Division.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 34,33,'16',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 33,34,'16',"+MainActivity.Operation.Division.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 35,34,'14',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 34,35,'14',"+MainActivity.Operation.Division.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 34,36,'28',"+MainActivity.Operation.Division.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 36,34,'28',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 34,37,'112',"+MainActivity.Operation.Division.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 37,34,'112',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 34,38,'2240',"+MainActivity.Operation.Division.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 38,34,'2240',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        //Property: Volume (Imperial system)
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 39,40,'5',"+MainActivity.Operation.Division.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 40,39,'5',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 39,41,'20',"+MainActivity.Operation.Division.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 41,39,'20',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 39,42,'40',"+MainActivity.Operation.Division.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 42,39,'40',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 39,43,'160',"+MainActivity.Operation.Division.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 43,39,'160',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        //Property: Length (Imperial system)
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 45,46,'12',"+MainActivity.Operation.Division.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 46,45,'12',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 46,47,'3',"+MainActivity.Operation.Division.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 47,46,'3',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 46,48,'66',"+MainActivity.Operation.Division.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 48,46,'66',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 46,49,'660',"+MainActivity.Operation.Division.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 49,46,'660',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 46,50,'5280',"+MainActivity.Operation.Division.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 50,46,'5280',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 46,51,'15840',"+MainActivity.Operation.Division.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 51,46,'15840',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        //Property: Area (SI)
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 52,53,'1000000',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 53,52,'0.000001',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 52,54,'10000',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 54,52,'0.0001',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 52,55,'100',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 55,52,'0.01',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 52,56,'0.01',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 56,52,'100',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 52,57,'0.0001',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 57,52,'10000',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 52,58,'0.000001',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 58,52,'1000000',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 59,52,'10000',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 52,59,'0.0001',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        //Property: Area (Imperial System)
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 66,60,'272.25',"+MainActivity.Operation.Division.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 60,66,'272.25',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 66,61,'10890',"+MainActivity.Operation.Division.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 61,66,'10890',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 66,62,'43560',"+MainActivity.Operation.Division.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 62,66,'43560',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 66,63,'9',"+MainActivity.Operation.Division.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 63,66,'9',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 66,64,'144',"+MainActivity.Operation.Division.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 64,66,'144',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 66,65,'33872400',"+MainActivity.Operation.Division.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 65,66,'33872400',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        //Inter system conversions
        //Property: Temperature (SI - Imperial)
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 44,22,'(UnitFrom + 459.67)/1.8',"+MainActivity.Operation.Convined.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 22,44,'(UnitFrom*1.8)-459.67',"+MainActivity.Operation.Convined.increaseOrdinal()+")");
        //Property: Weight (SI - Imperial)
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 34,1,'453.59237',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 1,34,'453.59237',"+MainActivity.Operation.Division.increaseOrdinal()+")");
        //Property: Volume (SI - Imperial)
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 8,39,'0.0284131',"+MainActivity.Operation.Division.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 39,8,'0.0284131',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 18,39,'35195.03328',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 39,18,'35195.03328',"+MainActivity.Operation.Division.increaseOrdinal()+")");
        //Property: Length (SI - Imperial)
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 24,46,'0.3048',"+MainActivity.Operation.Division.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 46,24,'0.3048',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        //Property: Area (SI - Imperial)
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 52,66,'0.09290304',"+MainActivity.Operation.Division.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 66,52,'0.09290304',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        //Property: Volume (KS)
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 69,67,'48',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 67,69,'48',"+MainActivity.Operation.Division.increaseOrdinal()+")");
        //Property: Volume (KS)
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 69,68,'16',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 68,69,'16',"+MainActivity.Operation.Division.increaseOrdinal()+")");
        //Property: Volume (KS - SI)
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 69,8,'0.284131',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 8,69,'0.284131',"+MainActivity.Operation.Division.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 69,18,'0.000284131',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 18,69,'0.000284131',"+MainActivity.Operation.Division.increaseOrdinal()+")");
        //Property: Volume (KS - Imperial)
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 69,39,'10',"+MainActivity.Operation.Multiplication.increaseOrdinal()+")");
        db.execSQL("INSERT INTO CONVERSION VALUES(null, 39,69,'10',"+MainActivity.Operation.Division.increaseOrdinal()+")");
    }// End of OnCreate method

    //Method to extract the units in DB that match the Property_ID . Returns cursor with data
    public Cursor getUnitsAsPerProperty(Property property){
        String query = "SELECT * FROM UNIT WHERE PROPERTY_ID = ";
        //SQLiteDatabase db = getReadableDatabase();
        switch (property){
            case WEIGHT:
                query += Property.WEIGHT.increaseOrdinal();
                break;
            case VOLUME:
                query += Property.VOLUME.increaseOrdinal();
                break;
            case TEMPERATURE:
                query += Property.TEMPERATURE.increaseOrdinal();
                break;
            case AREA:
                query += Property.AREA.increaseOrdinal();
                break;
            case LENGTH:
                query += Property.LENGTH.increaseOrdinal();
                break;
            default:
                query = "SELECT * FROM UNIT";
                break;
        }// End of switch
        Cursor cursor =  runQuery(query);
        return cursor;
    }

    //Method to extract the units in DB that match the PropertyName. Returns cursor with data. Calls previus Method.
    public Cursor getUnitsAsPerProperty(String propertyName){
        Property property;
        switch (propertyName.toUpperCase()){
            case "WEIGHT":
                property = Property.WEIGHT;
                break;
            case "VOLUME":
                property = Property.VOLUME;
                break;
            case "TEMPERATURE":
                property = Property.TEMPERATURE;
                break;
            case "AREA":
                property = Property.AREA;
                break;
            case "LENGTH":
                property = Property.LENGTH;
                break;
            default:
                property =  Property.TIME;
                break;
        }// End of switch
        Cursor cursor =  getUnitsAsPerProperty(property);
        return cursor;
    }// end of getUnitsAsPerProperty

    //Method to extract a Unit from a cursor
    public Unit extractUnit(Cursor cursor){
        Log.d("Ent_extractUnit","Enter extractUnit method.");
        int _id;
        String name;
        String symbol;
        int isRefereceInt = 0;
        boolean isReferece = false;
        Property property;
        Unit_System system;
        Unit unit;
        _id = cursor.getInt(0);
        name = cursor.getString(1);
        symbol = cursor.getString(2);
        isRefereceInt = cursor.getInt(3);
        if(isRefereceInt==1){
            isReferece = true;
        }else{
            isReferece = false;
        }
        property = Property.findProperty(cursor.getInt(4)-1);
        system = Unit_System.findSystem(cursor.getInt(5)-1);
        unit = new Unit(_id,name,symbol,isReferece,property,system);
        Log.d("Ext_extractUnit","Exit extractUnit method.");
        return unit;
    }//End of extractUnit method

    // Method to extract the units in DB that match the System_ID . Returns cursor with data
    public Cursor getUnitsAsPerSystem(Unit unit){
        String query = "SELECT * FROM UNIT WHERE SYSTEM_ID = ";
        switch(unit.getSystem()){
            case INTERNATIONAL_SYSTEM:
                query += Unit_System.INTERNATIONAL_SYSTEM.increaseOrdinal();
                break;
            case IMPERIAL_SYSTEM:
                query += Unit_System.IMPERIAL_SYSTEM.increaseOrdinal();
                break;
            default:
                query += Unit_System.KITCHEN_SYSTEM.increaseOrdinal();
                break;

        }//End of switch
        Cursor cursor =  runQuery(query);
        return cursor;
    }//End of getUnitsAsPerSystem

    //Method to return the conversion between units (When a direct link exists between units)
    public Cursor getConversion(Unit unitFrom, Unit unitTo){
        Log.d("Ent_getConversion","Enter getConvertion method.");
        String query = "SELECT CONVERSION._id," +
                //"(SELECT UNIT.Name  FROM CONVERSION JOIN UNIT " +
                //"ON  CONVERSION.UnitFrom = UNIT._id WHERE UnitFrom = "+ unitFrom.getId()+") AS Unit_From, " +
                //"(SELECT UNIT.Name  FROM CONVERSION JOIN UNIT " +
                //"ON  CONVERSION.UnitTo = UNIT._id WHERE UnitTo = "+unitTo.getId()+") AS Unit_To, " +
                "CONVERSION.Equation, OPERATIONS.Name AS Operation " +
                "FROM CONVERSION "+
                "JOIN OPERATIONS " +
                "ON OPERATIONS._id = CONVERSION.OPERATION_ID " +
                //"JOIN UNIT " +
                //"ON CONVERSION.UnitTo = UNIT._id " +
                "WHERE CONVERSION.UnitFrom = "+ unitFrom.getId()+ " AND CONVERSION.UnitTo = "+unitTo.getId();
        //SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = runQuery(query);
        //Check the cursor does not return null or is empty
        if (cursor == null || cursor.getCount()== 0){
            //if cursor returns null or is empty try the other way around: unitFrom, unitTo
            query = "SELECT CONVERSION._id," +
                    "CONVERSION.Equation, OPERATIONS.Name AS Operation " +
                    "FROM CONVERSION "+
                    "JOIN OPERATIONS " +
                    "ON OPERATIONS._id = CONVERSION.OPERATION_ID " +
                    "WHERE CONVERSION.UnitFrom = "+ unitTo.getId() + " AND CONVERSION.UnitTo = "+ unitFrom.getId();
            //Run the new sql query
            cursor = runQuery(query);
        }// End of if statement to check the cursor is not null or empty
        Log.d("Ext_getConversion","Exit getConversion method.");
        return cursor;
    }// End of getConversion method

    //Method to return conversion between unit and a reference
    public Unit getReference(Unit unit){
        Log.d("Ent_getConversionToRef","Enter getConversionToReference method.");
        Unit reference = new Unit();
        //Find the reference corresponding to this system and property
        //Define the SQL query to find the reference unit
        String query = "SELECT * FROM UNIT WHERE IsReference = 1 AND SYSTEM_ID = " + unit.getSystem().increaseOrdinal()+" AND PROPERTY_ID = "+unit.getProperty().increaseOrdinal();
        //Run the query en retrieve the result via cursor object
        Cursor cursor = runQuery(query);
        //Check the query does not retrieve a null value or is empty
        if(cursor != null && cursor.getCount()>0){
            //Check that there is only one reference for this property and system convination (could be more than one reference)
            if(cursor.getCount()==1){
                //Extract the reference unit from the only row returned
                reference = extractUnit(cursor);
            }else{
                //Define which reference will be extracted
                if(unit.getName().contains("litre")){
                    reference = extractUnit(cursor);
                }else{
                    cursor.moveToNext();
                    reference = extractUnit(cursor);
                }//End of if else statement to check the reference to be extracted
            }// End of if else statement to check the number of reference available
            //get the conversion for unit to reference

        }// End of if else statement to check the cursor does not return null or is empty
        Log.d("Ext_getConversionToRef","Exit getConversionToReference method.");
        return reference;
    }//End of getConversionToReference method

    private Cursor runQuery(String query){
        Log.d("Ent_runQuery","Enter runQuery method.");
        Cursor cursor = null;
        SQLiteDatabase db = getReadableDatabase();
        cursor = db.rawQuery(query,null);
        cursor.moveToFirst();
        Log.d("Ext_runQuery","Exit runQuery method.");
        return cursor;
    }

    //Method to extract a list of units
    public ArrayList<Unit> listUnits(Cursor cursor){
        ArrayList<Unit> unitList = new ArrayList<Unit>();
        int _id;
        String name;
        String symbol;
        int isRefereceInt = 0;
        boolean isReferece = false;
        Property property;
        Unit_System system;
        cursor.moveToPrevious();
        while (cursor.moveToNext()) {
            /*_id = cursor.getInt(0);
            name = cursor.getString(1);
            symbol = cursor.getString(2);
            isRefereceInt = cursor.getInt(3);
            if(isRefereceInt==1){
                isReferece = true;
            }else{
                isReferece = false;
            }
            property = Property.findProperty(cursor.getInt(4)-1);
            system = Unit_System.findSystem(cursor.getInt(5)-1);*/
            Unit unit = extractUnit(cursor);
            unitList.add(unit);
        }
        return unitList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
