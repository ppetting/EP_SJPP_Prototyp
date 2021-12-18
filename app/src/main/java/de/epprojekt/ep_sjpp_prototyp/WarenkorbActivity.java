package de.epprojekt.ep_sjpp_prototyp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class WarenkorbActivity extends AppCompatActivity {

    ImageButton ibtnLoeschen, ibtnRefresh;
    DBHelferlein hilfMirDaddyDB;
    LinearLayout ownLinearLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warenkorb);

        ibtnLoeschen = findViewById(R.id.imageButtonLoeschen);
        ibtnRefresh = findViewById(R.id.imageButtonRefresh);

        ownLinearLayout = findViewById(R.id.LinearLayoutWarenkorb);

        hilfMirDaddyDB = new DBHelferlein(this);

        ibtnLoeschen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hilfMirDaddyDB.deletefromWarenkorb();
            }
        });

        ibtnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView imageView = new ImageView(WarenkorbActivity.this);
                imageView.setImageResource(R.drawable.apfel);
                addView(imageView,200,200);
            }
        });

    }

    public void addView (ImageView imageView, int width, int height){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width,height);
        params.setMargins(0,10,0,10);
        imageView.setLayoutParams(params);
        ownLinearLayout.addView(imageView);
    }

}