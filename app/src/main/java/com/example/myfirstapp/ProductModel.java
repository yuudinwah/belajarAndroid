package com.example.myfirstapp;

public class ProductModel {
    String _id;
    String _name;
    String _category;
    int _price;

    public ProductModel(String id, String name, String category, int price){
        _id = id;
        _name = name;
        _category = category;
        _price = price;
    }

    void setName (String value){
        _name = value;
    }
    void setCategory (String value){
        _category = value;
    }
    void setPrice (int value){
        _price = value;
    }

    public String getID (){
        return _id;
    }
    public String getName (){
        return _name;
    }
    public String getCategory (){
        return _category;
    }
    public int getPrice (){
        return _price;
    }
}

