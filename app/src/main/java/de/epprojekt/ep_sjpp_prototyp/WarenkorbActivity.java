package de.epprojekt.ep_sjpp_prototyp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import de.epprojekt.ep_sjpp_prototyp.Helferlein.AddAndSetHelferlein;
import de.epprojekt.ep_sjpp_prototyp.Helferlein.AnimationsHelferlein;
import de.epprojekt.ep_sjpp_prototyp.Helferlein.DBHelferlein;
import de.epprojekt.ep_sjpp_prototyp.Helferlein.PreferenceHelferlein;

public class WarenkorbActivity extends AppCompatActivity {

    ImageButton ibtnLoeschen, ibtnHome;
    TextView tvToolbar;
    DBHelferlein hilfMirDaddyDB;
    AnimationsHelferlein hilfMirMommyAnimation;
    LinearLayout ownLinearLayout;
    int i = 0;
    final static String KEY_AKTIVERNUTZER = "aktiver_nutzer";
    String aktiverNutzer;
    int fiftyshadesofgrey = Color.parseColor("#CBD2D9");

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warenkorb);

        hilfMirDaddyDB = new DBHelferlein(this);
        hilfMirMommyAnimation = new AnimationsHelferlein();

        aktiverNutzer = PreferenceHelferlein.loadUserFromPref(getApplicationContext(), KEY_AKTIVERNUTZER);

        ownLinearLayout = findViewById(R.id.LinearLayoutWarenkorb);

        tvToolbar = findViewById(R.id.TVToolbar);
        tvToolbar.setText(aktiverNutzer);

        ibtnHome = findViewById(R.id.imageButtonHome);
        ibtnLoeschen = findViewById(R.id.imageButtonWarenkorb);
        ibtnLoeschen.setImageResource(R.drawable.loeschen);

        MediaPlayer mediaPlayerAllesLoeschen = MediaPlayer.create(this, R.raw.dengesamtenwarenkobleeren);

        ArrayList<String> arrayListOfWarenkorbitems = hilfMirDaddyDB.createArrayListOfWarenkorbItems(aktiverNutzer);

        while(i < arrayListOfWarenkorbitems.size()){
            AddAndSetHelferlein.addViewIBTN(AddAndSetHelferlein.setPicture(hilfMirDaddyDB.getWarenkorbItemname(arrayListOfWarenkorbitems.get(i),aktiverNutzer),arrayListOfWarenkorbitems.get(i),WarenkorbActivity.this,hilfMirDaddyDB,fiftyshadesofgrey,hilfMirMommyAnimation,ibtnLoeschen),ownLinearLayout);
            i++;
        }

        ibtnLoeschen.setOnClickListener(v -> {
            hilfMirDaddyDB.deleteCompletefromWarenkorb(aktiverNutzer);
            Intent refresh = new Intent(this,WarenkorbActivity.class);
            startActivity(refresh);
        });

        ibtnLoeschen.setOnClickListener(v -> {

            mediaPlayerAllesLoeschen.start();

            AlertDialog.Builder builder = new AlertDialog.Builder(WarenkorbActivity.this);
            builder.setTitle("Den Warenkorb vollständig löschen?");
            builder.setPositiveButtonIcon(getDrawable(R.drawable.check));
            builder.setPositiveButton("", (dialog, item) -> {
                hilfMirDaddyDB.deleteCompletefromWarenkorb(aktiverNutzer);
                Intent refresh = new Intent(WarenkorbActivity.this, WarenkorbActivity.class);
                startActivity(refresh);
            });
            builder.setNegativeButtonIcon(getDrawable(R.drawable.remove));
            builder.show();
        });

        ibtnHome.setOnClickListener(v -> {
            Intent intentHome = new Intent(this,MainActivity.class);
            startActivity(intentHome);
        });

    }

}