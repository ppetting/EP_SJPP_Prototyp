package de.epprojekt.ep_sjpp_prototyp.Helferlein;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;

import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;


import java.io.SequenceInputStream;

import de.epprojekt.ep_sjpp_prototyp.Einkauf.AepfelActivity;
import de.epprojekt.ep_sjpp_prototyp.MainActivity;
import de.epprojekt.ep_sjpp_prototyp.Menuebereich.UserCreationActivity;
import de.epprojekt.ep_sjpp_prototyp.Menuebereich.UserOverviewActivity;
import de.epprojekt.ep_sjpp_prototyp.R;
import de.epprojekt.ep_sjpp_prototyp.WarenkorbActivity;

public class AddAndSetHelferlein {

    final static String KEY_AKTIVERNUTZER = "aktiver_nutzer";

    public static void addViewIBTN (ImageButton imageButton, LinearLayout linearLayout){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(400,400);
        params.setMargins(0,10,0,10);
        imageButton.setLayoutParams(params);
        linearLayout.addView(imageButton);
    }

    public static ImageButton setPicture(String itemname, String itemnamekey, Context context, DBHelferlein dbHelferlein){
        ImageButton imageButton = new ImageButton(context);
        imageButton.setImageBitmap(BitmapFactory.decodeByteArray(dbHelferlein.getDrawableFromTable(itemname),0,dbHelferlein.getDrawableFromTable(itemname).length));
        imageButton.setScaleType(ImageView.ScaleType.FIT_CENTER);

        imageButton.setOnClickListener(v -> {
            dbHelferlein.deleteIndividuallyfromWarenkorb(itemnamekey, PreferenceHelferlein.loadUserFromPref(context, KEY_AKTIVERNUTZER));
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
           PreferenceHelferlein.saveUserInPref(context,username, KEY_AKTIVERNUTZER);
            menueauswahl(context,dbHelferlein);
        });

        return button;
    }

    private static void menueauswahl(Context context, DBHelferlein dbHelferlein) {
        final CharSequence[] options = {"Benutzer wechsel", "Benutzer bearbeiten", "Benutzer löschen", "Abbrechen"};
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Einstellungen für " + PreferenceHelferlein.loadUserFromPref(context, KEY_AKTIVERNUTZER));
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
                wirklichLoeschen(context,dbHelferlein);
                dialog.dismiss();

            } else if (options[item].equals("Abbrechen")) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    private static void wirklichLoeschen(Context context, DBHelferlein dbHelferlein) {
        final CharSequence[] options = {"Ja", "Nein"};
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Wollen Sie den Nutzer wirklich löschen?");
        builder.setItems(options, (dialog, item) -> {
            if (options[item].equals("Ja")) {
                dialog.dismiss();
                dbHelferlein.deletefromUserdaten(PreferenceHelferlein.loadUserFromPref(context, KEY_AKTIVERNUTZER));
                dialog.dismiss();
                PreferenceHelferlein.saveUserInPref(context,"", KEY_AKTIVERNUTZER);
                Intent refresh = new Intent(context, MainActivity.class);
                context.startActivity(refresh);
            } else if (options[item].equals("Nein")) {
                dialog.dismiss();
                Intent intent = new Intent(context,UserOverviewActivity.class);
                context.startActivity(intent);
            }
        });
        builder.show();
    }




   public static void playAudioFlagVoll(Context context) {
        MediaPlayer mediaPlayernichtsmehr = MediaPlayer.create(context, R.raw.nichtsmehr);
        mediaPlayernichtsmehr.start();
       Toast.makeText(context, "Es dürfen keine Produkte mehr hinzugefügt werden", Toast.LENGTH_SHORT).show();
    }


}
