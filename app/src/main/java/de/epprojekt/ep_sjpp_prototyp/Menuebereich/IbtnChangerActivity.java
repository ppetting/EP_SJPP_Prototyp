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
import android.view.View;
import android.widget.ImageButton;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import de.epprojekt.ep_sjpp_prototyp.Helferlein.DBHelferlein;
import de.epprojekt.ep_sjpp_prototyp.MainActivity;
import de.epprojekt.ep_sjpp_prototyp.R;


public class IbtnChangerActivity extends AppCompatActivity{

    ImageButton  ibtnMenue, ibtnWarenkorb;
    ImageButton ibtnRoterApfelChanger,ibtnGruenerApfelChanger,ibtnGurkeChanger,ibtnSechserEierChanger,ibtnZehnerEierChanger,ibtnHartkaeseChanger,ibtnStreichkaeseChanger,ibtnScheibenkaeseChanger,ibtnKaeseChanger;
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
        ibtnWarenkorb.setImageResource(R.drawable.mann);

        ibtnRoterApfelChanger = findViewById(R.id.imageButtonRoterApfeleChanger);
        ibtnGruenerApfelChanger = findViewById(R.id.imageButtonGruenerApfelChanger);
        ibtnGurkeChanger = findViewById(R.id.imageButtonGurkeChanger);
        ibtnSechserEierChanger = findViewById(R.id.imageButtonSechserEierChanger);
        ibtnZehnerEierChanger = findViewById(R.id.imageButtonZehnerEierChanger);
        ibtnHartkaeseChanger = findViewById(R.id.imageButtonHartkaeseChanger);
        ibtnStreichkaeseChanger = findViewById(R.id.imageButtonStreichkaeseChanger);
        ibtnScheibenkaeseChanger = findViewById(R.id.imageButtonScheibenkaeseChanger);

        ibtnRoterApfelChanger.setOnClickListener(v -> selectImage("roterApfel"));
        ibtnGruenerApfelChanger.setOnClickListener(v -> selectImage("gruenerApfel"));
        ibtnGurkeChanger.setOnClickListener(v -> selectImage("Salatgurke"));
        ibtnSechserEierChanger.setOnClickListener(v -> selectImage("sechserPackungEier"));
        ibtnZehnerEierChanger.setOnClickListener(v -> selectImage("zehnerPackungEier"));
        ibtnHartkaeseChanger.setOnClickListener(v -> selectImage("Hartkaese"));
        ibtnStreichkaeseChanger.setOnClickListener(v -> selectImage("Streichkaese"));
        ibtnScheibenkaeseChanger.setOnClickListener(v -> selectImage("Kaeseaufschnitt"));

        ibtnWarenkorb.setOnClickListener(v -> {
            Intent intentUOA = new Intent(this, UserOverviewActivity.class);
            startActivity(intentUOA);
        });

        ibtnMenue.setOnClickListener(v -> {
            Intent intentHome = new Intent(this, MainActivity.class);
            startActivity(intentHome);
        });

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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                //Hier müsste man das Kamerabild verarbeiten
            } else if (requestCode == 2) {
                try {
                    InputStream inputStream = getContentResolver().openInputStream(data.getData());
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    bitmap = getResizedBitmap(bitmap,400);
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
}

