package de.epprojekt.ep_sjpp_prototyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class WasserActivity extends AppCompatActivity {

    ImageButton ibtnStill, ibtnSprudel, ibtnHome, ibtnWarenkorb;
    DBHelferlein hilfMirDaddyDB;
    AnimationsHelferlein hilfMirMommyAnimation;
    Integer counterSprudel;
    Integer counterStill;
    final static String KEY_SPRUDEL = "key_sprudel";
    final static String KEY_STILL = "key_still";
    String itemname1 = "Sprudelwasser";
    String itemname2 = "Stilleswasser";
    TextView textViewToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wasser);

        hilfMirDaddyDB = new DBHelferlein(this);
        hilfMirMommyAnimation = new AnimationsHelferlein();

        counterSprudel = PreferenceHelferlein.loadTotalFromPref(this,KEY_SPRUDEL);
        counterStill = PreferenceHelferlein.loadTotalFromPref(this,KEY_STILL);

        textViewToolbar = findViewById(R.id.TVToolbar);

        ibtnStill = findViewById(R.id.imageButtonStill);
        ibtnStill.setImageResource(R.drawable.stilleswasser);

        ibtnSprudel = findViewById(R.id.imageButtonSprudel);

   /*     if(savedInstanceState == null){
            ibtnSprudel.setImageResource(Integer.parseInt(hilfMirDaddyDB.getDrawableFromTable("roterApfel")));
        }else{
            ibtnSprudel.setImageURI(Uri.parse(hilfMirDaddyDB.getDrawableFromTable("roterApfel")));
        }
*/
        ibtnSprudel.setImageResource(R.drawable.sprudelwasser);

        ibtnWarenkorb = findViewById(R.id.imageButtonWarenkorb);
        ibtnWarenkorb.setImageResource(R.drawable.warenkorb);

        ibtnHome = findViewById(R.id.imageButtonHome);
        ibtnHome.setImageResource(R.drawable.home);


        ibtnWarenkorb.setOnClickListener(v -> {
            Intent intentWarenkorb = new Intent(WasserActivity.this, WarenkorbActivity.class);
            startActivity(intentWarenkorb);
        });

        ibtnHome.setOnClickListener(v -> {
            Intent intentHome = new Intent(WasserActivity.this, MainActivity.class);
            startActivity(intentHome);
        });

        ibtnSprudel.setOnClickListener(v -> {


            if(darfHinzugefügtWerden(flagCountEinkaufswagen(getSortiment("Sprudelwasser")),getFlaganzahl(UserUebersichtActivity.aktiverNutzerUUA,getSortiment("Sprudelwasser")))){
                hilfMirDaddyDB.insertIntoWarenkorb(ibtnSprudel, R.drawable.sprudelwasser, itemname1 + counterSprudel, itemname1,UserUebersichtActivity.aktiverNutzerUUA);
                hilfMirMommyAnimation.ownAnimation(ibtnSprudel, ibtnWarenkorb);

                counterSprudel++;
                PreferenceHelferlein.saveTotalInPref(getApplicationContext(),counterSprudel,KEY_SPRUDEL);
            }else{
                Toast.makeText(WasserActivity.this, "Es dürfen keine Produkte mehr hinzugefügt werden", Toast.LENGTH_SHORT).show();
            }
        });

        ibtnStill.setOnClickListener(v -> {
            if(darfHinzugefügtWerden(flagCountEinkaufswagen(getSortiment("Stilleswasser")),getFlaganzahl(UserUebersichtActivity.aktiverNutzerUUA,getSortiment("Stilleswasser")))) {
                hilfMirDaddyDB.insertIntoWarenkorb(ibtnStill, R.drawable.stilleswasser, itemname2 + counterStill, itemname2, UserUebersichtActivity.aktiverNutzerUUA);
                hilfMirMommyAnimation.ownAnimation(ibtnStill, ibtnWarenkorb);

                counterStill++;
                PreferenceHelferlein.saveTotalInPref(getApplicationContext(),counterStill,KEY_STILL);
            }else{
                Toast.makeText(WasserActivity.this, "Es dürfen keine Produkte mehr hinzugefügt werden", Toast.LENGTH_SHORT).show();

            }



        });

    }



    //gibt die Flag-Art des Produkts als String zurück
    public String getSortiment(String produktname) {

        Cursor cursor = hilfMirDaddyDB.fetchSortiment(produktname);
        //Toast.makeText(WasserActivity.this, "Produkt hat folgenden Flag:" + cursor.getString(0), Toast.LENGTH_SHORT).show();

        cursor.moveToFirst();
        return cursor.getString(0);
    }


    // gibt den Namen des Produkts zurück (nicht des individuellen Produktnamens Item1)
    public String getWarenkorbItemname(String produktname, String aktuellerUser) {

        Cursor cursor = hilfMirDaddyDB.fetchItemnameAusWarenkorb(aktuellerUser,produktname);
        cursor.moveToFirst();
        return cursor.getString(0);
    }


    //gibt die als Integer zurück, wie viele Produkte der aktive User von dem angegebenen Flag hinzufügen darf
    public Integer getFlaganzahl(String aktiverbenutzer, String flagname) {

        Cursor cursor = hilfMirDaddyDB.fetchUserFlagAnzahl(aktiverbenutzer, flagname);
        Toast.makeText(WasserActivity.this, "Benutzer hat Flaganzahl:" + cursor.getInt(0), Toast.LENGTH_SHORT).show();
        cursor.moveToFirst();
        return cursor.getInt(0);
    }

    //Zählt die Anzahl einer bestimmten Flagart im Einkaufswagen des aktiven Nutzers
    public Integer flagCountEinkaufswagen(String flagart) {
        int count = 0;
        int x = 0;

        ArrayList<String> arrayListOfWarenkorbitems = hilfMirDaddyDB.createArrayListOfWarenkorbItems(UserUebersichtActivity.aktiverNutzerUUA);

        while (x < arrayListOfWarenkorbitems.size()) {
            String itemname = getWarenkorbItemname(arrayListOfWarenkorbitems.get(x),UserUebersichtActivity.aktiverNutzerUUA);
            String itemflag = getSortiment(itemname);

            if (flagart.equals(itemflag)) {
                count++;
                x++;
            } else {
                x++;
            }
        }
        Toast.makeText(WasserActivity.this, "Flaganzahl des Einkaufswagen:" + count, Toast.LENGTH_SHORT).show();
        return count;
    }



    public Boolean darfHinzugefügtWerden (Integer countEinkaufswagen,Integer personenmaximum){
        if(countEinkaufswagen < personenmaximum){
            return true;
        } else return false;
    }
}