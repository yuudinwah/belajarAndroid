package com.example.myfirstapp;

public class MenuModel {
    String _name;
    String _type;
    int _price;
    int _qty;

    public MenuModel(String name, String type, int price, int qty){
        _name = name;
        _type = type;
        _price = price;
        _qty = qty;
    }

    void setName (String value){
        _name = value;
    }
    void setType (String value){
        _type = value;
    }
    void setPrice (int value){
        _price = value;
    }
    void setQty (int value){
        _qty = value;
    }
    void inc (){
        _qty++;
    }
    void dec(){
        _qty--;
    }

    public String getName (){
        return _name;
    }
    public String getType (){
        return _type;
    }
    public int getPrice (){
        return _price;
    }
    public int getQty (){
        return _qty;
    }
}
