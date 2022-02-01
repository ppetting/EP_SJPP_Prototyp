package de.epprojekt.ep_sjpp_prototyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NutzerErstellenActivity extends AppCompatActivity {

    EditText name, gruenerFlag, roterFlag, blauerFlag;
    Button benutzerAnlegen;
    DBHelferlein hilfMirDaddyDB;

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

         benutzerAnlegen.setOnClickListener(v -> {
             String nameTXT = name.getText().toString();
             Integer gruenerFlagTXT = Integer.parseInt(gruenerFlag.getText().toString());
             Integer roterFlagTXT = Integer.parseInt(roterFlag.getText().toString());
             Integer blauerFlagTXT = Integer.parseInt(blauerFlag.getText().toString());

             hilfMirDaddyDB.insertIntoUserdaten(nameTXT,gruenerFlagTXT,roterFlagTXT,blauerFlagTXT);
             Toast.makeText(NutzerErstellenActivity.this,"Benutzer wurde angelegt", Toast.LENGTH_SHORT).show();

             hilfMirDaddyDB.createWarenkorbOnClick(nameTXT);

             Intent refresh = new Intent(NutzerErstellenActivity.this, UserUebersichtActivity.class);
             startActivity(refresh);

         });

    }
}
