package de.epprojekt.ep_sjpp_prototyp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import de.epprojekt.ep_sjpp_prototyp.Einkauf.GemueseUndObstActivity;
import de.epprojekt.ep_sjpp_prototyp.Einkauf.MilchprdoukteActivity;
import de.epprojekt.ep_sjpp_prototyp.Helferlein.DBHelferlein;
import de.epprojekt.ep_sjpp_prototyp.Helferlein.PreferenceHelferlein;
import de.epprojekt.ep_sjpp_prototyp.Menuebereich.UserCreationActivity;
import de.epprojekt.ep_sjpp_prototyp.Menuebereich.UserOverviewActivity;

public class MainActivity extends AppCompatActivity {

    ImageButton ibtnGemueseUndObst, ibtnGetraenke, ibtnWeizenprodukte, ibtnMilchprodukte, ibtnFleischundWurst, ibtnWarenkorb, ibtnMenue;
    ImageButton ibtnSoundGetraenke, ibtnSoundGemueseundObst, ibtnSoundWeizenprodukte, ibtnSoundMilchprdoukte, ibtnSoundFleischundWurst;
    TextView tvToolbar;
    Toolbar toolbar;
    DBHelferlein hilfMirDaddyDB;
    public final static String FIRST_APP_START_MAIN = "FirstAppStartMain";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(PreferenceHelferlein.firstAppStart(getApplicationContext(),FIRST_APP_START_MAIN)){
            Intent intent = new Intent(this, UserCreationActivity.class);
            startActivity(intent);
        }

        hilfMirDaddyDB = new DBHelferlein(this);

        //TOOLBAR
        toolbar = findViewById(R.id.toolbarMAIN);
        tvToolbar = findViewById(R.id.TVToolbar);
        tvToolbar.setText(UserOverviewActivity.aktiverNutzerUOA);
        setSupportActionBar(toolbar);

        //BTN ZUWEISUNG
        ibtnGemueseUndObst = findViewById(R.id.imageButtonGemueseUndObst);
        ibtnGetraenke = findViewById(R.id.imageButtonGetraenke);
        ibtnWeizenprodukte = findViewById(R.id.imageButtonWeizenprodukte);
        ibtnMilchprodukte = findViewById(R.id.imageButtonMilchprodukte);
        ibtnFleischundWurst = findViewById(R.id.imageButtonFleischundWurst);

        ibtnMenue = findViewById(R.id.imageButtonHome);
        ibtnWarenkorb = findViewById(R.id.imageButtonWarenkorb);

        ibtnSoundGetraenke = findViewById(R.id.imageButtonSoundGetraenke);
        ibtnSoundGemueseundObst = findViewById(R.id.imageButtonSoundGemueseundObst);
        ibtnSoundWeizenprodukte = findViewById(R.id.imageButtonSoundWeizenprodukte);
        ibtnSoundMilchprdoukte = findViewById(R.id.imageButtonSoundMilchprodukte);
        ibtnSoundFleischundWurst = findViewById(R.id.imageButtonSoundFleischundWurst);

        //SOUND
        MediaPlayer mediaPlayerGemueseundObst = MediaPlayer.create(this, R.raw.obstundgemuese);
        MediaPlayer mediaPlayerGetraenke = MediaPlayer.create(this, R.raw.getraenke);
        MediaPlayer mediaPlayerWeizenprodukte = MediaPlayer.create(this, R.raw.weizenprodukte);
        MediaPlayer mediaPlayerFleischUndWurst = MediaPlayer.create(this, R.raw.fleischundwurst);
        MediaPlayer mediaPlayerMilchprodukte = MediaPlayer.create(this, R.raw.michlprodukte);

        ibtnSoundGetraenke.setOnClickListener(v -> mediaPlayerGetraenke.start());
        ibtnSoundGemueseundObst.setOnClickListener(v -> mediaPlayerGemueseundObst.start());
        ibtnSoundWeizenprodukte.setOnClickListener(v -> mediaPlayerWeizenprodukte.start());
        ibtnSoundMilchprdoukte.setOnClickListener(v -> mediaPlayerMilchprodukte.start());
        ibtnSoundFleischundWurst.setOnClickListener(v -> mediaPlayerFleischUndWurst.start());

        //ONCLICKLISTENER
        ibtnGemueseUndObst.setOnClickListener(v -> {
            Intent intentGemueseUndObst = new Intent(this, GemueseUndObstActivity.class);
            startActivity(intentGemueseUndObst);
        });

        ibtnMilchprodukte.setOnClickListener(v -> {
            Intent intentMilchprdoukte = new Intent(this, MilchprdoukteActivity.class);
            startActivity(intentMilchprdoukte);
        });

        ibtnWarenkorb.setOnClickListener(v -> {
            Intent intentWarenkorb = new Intent(this, WarenkorbActivity.class);
            startActivity(intentWarenkorb);
        });

        ibtnMenue.setOnClickListener(v -> {
            Intent intentMenue = new Intent(this, UserOverviewActivity.class);
            startActivity(intentMenue);
        });

    }


}