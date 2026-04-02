package com.example.dishes;

abstract public class Plate {
    protected String manufacturer;
    protected String style;
    protected Double price;


    abstract String getManufacturer();
    abstract String getStyle();
    abstract Double getPrice(); 

    abstract String getPurpose();
    abstract boolean isMicrowaveSafe();
}