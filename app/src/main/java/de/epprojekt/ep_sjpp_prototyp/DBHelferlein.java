package de.epprojekt.ep_sjpp_prototyp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.drawable.Drawable;
import android.widget.ImageButton;

import java.util.ArrayList;


public class DBHelferlein extends SQLiteOpenHelper {

    final String warenkorb = "Warenkorb";
    int count = 1;

    public DBHelferlein(Context context) {
        super(context, "Sortiment.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE Table Warenkorb(id_key INTEGER primary key, btnID INTEGER, bild STRING)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE if EXISTS " + warenkorb);
    }

    public long insertIntoWarenkorb(ImageButton ibtn) {

        SQLiteDatabase database = this.getWritableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM " + warenkorb, null);


        if (count >= 1) {
            count = cursor.getCount() + 1;
        }

        Drawable bildpfad = ibtn.getDrawable();
        Integer btnid = ibtn.getId();


        ContentValues contentValues = new ContentValues();
        contentValues.put("id_key", count);
        contentValues.put("btnID", btnid);
        contentValues.put("bild", String.valueOf(bildpfad));

        count++;
        cursor.close();

        return database.insert(warenkorb, null, contentValues);

    }

    public void deletefromWarenkorb() {
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(this.warenkorb, null, null);
    }


    public ArrayList<String> createArrayListOfWarenkorb() {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT bild FROM Warenkorb", null);
        cursor.moveToFirst();
        ArrayList<String> arrayOfWarenkorbItems = new ArrayList<String>();

        for (int i = 0; i < cursor.getCount(); i++) {
            arrayOfWarenkorbItems.add(cursor.getString(i));
        }

        cursor.close();
        return arrayOfWarenkorbItems;


    }



}



