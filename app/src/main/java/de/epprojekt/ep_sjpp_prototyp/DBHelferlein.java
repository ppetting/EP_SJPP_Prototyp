package de.epprojekt.ep_sjpp_prototyp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ImageButton;
import java.util.ArrayList;
import java.util.Collections;


public class DBHelferlein extends SQLiteOpenHelper {

    final String warenkorb = "Warenkorb";
    final String sortiment = "Sortiment";
    final String userdaten = "Userdaten";
    SQLiteDatabase Einkaufsdatenbank;
    int countWarenkorb = 1;
    int countUserdaten = 1;

    public DBHelferlein(Context context) {
        super(context, "Einkaufsdatenbank.db", null, 1);
    }

    @SuppressLint("SQLiteString")
    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL("CREATE TABLE IF NOT EXISTS Warenkorb(id_key INTEGER primary key, btnID INTEGER, bildwert INTEGER, itemname STRING)");
        db.execSQL("CREATE TABLE IF NOT EXISTS Sortiment(id_key INTEGER primary key, bildname TEXT, bild BLOB,flag TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS Userdaten(id_key INTEGER primary key, username STRING, flaggruen INTEGER, flagblau INETGER, flagrot INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE if EXISTS " + warenkorb);
        db.execSQL("DROP TABLE if EXISTS " + sortiment);
        db.execSQL("DROP TABLE if EXISTS " + userdaten);
    }

    //Warenkorb OnClick erstellen
    @SuppressLint("SQLiteString")
    public void createWarenkorbOnClick(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("CREATE TABLE IF NOT EXISTS Warenkorb"+name+"(id_key INTEGER primary key, btnID INTEGER, bildwert INTEGER, itemname STRING)");
        close();
    }

    //WARENKORB FUNKTIONEN
    public long insertIntoWarenkorb(ImageButton ibtn, Integer bildInteger, String itemname, String username) {

        SQLiteDatabase database = this.getWritableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM Warenkorb" + username, null);

        if (countWarenkorb >= 1) {
            countWarenkorb = cursor.getCount() + 1;
        }

        Integer btnid = ibtn.getId();

        ContentValues contentValues = new ContentValues();
        contentValues.put("id_key", countWarenkorb);
        contentValues.put("btnID", btnid);
        contentValues.put("bildwert", bildInteger);
        contentValues.put("itemname", itemname);

        countWarenkorb++;
        cursor.close();

        return database.insert(warenkorb, null, contentValues);

    }

    public void deleteCompletefromWarenkorb() {
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(this.warenkorb, null, null);
        database.close();
    }

    public void deleteIndividuallyfromWarenkorb(String itemname_local) {
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(this.warenkorb, "itemname = ?", new String[]{itemname_local});
        database.close();
    }


    public ArrayList<Integer> createArrayListOfWarenkorb() {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT bildwert FROM Warenkorb", null);
        cursor.moveToFirst();

        ArrayList<Integer> arrayOfWarenkorbItems = new ArrayList<>();

        if(cursor.getCount() == 0){
            arrayOfWarenkorbItems.clear();
        }

        while(!cursor.isAfterLast()){
            arrayOfWarenkorbItems.add(cursor.getInt(cursor.getColumnIndexOrThrow("bildwert")));
            cursor.moveToNext();
        }

        Collections.sort(arrayOfWarenkorbItems);

        cursor.close();
        return arrayOfWarenkorbItems;
    }

    public ArrayList<String> createArrayListOfWarenkorbItems() {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT itemname FROM Warenkorb", null);
        cursor.moveToFirst();

        ArrayList<String> arrayOfWarenkorbItemsNAME = new ArrayList<>();

        if(cursor.getCount() == 0){
            arrayOfWarenkorbItemsNAME.clear();
        }

        while(!cursor.isAfterLast()){
            arrayOfWarenkorbItemsNAME.add(cursor.getString(cursor.getColumnIndexOrThrow("itemname")));
            cursor.moveToNext();
        }

        Collections.sort(arrayOfWarenkorbItemsNAME);

        cursor.close();
        return arrayOfWarenkorbItemsNAME;
    }


    //USERDATEN FUNKTIONEN
    public long insertIntoUserdaten(String username, Integer flaggruen, Integer flagblau, Integer flagrot) {

        SQLiteDatabase database = this.getWritableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM " + userdaten, null);

        if (countUserdaten >= 1) {
            countUserdaten = cursor.getCount() + 1;
        }

        ContentValues contentValues = new ContentValues();
        contentValues.put("id_key", countUserdaten);
        contentValues.put("username", username);
        contentValues.put("flaggruen", flaggruen);
        contentValues.put("flagrot", flagrot);
        contentValues.put("flagblau", flagblau);

        countUserdaten++;
        cursor.close();

        return database.insert(userdaten, null, contentValues);

    }

    public ArrayList<String> createArrayListOfUserdaten() {

        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = database.rawQuery("SELECT username FROM Userdaten", null);
        cursor.moveToFirst();

        ArrayList<String> arrayOfUsers = new ArrayList<>();

        if(cursor.getCount() == 0){
            arrayOfUsers.clear();
        }

        while(!cursor.isAfterLast()){
            arrayOfUsers.add(cursor.getString(cursor.getColumnIndexOrThrow("username")));
            cursor.moveToNext();
        }

        Collections.sort(arrayOfUsers);

        cursor.close();
        return arrayOfUsers;
    }

    public void deletefromUserdaten(String username) {
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(this.userdaten, "username = ?", new String[]{username});
    }

}



