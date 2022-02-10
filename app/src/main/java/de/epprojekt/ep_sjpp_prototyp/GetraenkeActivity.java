package de.epprojekt.ep_sjpp_prototyp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

public class GetraenkeActivity extends AppCompatActivity {

    ImageButton ibtnWasser, ibtnSaft, ibtnWarenkorb, ibtnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getraenke);

        //Toolbar aktiver Username wird angezeigt
        TextView tvAktiverUser = findViewById(R.id.TVToolbar);
        tvAktiverUser.setText(UserUebersichtActivity.aktiverNutzerUUA);

        ibtnWasser = findViewById(R.id.imageButtonWasser);
        ibtnWasser.setImageResource(R.drawable.wasser);

        ibtnWarenkorb = findViewById(R.id.imageButtonWarenkorb);
        ibtnWarenkorb.setImageResource(R.drawable.warenkorb);

        ibtnSaft = findViewById(R.id.imageButtonSaft);
        ibtnSaft.setImageResource(R.drawable.saft);

        ibtnHome = findViewById(R.id.imageButtonHome);
        ibtnHome.setImageResource(R.drawable.home);

        ibtnWasser.setOnClickListener(v -> {
            Intent intentWasser = new Intent(GetraenkeActivity.this,WasserActivity.class);
            startActivity(intentWasser);
        });

        ibtnWarenkorb.setOnClickListener(v -> {
            Intent intentWarenkorb = new Intent(GetraenkeActivity.this, WarenkorbActivity.class);
            startActivity(intentWarenkorb);
        });

        ibtnHome.setOnClickListener(v -> {
            Intent intentHome = new Intent(GetraenkeActivity.this, MainActivity.class);
            startActivity(intentHome);
        });

    }
}