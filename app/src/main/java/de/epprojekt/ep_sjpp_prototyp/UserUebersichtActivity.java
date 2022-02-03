package de.epprojekt.ep_sjpp_prototyp;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

public class UserUebersichtActivity extends AppCompatActivity {

    ImageButton ibtnBenutzerErstellen;
    Button btnLoeschen,btnWechsel,btnBearbeiten;
    TextView textViewToolbar;
    LinearLayout ownLinearLayout;
    DBHelferlein hilfMirDaddyDB;

    static String aktiverNutzerUUA;
    int j = 0;
    int grandbudapesthotelrosa = Color.parseColor("#FA86C4");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_uebersicht);

        ibtnBenutzerErstellen = findViewById(R.id.imageButtonWarenkorb);
        ibtnBenutzerErstellen.setImageResource(R.drawable.plus);

        textViewToolbar = findViewById(R.id.TVToolbar);

        ownLinearLayout = findViewById(R.id.LinearLayoutDeletePage);

        hilfMirDaddyDB = new DBHelferlein(this);

        aktiverNutzerUUA  = NutzerErstellenActivity.nameTXT;

        ibtnBenutzerErstellen.setOnClickListener(v -> {
            Intent intentNutzerErstellen = new Intent(UserUebersichtActivity.this, NutzerErstellenActivity.class);
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
        Button button = new Button(UserUebersichtActivity.this);
        button.setText(username);
        button.setBackgroundColor(grandbudapesthotelrosa);

        button.setOnClickListener(v -> {
            aktiverNutzerUUA = username;
            onButtonShowPopupWindowClick(ownLinearLayout);
        });

        addView(button,400,400);
    }

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
    }

}
