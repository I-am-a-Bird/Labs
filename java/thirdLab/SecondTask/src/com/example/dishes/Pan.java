package com.example.dishes;
import com.example.object.Object;
public class Pan implements Dishes,Object{
    private String manufacturer;
    private String style;
    private String coating;
    private double handle_length;
    private double price;
    
    public Pan(String manufacturer, String style, String coating, Double handle_length,Double price){
        this.manufacturer=manufacturer;
        this.style=style;
        this.coating=coating;
        this.handle_length=handle_length;
        this.price=price;

    }

    public Pan(Pan pan){
        this.manufacturer=pan.manufacturer;
        this.style=pan.style;
        this.coating=pan.coating;
        this.handle_length=pan.handle_length;
        this.price=pan.price;
    }
    public Pan(){
        this.manufacturer=null;
        this.style=null;
        this.coating=null;
        this.price=0;
        this.handle_length=0;
    }
    @Override

    public String name(){
        return "Pan";
    }
    public void print(){
        
        System.out.println("==================");
        System.out.println("manufacturer:" + getManufacturer());
        System.out.println("style:" + getStyle());
        System.out.println("price:" + getPrice());
        System.out.println("coating:" + getCoating());
        System.out.println("handle length:" + getHandle_length());
        name();
        System.out.println("==================");
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