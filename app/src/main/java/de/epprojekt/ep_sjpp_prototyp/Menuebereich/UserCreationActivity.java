package de.epprojekt.ep_sjpp_prototyp.Menuebereich;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import de.epprojekt.ep_sjpp_prototyp.Helferlein.DBHelferlein;
import de.epprojekt.ep_sjpp_prototyp.MainActivity;
import de.epprojekt.ep_sjpp_prototyp.R;

public class UserCreationActivity extends AppCompatActivity {

    public EditText name, gruenerFlag, roterFlag, blauerFlag;
    public Button benutzerAnlegen;
    DBHelferlein hilfMirDaddyDB;
    public static String nameTXT;
    public String aktiverUser = UserOverviewActivity.aktiverNutzerUOA;
    public Integer gruenerFlagTXT, roterFlagTXT, blauerFlagTXT;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_creation);

         hilfMirDaddyDB = new DBHelferlein(this);




        name = findViewById(R.id.ETname);
         gruenerFlag = findViewById(R.id.ETgruen);
         roterFlag = findViewById(R.id.ETrot);
         blauerFlag = findViewById(R.id.ETblau);
         benutzerAnlegen = findViewById(R.id.btnCreate);

        if(UserOverviewActivity.anlegen_bearbeiten.equals("Benutzer anlegen")){
            //Button Anzeigetext
            benutzerAnlegen.setText(UserOverviewActivity.anlegen_bearbeiten);
        }else if(UserOverviewActivity.anlegen_bearbeiten.equals("Benutzer aktualisieren")){
            //Button Anzeigetext
            benutzerAnlegen.setText(UserOverviewActivity.anlegen_bearbeiten);
            //Vorausgefüllt mit den aktuellen Userdaten
            name.setText(aktiverUser);
            gruenerFlag.setText(hilfMirDaddyDB.getFlaganzahlString(aktiverUser,"flaggruen"));
            roterFlag.setText(hilfMirDaddyDB.getFlaganzahlString(aktiverUser,"flagrot"));
            blauerFlag.setText(hilfMirDaddyDB.getFlaganzahlString(aktiverUser,"flagblau"));
        }

         benutzerAnlegen.setOnClickListener(v -> {
             //Benutzer anlegen
             if(UserOverviewActivity.anlegen_bearbeiten.equals("Benutzer anlegen")) {
                 nameTXT = name.getText().toString();
                 gruenerFlagTXT = Integer.parseInt(gruenerFlag.getText().toString());
                 roterFlagTXT = Integer.parseInt(roterFlag.getText().toString());
                 blauerFlagTXT = Integer.parseInt(blauerFlag.getText().toString());

                 hilfMirDaddyDB.insertIntoUserdaten(nameTXT, gruenerFlagTXT, blauerFlagTXT, roterFlagTXT);
                 hilfMirDaddyDB.createWarenkorbOnClick(nameTXT);

                 UserOverviewActivity.aktiverNutzerUOA = nameTXT;
                 Intent refresh = new Intent(UserCreationActivity.this, MainActivity.class);
                 startActivity(refresh);

                 Toast.makeText(UserCreationActivity.this, "Benutzer wurde angelegt", Toast.LENGTH_SHORT).show();

             //Benutzer bearbeiten
             }else if(UserOverviewActivity.anlegen_bearbeiten.equals("Benutzer aktualisieren")){

                 nameTXT = name.getText().toString();
                 gruenerFlagTXT = Integer.parseInt(gruenerFlag.getText().toString());
                 roterFlagTXT = Integer.parseInt(roterFlag.getText().toString());
                 blauerFlagTXT = Integer.parseInt(blauerFlag.getText().toString());

                 hilfMirDaddyDB.updateUserdata(aktiverUser,nameTXT, gruenerFlagTXT, blauerFlagTXT, roterFlagTXT);

                 UserOverviewActivity.aktiverNutzerUOA = nameTXT;

                 Intent refresh = new Intent(UserCreationActivity.this, MainActivity.class);
                 startActivity(refresh);

                 Toast.makeText(UserCreationActivity.this, "Benutzer aktualisiert", Toast.LENGTH_SHORT).show();

             }else{
                 Toast.makeText(UserCreationActivity.this, "FEHELER!!!!", Toast.LENGTH_SHORT).show();
             }


         });

    }

}
