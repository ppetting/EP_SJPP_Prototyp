package de.epprojekt.ep_sjpp_prototyp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.ImageButton;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;


public class PictureChangeActivity extends AppCompatActivity{

    ImageButton  ibtnhome, ibtnStillesWasserChanger, ibtnSprudelwasserChanger;
    DBHelferlein hilfMirDaddyDB;
    public static byte[] test;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_change);

        hilfMirDaddyDB = new DBHelferlein(this);

        ibtnSprudelwasserChanger = findViewById(R.id.imageButtonSprudelWasserChanger);
        ibtnStillesWasserChanger = findViewById(R.id.imageButtonStillesWasserChanger);

        ibtnSprudelwasserChanger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
        ibtnStillesWasserChanger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hilfMirDaddyDB.setDrawableFromGallery("roterApfel",test);
            }
        });

    }

    private void selectImage() {
        final CharSequence[] options = { "Foto aufnehmen", "Bild aus Gallerie auswählen","Abbrechen" };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Bild ändern");
        builder.setItems(options, (dialog, item) -> {
            if (options[item].equals("Foto aufnehmen"))
            {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File file = new File(Environment.getExternalStorageDirectory(), "temp.jpg");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(intent, 1);
            }
            else if (options[item].equals("Bild aus Gallerie auswählen"))
            {
                Intent intent = new   Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(intent, 2);
            }
            else if (options[item].equals("Abbrechen")) {
                dialog.dismiss();
            }
        });
        builder.show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                //Hier müsste man das Kamerabild verarbeiten
            } else if (requestCode == 2) {

                try {
                    InputStream inputStream = getContentResolver().openInputStream(data.getData());
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    bitmap = getResizedBitmap(bitmap,400);
                    //test = BitMapToString(bitmap);
                    test = getBytes(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    // convert from bitmap to byte array
    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }

    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float)width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

}

