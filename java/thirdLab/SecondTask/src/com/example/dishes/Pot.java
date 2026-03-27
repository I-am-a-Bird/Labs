package com.example.dishes;
import com.example.object.Object;
public class Pot implements Dishes,Object{
    private String manufacturer;
    private String style;
    private double price;
    private double volume;
    private boolean presence_of_a_cover;

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
