package de.epprojekt.ep_sjpp_prototyp.Menuebereich;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import de.epprojekt.ep_sjpp_prototyp.Helferlein.DBHelferlein;
import de.epprojekt.ep_sjpp_prototyp.Helferlein.PreferenceHelferlein;
import de.epprojekt.ep_sjpp_prototyp.MainActivity;
import de.epprojekt.ep_sjpp_prototyp.R;

public class UserCreationActivity extends AppCompatActivity {

    public EditText name, gruenerFlag, roterFlag, blauerFlag;
    ImageButton ibtnHome, ibtnUseruebersicht;
    public Button benutzerAnlegen;
    DBHelferlein hilfMirDaddyDB;
    public static String nameTXT;
    public Integer gruenerFlagTXT, roterFlagTXT, blauerFlagTXT;
    final static String KEY_AKTIVERNUTZER = "aktiver_nutzer";
    String aktiverNutzer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_creation);

         hilfMirDaddyDB = new DBHelferlein(this);

         aktiverNutzer = PreferenceHelferlein.loadUserFromPref(getApplicationContext(), KEY_AKTIVERNUTZER);

         name = findViewById(R.id.ETname);
         gruenerFlag = findViewById(R.id.ETgruen);
         roterFlag = findViewById(R.id.ETrot);
         blauerFlag = findViewById(R.id.ETblau);
         benutzerAnlegen = findViewById(R.id.btnCreate);
         ibtnHome = findViewById(R.id.imageButtonHome);
         ibtnUseruebersicht = findViewById(R.id.imageButtonWarenkorb);
         ibtnUseruebersicht.setImageResource(R.drawable.mann);

        if(UserOverviewActivity.anlegen_bearbeiten.equals("Benutzer anlegen")){
            //Button Anzeigetext
            benutzerAnlegen.setText(UserOverviewActivity.anlegen_bearbeiten);
        }else if(UserOverviewActivity.anlegen_bearbeiten.equals("Benutzer aktualisieren")){
            //Button Anzeigetext
            benutzerAnlegen.setText(UserOverviewActivity.anlegen_bearbeiten);
            //Vorausgefüllt mit den aktuellen Userdaten
            gruenerFlag.setText(hilfMirDaddyDB.getFlaganzahlString(aktiverNutzer,"flaggruen"));
            roterFlag.setText(hilfMirDaddyDB.getFlaganzahlString(aktiverNutzer,"flagrot"));
            blauerFlag.setText(hilfMirDaddyDB.getFlaganzahlString(aktiverNutzer,"flagblau"));
            name.setText(aktiverNutzer);
        }

        ibtnHome.setOnClickListener(v -> {
            Intent intentHome = new Intent(this, MainActivity.class);
            startActivity(intentHome);
        });

        ibtnUseruebersicht.setOnClickListener(v -> {
            Intent intentUser = new Intent(this, UserOverviewActivity.class);
            startActivity(intentUser);
        });

         benutzerAnlegen.setOnClickListener(v -> {
             //Benutzer anlegen
             if(UserOverviewActivity.anlegen_bearbeiten.equals("Benutzer anlegen")) {
                 nameTXT = name.getText().toString();
                 gruenerFlagTXT = Integer.parseInt(gruenerFlag.getText().toString());
                 roterFlagTXT = Integer.parseInt(roterFlag.getText().toString());
                 blauerFlagTXT = Integer.parseInt(blauerFlag.getText().toString());

                 hilfMirDaddyDB.insertIntoUserdaten(nameTXT, gruenerFlagTXT, blauerFlagTXT, roterFlagTXT);
                 hilfMirDaddyDB.createWarenkorbOnClick(nameTXT);

                 PreferenceHelferlein.saveUserInPref(getApplicationContext(),nameTXT,KEY_AKTIVERNUTZER);
                 Intent refresh = new Intent(UserCreationActivity.this, MainActivity.class);
                 startActivity(refresh);

                 Toast.makeText(UserCreationActivity.this, "Benutzer wurde angelegt", Toast.LENGTH_SHORT).show();

             //Benutzer bearbeiten
             }else if(UserOverviewActivity.anlegen_bearbeiten.equals("Benutzer aktualisieren")){

                 final CharSequence[] options = {"Ja", "Nein"};
                 AlertDialog.Builder builder = new AlertDialog.Builder(this);
                 builder.setTitle("Jeglicher Warenkorbinhalt geht verloren. Fortfahren?");
                 builder.setItems(options, (dialog, item) -> {
                     if (options[item].equals("Ja")) {
                         dialog.dismiss();
                         hilfMirDaddyDB.deletefromUserdaten(PreferenceHelferlein.loadUserFromPref(this, KEY_AKTIVERNUTZER));
                         dialog.dismiss();
                         PreferenceHelferlein.saveUserInPref(this,"", KEY_AKTIVERNUTZER);

                         nameTXT = name.getText().toString();
                         gruenerFlagTXT = Integer.parseInt(gruenerFlag.getText().toString());
                         roterFlagTXT = Integer.parseInt(roterFlag.getText().toString());
                         blauerFlagTXT = Integer.parseInt(blauerFlag.getText().toString());

                         hilfMirDaddyDB.insertIntoUserdaten(nameTXT, gruenerFlagTXT, blauerFlagTXT, roterFlagTXT);
                         hilfMirDaddyDB.createWarenkorbOnClick(nameTXT);

                         PreferenceHelferlein.saveUserInPref(getApplicationContext(),nameTXT,KEY_AKTIVERNUTZER);
                         Intent refresh = new Intent(UserCreationActivity.this, MainActivity.class);
                         startActivity(refresh);

                         Toast.makeText(UserCreationActivity.this, "Benutzer wurde aktuallisiert", Toast.LENGTH_SHORT).show();

                     } else if (options[item].equals("Nein")) {
                         dialog.dismiss();
                         Intent intent = new Intent(this,UserOverviewActivity.class);
                         startActivity(intent);
                     }
                 });
                 builder.show();
             }else{
                 Toast.makeText(UserCreationActivity.this, "Fehler", Toast.LENGTH_SHORT).show();
             }
         });

    }
}
