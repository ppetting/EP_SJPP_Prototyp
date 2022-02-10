package de.epprojekt.ep_sjpp_prototyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Base64;

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

        ibtnSprudel.setImageResource(R.drawable.sprudelwasser);

       /*

        if(savedInstanceState == null){
            ibtnSprudel.setImageBitmap(BitmapFactory.decodeByteArray(hilfMirDaddyDB.getDrawableFromTable("roterApfel"),0,hilfMirDaddyDB.getDrawableFromTable("roterApfel").length));


        }else{
            ibtnSprudel.setImageBitmap(BitmapFactory.decodeByteArray(hilfMirDaddyDB.getDrawableFromTable("roterApfel"),0,hilfMirDaddyDB.getDrawableFromTable("roterApfel").length));
        }
*/


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


            if (hilfMirDaddyDB.darfHinzugefügtWerden("Sprudelwasser", UserUebersichtActivity.aktiverNutzerUUA)){
                hilfMirDaddyDB.insertIntoWarenkorb(ibtnSprudel, R.drawable.sprudelwasser, itemname1 + counterSprudel, itemname1,UserUebersichtActivity.aktiverNutzerUUA);
                hilfMirMommyAnimation.ownAnimation(ibtnSprudel, ibtnWarenkorb);

                counterSprudel++;
                PreferenceHelferlein.saveTotalInPref(getApplicationContext(),counterSprudel,KEY_SPRUDEL);
            }else{
                Toast.makeText(WasserActivity.this, "Es dürfen keine Produkte mehr hinzugefügt werden", Toast.LENGTH_SHORT).show();
            }
        });

        ibtnStill.setOnClickListener(v -> {
            if (hilfMirDaddyDB.darfHinzugefügtWerden("Stilleswasser", UserUebersichtActivity.aktiverNutzerUUA)) {
                hilfMirDaddyDB.insertIntoWarenkorb(ibtnStill, R.drawable.stilleswasser, itemname2 + counterStill, itemname2, UserUebersichtActivity.aktiverNutzerUUA);
                hilfMirMommyAnimation.ownAnimation(ibtnStill, ibtnWarenkorb);

                counterStill++;
                PreferenceHelferlein.saveTotalInPref(getApplicationContext(),counterStill,KEY_STILL);
            }else{
                Toast.makeText(WasserActivity.this, "Es dürfen keine Produkte mehr hinzugefügt werden", Toast.LENGTH_SHORT).show();

            }



        });

    }
}



