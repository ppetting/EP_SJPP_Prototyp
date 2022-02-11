package de.epprojekt.ep_sjpp_prototyp.Einkauf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import de.epprojekt.ep_sjpp_prototyp.MainActivity;
import de.epprojekt.ep_sjpp_prototyp.Menuebereich.UserOverviewActivity;
import de.epprojekt.ep_sjpp_prototyp.R;
import de.epprojekt.ep_sjpp_prototyp.WarenkorbActivity;

public class MilchprdoukteActivity extends AppCompatActivity {

    ImageButton ibtnWasser, ibtnSaft, ibtnWarenkorb, ibtnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_milchprodukte);

        //Toolbar aktiver Username wird angezeigt
        TextView tvAktiverUser = findViewById(R.id.TVToolbar);
        tvAktiverUser.setText(UserOverviewActivity.aktiverNutzerUOA);

        ibtnWasser = findViewById(R.id.imageButtonWasser);


        ibtnWarenkorb = findViewById(R.id.imageButtonWarenkorb);
        ibtnWarenkorb.setImageResource(R.drawable.warenkorb);

        ibtnSaft = findViewById(R.id.imageButtonSaft);


        ibtnHome = findViewById(R.id.imageButtonHome);
        ibtnHome.setImageResource(R.drawable.home);

        ibtnWasser.setOnClickListener(v -> {
            Intent intentWasser = new Intent(MilchprdoukteActivity.this, KaeseActivity.class);
            startActivity(intentWasser);
        });

        ibtnWarenkorb.setOnClickListener(v -> {
            Intent intentWarenkorb = new Intent(MilchprdoukteActivity.this, WarenkorbActivity.class);
            startActivity(intentWarenkorb);
        });

        ibtnHome.setOnClickListener(v -> {
            Intent intentHome = new Intent(MilchprdoukteActivity.this, MainActivity.class);
            startActivity(intentHome);
        });

    }
}