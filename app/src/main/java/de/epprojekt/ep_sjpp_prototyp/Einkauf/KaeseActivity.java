package de.epprojekt.ep_sjpp_prototyp.Einkauf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import de.epprojekt.ep_sjpp_prototyp.Helferlein.AnimationsHelferlein;
import de.epprojekt.ep_sjpp_prototyp.Helferlein.DBHelferlein;
import de.epprojekt.ep_sjpp_prototyp.Helferlein.PreferenceHelferlein;
import de.epprojekt.ep_sjpp_prototyp.MainActivity;
import de.epprojekt.ep_sjpp_prototyp.Menuebereich.UserOverviewActivity;
import de.epprojekt.ep_sjpp_prototyp.R;
import de.epprojekt.ep_sjpp_prototyp.WarenkorbActivity;

public class KaeseActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_kaese);



        hilfMirDaddyDB = new DBHelferlein(this);
        hilfMirMommyAnimation = new AnimationsHelferlein();

        counterSprudel = PreferenceHelferlein.loadTotalFromPref(this,KEY_SPRUDEL);
        counterStill = PreferenceHelferlein.loadTotalFromPref(this,KEY_STILL);


        //Toolbar aktiver Username wird angezeigt
        TextView tvAktiverUser = findViewById(R.id.TVToolbar);
        tvAktiverUser.setText(UserOverviewActivity.aktiverNutzerUOA);

        ibtnStill = findViewById(R.id.imageButtonStill);


        ibtnSprudel = findViewById(R.id.imageButtonSprudel);



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
            Intent intentWarenkorb = new Intent(KaeseActivity.this, WarenkorbActivity.class);
            startActivity(intentWarenkorb);
        });

        ibtnHome.setOnClickListener(v -> {
            Intent intentHome = new Intent(KaeseActivity.this, MainActivity.class);
            startActivity(intentHome);
        });

        ibtnSprudel.setOnClickListener(v -> {


            if (hilfMirDaddyDB.darfHinzugefügtWerden("Sprudelwasser", UserOverviewActivity.aktiverNutzerUOA)){
                hilfMirDaddyDB.insertIntoWarenkorb(ibtnSprudel, R.drawable.warenkorb, itemname1 + counterSprudel, itemname1, UserOverviewActivity.aktiverNutzerUOA);
                hilfMirMommyAnimation.ownAnimation(ibtnSprudel, ibtnWarenkorb);

                counterSprudel++;
                PreferenceHelferlein.saveTotalInPref(getApplicationContext(),counterSprudel,KEY_SPRUDEL);
            }else{
                Toast.makeText(KaeseActivity.this, "Es dürfen keine Produkte mehr hinzugefügt werden", Toast.LENGTH_SHORT).show();
            }
        });

        ibtnStill.setOnClickListener(v -> {
            if (hilfMirDaddyDB.darfHinzugefügtWerden("Stilleswasser", UserOverviewActivity.aktiverNutzerUOA)) {
                hilfMirDaddyDB.insertIntoWarenkorb(ibtnStill, R.drawable.warenkorb, itemname2 + counterStill, itemname2, UserOverviewActivity.aktiverNutzerUOA);
                hilfMirMommyAnimation.ownAnimation(ibtnStill, ibtnWarenkorb);

                counterStill++;
                PreferenceHelferlein.saveTotalInPref(getApplicationContext(),counterStill,KEY_STILL);
            }else{
                Toast.makeText(KaeseActivity.this, "Es dürfen keine Produkte mehr hinzugefügt werden", Toast.LENGTH_SHORT).show();

            }



        });

    }
}



