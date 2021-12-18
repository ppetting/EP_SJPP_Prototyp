package de.epprojekt.ep_sjpp_prototyp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class WarenkorbActivity extends AppCompatActivity {

    ImageButton ibtnLoeschen, ibtnrefresh;
    DBHelferlein hilfMirDaddyDB;
    LinearLayout ownLinearLayout;
    int i = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warenkorb);

        ibtnLoeschen = findViewById(R.id.imageButtonLoeschen);
        ibtnLoeschen.setImageResource(R.drawable.delete);
        ibtnrefresh = findViewById(R.id.imageButtonRefresh);
        ibtnrefresh.setImageResource(R.drawable.refresh);

        ownLinearLayout = findViewById(R.id.LinearLayoutWarenkorb);

        hilfMirDaddyDB = new DBHelferlein(this);

        ibtnLoeschen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hilfMirDaddyDB.deletefromWarenkorb();
            }
        });

        ibtnrefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<Integer> arrayListOfDrawablesID = hilfMirDaddyDB.createArrayListOfWarenkorb();

                while( i < arrayListOfDrawablesID.size()){
                    setPicture(arrayListOfDrawablesID.get(i));
                    i++;
                }

            }
        });


    }

    public void addView (ImageView imageView, int width, int height){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width,height);
        params.setMargins(0,10,0,10);
        imageView.setLayoutParams(params);
        ownLinearLayout.addView(imageView);
    }

    @SuppressLint("ResourceType")
    public void setPicture(int i){
        ImageView imageView = new ImageView(WarenkorbActivity.this);
        imageView.setImageResource(i);
        addView(imageView,200,200);
    }

}