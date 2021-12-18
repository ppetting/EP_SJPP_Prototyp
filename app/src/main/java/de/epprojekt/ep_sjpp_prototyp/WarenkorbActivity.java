package de.epprojekt.ep_sjpp_prototyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class WarenkorbActivity extends AppCompatActivity {

    ImageButton ibtnLoeschen, ibtnrefresh;
    DBHelferlein hilfMirDaddyDB;
    LinearLayout ownLinearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warenkorb);

        ibtnLoeschen = findViewById(R.id.imageButtonLoeschen);
        ibtnrefresh = findViewById(R.id.imageButtonRefresh);

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
                setPicture();
            }
        });


    }

    public void addView (ImageView imageView, int width, int height){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width,height);
        params.setMargins(0,10,0,10);
        imageView.setLayoutParams(params);
        ownLinearLayout.addView(imageView);
    }

    public void setPicture(){
        ImageView imageView = new ImageView(WarenkorbActivity.this);
        imageView.setImageResource(R.drawable.apfel);
        addView(imageView,200,200);
    }

}