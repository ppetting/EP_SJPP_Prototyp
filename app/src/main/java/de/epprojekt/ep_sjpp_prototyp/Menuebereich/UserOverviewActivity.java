package de.epprojekt.ep_sjpp_prototyp.Menuebereich;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import de.epprojekt.ep_sjpp_prototyp.Helferlein.AddAndSetHelferlein;
import de.epprojekt.ep_sjpp_prototyp.Helferlein.DBHelferlein;
import de.epprojekt.ep_sjpp_prototyp.Helferlein.PreferenceHelferlein;
import de.epprojekt.ep_sjpp_prototyp.MainActivity;
import de.epprojekt.ep_sjpp_prototyp.R;

public class UserOverviewActivity extends AppCompatActivity {

    ImageButton ibtnBenutzerErstellen, ibtnHome, ibtnPictureChange;
    LinearLayout ownLinearLayout;
    DBHelferlein hilfMirDaddyDB;
    public static String anlegen_bearbeiten = "Benutzer anlegen";
    TextView tvToolbar;
    //public static String aktiverNutzerUOA;
    int j = 0;
    int grandbudapesthotelrosa = Color.parseColor("#FA86C4");
    final static String KEY_AKTIVERNUTZER = "aktiver_nutzer";
    String aktiverNutzer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_overview);

        ownLinearLayout = findViewById(R.id.LinearLayoutDeletePage);

        hilfMirDaddyDB = new DBHelferlein(this);

        aktiverNutzer = PreferenceHelferlein.loadUserFromPref(getApplicationContext(), KEY_AKTIVERNUTZER);

        ibtnBenutzerErstellen = findViewById(R.id.imageButtonWarenkorb);
        ibtnBenutzerErstellen.setImageResource(R.drawable.plus);
        ibtnHome = findViewById(R.id.imageButtonHome);
        ibtnPictureChange = findViewById(R.id.imageButtonPictureChangeMenue);

        tvToolbar = findViewById(R.id.TVToolbar);
        tvToolbar.setText(aktiverNutzer);

        aktiverNutzer = UserCreationActivity.nameTXT;

        ibtnBenutzerErstellen.setOnClickListener(v -> {
            anlegen_bearbeiten = "Benutzer anlegen";
            Intent intentNutzerErstellen = new Intent(this, UserCreationActivity.class);
            startActivity(intentNutzerErstellen);
        });

        ibtnHome.setOnClickListener(v -> {
            Intent intentHome = new Intent(this, MainActivity.class);
            startActivity(intentHome);
        });

        ibtnPictureChange.setOnClickListener(v -> {
            Intent intentHome = new Intent(this, IbtnChangerActivity.class);
            startActivity(intentHome);
        });

        while (j < hilfMirDaddyDB.createArrayListOfUserdaten().size()) {
            AddAndSetHelferlein.addViewBTN(AddAndSetHelferlein.generateButtonsAndSetName(hilfMirDaddyDB.createArrayListOfUserdaten().get(j),UserOverviewActivity.this,grandbudapesthotelrosa,hilfMirDaddyDB),ownLinearLayout);
            j++;
        }
    }

}
