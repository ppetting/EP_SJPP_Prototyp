package de.epprojekt.ep_sjpp_prototyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class WasserActivity extends AppCompatActivity {

    ImageButton ibtnStill, ibtnSprudel, ibtnHome, ibtnWarenkorb;
    DBHelferlein hilfMirDaddyDB;
    AnimationsHelferlein hilfMirMommyAnimation;
    static int i = 0;
    static int j = 0;
    String itemname1 = "Sprudelwasser";
    String itemname2 = "Stilleswasser";
    TextView textViewToolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wasser);

        textViewToolbar = findViewById(R.id.TVToolbar);

        ibtnStill = findViewById(R.id.imageButtonStill);
        ibtnStill.setImageResource(R.drawable.stilleswasser);

        ibtnSprudel = findViewById(R.id.imageButtonSprudel);
        ibtnSprudel.setImageResource(getIntent().getIntExtra("keyPoint", R.drawable.sprudelwasser));

        ibtnWarenkorb = findViewById(R.id.imageButtonWarenkorb);
        ibtnWarenkorb.setImageResource(R.drawable.warenkorb);

        ibtnHome = findViewById(R.id.imageButtonHome);
        ibtnHome.setImageResource(R.drawable.home);

        hilfMirDaddyDB = new DBHelferlein(this);
        hilfMirMommyAnimation = new AnimationsHelferlein();


        ibtnWarenkorb.setOnClickListener(v -> {
            Intent intentWarenkorb = new Intent(WasserActivity.this, WarenkorbActivity.class);
            startActivity(intentWarenkorb);
        });

        ibtnHome.setOnClickListener(v -> {
            Intent intentHome = new Intent(WasserActivity.this, MainActivity.class);
            startActivity(intentHome);
        });

        ibtnSprudel.setOnClickListener(v -> {
            hilfMirDaddyDB.insertIntoWarenkorb(ibtnSprudel, R.drawable.sprudelwasser, itemname1+i,UserUebersichtActivity.aktiverNutzerUUA);
            hilfMirMommyAnimation.ownAnimation(ibtnSprudel, ibtnWarenkorb);
            getFlaganzahl(UserUebersichtActivity.aktiverNutzerUUA,getSortiment("Kaeseaufschnitt"));
            i++;
        });

        ibtnStill.setOnClickListener(v -> {
            hilfMirDaddyDB.insertIntoWarenkorb(ibtnStill, R.drawable.stilleswasser, itemname2+j,UserUebersichtActivity.aktiverNutzerUUA);
            hilfMirMommyAnimation.ownAnimation(ibtnStill, ibtnWarenkorb);
            j++;
        });

    }

    //gibt die Flag-Art des Produkts als String zurück
    public String getSortiment (String produktname){

        Cursor cursor = hilfMirDaddyDB.fetchSortiment(produktname);
        Toast.makeText(WasserActivity.this,"Produkt hat folgenden Flag:"+cursor.getString(0), Toast.LENGTH_SHORT).show();

        cursor.moveToFirst();
        return cursor.getString(0);
    }

    //gibt die als Integer zurück, wie viele Produkte der aktive User von dem angegebenen Flag hinzufügen darf
    public Integer getFlaganzahl (String aktiverbenutzer, String flagname){

        Cursor cursor = hilfMirDaddyDB.fetchUserFlagAnzahl(aktiverbenutzer,flagname);
        Toast.makeText(WasserActivity.this,"Benutzer hat Flaganzahl:"+cursor.getInt(0), Toast.LENGTH_SHORT).show();
        cursor.moveToFirst();
        return cursor.getInt(0);
    }

}