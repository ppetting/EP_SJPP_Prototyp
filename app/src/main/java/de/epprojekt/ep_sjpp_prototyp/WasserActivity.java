package de.epprojekt.ep_sjpp_prototyp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;

public class WasserActivity extends AppCompatActivity {

    ImageButton ibtnStill, ibtnSprudel;
    DBHelferlein hilfMirDaddyDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wasser);

        ibtnStill = findViewById(R.id.imageButtonStill);
        ibtnStill.setImageResource(R.drawable.stilleswasser);

        ibtnSprudel = findViewById(R.id.imageButtonSprudel);
        ibtnSprudel.setImageResource(R.drawable.sprudelwasser);

        hilfMirDaddyDB = new DBHelferlein(this);

        ibtnSprudel.setOnClickListener(v -> hilfMirDaddyDB.insertIntoWarenkorb(ibtnSprudel, R.drawable.sprudelwasser));

        ibtnStill.setOnClickListener(v -> hilfMirDaddyDB.insertIntoWarenkorb(ibtnStill, R.drawable.stilleswasser));
}
}