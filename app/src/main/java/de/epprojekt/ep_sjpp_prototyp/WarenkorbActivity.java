package de.epprojekt.ep_sjpp_prototyp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import de.epprojekt.ep_sjpp_prototyp.Helferlein.AddAndSetHelferlein;
import de.epprojekt.ep_sjpp_prototyp.Helferlein.DBHelferlein;
import de.epprojekt.ep_sjpp_prototyp.Menuebereich.UserOverviewActivity;

public class WarenkorbActivity extends AppCompatActivity {

    ImageButton ibtnLoeschen, ibtnHome;
    TextView tvToolbar;
    DBHelferlein hilfMirDaddyDB;
    LinearLayout ownLinearLayout;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warenkorb);

        hilfMirDaddyDB = new DBHelferlein(this);

        ownLinearLayout = findViewById(R.id.LinearLayoutWarenkorb);

        tvToolbar = findViewById(R.id.TVToolbar);
        tvToolbar.setText(UserOverviewActivity.aktiverNutzerUOA);

        ibtnHome = findViewById(R.id.imageButtonHome);
        ibtnLoeschen = findViewById(R.id.imageButtonWarenkorb);
        ibtnLoeschen.setImageResource(R.drawable.loeschen);


        ArrayList<Integer> arrayListOfDrawablesID = hilfMirDaddyDB.createArrayListOfWarenkorb(UserOverviewActivity.aktiverNutzerUOA);
        ArrayList<String> arrayListOfWarenkorbitems = hilfMirDaddyDB.createArrayListOfWarenkorbItems(UserOverviewActivity.aktiverNutzerUOA);

        while(i < arrayListOfDrawablesID.size()){
            AddAndSetHelferlein.addViewIBTN(AddAndSetHelferlein.setPicture(arrayListOfDrawablesID.get(i),arrayListOfWarenkorbitems.get(i),WarenkorbActivity.this,hilfMirDaddyDB),ownLinearLayout);
            i++;
        }

        ibtnLoeschen.setOnClickListener(v -> {
            hilfMirDaddyDB.deleteCompletefromWarenkorb(UserOverviewActivity.aktiverNutzerUOA);
            Intent refresh = new Intent(this,WarenkorbActivity.class);
            startActivity(refresh);
        });

        ibtnHome.setOnClickListener(v -> {
            Intent intentHome = new Intent(this,MainActivity.class);
            startActivity(intentHome);
        });

    }



}