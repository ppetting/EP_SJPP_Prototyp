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

public class AepfelActivity extends AppCompatActivity {

    ImageButton ibtnRoterApfel, ibtnGruenerApfel, ibtnMenue, ibtnWarenkorb;
    ImageButton ibtnSoundRoterApfel, ibntSoundGruenerApfel;
    TextView tvToolbar;
    final String roterApfel = "roterApfel";
    final String gruenerApfel = "gruenerApfel";
    final static String KEY_ROTERAPFEL = "key_roterapfel";
    final static String KEY_GRUENERAPFEL = "key_gruenerapfel";
    Integer counterRoterApfel;
    Integer counterGruenerApfel;
    DBHelferlein hilfMirDaddyDB;
    AnimationsHelferlein hilfMirMommyAnimation;
    final static String KEY_AKTIVERNUTZER = "aktiver_nutzer";
    String aktiverNutzer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aepfel);

        hilfMirDaddyDB = new DBHelferlein(this);
        hilfMirMommyAnimation = new AnimationsHelferlein();

        counterRoterApfel = PreferenceHelferlein.loadTotalFromPref(this,KEY_ROTERAPFEL);
        counterGruenerApfel = PreferenceHelferlein.loadTotalFromPref(this,KEY_GRUENERAPFEL);
        aktiverNutzer = PreferenceHelferlein.loadUserFromPref(getApplicationContext(), KEY_AKTIVERNUTZER);

        tvToolbar = findViewById(R.id.TVToolbar);
        tvToolbar.setText(aktiverNutzer);

        ibtnRoterApfel = findViewById(R.id.iamgeButtonRoterApfel);
        ibtnRoterApfel.setImageBitmap(BitmapFactory.decodeByteArray(hilfMirDaddyDB.getDrawableFromTable(roterApfel),0,hilfMirDaddyDB.getDrawableFromTable(roterApfel).length));
        ibtnGruenerApfel = findViewById(R.id.imageButtonGruenerApfel);
        ibtnGruenerApfel.setImageBitmap(BitmapFactory.decodeByteArray(hilfMirDaddyDB.getDrawableFromTable(gruenerApfel),0,hilfMirDaddyDB.getDrawableFromTable(gruenerApfel).length));

        ibtnWarenkorb = findViewById(R.id.imageButtonWarenkorb);
        ibtnMenue = findViewById(R.id.imageButtonHome);

        ibtnSoundRoterApfel = findViewById(R.id.imageButtonSoundRoterApfel);
        ibntSoundGruenerApfel = findViewById(R.id.imageButtonSoundGruenerApfel);

        MediaPlayer mediaPlayerRoterApfel = MediaPlayer.create(this, R.raw.roterapfel);
        MediaPlayer mediaPlayerGruenerApfel = MediaPlayer.create(this, R.raw.gruenerapfel);

        ibtnSoundRoterApfel.setOnClickListener(v -> mediaPlayerRoterApfel.start());
        ibntSoundGruenerApfel.setOnClickListener(v -> mediaPlayerGruenerApfel.start());

        ibtnWarenkorb.setOnClickListener(v -> {
            Intent intentWarenkorb = new Intent(this, WarenkorbActivity.class);
            startActivity(intentWarenkorb);
        });

        ibtnMenue.setOnClickListener(v -> {
            Intent intentHome = new Intent(this, MainActivity.class);
            startActivity(intentHome);
        });

        ibtnRoterApfel.setOnClickListener(v -> {
            if (hilfMirDaddyDB.darfHinzugefuegtWerden(roterApfel, aktiverNutzer)){
                hilfMirDaddyDB.insertIntoWarenkorb(ibtnRoterApfel, R.drawable.roterapfel, roterApfel + counterRoterApfel, roterApfel, aktiverNutzer);
                hilfMirMommyAnimation.ownAnimation(ibtnRoterApfel, ibtnWarenkorb);
                counterRoterApfel++;
                PreferenceHelferlein.saveTotalInPref(getApplicationContext(),counterRoterApfel,KEY_ROTERAPFEL);
            }else{
                AddAndSetHelferlein.playAudioFlagVoll(this);
            }
        });

        ibtnGruenerApfel.setOnClickListener(v -> {
            if (hilfMirDaddyDB.darfHinzugefuegtWerden(gruenerApfel, aktiverNutzer)){
                hilfMirDaddyDB.insertIntoWarenkorb(ibtnGruenerApfel, R.drawable.gruenerapfel, gruenerApfel + counterGruenerApfel, gruenerApfel, aktiverNutzer);
                hilfMirMommyAnimation.ownAnimation(ibtnGruenerApfel, ibtnWarenkorb);
                counterGruenerApfel++;
                PreferenceHelferlein.saveTotalInPref(getApplicationContext(),counterGruenerApfel,KEY_GRUENERAPFEL);
            }else{
                AddAndSetHelferlein.playAudioFlagVoll(this);
            }
        });
    }
}