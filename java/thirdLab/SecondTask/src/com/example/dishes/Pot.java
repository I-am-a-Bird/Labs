package com.example.dishes;
import com.example.object.Object;
public class Pot implements Dishes,Object{
    private String manufacturer;
    private String style;
    private double price;
    private double volume;
    private boolean presence_of_a_cover;


    public Pot(String manufacturer, String style, Double volume,Double price, Boolean presence_of_a_cover){
        this.manufacturer=manufacturer;
        this.style=style;
        this.price=price;
        this.volume=volume;
        this.price=price;

    }
    public Pot(Pot pot){
        this.manufacturer=pot.manufacturer;
        this.style=pot.style;
        this.price=pot.price;
        this.volume=pot.volume;
        this.presence_of_a_cover=pot.presence_of_a_cover;
    }
    public Pot(){
        this.manufacturer=null;
        this.style=null;
        this.price=0;
        this.volume=0;
        this.presence_of_a_cover=false;
    }
    
    @Override
    public String name(){
        return "Pot";
    }
    @Override
    public void print(){
        String coverInfo = presence_of_a_cover?"with cover":"without cover";
        System.out.println(name() + " with volume " + volume + " litres, "+ coverInfo);
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
    public double getVolume() {
        return volume;
    }
    public boolean getPresence_of_a_cover(){
        return presence_of_a_cover;
    }
}
