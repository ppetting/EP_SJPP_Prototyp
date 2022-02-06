package de.epprojekt.ep_sjpp_prototyp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;


public class DBHelferlein extends SQLiteOpenHelper {

    final String warenkorb = "Warenkorb";
    final String sortiment = "Sortiment";
    final String userdaten = "Userdaten";
    final String flaggruen = "flaggruen";
    final String flagblau = "flagblau";
    final String flagrot = "flagrot";
    final String flagweiss = "flagweiss";
    int countWarenkorb = 1;
    int countUserdaten = 1;

    public DBHelferlein(Context context) {
        super(context, "Einkaufsdatenbank.db", null, 1);
    }

    @SuppressLint("SQLiteString")
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS Sortiment(id_key INTEGER primary key, bildname STRING, bild BLOB,flag STRING)");
        db.execSQL("CREATE TABLE IF NOT EXISTS Userdaten(id_key INTEGER primary key, username STRING, flaggruen INTEGER, flagblau INETGER, flagrot INTEGER)");
        startInsertIntoSortiment(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE if EXISTS " + sortiment);
        db.execSQL("DROP TABLE if EXISTS " + userdaten);
    }

    //Sortiment Stuff
    public void startInsertIntoSortiment(SQLiteDatabase database){

        ContentValues contentValues = new ContentValues();

        contentValues.put("id_key", 1); contentValues.put("bildname", "roter Apfel"); contentValues.put("bild", R.drawable.apfel); contentValues.put("flag", flaggruen);
        database.insert(sortiment, null, contentValues);

        contentValues.put("id_key", 2); contentValues.put("bildname", "gruener Apfel"); contentValues.put("bild", R.drawable.apfelgruen); contentValues.put("flag", flaggruen);
        database.insert(sortiment, null, contentValues);

        contentValues.put("id_key", 3); contentValues.put("bildname", "Salatgurke"); contentValues.put("bild", R.drawable.gurke); contentValues.put("flag", flaggruen);
        database.insert(sortiment, null, contentValues);

        contentValues.put("id_key", 4); contentValues.put("bildname", "Hartkaese"); contentValues.put("bild", R.drawable.cheese); contentValues.put("flag", flagrot);
        database.insert(sortiment, null, contentValues);

        contentValues.put("id_key", 5); contentValues.put("bildname", "Streichkaese"); contentValues.put("bild", R.drawable.cheeseweich); contentValues.put("flag", flagrot);
        database.insert(sortiment, null, contentValues);

        contentValues.put("id_key", 6); contentValues.put("bildname", "Kaeseaufschnitt"); contentValues.put("bild", R.drawable.cheeseslice); contentValues.put("flag", flagrot);
        database.insert(sortiment, null, contentValues);

        contentValues.put("id_key", 7); contentValues.put("bildname", "sechserPackungEier"); contentValues.put("bild", R.drawable.eier6); contentValues.put("flag", flagblau);
        database.insert(sortiment, null, contentValues);

        contentValues.put("id_key", 8); contentValues.put("bildname", "zehnerPackungEier"); contentValues.put("bild", R.drawable.eier10); contentValues.put("flag", flagblau);
        database.insert(sortiment, null, contentValues);

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

        Cursor cursor = database.rawQuery("SELECT * FROM Warenkorb" +username, null);

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

        return database.insert(warenkorb+username, null, contentValues);

    }

    public void deleteCompletefromWarenkorb(String name) {
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(this.warenkorb+name, null, null);
        database.close();
    }

    public void deleteIndividuallyfromWarenkorb(String itemname_local, String username) {
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(this.warenkorb+username, "itemname = ?", new String[]{itemname_local});
        database.close();
    }


    public ArrayList<Integer> createArrayListOfWarenkorb(String username) {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT bildwert FROM Warenkorb" + username, null);
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

    public ArrayList<String> createArrayListOfWarenkorbItems(String username) {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT itemname FROM Warenkorb" +username, null);
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
        database.execSQL("DROP TABLE if EXISTS " + warenkorb+username);
        database.delete(this.userdaten, "username = ?", new String[]{username});
        database.close();
    }


    // Sucht Flag (ob grün,rot,blau) zum entsprechenden Produkt
    public Cursor fetchSortiment(String produktname) {
       SQLiteDatabase database = this.getWritableDatabase();
       Cursor cursor = database.query(sortiment, new String[]{"flag" }, "bildname =?", new String[]{produktname }, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    // Sucht Flaganzahl einer bestimmten Flagart
    public Cursor fetchUserFlagAnzahl(String aktuellerUser,String flag) {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.query(userdaten, new String[]{flag}, "username =?", new String[]{aktuellerUser }, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
}



