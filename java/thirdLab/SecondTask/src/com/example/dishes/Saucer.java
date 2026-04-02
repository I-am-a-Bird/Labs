package com.example.dishes;
import com.example.object.Object;
public class Saucer extends Plate implements Dishes,Object{
    private int depth;
    public String getManufacturer(){
        return manufacturer;
    }
    public String getStyle(){
        return style;
    }
    public Double getPrice(){
        return price;
    }
    public String getPurpose(){
        return "for cup of tea";
    }
    public int getDepth(){
        return depth;
    }
    public boolean isMicrowaveSafe(){
        return false;
    }
    public String name(){
        return "Saucer";
    }
    public void print(){
        System.out.println("==================");

        System.out.println("manufacturer:" + getManufacturer());
        System.out.println("style:" + getStyle());
        System.out.println("price:" + getPrice());
        System.out.println("depth:" + getDepth());
        name();
        System.out.println("==================");
    }
    
}
