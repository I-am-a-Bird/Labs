package com.example.dishes;

public class Dishes {
    protected String manufacturer;
    protected double price;
    protected String style;

    public Dishes(String manufacturer,double price,String style){
        this.manufacturer=manufacturer;
        this.price=price;
        this.style=style;
    }
    public Dishes(String manufacturer,double price){
        this.manufacturer=manufacturer;
        this.price=price;
    }
    public Dishes(){
        manufacturer="";
        price=0;
        style="";
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getStyle() {
        return style;
    }
    public double getPrice() {
        return price;
    }
    public String name(){
        return "Dishes";
    }

}
