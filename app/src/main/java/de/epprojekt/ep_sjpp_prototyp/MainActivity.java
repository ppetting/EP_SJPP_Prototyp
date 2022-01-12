package de.epprojekt.ep_sjpp_prototyp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageButton ibtnGemueseUndObst, ibtnGetraenke, ibtnWarenkorb;
    DBHelferlein hilfMirDaddyDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ibtnWarenkorb = findViewById(R.id.imageButtonWarenkorb);
        ibtnGemueseUndObst = findViewById(R.id.imageButtonGemueseUndObst);
        ibtnGetraenke = findViewById(R.id.imageButtonGetraenke);

        ibtnGetraenke.setImageResource(R.drawable.getraenke);
        ibtnGemueseUndObst.setImageResource(R.drawable.gemueseundobst);
        ibtnWarenkorb.setImageResource(R.drawable.warenkorb);

        hilfMirDaddyDB = new DBHelferlein(this);

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

    }


}