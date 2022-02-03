package de.epprojekt.ep_sjpp_prototyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

public class WasserActivity extends AppCompatActivity {

    ImageButton ibtnStill, ibtnSprudel, ibtnHome, ibtnWarenkorb;
    DBHelferlein hilfMirDaddyDB;
    AnimationsHelferlein hilfMirMommyAnimation;
    int i = 0;
    int j = 0;
    String itemname1 = "Sprudelwasser";
    String itemname2 = "Stilleswasser";
    TextView textViewToolbar;
    UserUebersichtActivity uua;
    NutzerErstellenActivity nea;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wasser);

        textViewToolbar = findViewById(R.id.TVToolbar);

        ibtnStill = findViewById(R.id.imageButtonStill);
        ibtnStill.setImageResource(R.drawable.stilleswasser);

        ibtnSprudel = findViewById(R.id.imageButtonSprudel);
        ibtnSprudel.setImageResource(R.drawable.sprudelwasser);

        ibtnWarenkorb = findViewById(R.id.imageButtonWarenkorb);
        ibtnWarenkorb.setImageResource(R.drawable.warenkorb);

        ibtnHome = findViewById(R.id.imageButtonHome);
        ibtnHome.setImageResource(R.drawable.home);

        hilfMirDaddyDB = new DBHelferlein(this);
        hilfMirMommyAnimation = new AnimationsHelferlein();


        String a;
        a = NutzerErstellenActivity.nameTXT;

        String b;
        b = NutzerErstellenActivity.nameTXT;



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
            i++;
        });

        ibtnStill.setOnClickListener(v -> {
            hilfMirDaddyDB.insertIntoWarenkorb(ibtnStill, R.drawable.stilleswasser, itemname2+j,UserUebersichtActivity.aktiverNutzerUUA);
            hilfMirMommyAnimation.ownAnimation(ibtnStill, ibtnWarenkorb);
            j++;
        });

    }


}