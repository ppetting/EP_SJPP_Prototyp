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

import de.epprojekt.ep_sjpp_prototyp.Helferlein.DBHelferlein;
import de.epprojekt.ep_sjpp_prototyp.MainActivity;
import de.epprojekt.ep_sjpp_prototyp.R;

public class UserOverviewActivity extends AppCompatActivity {

    ImageButton ibtnBenutzerErstellen;
    //Button btnLoeschen,btnWechsel,btnBearbeiten;
    LinearLayout ownLinearLayout;
    DBHelferlein hilfMirDaddyDB;
    static String anlegen_bearbeiten = "Benutzer anlegen";

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
            generateButtonsAndSetName(hilfMirDaddyDB.createArrayListOfUserdaten().get(j));
            j++;
        }
    }


    public void addView (Button button, int width, int height){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width,height);
        params.setMargins(0,10,0,10);
        button.setLayoutParams(params);
        ownLinearLayout.addView(button);
    }

    @SuppressLint("ResourceType")
    public void generateButtonsAndSetName(String username){
        Button button = new Button(UserOverviewActivity.this);
        button.setText(username);
        button.setBackgroundColor(grandbudapesthotelrosa);

        button.setOnClickListener(v -> {
            aktiverNutzerUOA = username;
            menueauswahl();

        });

        addView(button,400,400);
    }
    private void menueauswahl() {
        final CharSequence[] options = {"Benutzer wechsel", "Benutzer bearbeiten", "Benutzer löschen", "Abbrechen"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Einstellungen für " + aktiverNutzerUOA);
        builder.setItems(options, (dialog, item) -> {
            if (options[item].equals("Benutzer wechsel")) {
                dialog.dismiss();
                Intent refresh = new Intent(UserOverviewActivity.this, MainActivity.class);
                startActivity(refresh);
            } else if (options[item].equals("Benutzer bearbeiten")) {
                //Status festlegen auf Benutzer bearbeiten
                anlegen_bearbeiten = "Benutzer aktualisieren";
                dialog.dismiss();
                Intent intentNutzerErstellen = new Intent(UserOverviewActivity.this, UserCreationActivity.class);
                startActivity(intentNutzerErstellen);

            } else if (options[item].equals("Benutzer löschen")) {
                hilfMirDaddyDB.deletefromUserdaten(aktiverNutzerUOA);
                dialog.dismiss();
                Intent refresh = new Intent(UserOverviewActivity.this, MainActivity.class);
                startActivity(refresh);
            } else if (options[item].equals("Abbrechen")) {
                dialog.dismiss();
            }
        });
        builder.show();
    }


/*
// Altes Popup
//ehemaliger Aufruf:  onButtonShowPopupWindowClick(ownLinearLayout);
    @SuppressLint("ClickableViewAccessibility")
    public void onButtonShowPopupWindowClick(View view) {
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = layoutInflater.inflate(R.layout.activity_popup_delete_change,null );
        final PopupWindow popupWindow = new PopupWindow(popupView, 800, 800, true);
        popupWindow.showAtLocation(view,Gravity.CENTER, 0, 0);

        btnLoeschen = (Button) popupView.findViewById(R.id.btnBenutzerLoeschen);
        btnWechsel = (Button) popupView.findViewById(R.id.btnBenutzerWechseln);
        btnBearbeiten = (Button) popupView.findViewById(R.id.btnBenutzerBearbeiten);

        btnLoeschen.setOnClickListener(v -> {
            hilfMirDaddyDB.deletefromUserdaten(aktiverNutzerUUA);
            popupWindow.dismiss();
            Intent refresh = new Intent(UserUebersichtActivity.this, MainActivity.class);
            startActivity(refresh);
        });

        btnWechsel.setOnClickListener(v -> {
            popupWindow.dismiss();
            Intent refresh = new Intent(UserUebersichtActivity.this, MainActivity.class);
            startActivity(refresh);
        });

        popupView.setOnTouchListener((v, event) -> {
            popupWindow.dismiss();
            return true;
        });
    }*/

}
