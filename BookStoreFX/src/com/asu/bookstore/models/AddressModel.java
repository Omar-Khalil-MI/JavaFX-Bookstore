package com.asu.bookstore.models;

public class AddressModel {
    
    private String City;
    private String Postal_Code;

    //Constructors//
    public AddressModel() {
        City = null;
        Postal_Code = null;
    }
    
    public AddressModel(String city, String code){
        City = city;
        Postal_Code = code;
    }

    //Getters-Setters//
    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public String getPostal_Code() {
        return Postal_Code;
    }

    public void setPostal_Code(String Postal_Code) {
        this.Postal_Code = Postal_Code;
    }
    
    @Override
    public String toString() {
        return "Address{" + ", City=" + City + ", Postal_Code=" + Postal_Code +'}';
    }
}
