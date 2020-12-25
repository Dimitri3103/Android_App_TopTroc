package com.example.toptroc;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ObjectDepositActivity extends AppCompatActivity {

    private EditText nameObject,descriptionObjet;
    private Button btnAddObject,btnAddImageObject;
    private ImageView imageViewObject;

    String serverUploadPath = "http://192.168.1.4/toptroc/add_objects.php";

    final int REQUEST_CODE_GALLERY = 999;

    public static SQLiteHelper sqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_deposit);

        // Couleur de la barre de statut
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        init();

        sqLiteHelper = new SQLiteHelper(ObjectDepositActivity.this);

        btnAddImageObject.setOnClickListener(v -> ActivityCompat.requestPermissions(
                ObjectDepositActivity.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                REQUEST_CODE_GALLERY
        ));

        btnAddObject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                try{
                    sqLiteHelper.insertData(
                            nameObject.getText().toString().trim(),
                            descriptionObjet.getText().toString().trim(),
                            imageViewtoByte(imageViewObject)
                    );
                    Toast.makeText(getApplicationContext(),"Objet Ajouté",Toast.LENGTH_SHORT).show();
                    nameObject.setText("");
                    descriptionObjet.setText("");
                    imageViewObject.setImageResource(R.drawable.ic_launcher_background);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }



    private byte[] imageViewtoByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_GALLERY){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_CODE_GALLERY);
            }
            else {
                Toast.makeText(getApplicationContext(),"Vous n'avez pas la permission d'accéder aux images", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
            Uri uri= data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageViewObject.setImageBitmap(bitmap);
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void init() {
        nameObject = findViewById(R.id.nameObject);
        descriptionObjet = findViewById(R.id.descriptionObjet);
        btnAddImageObject = findViewById(R.id.btnAddImageObject);
        btnAddObject = findViewById(R.id.btnAddObject);
        imageViewObject = findViewById(R.id.imageViewObject);
    }

    public void goToHomePage(View view) {
        Intent intent = new Intent(ObjectDepositActivity.this,HomeActivity.class);
        startActivity(intent);
        finish();
    }
}