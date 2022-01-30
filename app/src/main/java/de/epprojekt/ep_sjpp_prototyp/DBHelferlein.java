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
    int countWarenkorb = 1;
    int countUserdaten = 1;

    public DBHelferlein(Context context) {
        super(context, "Einkaufsdatenbank.db", null, 1);
    }

    @SuppressLint("SQLiteString")
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS Warenkorb(id_key INTEGER primary key, btnID INTEGER, bildwert INTEGER)");
        db.execSQL("CREATE TABLE IF NOT EXISTS Sortiment(id_key INTEGER primary key, bildname TEXT, bild BLOB,flag TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS Userdaten(id_key INTEGER primary key, username STRING, flaggruen INTEGER, flagblau INETGER, flagrot INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE if EXISTS " + warenkorb);
        db.execSQL("DROP TABLE if EXISTS " + sortiment);
        db.execSQL("DROP TABLE if EXISTS " + userdaten);
    }

    //WARENKORB FUNKTIONEN
    public long insertIntoWarenkorb(ImageButton ibtn, Integer bildInteger) {

        SQLiteDatabase database = this.getWritableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM " + warenkorb, null);

        if (countWarenkorb >= 1) {
            countWarenkorb = cursor.getCount() + 1;
        }

        Integer btnid = ibtn.getId();

        ContentValues contentValues = new ContentValues();
        contentValues.put("id_key", countWarenkorb);
        contentValues.put("btnID", btnid);
        contentValues.put("bildwert", bildInteger);

        countWarenkorb++;
        cursor.close();

        return database.insert(warenkorb, null, contentValues);

    }

    public void deletefromWarenkorb() {
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(this.warenkorb, null, null);
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


}



