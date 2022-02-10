package de.epprojekt.ep_sjpp_prototyp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NutzerErstellenActivity extends AppCompatActivity {

    EditText name, gruenerFlag, roterFlag, blauerFlag;
    Button benutzerAnlegen;
    DBHelferlein hilfMirDaddyDB;
    static String nameTXT;
    String aktiverUser = UserUebersichtActivity.aktiverNutzerUUA;
    Integer gruenerFlagTXT, roterFlagTXT, blauerFlagTXT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutzer_erstellen);

         hilfMirDaddyDB = new DBHelferlein(this);




        name = findViewById(R.id.ETname);
         gruenerFlag = findViewById(R.id.ETgruen);
         roterFlag = findViewById(R.id.ETrot);
         blauerFlag = findViewById(R.id.ETblau);
         benutzerAnlegen = findViewById(R.id.btnCreate);

        if(UserUebersichtActivity.anlegen_bearbeiten.equals("Benutzer anlegen")){
            //Button Anzeigetext
            benutzerAnlegen.setText(UserUebersichtActivity.anlegen_bearbeiten);
        }else if(UserUebersichtActivity.anlegen_bearbeiten.equals("Benutzer aktualisieren")){
            //Button Anzeigetext
            benutzerAnlegen.setText(UserUebersichtActivity.anlegen_bearbeiten);
            //Vorausgefüllt mit den aktuellen Userdaten
            name.setText(aktiverUser);
            gruenerFlag.setText(hilfMirDaddyDB.getFlaganzahlString(aktiverUser,"flaggruen"));
            roterFlag.setText(hilfMirDaddyDB.getFlaganzahlString(aktiverUser,"flagrot"));
            blauerFlag.setText(hilfMirDaddyDB.getFlaganzahlString(aktiverUser,"flagblau"));
        }

         benutzerAnlegen.setOnClickListener(v -> {
             //Benutzer anlegen
             if(UserUebersichtActivity.anlegen_bearbeiten.equals("Benutzer anlegen")) {
                 nameTXT = name.getText().toString();
                 gruenerFlagTXT = Integer.parseInt(gruenerFlag.getText().toString());
                 roterFlagTXT = Integer.parseInt(roterFlag.getText().toString());
                 blauerFlagTXT = Integer.parseInt(blauerFlag.getText().toString());

                 hilfMirDaddyDB.insertIntoUserdaten(nameTXT, gruenerFlagTXT, blauerFlagTXT, roterFlagTXT);
                 hilfMirDaddyDB.createWarenkorbOnClick(nameTXT);

                 UserUebersichtActivity.aktiverNutzerUUA = nameTXT;
                 Intent refresh = new Intent(NutzerErstellenActivity.this, MainActivity.class);
                 startActivity(refresh);

                 Toast.makeText(NutzerErstellenActivity.this, "Benutzer wurde angelegt", Toast.LENGTH_SHORT).show();

             //Benutzer bearbeiten
             }else if(UserUebersichtActivity.anlegen_bearbeiten.equals("Benutzer aktualisieren")){

                 nameTXT = name.getText().toString();
                 gruenerFlagTXT = Integer.parseInt(gruenerFlag.getText().toString());
                 roterFlagTXT = Integer.parseInt(roterFlag.getText().toString());
                 blauerFlagTXT = Integer.parseInt(blauerFlag.getText().toString());

                 hilfMirDaddyDB.updateUserdata(aktiverUser,nameTXT, gruenerFlagTXT, blauerFlagTXT, roterFlagTXT);

                 UserUebersichtActivity.aktiverNutzerUUA = nameTXT;

                 Intent refresh = new Intent(NutzerErstellenActivity.this, MainActivity.class);
                 startActivity(refresh);

                 Toast.makeText(NutzerErstellenActivity.this, "Benutzer aktualisiert", Toast.LENGTH_SHORT).show();

             }else{
                 Toast.makeText(NutzerErstellenActivity.this, "FEHELER!!!!", Toast.LENGTH_SHORT).show();
             }


         });

    }

}
