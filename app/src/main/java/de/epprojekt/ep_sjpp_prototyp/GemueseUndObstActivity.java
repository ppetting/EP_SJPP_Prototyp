package de.epprojekt.ep_sjpp_prototyp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.net.URI;

public class GemueseUndObstActivity extends AppCompatActivity {

    ImageButton ibtnApfel, ibtnGurke, ibtnWarenkorb;
    DBHelferlein hilfMirDaddyDB;
    AnimationsHelferlein hilfMirMommyAnimation;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gemuese_und_obst);

        ibtnApfel = findViewById(R.id.imageButtonApfel);
        ibtnGurke = findViewById(R.id.imageButtonGurke);
        imageView = findViewById(R.id.IVTEST);


        ibtnApfel.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            activityResultLauncher.launch(intent);
        });
    }

    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult activityResult) {
            int result = activityResult.getResultCode();
            Intent data = activityResult.getData();

            if (result == RESULT_OK) {
                Uri selectedImageUri = data.getData();
                ibtnGurke.setImageURI(selectedImageUri);
            }
        }

    });
}










