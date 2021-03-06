package de.epprojekt.ep_sjpp_prototyp.Helferlein;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageButton;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

public class DBHelferlein extends SQLiteOpenHelper {

    final String warenkorb = "Warenkorb";
    final String sortiment = "Sortiment";
    final String userdaten = "Userdaten";
    final String flaggruen = "flaggruen";
    final String flagblau = "flagblau";
    final String flagrot = "flagrot";
    int countUserdaten = 1;


    public DBHelferlein(Context context) {
        super(context, "Einkaufsdatenbank.db", null, 1);
    }

    @SuppressLint("SQLiteString")
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS Sortiment(id_key INTEGER primary key, bildname STRING, bild BLOB, flag STRING)");
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

        contentValues.put("id_key", 1); contentValues.put("bildname", "roterApfel"); contentValues.put("bild",""); contentValues.put("flag", flaggruen);
        database.insert(sortiment, null, contentValues);

        contentValues.put("id_key", 2); contentValues.put("bildname", "gruenerApfel"); contentValues.put("bild", ""); contentValues.put("flag", flaggruen);
        database.insert(sortiment, null, contentValues);

        contentValues.put("id_key", 3); contentValues.put("bildname", "Salatgurke"); contentValues.put("bild", ""); contentValues.put("flag", flaggruen);
        database.insert(sortiment, null, contentValues);

        contentValues.put("id_key", 4); contentValues.put("bildname", "Hartkaese"); contentValues.put("bild", ""); contentValues.put("flag", flagrot);
        database.insert(sortiment, null, contentValues);

        contentValues.put("id_key", 5); contentValues.put("bildname", "Streichkaese"); contentValues.put("bild", ""); contentValues.put("flag", flagrot);
        database.insert(sortiment, null, contentValues);

        contentValues.put("id_key", 6); contentValues.put("bildname", "Kaeseaufschnitt"); contentValues.put("bild", ""); contentValues.put("flag", flagrot);
        database.insert(sortiment, null, contentValues);

        contentValues.put("id_key", 7); contentValues.put("bildname", "sechserPackungEier"); contentValues.put("bild", ""); contentValues.put("flag", flagblau);
        database.insert(sortiment, null, contentValues);

        contentValues.put("id_key", 8); contentValues.put("bildname", "zehnerPackungEier"); contentValues.put("bild",""); contentValues.put("flag", flagblau);
        database.insert(sortiment, null, contentValues);
    }

    @SuppressLint("SQLiteString")
    public void createWarenkorbOnClick(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("CREATE TABLE IF NOT EXISTS Warenkorb"+name+"(btnID INTEGER, bildwert INTEGER, itemnamekey STRING primary key, itemname STRING)");
        close();
    }

    public long insertIntoWarenkorb(ImageButton ibtn, Integer bildInteger, String itemnamekey, String itemname, String username) {

        SQLiteDatabase database = this.getWritableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM Warenkorb" +username, null);

        Integer btnid = ibtn.getId();

        ContentValues contentValues = new ContentValues();
        contentValues.put("btnID", btnid);
        contentValues.put("bildwert", bildInteger);
        contentValues.put("itemnamekey", itemnamekey);
        contentValues.put("itemname", itemname);

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
        database.delete(this.warenkorb+username, "itemnamekey =?", new String[]{itemname_local});
        database.close();
    }

    public ArrayList<String> createArrayListOfWarenkorbItems(String username) {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT itemnamekey FROM Warenkorb" +username, null);
        cursor.moveToFirst();

        ArrayList<String> arrayOfWarenkorbItemsNAME = new ArrayList<>();

        if(cursor.getCount() == 0){
            arrayOfWarenkorbItemsNAME.clear();
        }

        while(!cursor.isAfterLast()){
            arrayOfWarenkorbItemsNAME.add(cursor.getString(cursor.getColumnIndexOrThrow("itemnamekey")));
            cursor.moveToNext();
        }

        Collections.sort(arrayOfWarenkorbItemsNAME);

        cursor.close();
        return arrayOfWarenkorbItemsNAME;
    }

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

    public String fetchSortiment(String produktname) {
       SQLiteDatabase database = this.getWritableDatabase();
       Cursor cursor = database.query(sortiment, new String[]{"flag" }, "bildname =?", new String[]{produktname }, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor.getString(0);
    }

    public Integer getFlaganzahl(String aktuellerUser,String flag) {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.query(userdaten, new String[]{flag}, "username =?", new String[]{aktuellerUser }, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor.getInt(0);
    }

    public String getFlaganzahlString(String aktuellerUser,String flag) {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.query(userdaten, new String[]{flag}, "username =?", new String[]{aktuellerUser }, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor.getString(0);
    }

    public String getWarenkorbItemname (String itemnamekey, String aktuellerUser) {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.query(warenkorb + aktuellerUser, new String[]{"itemname"}, "itemnamekey =?", new String[]{itemnamekey}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor.getString(0);
    }

    public Integer flagCountEinkaufswagen(String flagart, String aktivernutzer) {
        int count = 0;
        int x = 0;

        ArrayList<String> arrayListOfWarenkorbitems = this.createArrayListOfWarenkorbItems(aktivernutzer);

        while (x < arrayListOfWarenkorbitems.size()) {
            String itemname = getWarenkorbItemname(arrayListOfWarenkorbitems.get(x),aktivernutzer);
            String itemflag = fetchSortiment(itemname);

            if (flagart.equals(itemflag)) {
                count++;
                x++;
            } else {
                x++;
            }
        }
        return count;
    }

    public Boolean darfHinzugefuegtWerden(String produktname, String aktiverNutzer){
        Integer countEinkaufswagen = flagCountEinkaufswagen(fetchSortiment(produktname),aktiverNutzer);
        Integer personenmaximum = getFlaganzahl(aktiverNutzer, fetchSortiment(produktname));
        if(countEinkaufswagen < personenmaximum){
            return true;
        } else return false;
    }

    public byte[] getDrawableFromTable(String bildname){

        SQLiteDatabase database = this.getWritableDatabase();

        Cursor cursor = database.query(sortiment, new String[]{"bild"},"bildname =?",new String[]{bildname},null,null,null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        assert cursor != null;
        return cursor.getBlob(0);
    }

    public void setDrawableFromGallery(String bildname, byte[] bildID){

        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("bild", bildID);

        database.update(sortiment,contentValues,"bildname =?",new String[]{bildname});

    }

    public boolean isEmpty(){
        SQLiteDatabase database = this.getReadableDatabase();

        long NoOfRows = DatabaseUtils.queryNumEntries(database,userdaten);

        if (NoOfRows == 0){
            return true;
        } else {
            return false;
        }
    }

    public byte[] drawableToByteArray (Context context, Integer i) {
        InputStream inputStream = context.getResources().openRawResource(i);
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
        bitmap = getResizedBitmap(bitmap,400);
        return getBytes(bitmap);
    }

    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }

    public static Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float)width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

}



