package de.epprojekt.ep_sjpp_prototyp.Einkauf;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import de.epprojekt.ep_sjpp_prototyp.Helferlein.AddAndSetHelferlein;
import de.epprojekt.ep_sjpp_prototyp.Helferlein.AnimationsHelferlein;
import de.epprojekt.ep_sjpp_prototyp.Helferlein.DBHelferlein;
import de.epprojekt.ep_sjpp_prototyp.Helferlein.PreferenceHelferlein;
import de.epprojekt.ep_sjpp_prototyp.MainActivity;
import de.epprojekt.ep_sjpp_prototyp.R;
import de.epprojekt.ep_sjpp_prototyp.WarenkorbActivity;

public class KaeseActivity extends AppCompatActivity {

    ImageButton ibtnHartkaese, ibtnStreichkaese, ibtnScheibenkaese, ibtnWarenkorb, ibtnMenue;
    ImageButton ibtnSoundHartkaese, ibtnSoundStreichkaese, ibtnSoundScheibenkaese;
    DBHelferlein hilfMirDaddyDB;
    AnimationsHelferlein hilfMirMommyAnimation;
    TextView tvToolbar;
    final String hartkaese = "Hartkaese";
    final String streichkaese = "Streichkaese";
    final String scheibenkaese = "Kaeseaufschnitt";
    Integer counterHartkaese;
    Integer counterStreichkaese;
    Integer counterScheibenkaese;
    final static String KEY_HARTKAESE = "key_hartkaese";
    final static String KEY_STREICHKAESE = "key_streichkaese";
    final static String KEY_SCHEIBENKAESE = "key_scheibenkaese";
    final static String KEY_AKTIVERNUTZER = "aktiver_nutzer";
    String aktiverNutzer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kaese);

        hilfMirDaddyDB = new DBHelferlein(this);
        hilfMirMommyAnimation = new AnimationsHelferlein();

        hilfMirDaddyDB = new DBHelferlein(this);
        hilfMirMommyAnimation = new AnimationsHelferlein();

        ibtnHartkaese = findViewById(R.id.imageButtonHartkaese);
        ibtnHartkaese.setImageBitmap(BitmapFactory.decodeByteArray(hilfMirDaddyDB.getDrawableFromTable(hartkaese),0,hilfMirDaddyDB.getDrawableFromTable(hartkaese).length));
        ibtnStreichkaese = findViewById(R.id.imageButtonStreichkaese);
        ibtnStreichkaese.setImageBitmap(BitmapFactory.decodeByteArray(hilfMirDaddyDB.getDrawableFromTable(streichkaese),0,hilfMirDaddyDB.getDrawableFromTable(streichkaese).length));
        ibtnScheibenkaese = findViewById(R.id.imageButtonScheibenkaese);
        ibtnScheibenkaese.setImageBitmap(BitmapFactory.decodeByteArray(hilfMirDaddyDB.getDrawableFromTable(scheibenkaese),0,hilfMirDaddyDB.getDrawableFromTable(scheibenkaese).length));

        counterHartkaese = PreferenceHelferlein.loadTotalFromPref(this,KEY_HARTKAESE);
        counterStreichkaese = PreferenceHelferlein.loadTotalFromPref(this,KEY_STREICHKAESE);
        counterScheibenkaese = PreferenceHelferlein.loadTotalFromPref(this, KEY_SCHEIBENKAESE);
        aktiverNutzer = PreferenceHelferlein.loadUserFromPref(getApplicationContext(), KEY_AKTIVERNUTZER);

        ibtnWarenkorb = findViewById(R.id.imageButtonWarenkorb);
        ibtnMenue = findViewById(R.id.imageButtonHome);

        ibtnSoundHartkaese = findViewById(R.id.imageButtonSoundHartkaese);
        ibtnSoundScheibenkaese = findViewById(R.id.imageButtonSoundScheibenkaese);
        ibtnSoundStreichkaese = findViewById(R.id.imageButtonSoundStreichkaese);

        tvToolbar = findViewById(R.id.TVToolbar);
        tvToolbar.setText(aktiverNutzer);

        MediaPlayer mediaPlayerHartkaese = MediaPlayer.create(this, R.raw.hartkaese);
        MediaPlayer mediaPlayerStreichkaese = MediaPlayer.create(this, R.raw.streichkaese);
        MediaPlayer mediaPlayerScheibenkaese = MediaPlayer.create(this, R.raw.kaeseaufschnitt);

        ibtnSoundHartkaese.setOnClickListener(v -> mediaPlayerHartkaese.start());
        ibtnSoundStreichkaese.setOnClickListener(v -> mediaPlayerStreichkaese.start());
        ibtnSoundScheibenkaese.setOnClickListener(v -> mediaPlayerScheibenkaese.start());

        ibtnWarenkorb.setOnClickListener(v -> {
            Intent intentWarenkorb = new Intent(this, WarenkorbActivity.class);
            startActivity(intentWarenkorb);
        });

        ibtnMenue.setOnClickListener(v -> {
            Intent intentHome = new Intent(this, MainActivity.class);
            startActivity(intentHome);
        });

        ibtnHartkaese.setOnClickListener(v -> {
            if (hilfMirDaddyDB.darfHinzugefuegtWerden(hartkaese, aktiverNutzer)){
                hilfMirDaddyDB.insertIntoWarenkorb(ibtnHartkaese, R.drawable.hartkaese, hartkaese + counterHartkaese, hartkaese, aktiverNutzer);
                hilfMirMommyAnimation.ownAnimation(ibtnHartkaese, ibtnWarenkorb);
                counterHartkaese++;
                PreferenceHelferlein.saveTotalInPref(getApplicationContext(),counterHartkaese,KEY_HARTKAESE);
            }else{
                AddAndSetHelferlein.playAudioFlagVoll(this);
            }
        });

        ibtnScheibenkaese.setOnClickListener(v -> {
            if (hilfMirDaddyDB.darfHinzugefuegtWerden(scheibenkaese, aktiverNutzer)){
                hilfMirDaddyDB.insertIntoWarenkorb(ibtnScheibenkaese, R.drawable.kaesescheibe, scheibenkaese + counterScheibenkaese, scheibenkaese, aktiverNutzer);
                hilfMirMommyAnimation.ownAnimation(ibtnScheibenkaese, ibtnWarenkorb);
                counterScheibenkaese++;
                PreferenceHelferlein.saveTotalInPref(getApplicationContext(),counterScheibenkaese,KEY_SCHEIBENKAESE);
            }else{
                AddAndSetHelferlein.playAudioFlagVoll(this);
            }
        });

        ibtnStreichkaese.setOnClickListener(v -> {
            if (hilfMirDaddyDB.darfHinzugefuegtWerden(streichkaese, aktiverNutzer)){
                hilfMirDaddyDB.insertIntoWarenkorb(ibtnStreichkaese, R.drawable.streichkaese, streichkaese + counterStreichkaese, streichkaese, aktiverNutzer);
                hilfMirMommyAnimation.ownAnimation(ibtnStreichkaese, ibtnWarenkorb);
                counterStreichkaese++;
                PreferenceHelferlein.saveTotalInPref(getApplicationContext(),counterStreichkaese,KEY_STREICHKAESE);
            }else{
                AddAndSetHelferlein.playAudioFlagVoll(this);
            }
        });

    }
}



