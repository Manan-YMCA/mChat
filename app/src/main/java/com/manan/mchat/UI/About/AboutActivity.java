package com.manan.mchat.UI.About;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.manan.mchat.R;
import com.manan.mchat.SQLDatabaseHandler.DatabaseHelper;
import com.manan.mchat.SQLDatabaseHandler.DbHelper;
import com.manan.mchat.Utilities.BitmapUtility;
import com.manan.mchat.Utilities.GetRoundedImage;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class AboutActivity extends AppCompatActivity {
    TextView userName,cancelButton;
    EditText firstName,lastName;
    ImageView profilePicture,editButton;
    DatabaseHelper databaseHelper;
    DbHelper dbHelper;
    Button saveButton;
    GetRoundedImage getRoundedImage;
    BitmapUtility bitmapUtility;
    final int REQUEST_CODE_GALLERY = 999;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        userName = findViewById(R.id.name);
        profilePicture = findViewById(R.id.profile_picture);
        editButton = findViewById(R.id.edit_name);

        databaseHelper = new DatabaseHelper(AboutActivity.this);
        dbHelper = new DbHelper(AboutActivity.this);
        getRoundedImage = new GetRoundedImage();
        bitmapUtility = new BitmapUtility();
      //  updateImage();
        updateName();

        profilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(
                        AboutActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );

            }
        });
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(AboutActivity.this);
                LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View dialogView = inflater.inflate(R.layout.alert_label_editor, null);
                dialogBuilder.setView(dialogView);
                final AlertDialog alertDialog = dialogBuilder.create();
                alertDialog.show();
                firstName = dialogView.findViewById(R.id.et_first_name);
                lastName = dialogView.findViewById(R.id.et_last_name);
                saveButton = dialogView.findViewById(R.id.save_btn);
                cancelButton = dialogView.findViewById(R.id.cancel_btn);
                saveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = firstName.getText().toString() + " " + lastName.getText().toString();
                        String uname = dbHelper.retrieveName();
                        if(uname == null) {
                            dbHelper.addName(name);
                        }
                        else {
                            dbHelper.updateName(name);
                        }
                        updateName();
                        alertDialog.dismiss();
                    }
                });
                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });

            }
        });
    }

    private void updateName() {
    String name = dbHelper.retrieveName();
    if(name!=null) {
        Log.d("prerna","hii");
        userName.setText(name);
    }
    }
    private void updateImage() {
        byte[] bytes = databaseHelper.retrieveImage();
        Bitmap bitmap = bitmapUtility.getImage(bytes);
        profilePicture.setImageBitmap(bitmap);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == REQUEST_CODE_GALLERY){
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }
            else {
                Toast.makeText(getApplicationContext(), "You don't have permission to access file location!", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                Bitmap roundBitmap = getRoundedImage.getRoundedShape(bitmap);
                profilePicture.setImageBitmap(roundBitmap);
                    byte[] imageByte = bitmapUtility.getBytes(roundBitmap);
                    databaseHelper.addImage(imageByte);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}