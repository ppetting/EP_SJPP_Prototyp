package de.epprojekt.ep_sjpp_prototyp.Einkauf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import de.epprojekt.ep_sjpp_prototyp.Helferlein.PreferenceHelferlein;
import de.epprojekt.ep_sjpp_prototyp.MainActivity;
import de.epprojekt.ep_sjpp_prototyp.R;
import de.epprojekt.ep_sjpp_prototyp.WarenkorbActivity;

public class MilchprdoukteActivity extends AppCompatActivity {

    ImageButton ibtnEier, ibtnKaese, ibtnWarenkorb, ibtnMenue;
    ImageButton ibtnSoundEier, ibtnSoundKaese;
    TextView tvToolbar;
    final static String KEY_AKTIVERNUTZER = "aktiver_nutzer";
    String aktiverNutzer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_milchprodukte);

        aktiverNutzer = PreferenceHelferlein.loadUserFromPref(getApplicationContext(), KEY_AKTIVERNUTZER);

        tvToolbar = findViewById(R.id.TVToolbar);
        tvToolbar.setText(aktiverNutzer);

        ibtnEier = findViewById(R.id.imageButtonEier);
        ibtnKaese = findViewById(R.id.imageButtonKaese);

        ibtnSoundKaese = findViewById(R.id.imageButtonSoundKaese);
        ibtnSoundEier = findViewById(R.id.imageButtonSoundEier);

        ibtnMenue = findViewById(R.id.imageButtonHome);
        ibtnWarenkorb = findViewById(R.id.imageButtonWarenkorb);

        MediaPlayer mediaPlayerEier = MediaPlayer.create(this, R.raw.eier);
        MediaPlayer mediaPlayerKaese = MediaPlayer.create(this, R.raw.kaese);

        ibtnSoundEier.setOnClickListener(v -> mediaPlayerEier.start());
        ibtnSoundKaese.setOnClickListener(v -> mediaPlayerKaese.start());

        ibtnEier.setOnClickListener(v -> {
            Intent intentEier = new Intent(this, EierActivity.class);
            startActivity(intentEier);
        });

        ibtnKaese.setOnClickListener(v -> {
            Intent intentKaese = new Intent(this, KaeseActivity.class);
            startActivity(intentKaese);
        });

        ibtnWarenkorb.setOnClickListener(v -> {
            Intent intentWarenkorb = new Intent(this, WarenkorbActivity.class);
            startActivity(intentWarenkorb);
        });

        ibtnMenue.setOnClickListener(v -> {
            Intent intentHome = new Intent(this, MainActivity.class);
            startActivity(intentHome);
        });

    }
}