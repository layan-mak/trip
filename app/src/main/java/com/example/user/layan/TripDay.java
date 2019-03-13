package com.example.user.layan;

import java.io.Serializable;
import java.util.ArrayList;

public class TripDay implements Serializable{

    private String country;
    private int image;
    private int tripDayId;
    private ArrayList<String> cities;
    private String description;
    private String key;


    public TripDay(String country, String city, String description,int image) {
        this.country=country;
        this.image=image;
        this.description=description;
        cities= new ArrayList<String>();
        this.key=key;

    }

    public TripDay() {
    }

    public String getCountry() {
        return country;
    }

    public int getImage() {

        return image;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public ArrayList<String> getCities() {
        return this.cities;
    }

    public void addCity(String city) {
        this.cities.add(city);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(int image) {
        this.image=image;
    }


}
