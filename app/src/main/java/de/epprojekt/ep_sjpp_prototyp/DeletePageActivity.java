package de.epprojekt.ep_sjpp_prototyp;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;


import java.util.ArrayList;

public class DeletePageActivity extends AppCompatActivity {

    LinearLayout ownLinearLayout;
    DBHelferlein hilfMirDaddyDB;
    int i = 0;
    int grandbudapesthotelrosa = Color.parseColor("#FA86C4");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_page);

        hilfMirDaddyDB = new DBHelferlein(this);
        ownLinearLayout = findViewById(R.id.LinearLayoutDeletePage);

        ArrayList<String> arrayListOfUsers = hilfMirDaddyDB.createArrayListOfUserdaten();

        while (i < arrayListOfUsers.size()) {
            setPicture(arrayListOfUsers.get(i));
            i++;
        }

    }


    public void addView (Button button, int width, int height){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width,height);
        params.setMargins(0,10,0,10);
        button.setLayoutParams(params);
        ownLinearLayout.addView(button);
    }

    @SuppressLint("ResourceType")
    public void setPicture (String i){
        Button button = new Button(DeletePageActivity.this);
        button.setText(i);
        button.setOnClickListener(v -> {
           hilfMirDaddyDB.deletefromUserdaten(i);
           Intent refresh = new Intent(DeletePageActivity.this,DeletePageActivity.class);
           startActivity(refresh);
           finish();
        });
        button.setBackgroundColor(grandbudapesthotelrosa);
        addView(button,400,400);
    }

}