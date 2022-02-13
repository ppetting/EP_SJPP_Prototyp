package de.epprojekt.ep_sjpp_prototyp.Menuebereich;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.ImageButton;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import de.epprojekt.ep_sjpp_prototyp.Helferlein.DBHelferlein;
import de.epprojekt.ep_sjpp_prototyp.R;


public class IbtnChangerActivity extends AppCompatActivity{

    ImageButton  ibtnMenue, ibtnWarenkorb;
    ImageButton ibtnRoterApfelChanger,ibtnGruenerApfelChanger,ibtnAepfelChanger,ibtnGurkeChanger,ibtnSechserEierChanger,ibtnZehnerEierChanger,ibtnEierChanger,ibtnHartkaeseChanger,ibtnStreichkaeseChanger,ibtnScheibenkaeseChanger,ibtnKaeseChanger,ibtnGemueseUndObstChanger,ibtnGetraenkeChanger,ibtnWeizenprodukteChanger,ibtnMilchprodukteChanger,ibtnFleischundWurstChanger;
    DBHelferlein hilfMirDaddyDB;
    public static byte[] byteArray = null;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ibtn_changer);

        hilfMirDaddyDB = new DBHelferlein(this);

        ibtnMenue = findViewById(R.id.imageButtonHome);
        ibtnWarenkorb = findViewById(R.id.imageButtonWarenkorb);

        ibtnRoterApfelChanger = findViewById(R.id.imageButtonRoterApfeleChanger);
        ibtnGruenerApfelChanger = findViewById(R.id.imageButtonGruenerApfelChanger);
        ibtnAepfelChanger = findViewById(R.id.imageButtonAepfelChanger);
        ibtnGurkeChanger = findViewById(R.id.imageButtonGurkeChanger);
        ibtnSechserEierChanger = findViewById(R.id.imageButtonSechserEierChanger);
        ibtnZehnerEierChanger = findViewById(R.id.imageButtonZehnerEierChanger);
        ibtnEierChanger = findViewById(R.id.imageButtonEierChanger);
        ibtnHartkaeseChanger = findViewById(R.id.imageButtonHartkaeseChanger);
        ibtnStreichkaeseChanger = findViewById(R.id.imageButtonStreichkaeseChanger);
        ibtnScheibenkaeseChanger = findViewById(R.id.imageButtonScheibenkaeseChanger);
        ibtnKaeseChanger = findViewById(R.id.imageButtonKaeseChanger);
        ibtnGemueseUndObstChanger = findViewById(R.id.imageButtonGemueseUndObstChanger);
        ibtnGetraenkeChanger = findViewById(R.id.imageButtonGetraenkeChanger);
        ibtnWeizenprodukteChanger = findViewById(R.id.imageButtonWeizenprodukteChanger);
        ibtnMilchprodukteChanger = findViewById(R.id.imageButtonMilchprodukteChanger);
        ibtnFleischundWurstChanger = findViewById(R.id.imageButtonFleischundWurstChanger);


        ibtnRoterApfelChanger.setOnClickListener(v -> selectImage("roterApfel"));
        ibtnGruenerApfelChanger.setOnClickListener(v -> selectImage("gruenerApfel"));


    }

    private void selectImage(String string) {
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
                agreement(string);
            }
            else if (options[item].equals("Bild aus Gallerie auswählen"))
            {
                Intent intent = new   Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(intent, 2);
                agreement(string);
            }
            else if (options[item].equals("Abbrechen")) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    private void agreement(String string) {
        final CharSequence[] options = { "Ja", "Nein" };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Neues Bild speichern?");
        builder.setItems(options, (dialog, item) -> {
            if (options[item].equals("Ja"))
            {
                hilfMirDaddyDB.setDrawableFromGallery(string, byteArray);
            }
            else if (options[item].equals("Nein")) {
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
                    byteArray = getBytes(bitmap);
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

    public static Bitmap getResizedBitmap(Bitmap image, int maxSize) {
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

