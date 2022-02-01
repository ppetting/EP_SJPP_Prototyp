package de.epprojekt.ep_sjpp_prototyp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    ImageButton ibtnGemueseUndObst, ibtnGetraenke, ibtnWarenkorb, ibtnMenue;
    ImageButton ibtnSoundGetraenke;
    DBHelferlein hilfMirDaddyDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbarMAIN);
        setSupportActionBar(toolbar);

        hilfMirDaddyDB = new DBHelferlein(this);

        ibtnMenue = findViewById(R.id.imageButtonHome);
        ibtnMenue.setImageResource(R.drawable.menue);

        ibtnWarenkorb = findViewById(R.id.imageButtonWarenkorb);
        ibtnWarenkorb.setImageResource(R.drawable.warenkorb);

        ibtnGemueseUndObst = findViewById(R.id.imageButtonGemueseUndObst);
        ibtnGemueseUndObst.setImageResource(R.drawable.gemueseundobst);

        ibtnGetraenke = findViewById(R.id.imageButtonGetraenke);
        ibtnGetraenke.setImageResource(R.drawable.getraenke);

        ibtnSoundGetraenke = findViewById(R.id.imageButtonSoundGetraenke);
        ibtnSoundGetraenke.setImageResource(R.drawable.klang);

        //SOUND
        final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.getraenke);
        ibtnSoundGetraenke.setOnClickListener(v ->
                mediaPlayer.start());

        ibtnGemueseUndObst.setOnClickListener(v -> {
            Intent intentGemueseUndObst = new Intent(MainActivity.this,GemueseUndObstActivity.class);
            startActivity(intentGemueseUndObst);
        });

        ibtnGetraenke.setOnClickListener(v -> {
            Intent intentGetraenke = new Intent(MainActivity.this,GetraenkeActivity.class);
            startActivity(intentGetraenke);
        });

        ibtnWarenkorb.setOnClickListener(v -> {
            Intent intentWarenkorb = new Intent(MainActivity.this, WarenkorbActivity.class);
            startActivity(intentWarenkorb);
        });

        ibtnMenue.setOnClickListener(v -> {
            Intent intentMenue = new Intent(MainActivity.this, UserUebersichtActivity.class);
            startActivity(intentMenue);
        });

    }
}