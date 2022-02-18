package de.epprojekt.ep_sjpp_prototyp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Objects;
import de.epprojekt.ep_sjpp_prototyp.Einkauf.GemueseUndObstActivity;
import de.epprojekt.ep_sjpp_prototyp.Einkauf.MilchprdoukteActivity;
import de.epprojekt.ep_sjpp_prototyp.Helferlein.DBHelferlein;
import de.epprojekt.ep_sjpp_prototyp.Helferlein.PreferenceHelferlein;
import de.epprojekt.ep_sjpp_prototyp.Menuebereich.RegistrationActivity;
import de.epprojekt.ep_sjpp_prototyp.Menuebereich.UserCreationActivity;
import de.epprojekt.ep_sjpp_prototyp.Menuebereich.UserOverviewActivity;


public class MainActivity extends AppCompatActivity {

    ImageButton ibtnGemueseUndObst, ibtnGetraenke, ibtnWeizenprodukte, ibtnMilchprodukte, ibtnFleischundWurst, ibtnWarenkorb, ibtnMenue;
    ImageButton ibtnSoundGetraenke, ibtnSoundGemueseundObst, ibtnSoundWeizenprodukte, ibtnSoundMilchprdoukte, ibtnSoundFleischundWurst;
    TextView tvToolbar;
    Toolbar toolbar;
    DBHelferlein hilfMirDaddyDB;
    public final static String FIRST_APP_START_MAIN = "FirstAppStartMain";
    final static String KEY_AKTIVERNUTZER = "aktiver_nutzer";
    final static String KEY_PASSWORT = "key_passwort";
    String aktiverNutzer;

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hilfMirDaddyDB = new DBHelferlein(this);

         if(PreferenceHelferlein.loadPassword(getApplicationContext(), KEY_PASSWORT).equals("")) {
             Intent intentRegistration = new Intent(this, RegistrationActivity.class);
             startActivity(intentRegistration);}
         else if(hilfMirDaddyDB.isEmpty()){
             Intent intentUCO = new Intent(this, UserCreationActivity.class);
             startActivity(intentUCO);
        }

        aktiverNutzer = PreferenceHelferlein.loadUserFromPref(getApplicationContext(), KEY_AKTIVERNUTZER);

        toolbar = findViewById(R.id.toolbarMAIN);
        tvToolbar = findViewById(R.id.TVToolbar);
        tvToolbar.setText(aktiverNutzer);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        ibtnGemueseUndObst = findViewById(R.id.imageButtonGemueseUndObst);
        ibtnGetraenke = findViewById(R.id.imageButtonGetraenke);
        ibtnWeizenprodukte = findViewById(R.id.imageButtonWeizenprodukte);
        ibtnMilchprodukte = findViewById(R.id.imageButtonMilchprodukte);
        ibtnFleischundWurst = findViewById(R.id.imageButtonFleischundWurst);

        ibtnMenue = findViewById(R.id.imageButtonHome);
        ibtnMenue. setImageResource(R.drawable.mann);
        ibtnWarenkorb = findViewById(R.id.imageButtonWarenkorb);

        ibtnSoundGetraenke = findViewById(R.id.imageButtonSoundGetraenke);
        ibtnSoundGemueseundObst = findViewById(R.id.imageButtonSoundGemueseundObst);
        ibtnSoundWeizenprodukte = findViewById(R.id.imageButtonSoundWeizenprodukte);
        ibtnSoundMilchprdoukte = findViewById(R.id.imageButtonSoundMilchprodukte);
        ibtnSoundFleischundWurst = findViewById(R.id.imageButtonSoundFleischundWurst);

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
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Bitte Passwort eingeben");
            final EditText passwortText = new EditText(this);
            passwortText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            builder.setView(passwortText);
            builder.setPositiveButton("Einloggen", (dialog, item) -> {
                if(PreferenceHelferlein.loadPassword(getApplicationContext(), KEY_PASSWORT).equals(passwortText.getText().toString())){
                    Intent intentMenue = new Intent(MainActivity.this,UserOverviewActivity.class);
                    startActivity(intentMenue);
                }else if(!PreferenceHelferlein.loadPassword(getApplicationContext(), KEY_PASSWORT).equals(passwortText.getText().toString())){
                    Toast.makeText(this, "Passwort nicht korrekt", Toast.LENGTH_LONG).show();
                }
            });
            builder.setNegativeButton("Abbrechen", null);
            builder.show();
        });

        if(PreferenceHelferlein.firstAppStart(getApplicationContext(),FIRST_APP_START_MAIN)) {
            hilfMirDaddyDB.setDrawableFromGallery("roterApfel", hilfMirDaddyDB.drawableToByteArray(MainActivity.this, R.drawable.roterapfel));
            hilfMirDaddyDB.setDrawableFromGallery("gruenerApfel", hilfMirDaddyDB.drawableToByteArray(MainActivity.this, R.drawable.gruenerapfel));
            hilfMirDaddyDB.setDrawableFromGallery("Salatgurke", hilfMirDaddyDB.drawableToByteArray(MainActivity.this, R.drawable.gurke));
            hilfMirDaddyDB.setDrawableFromGallery("Hartkaese", hilfMirDaddyDB.drawableToByteArray(MainActivity.this, R.drawable.hartkaese));
            hilfMirDaddyDB.setDrawableFromGallery("Streichkaese", hilfMirDaddyDB.drawableToByteArray(MainActivity.this, R.drawable.streichkaese));
            hilfMirDaddyDB.setDrawableFromGallery("Kaeseaufschnitt", hilfMirDaddyDB.drawableToByteArray(MainActivity.this, R.drawable.kaesescheibe));
            hilfMirDaddyDB.setDrawableFromGallery("sechserPackungEier", hilfMirDaddyDB.drawableToByteArray(MainActivity.this, R.drawable.sechser_eier));
            hilfMirDaddyDB.setDrawableFromGallery("zehnerPackungEier", hilfMirDaddyDB.drawableToByteArray(MainActivity.this, R.drawable.zehner_eier));
        }
    }


}