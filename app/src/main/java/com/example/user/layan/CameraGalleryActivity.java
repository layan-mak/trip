package com.example.user.layan;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CameraGalleryActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int CAMERA_REQUEST = 0;
    private static final int SELECT_IMAGE = 1;
    Button btTakephoto, btGallery;
    ImageView imageViewCamera;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_gallery);

        btTakephoto = (Button) findViewById(R.id.btTakePhoto);
        btGallery = (Button) findViewById(R.id.btGallery);

        imageViewCamera = (ImageView) findViewById(R.id.imageViewCamera);

        btTakephoto.setOnClickListener(this);
        btGallery.setOnClickListener(this);

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//      if the request was from the camera and the result was OK meaning the camera worked
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
//          the image captured is saved in the data object
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            String imageTrip = BitMapToString(photo);
//            imageViewCamera.setImageBitmap(photo);
//            saveImage(bitmap);
//            String imagePath= saveImage(bitmap);

//            SharedPreferences pref= getSharedPreferences("mypref", MODE_PRIVATE);
//            SharedPreferences.Editor editor= pref.edit();
//            editor.putString("image", imagePath);
//            editor.commit();

            Intent tryToSaveImage= new Intent(this, PlanTripActivity.class);
            tryToSaveImage.putExtra("imageTrip",imageTrip);
            startActivity(tryToSaveImage);
        }
//         else if (requestCode == SELECT_IMAGE && resultCode == Activity.RESULT_OK) {
//            Uri targetUri = data.getData();
//            try {
//                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
//                imageViewCamera.setImageBitmap(bitmap);
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
    }

    public String BitMapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,baos);
        byte[] b= baos.toByteArray();
        String temp= Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

    @Override
    public void onClick(View v) {
        if (v == btTakephoto) {
            Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(i, CAMERA_REQUEST);
        } else if(v == btGallery) {
            Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(i, SELECT_IMAGE);
        }
    }


//    public String saveImage(Bitmap bitmap) {
//        File root= Environment.getExternalStorageDirectory();
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//        String filePath= root.getAbsolutePath()+"/DCIM/Camera/IMG_"+timeStamp+".jpg";
//        File file = new File(filePath);
//
//        try {
//            //if gallery notfull create a file and save images
//            file.createNewFile(); //createnew file to save the image
//            FileOutputStream ostream= new FileOutputStream(file); //saves root in this file
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, ostream);
//            ostream.close();
//        }
//
//        catch (Exception e) {
//            e.printStackTrace();
//            Toast.makeText(this, "Failed to save image", Toast.LENGTH_SHORT).show();
//        }
//        return filePath;
//    }
}