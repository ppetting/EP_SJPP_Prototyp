package de.epprojekt.ep_sjpp_prototyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

public class GetraenkeActivity extends AppCompatActivity {

    ImageButton ibtnWasser, ibtnSaft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getraenke);

        ibtnWasser = findViewById(R.id.imageButtonWasser);
        ibtnWasser.setImageResource(R.drawable.wasser);
        ibtnSaft = findViewById(R.id.imageButtonSaft);
        ibtnSaft.setImageResource(R.drawable.saft);

        ibtnWasser.setOnClickListener(v -> {
            Intent intentWasser = new Intent(GetraenkeActivity.this,WasserActivity.class);
            startActivity(intentWasser);
        });

    }
}