package de.epprojekt.ep_sjpp_prototyp;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import java.util.ArrayList;

public class UserUebersichtActivity extends AppCompatActivity {

    ImageButton ibtnBenutzerErstellen;
    LinearLayout ownLinearLayout;
    DBHelferlein hilfMirDaddyDB;
    Button btnLoeschen, btnBearbeiten, btnWechseln;
    String aktiverNutzer;
    int j = 0;
    int grandbudapesthotelrosa = Color.parseColor("#FA86C4");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_uebersicht);

        btnLoeschen = findViewById(R.id.btnBenutzerLoeschen);
        btnWechseln = findViewById(R.id.btnBenutzerWechseln);
        btnBearbeiten = findViewById(R.id.btnBenutzerBearbeiten);

        ibtnBenutzerErstellen = findViewById(R.id.imageButtonWarenkorb);
        ibtnBenutzerErstellen.setImageResource(R.drawable.plus);

        hilfMirDaddyDB = new DBHelferlein(this);
        ownLinearLayout = findViewById(R.id.LinearLayoutDeletePage);

        ArrayList<String> arrayListOfUsers = hilfMirDaddyDB.createArrayListOfUserdaten();

        while (j < arrayListOfUsers.size()) {
            generateButtonsAndSetName(arrayListOfUsers.get(j));
            j++;
        }

        ibtnBenutzerErstellen.setOnClickListener(v -> {
            Intent intentNutzerErstellen = new Intent(UserUebersichtActivity.this, NutzerErstellenActivity.class);
            startActivity(intentNutzerErstellen);
        });

    }

    public void addView (Button button, int width, int height){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width,height);
        params.setMargins(0,10,0,10);
        button.setLayoutParams(params);
        ownLinearLayout.addView(button);
    }

    @SuppressLint("ResourceType")
    public void generateButtonsAndSetName(String i){
        Button button = new Button(UserUebersichtActivity.this);
        button.setText(i);
        button.setBackgroundColor(grandbudapesthotelrosa);
        button.setOnClickListener(v -> {
            aktiverNutzer = i;
            onButtonShowPopupWindowClick(ownLinearLayout);
        });
        addView(button,400,400);
    }

    public void onButtonShowPopupWindowClick(View view) {
        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_delte_change_edit, null);

        // create the popup window
        final PopupWindow popupWindow = new PopupWindow(popupView, 800, 800, true);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        // dismiss the popup window when touched
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }
}
