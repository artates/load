package com.example.nicolasartates.load;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    public static final int IMAGE_GALLERY_REQUEST = 20;
    Button button;

    ImageView imgPicture;
    private static int RESULT_LOAD_IMAGE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button)findViewById(R.id.button);

        imgPicture = (ImageView)findViewById(R.id.imgPicture);




    }

    public void onButtonClicked(View v)
    {
        Intent photopickerintent = new Intent(Intent.ACTION_PICK);

        File  pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        String pictureDirectoryPath =pictureDirectory.getPath();

        Uri data = Uri.parse(pictureDirectoryPath);

        photopickerintent.setDataAndType(data, "image/*");

        startActivityForResult(photopickerintent, IMAGE_GALLERY_REQUEST);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

      if (resultCode == RESULT_OK)
      {
          if(requestCode == IMAGE_GALLERY_REQUEST)
          {
              Uri imageUri = data.getData();

              InputStream inputStream;

              //we are geting an imput based on URI

              try
              {
                  inputStream =getContentResolver().openInputStream(imageUri);
                  Bitmap image = BitmapFactory.decodeStream(inputStream);

                  imgPicture.setImageBitmap(image);
              }

              catch (FileNotFoundException e)
              {
                  e.printStackTrace();
                  Toast.makeText(this, "RIP", Toast.LENGTH_LONG).show();
              }
          }
      }

    }
}
