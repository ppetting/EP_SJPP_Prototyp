package de.epprojekt.ep_sjpp_prototyp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ImageButton;

public class PictureChangeActivity extends AppCompatActivity {

    ImageButton ibtnSprudelwasserChanger, ibtnSprudelwasser, ibtnhome;
    static Uri mImageUri;
    private static final int PICK_IMAGE_REQUEST = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_change);


        ibtnSprudelwasserChanger = findViewById(R.id.imageButtonSprudelWasserChanger);

        ibtnSprudelwasser = findViewById(R.id.imageButtonSprudel);
        ibtnhome = findViewById(R.id.imageButtonHome);
        ibtnhome.setImageResource(R.drawable.home);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String mImageUri = preferences.getString("image", null);


        if (mImageUri != null) {
            ibtnSprudelwasserChanger.setImageURI(Uri.parse(mImageUri));
        } else {
            ibtnSprudelwasserChanger.setImageResource(R.drawable.sprudelwasser);
        }

        ibtnSprudelwasserChanger.setOnClickListener(v ->{
            imageSelect();
            Intent intent = new Intent(this,WasserActivity.class);
            intent.putExtra("keyPoint",mImageUri);

        });


        ibtnhome.setOnClickListener(v -> {
            Intent intent = new Intent(PictureChangeActivity.this, MainActivity.class);
            startActivity(intent);
        });

    }


  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
      // Check which request we're responding to
      super.onActivityResult(requestCode, resultCode, data);
      if (requestCode == PICK_IMAGE_REQUEST) {
          // Make sure the request was successful
          if (resultCode == RESULT_OK) {
              // The user picked a image.
              // The Intent's data Uri identifies which item was selected.
              if (data != null) {

                  // This is the key line item, URI specifies the name of the data
                  mImageUri = data.getData();

                  // Saves image URI as string to Default Shared Preferences
                  SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
                  SharedPreferences.Editor editor = preferences.edit();
                  editor.putString("image", String.valueOf(mImageUri));
                  editor.apply();

                  // Sets the ImageView with the Image URI
                  ibtnSprudelwasserChanger.setImageURI(mImageUri);
                  ibtnSprudelwasserChanger.invalidate();
              }
          }
      }
  }

    public void imageSelect() {
        Intent intent;
        intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }



}