package de.epprojekt.ep_sjpp_prototyp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;

public class GemueseUndObstActivity extends AppCompatActivity {

    ImageButton ibtnApfel, ibtnGurke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gemuese_und_obst);

        ibtnApfel = findViewById(R.id.imageButtonApfel);
        ibtnGurke = findViewById(R.id.imageButtonGurke);

        ibtnApfel.setImageResource(R.drawable.apfel);
        ibtnGurke.setImageResource(R.drawable.gurke);

    }


}