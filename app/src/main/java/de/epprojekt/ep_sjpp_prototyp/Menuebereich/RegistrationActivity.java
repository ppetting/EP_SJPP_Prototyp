package de.epprojekt.ep_sjpp_prototyp.Menuebereich;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import de.epprojekt.ep_sjpp_prototyp.Helferlein.PreferenceHelferlein;
import de.epprojekt.ep_sjpp_prototyp.R;

public class RegistrationActivity extends AppCompatActivity {

    EditText etPasswort;
    Button btnRegistrierung;
    final static String KEY_PASSWORT = "key_passwort";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        etPasswort = findViewById(R.id.editTextPassword);
        btnRegistrierung = findViewById(R.id.btnRegistration);

        btnRegistrierung.setOnClickListener(v -> {
            if(etPasswort.length() < 5){
                Toast.makeText(RegistrationActivity.this, "Passwort muss mindestens fünf Zeichen lang sein", Toast.LENGTH_LONG).show();
            }else{
                PreferenceHelferlein.savePassword(RegistrationActivity.this,etPasswort.getText(),KEY_PASSWORT);
                Intent intent = new Intent(this,UserCreationActivity.class);
                startActivity(intent);
            }
        });
    }
}