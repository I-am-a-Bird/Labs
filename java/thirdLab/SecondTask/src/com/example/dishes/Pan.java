package com.example.dishes;
import com.example.object.Object;
public class Pan implements Dishes,Object{
    private String manufacturer;
    private String style;
    private String coating;
    private double handle_length;
    private double price;
    
    @Override
    public String name(){
        return "Pan";
    }
    public void print(){
        
    }
    public String getManufacturer() {
        return manufacturer;
    }
    public String getStyle() {
        return style;
    }
    public String getCoating() {
        return coating;
    }
    public double getHandle_length() {
        return handle_length;
    }
    public double getPrice() {
        return price;
    }
}