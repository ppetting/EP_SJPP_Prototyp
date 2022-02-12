package de.epprojekt.ep_sjpp_prototyp.Menuebereich;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import de.epprojekt.ep_sjpp_prototyp.Helferlein.AddAndSetHelferlein;
import de.epprojekt.ep_sjpp_prototyp.Helferlein.DBHelferlein;
import de.epprojekt.ep_sjpp_prototyp.MainActivity;
import de.epprojekt.ep_sjpp_prototyp.R;

public class UserOverviewActivity extends AppCompatActivity {

    ImageButton ibtnBenutzerErstellen;
    //Button btnLoeschen,btnWechsel,btnBearbeiten;
    LinearLayout ownLinearLayout;
    DBHelferlein hilfMirDaddyDB;
    public static String anlegen_bearbeiten = "Benutzer anlegen";

    public static String aktiverNutzerUOA;
    int j = 0;
    int grandbudapesthotelrosa = Color.parseColor("#FA86C4");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_overview);


        ibtnBenutzerErstellen = findViewById(R.id.imageButtonWarenkorb);


        //Toolbar aktiver Username wird angezeigt
        TextView tvAktiverUser = findViewById(R.id.TVToolbar);
        tvAktiverUser.setText(UserOverviewActivity.aktiverNutzerUOA);


        ownLinearLayout = findViewById(R.id.LinearLayoutDeletePage);

        hilfMirDaddyDB = new DBHelferlein(this);

        aktiverNutzerUOA = UserCreationActivity.nameTXT;

        ibtnBenutzerErstellen.setOnClickListener(v -> {
            //Status festlegen auf Benutzer anlegen
            anlegen_bearbeiten = "Benutzer anlegen";
            Intent intentNutzerErstellen = new Intent(UserOverviewActivity.this, UserCreationActivity.class);
            startActivity(intentNutzerErstellen);
        });

        while (j < hilfMirDaddyDB.createArrayListOfUserdaten().size()) {
            AddAndSetHelferlein.addViewBTN(AddAndSetHelferlein.generateButtonsAndSetName(hilfMirDaddyDB.createArrayListOfUserdaten().get(j),UserOverviewActivity.this,grandbudapesthotelrosa,hilfMirDaddyDB),ownLinearLayout);
            j++;
        }
    }




}
