package com.example.myfirstapp;

public class UserModel {
    String _id;
    String _userID;
    String _status;
    String _name;
    String _email;
    String _photo;
    int _balance;
    int _point;

    public UserModel(String id, String userID, String status, String name, String email, String photo, int balance, int point){
        _id = id;
        _userID = userID;
        _status = status;
        _name = name;
        _email = email;
        _photo = photo;
        _balance = balance;
        _point = point;
    }

    public String getID (){
        return _id;
    }
    public String getUserID (){
        return _userID;
    }
    public String getStatus (){
        return _status;
    }
    public String getName (){
        return _name;
    }
    public String getEmail (){
        return _email;
    }
    public String getPhoto (){
        return _photo;
    }
    public int getBalance (){
        return _balance;
    }
    public int getPoint (){
        return _point;
    }
}