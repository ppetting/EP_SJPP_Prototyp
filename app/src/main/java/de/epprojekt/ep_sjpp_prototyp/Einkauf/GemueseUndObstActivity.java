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

public class GemueseUndObstActivity extends AppCompatActivity {

    ImageButton ibtnAepfel, ibtnGurke, ibtnWarenkorb, ibtnMenue;
    ImageButton ibtnSoundAepfel, ibtnSoundGurke;
    TextView tvToolbar;
    final String gurke = "Salatgurke";
    final static String KEY_GURKE = "key_gurke";
    Integer counterGurke;
    DBHelferlein hilfMirDaddyDB;
    AnimationsHelferlein hilfMirMommyAnimation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gemuese_und_obst);

        hilfMirDaddyDB = new DBHelferlein(this);
        hilfMirMommyAnimation = new AnimationsHelferlein();

        counterGurke = PreferenceHelferlein.loadTotalFromPref(this,KEY_GURKE);

        tvToolbar = findViewById(R.id.TVToolbar);
        tvToolbar.setText(UserOverviewActivity.aktiverNutzerUOA);

        ibtnAepfel = findViewById(R.id.imageButtonAepfel);
        ibtnGurke = findViewById(R.id.imageButtonGurke);

        ibtnWarenkorb = findViewById(R.id.imageButtonWarenkorb);
        ibtnMenue = findViewById(R.id.imageButtonHome);

        ibtnSoundAepfel = findViewById(R.id.imageButtonSoundAepfel);
        ibtnSoundGurke = findViewById(R.id.imageButtonSoundGurke);

        MediaPlayer mediaPlayerAepfel = MediaPlayer.create(this, R.raw.aepfel);
        MediaPlayer mediaPlayerGurke = MediaPlayer.create(this, R.raw.salatgruke);

        ibtnSoundAepfel.setOnClickListener(v -> mediaPlayerAepfel.start());
        ibtnSoundGurke.setOnClickListener(v -> mediaPlayerGurke.start());

        ibtnAepfel.setOnClickListener(v -> {
            Intent intentAepfel = new Intent(this, AepfelActivity.class);
            startActivity(intentAepfel);
        });

        ibtnWarenkorb.setOnClickListener(v -> {
            Intent intentWarenkorb = new Intent(this, WarenkorbActivity.class);
            startActivity(intentWarenkorb);
        });

        ibtnMenue.setOnClickListener(v -> {
            Intent intentHome = new Intent(this, MainActivity.class);
            startActivity(intentHome);
        });

        ibtnGurke.setOnClickListener(v -> {
            if (hilfMirDaddyDB.darfHinzugefügtWerden(gurke, UserOverviewActivity.aktiverNutzerUOA)){
                hilfMirDaddyDB.insertIntoWarenkorb(ibtnGurke, R.drawable.gurke, gurke + counterGurke, gurke, UserOverviewActivity.aktiverNutzerUOA);
                hilfMirMommyAnimation.ownAnimation(ibtnGurke, ibtnWarenkorb);
                counterGurke++;
                PreferenceHelferlein.saveTotalInPref(getApplicationContext(),counterGurke,KEY_GURKE);
            }else{
                Toast.makeText(this, "Es dürfen keine Produkte mehr hinzugefügt werden", Toast.LENGTH_SHORT).show();
            }
        });

    }

}










