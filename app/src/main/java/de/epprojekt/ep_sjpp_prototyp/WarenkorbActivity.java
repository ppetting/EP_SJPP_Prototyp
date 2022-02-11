package de.epprojekt.ep_sjpp_prototyp;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import de.epprojekt.ep_sjpp_prototyp.Helferlein.DBHelferlein;
import de.epprojekt.ep_sjpp_prototyp.Menuebereich.UserOverviewActivity;

public class WarenkorbActivity extends AppCompatActivity {

    ImageButton ibtnLoeschen, ibtnHome;
    DBHelferlein hilfMirDaddyDB;
    LinearLayout ownLinearLayout;
    UserOverviewActivity uua;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warenkorb);


        //Toolbar aktiver Username wird angezeigt
        TextView tvAktiverUser = findViewById(R.id.TVToolbar);
        tvAktiverUser.setText(UserOverviewActivity.aktiverNutzerUOA);

        ibtnHome = findViewById(R.id.imageButtonHome);
        ibtnHome.setImageResource(R.drawable.home);

        ibtnLoeschen = findViewById(R.id.imageButtonWarenkorb);


        ownLinearLayout = findViewById(R.id.LinearLayoutWarenkorb);

        hilfMirDaddyDB = new DBHelferlein(this);
        uua = new UserOverviewActivity();

        ArrayList<Integer> arrayListOfDrawablesID = hilfMirDaddyDB.createArrayListOfWarenkorb(UserOverviewActivity.aktiverNutzerUOA);
        ArrayList<String> arrayListOfWarenkorbitems = hilfMirDaddyDB.createArrayListOfWarenkorbItems(UserOverviewActivity.aktiverNutzerUOA);

        while(i < arrayListOfDrawablesID.size()){
            setPicture(arrayListOfDrawablesID.get(i),arrayListOfWarenkorbitems.get(i));
            i++;
        }

        ibtnLoeschen.setOnClickListener(v -> {
            hilfMirDaddyDB.deleteCompletefromWarenkorb(UserOverviewActivity.aktiverNutzerUOA);
            Intent refresh = new Intent(WarenkorbActivity.this,WarenkorbActivity.class);
            startActivity(refresh);
            finish();
        });

        ibtnHome.setOnClickListener(v -> {
            Intent intentHome = new Intent(WarenkorbActivity.this,MainActivity.class);
            startActivity(intentHome);
        });

    }

    public void addView (ImageButton imageButton, int width, int height){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width,height);
        params.setMargins(0,10,0,10);
        imageButton.setLayoutParams(params);
        ownLinearLayout.addView(imageButton);
    }

    @SuppressLint("ResourceType")
    public void setPicture(Integer i, String j){
        ImageButton imageButton = new ImageButton(WarenkorbActivity.this);
        imageButton.setImageResource(i);
        imageButton.setScaleType(ImageView.ScaleType.FIT_CENTER);

        imageButton.setOnClickListener(v -> {
            hilfMirDaddyDB.deleteIndividuallyfromWarenkorb(j, UserOverviewActivity.aktiverNutzerUOA);
            Intent refresh = new Intent(WarenkorbActivity.this,WarenkorbActivity.class);
            startActivity(refresh);
            finish();
        });
        addView(imageButton,400,400);
    }

}