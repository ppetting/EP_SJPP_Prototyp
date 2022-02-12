package de.epprojekt.ep_sjpp_prototyp.Einkauf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
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

public class EierActivity extends AppCompatActivity {

    ImageButton ibtnSechserEier, ibtnZehnerEier, ibtnMenue, ibtnWarenkorb;
    ImageButton ibtnSoundSechserEier, ibtnSoundZehnerEier;
    TextView tvToolbar;
    final String zehnerEier = "zehnerPackungEier";
    final String sechserEier = "sechserPackungEier";
    final static String KEY_SECHSEREIER = "key_sechsereier";
    final static String KEY_ZEHNEREIER = "key_zehnereier";
    Integer counterSechserEier;
    Integer counterZehnerEier;
    DBHelferlein hilfMirDaddyDB;
    AnimationsHelferlein hilfMirMommyAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eier);

        hilfMirDaddyDB = new DBHelferlein(this);
        hilfMirMommyAnimation = new AnimationsHelferlein();

        counterZehnerEier = PreferenceHelferlein.loadTotalFromPref(this,KEY_ZEHNEREIER);
        counterSechserEier = PreferenceHelferlein.loadTotalFromPref(this,KEY_SECHSEREIER);

        tvToolbar = findViewById(R.id.TVToolbar);
        tvToolbar.setText(UserOverviewActivity.aktiverNutzerUOA);

        ibtnSechserEier = findViewById(R.id.imageButtonSechserEier);
        ibtnZehnerEier = findViewById(R.id.imageButtonZehnerEier);

        ibtnSoundSechserEier = findViewById(R.id.imageButtonSoundSechserEier);
        ibtnSoundZehnerEier = findViewById(R.id.imageButtonSoundZehnerEier);

        ibtnMenue = findViewById(R.id.imageButtonHome);
        ibtnWarenkorb = findViewById(R.id.imageButtonWarenkorb);

        MediaPlayer mediaPlayerSechserEier = MediaPlayer.create(this, R.raw.sechserpackungeier);
        MediaPlayer mediaPlayerZehnerEier = MediaPlayer.create(this, R.raw.zehnerpackungeier);

        ibtnSoundSechserEier.setOnClickListener(v -> mediaPlayerSechserEier.start());
        ibtnSoundZehnerEier.setOnClickListener(v -> mediaPlayerZehnerEier.start());

        ibtnWarenkorb.setOnClickListener(v -> {
            Intent intentWarenkorb = new Intent(this, WarenkorbActivity.class);
            startActivity(intentWarenkorb);
        });

        ibtnMenue.setOnClickListener(v -> {
            Intent intentHome = new Intent(this, MainActivity.class);
            startActivity(intentHome);
        });


        ibtnSechserEier.setOnClickListener(v -> {
            if (hilfMirDaddyDB.darfHinzugefügtWerden(sechserEier, UserOverviewActivity.aktiverNutzerUOA)){
                hilfMirDaddyDB.insertIntoWarenkorb(ibtnSechserEier, R.drawable.sechser_eier, sechserEier + counterSechserEier, sechserEier, UserOverviewActivity.aktiverNutzerUOA);
                hilfMirMommyAnimation.ownAnimation(ibtnSechserEier, ibtnWarenkorb);
                counterSechserEier++;
                PreferenceHelferlein.saveTotalInPref(getApplicationContext(),counterSechserEier,KEY_SECHSEREIER);
            }else{
                Toast.makeText(this, "Es dürfen keine Produkte mehr hinzugefügt werden", Toast.LENGTH_SHORT).show();
            }
        });

        ibtnZehnerEier.setOnClickListener(v -> {
            if (hilfMirDaddyDB.darfHinzugefügtWerden(zehnerEier, UserOverviewActivity.aktiverNutzerUOA)){
                hilfMirDaddyDB.insertIntoWarenkorb(ibtnZehnerEier, R.drawable.zehner_eier, zehnerEier + counterZehnerEier, zehnerEier, UserOverviewActivity.aktiverNutzerUOA);
                hilfMirMommyAnimation.ownAnimation(ibtnZehnerEier, ibtnWarenkorb);
                counterZehnerEier++;
                PreferenceHelferlein.saveTotalInPref(getApplicationContext(),counterZehnerEier,KEY_ZEHNEREIER);
            }else{
                Toast.makeText(this, "Es dürfen keine Produkte mehr hinzugefügt werden", Toast.LENGTH_SHORT).show();
            }
        });


    }
}