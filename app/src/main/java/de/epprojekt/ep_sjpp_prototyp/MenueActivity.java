package de.epprojekt.ep_sjpp_prototyp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MenueActivity extends AppCompatActivity {

    EditText name, gruenerFlag, roterFlag, blauerFlag;
    Button benutzerAnlegen, benutzerWechseln, benutzerBearbeiten, benutzerLoeschen, benutzerAnzeigen;
    DBHelferlein hilfMirDaddyDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menue);

         hilfMirDaddyDB = new DBHelferlein(this);

         name = findViewById(R.id.ETname);
         gruenerFlag = findViewById(R.id.ETgruen);
         roterFlag = findViewById(R.id.ETrot);
         blauerFlag = findViewById(R.id.ETblau);

         benutzerAnlegen = findViewById(R.id.btnCreate);
         benutzerWechseln = findViewById(R.id.btnChange);
         benutzerBearbeiten = findViewById(R.id.btnUpdate);
         benutzerLoeschen = findViewById(R.id.btnDelete);
         benutzerAnzeigen = findViewById(R.id.btnView);


         benutzerAnlegen.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String nameTXT = name.getText().toString();
                 Integer gruenerFlagTXT = Integer.parseInt(gruenerFlag.getText().toString());
                 Integer roterFlagTXT = Integer.parseInt(roterFlag.getText().toString());
                 Integer blauerFlagTXT = Integer.parseInt(blauerFlag.getText().toString());

                 hilfMirDaddyDB.insertIntoUserdaten(nameTXT,gruenerFlagTXT,roterFlagTXT,blauerFlagTXT);
                 Toast.makeText(MenueActivity.this,"Benutzer wurde angelegt", Toast.LENGTH_SHORT).show();
             }
         });


    }
}
