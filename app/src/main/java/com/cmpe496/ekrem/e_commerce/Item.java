package com.cmpe496.ekrem.e_commerce;

import java.io.Serializable;

/**
 * Created by ekrem on 06/03/2017.
 */

public class Item implements Serializable {

    private Long id;
    private int photo;
    private String name;
    private String year;
    private String length;
    private String location;

    public Item(Long id, int photo, String name, String year, String length, String location, String material, double price) {
        this.id = id;
        this.photo = photo;
        this.name = name;
        this.year = year;
        this.length = length;
        this.location = location;
        this.material = material;
        this.price = price;
    }

    public Item(Long id, String name, String year, String length, String location, String material, double price) {
        this.id = id;
        this.photo = R.drawable.a;
        this.name = name;
        this.year = year;
        this.length = length;
        this.location = location;
        this.material = material;
        this.price = price;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    private String material;
    private double price;


    public Item(Long id, int photo, String name, String year, double price) {
        this.id = id;
        this.photo = photo;
        this.name = name;
        this.year = year;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
