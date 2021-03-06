package com.example.adnansakel.bingo.Model;

import android.graphics.Bitmap;

/**
 * Created by Adnan Sakel on 12/28/2016.
 */
public class Player {
    private String PlayerID="";
    private String Name="";
    private String Age="";
    private String Email="";
    private String Gender="";
    private Bitmap bmpProfilePhoto = null;


    public String getPlayerID() {
        return PlayerID;
    }

    public void setPlayerID(String playerID) {
        PlayerID = playerID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public Bitmap getBmpProfilePhoto() {
        return bmpProfilePhoto;
    }

    public void setBmpProfilePhoto(Bitmap bmpProfilePhoto) {
        this.bmpProfilePhoto = bmpProfilePhoto;
    }
}
