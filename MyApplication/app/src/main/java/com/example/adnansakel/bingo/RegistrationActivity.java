package com.example.adnansakel.bingo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.adnansakel.bingo.Model.Player;
import com.example.adnansakel.bingo.Util.AppConstants;

import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Adnan Sakel on 11/26/2016.
 */
public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnSignIn;
    Button btnUploadPhoto;
    EditText editTextName;
    EditText editTextAge;
    EditText editTextGender;
    EditText editTextEmail;
    BingoServerCalls bingoServerCalls;
    ImageView imgProfilePhoto;

    RadioButton rdbtnMale;
    RadioButton rdbtnFemale;

    private static final int REQUEST_CAMERA = 0;
    private static final int SELECT_FILE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getSupportActionBar().setTitle("Bingo Registration");
        bingoServerCalls = new BingoServerCalls(((MyApplication)getApplication()).getBingoGameModel(),this);
        //new HomeView(findViewById(R.id.rl_home_view),((MyApplication)getApplication()).getBingoGameModel());
        initialize();
        getUserInfo();
    }

    private void initialize(){
        editTextName = (EditText)findViewById(R.id.editTextName);
        editTextAge = (EditText)findViewById(R.id.editTextAge);
        //editTextGender = (EditText)findViewById(R.id.editTextGender);
        editTextEmail = (EditText)findViewById(R.id.editTextEmail);
        btnSignIn = (Button)findViewById(R.id.btnSignIn);
        btnUploadPhoto = (Button)findViewById(R.id.btnPhotoUpload);
        rdbtnFemale = (RadioButton)findViewById(R.id.radioButton_Female);
        rdbtnMale = (RadioButton)findViewById(R.id.radioButton_Male);
        btnUploadPhoto.setVisibility(View.GONE);

        imgProfilePhoto = (ImageView)findViewById(R.id.imgProfilePhoto);
        imgProfilePhoto.setOnClickListener(this);
        btnSignIn.setOnClickListener(this);
    }

    private void getUserInfo()
    {
        SharedPreferences sharedPreferences = getSharedPreferences(AppConstants.PLAYER_INFO, Context.MODE_PRIVATE);
        String userID = sharedPreferences.getString(AppConstants.PLAYER_ID,"");
        String name = sharedPreferences.getString(AppConstants.NAME,"");
        String gender = sharedPreferences.getString(AppConstants.GENDER,"");
        String age = sharedPreferences.getString(AppConstants.AGE,"");
        String email = sharedPreferences.getString(AppConstants.EMAIL,"");


        if(userID.length()>0){
            ((MyApplication)getApplication()).getBingoGameModel().getMyPlayer().setPlayerID(userID);
        }
        if(name.length()>0){
            ((MyApplication)getApplication()).getBingoGameModel().getMyPlayer().setName(name);
        }
        if(age.length()>0){
            ((MyApplication)getApplication()).getBingoGameModel().getMyPlayer().setAge(age);
        }
        if(gender.length()>0){
            ((MyApplication)getApplication()).getBingoGameModel().getMyPlayer().setGender(gender);
        }
        if(email.length()>0){
            ((MyApplication)getApplication()).getBingoGameModel().getMyPlayer().setEmail(email);
        }

        if(((MyApplication)getApplication()).getBingoGameModel().getMyPlayer().getPlayerID().length()>0){
            startActivity(new Intent(RegistrationActivity.this, HomeActivity.class));
            this.finish();
        }

    }
    @Override
    public void onClick(View view) {
        //startActivity(new Intent(RegistrationActivity.this, HomeActivity.class));
        if(view == btnSignIn){
            if(editTextName.getText().length()>0){
                //making server call to register user
                Player player = new Player();
                if(editTextName.getText().toString()!=null){player.setName(editTextName.getText().toString());}
                else {player.setName("");}
                if(editTextAge.getText().toString()!=null){player.setAge(editTextAge.getText().toString());}
                else{player.setAge("");}
                //if(editTextGender.getText().toString()!=null){player.setGender(editTextGender.getText().toString());}
                //else{player.setGender("");}
                if(editTextEmail.getText().toString()!=null){player.setEmail(editTextEmail.getText().toString());}
                else{player.setEmail("");}
                if(((MyApplication)getApplication()).getBingoGameModel().getMyPlayer().getBmpProfilePhoto()!=null){
                    player.setBmpProfilePhoto(
                            ((MyApplication)getApplication()).getBingoGameModel().getMyPlayer().getBmpProfilePhoto());
                }

                if(rdbtnMale.isChecked()){
                    player.setGender(AppConstants.MALE);
                    Toast.makeText(this,"Male",Toast.LENGTH_SHORT).show();
                }
                else if(rdbtnFemale.isChecked()){
                    player.setGender(AppConstants.FEMALE);
                    Toast.makeText(this,"Female",Toast.LENGTH_SHORT).show();
                }
                else{
                    player.setGender("");
                }

                try {
                    ((MyApplication)getApplication()).getBingoGameModel().setMyPlayer(player);
                    bingoServerCalls.registerUser(((MyApplication)getApplication()).getBingoGameModel().getMyPlayer());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            else{
                Toast.makeText(this,"Name cannot be empty",Toast.LENGTH_SHORT).show();
            }

        }

        if(view == imgProfilePhoto){
            selectImage();
        }

    }

    private void selectImage() {
        final CharSequence[] items = { "Take Photo", "Choose from Library", "Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Photo!");
        builder.setCancelable(false);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_CAMERA);

                } else if (items[item].equals("Choose from Library")) {
                    Intent intent = new Intent(
                            Intent.ACTION_PICK,
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(
                            Intent.createChooser(intent, "Select File"),
                            SELECT_FILE);

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                    //Intent intent = new Intent();
                    //setResult(RESULT_CANCELED, intent);
                    //finish();
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CAMERA) {
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");

                imgProfilePhoto.setImageBitmap(thumbnail);
                ((MyApplication)getApplication()).getBingoGameModel().getMyPlayer().setBmpProfilePhoto(thumbnail);
                /*
                String encodeImage = Base64.encodeToString(bytes.toByteArray(), Base64.DEFAULT);

                File destination = new File(Environment.getExternalStorageDirectory(),
                        System.currentTimeMillis() + ".jpg");
                FileOutputStream fo;
                try {
                    destination.createNewFile();
                    fo = new FileOutputStream(destination);
                    fo.write(bytes.toByteArray());
                    fo.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
                //Intent intent = new Intent();
                //intent.putExtra("encoded_image",encodeImage);
                //setResult(RESULT_OK, intent);
                //finish();

            } else if (requestCode == SELECT_FILE) {
                System.out.println("Photo selected");
                Uri selectedImageUri = data.getData();
                String[] projection = {MediaStore.MediaColumns.DATA};
                CursorLoader cursorLoader = new CursorLoader(this, selectedImageUri, projection, null, null,
                        null);
                Cursor cursor = cursorLoader.loadInBackground();
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
                cursor.moveToFirst();
                String selectedImagePath = cursor.getString(column_index);
                Bitmap bm;
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(selectedImagePath, options);
                final int REQUIRED_SIZE = 200;
                int scale = 1;
                while (options.outWidth / scale / 2 >= REQUIRED_SIZE
                        && options.outHeight / scale / 2 >= REQUIRED_SIZE)
                    scale *= 2;
                options.inSampleSize = scale;
                options.inJustDecodeBounds = false;
                bm = BitmapFactory.decodeFile(selectedImagePath, options);
                //ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                //bm.compress(Bitmap.CompressFormat.JPEG, 10, bytes);
                imgProfilePhoto.setImageBitmap(bm);
                ((MyApplication)getApplication()).getBingoGameModel().getMyPlayer().setBmpProfilePhoto(bm);
                /*String encodeImage = Base64.encodeToString(bytes.toByteArray(), Base64.DEFAULT);
                Intent intent = new Intent();
                intent.putExtra("encoded_image",encodeImage);
                setResult(RESULT_OK, intent);
                finish();*/

            }
            else{
                System.out.println("Photo not selected "+requestCode);
            }
        }
    }
}
