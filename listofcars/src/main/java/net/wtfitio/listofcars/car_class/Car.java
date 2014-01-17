package net.wtfitio.listofcars.car_class;

import java.io.Serializable;

/**
 * Created by plamend on 1/17/14.
 */
public class Car implements Serializable {

    private String Name;
    private String Make;
    private String Model;
    private String prodYear;
    private String Color;



    public String getName() {
        return Name;
    }

    public String getMake() {
        return Make;
    }

    public String getModel() {
        return Model;
    }

    public String getProdYear() {
        return prodYear;
    }

    public String getColor() {
        return Color;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public void setMake(String make) {
        this.Make = make;
    }

    public void setModel(String model) {
        this.Model = model;
    }

    public void setProdYear(String prodYear) {
        this.prodYear = prodYear;
    }

    public void setColor(String color) {
        this.Color=color;
    }
}
