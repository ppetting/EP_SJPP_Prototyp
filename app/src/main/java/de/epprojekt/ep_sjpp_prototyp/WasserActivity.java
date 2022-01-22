package de.epprojekt.ep_sjpp_prototyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;

public class WasserActivity extends AppCompatActivity {

    ImageButton ibtnStill, ibtnSprudel, ibtnHome, ibtnWarenkorb;
    DBHelferlein hilfMirDaddyDB;
    AnimationsHelferlein hilfMirMommyAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wasser);

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


        ibtnWarenkorb.setOnClickListener(v -> {
            Intent intentWarenkorb = new Intent(WasserActivity.this, WarenkorbActivity.class);
            startActivity(intentWarenkorb);
        });

        ibtnHome.setOnClickListener(v -> {
            Intent intentHome = new Intent(WasserActivity.this, MainActivity.class);
            startActivity(intentHome);
        });

        ibtnSprudel.setOnClickListener(v -> {
            hilfMirDaddyDB.insertIntoWarenkorb(ibtnSprudel, R.drawable.sprudelwasser);
            hilfMirMommyAnimation.ownAnimation(ibtnSprudel, ibtnWarenkorb);
        });

        ibtnStill.setOnClickListener(v -> {
            hilfMirDaddyDB.insertIntoWarenkorb(ibtnStill, R.drawable.stilleswasser);
            hilfMirMommyAnimation.ownAnimation(ibtnStill, ibtnWarenkorb);
        });

    }


}