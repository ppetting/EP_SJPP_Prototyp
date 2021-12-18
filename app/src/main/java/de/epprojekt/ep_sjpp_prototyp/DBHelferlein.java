package de.epprojekt.ep_sjpp_prototyp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ImageButton;


public class DBHelferlein extends SQLiteOpenHelper {

    final String warenkorb = "Warenkorb";
    int count=1;

    public DBHelferlein(Context context) {
        super(context, "Sortiment.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE Table Warenkorb(id_key INTEGER primary key, btnID INTEGER, bild INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE if EXISTS " + warenkorb);
    }

    public long insertIntoWarenkorb(ImageButton ibtn){

        SQLiteDatabase database = this.getWritableDatabase();

       Cursor cursor = database.rawQuery("SELECT * FROM " + warenkorb, null);


        if(count>=1){
            count = cursor.getCount()+1;
        }

        Integer btnid = ibtn.getId();
        Integer bildpfad = (Integer) ibtn.getTag();

        ContentValues contentValues = new ContentValues();
        contentValues.put("id_key", count);
        contentValues.put("btnID", btnid);
        contentValues.put("bild",bildpfad);

        count++;
        cursor.close();

        return database.insert(warenkorb,null,contentValues);

    }

    public void deletefromWarenkorb (){
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(this.warenkorb, null, null);
    }


    public void viewWarenkorb(){
        SQLiteDatabase database = this.getWritableDatabase();
    }


}
