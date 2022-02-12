package de.epprojekt.ep_sjpp_prototyp.Helferlein;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;

import de.epprojekt.ep_sjpp_prototyp.MainActivity;
import de.epprojekt.ep_sjpp_prototyp.Menuebereich.UserCreationActivity;
import de.epprojekt.ep_sjpp_prototyp.Menuebereich.UserOverviewActivity;
import de.epprojekt.ep_sjpp_prototyp.WarenkorbActivity;

public class AddAndSetHelferlein {

    public static void addViewIBTN (ImageButton imageButton, LinearLayout linearLayout){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(400,400);
        params.setMargins(0,10,0,10);
        imageButton.setLayoutParams(params);
        linearLayout.addView(imageButton);
    }

    public static ImageButton setPicture(Integer i, String j, Context context, DBHelferlein dbHelferlein){
        ImageButton imageButton = new ImageButton(context);
        imageButton.setImageResource(i);
        imageButton.setScaleType(ImageView.ScaleType.FIT_CENTER);

        imageButton.setOnClickListener(v -> {
            dbHelferlein.deleteIndividuallyfromWarenkorb(j, UserOverviewActivity.aktiverNutzerUOA);
            Intent refresh = new Intent(context,WarenkorbActivity.class);
            context.startActivity(refresh);
        });
         return imageButton;
    }

    public static void addViewBTN (Button button, LinearLayout linearLayout){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(400,400);
        params.setMargins(0,10,0,10);
        button.setLayoutParams(params);
        linearLayout.addView(button);
    }

    public static Button generateButtonsAndSetName(String username, Context context, Integer farbe, DBHelferlein dbHelferlein){
        Button button = new Button(context);
        button.setText(username);
        button.setBackgroundColor(farbe);

        button.setOnClickListener(v -> {
            UserOverviewActivity.aktiverNutzerUOA = username;
            menueauswahl(context,dbHelferlein);
        });

        return button;
    }

    private static void menueauswahl(Context context, DBHelferlein dbHelferlein) {
        final CharSequence[] options = {"Benutzer wechsel", "Benutzer bearbeiten", "Benutzer löschen", "Abbrechen"};
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Einstellungen für " + UserOverviewActivity.aktiverNutzerUOA);
        builder.setItems(options, (dialog, item) -> {
            if (options[item].equals("Benutzer wechsel")) {
                dialog.dismiss();
                Intent refresh = new Intent(context, MainActivity.class);
                context.startActivity(refresh);
            } else if (options[item].equals("Benutzer bearbeiten")) {
                //Status festlegen auf Benutzer bearbeiten
                UserOverviewActivity.anlegen_bearbeiten = "Benutzer aktualisieren";
                dialog.dismiss();
                Intent intentNutzerErstellen = new Intent(context, UserCreationActivity.class);
                context.startActivity(intentNutzerErstellen);

            } else if (options[item].equals("Benutzer löschen")) {
                dbHelferlein.deletefromUserdaten(UserOverviewActivity.aktiverNutzerUOA);
                dialog.dismiss();
                UserOverviewActivity.aktiverNutzerUOA = "";
                Intent refresh = new Intent(context, MainActivity.class);
                context.startActivity(refresh);
            } else if (options[item].equals("Abbrechen")) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

}
