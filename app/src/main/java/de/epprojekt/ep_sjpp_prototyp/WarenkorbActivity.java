package de.epprojekt.ep_sjpp_prototyp;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import java.util.ArrayList;

public class WarenkorbActivity extends AppCompatActivity {

    ImageButton ibtnLoeschen, ibtnHome;
    DBHelferlein hilfMirDaddyDB;
    LinearLayout ownLinearLayout;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warenkorb);

        ibtnHome = findViewById(R.id.imageButtonHome);
        ibtnHome.setImageResource(R.drawable.home);

        ibtnLoeschen = findViewById(R.id.imageButtonWarenkorb);
        ibtnLoeschen.setImageResource(R.drawable.delete);

        ownLinearLayout = findViewById(R.id.LinearLayoutWarenkorb);

        hilfMirDaddyDB = new DBHelferlein(this);

        ArrayList<Integer> arrayListOfDrawablesID = hilfMirDaddyDB.createArrayListOfWarenkorb();

        while(i < arrayListOfDrawablesID.size()){
            setPicture(arrayListOfDrawablesID.get(i));
            i++;
        }

        ibtnLoeschen.setOnClickListener(v -> {
            hilfMirDaddyDB.deleteCompletefromWarenkorb();
            Intent refresh = new Intent(WarenkorbActivity.this,WarenkorbActivity.class);
            startActivity(refresh);
            finish();
        });

        ibtnHome.setOnClickListener(v -> {
            Intent intentHome = new Intent(WarenkorbActivity.this,MainActivity.class);
            startActivity(intentHome);
        });

    }

    public void addView (ImageButton imageButton, int width, int height){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width,height);
        params.setMargins(0,10,0,10);
        imageButton.setLayoutParams(params);
        ownLinearLayout.addView(imageButton);
    }

    @SuppressLint("ResourceType")
    public void setPicture(int i){
        ImageButton imageButton = new ImageButton(WarenkorbActivity.this);
        imageButton.setImageResource(i);
        imageButton.setScaleType(ImageView.ScaleType.FIT_CENTER);
        addView(imageButton,400,400);
    }

}