package com.example.dishes;
import com.example.object.Object;
public class SaladBowl extends Plate implements Dishes,Object {
    private Boolean isDrawing;

    public SaladBowl(String manufacturer, String style, Double price, Boolean isDrawing){
        this.manufacturer=manufacturer;
        this.style=style;
        this.price=price;
        this.isDrawing=isDrawing;
        this.price=price;

    }

    public SaladBowl(SaladBowl saladBowl){
        this.manufacturer=saladBowl.manufacturer;
        this.style=saladBowl.style;
        this.price=saladBowl.price;
        this.isDrawing=saladBowl.isDrawing;
        this.price=saladBowl.price;
    
    }
    public SaladBowl(){
        this.manufacturer=null;
        this.style=null;
        this.price=null;
        this.isDrawing=null;
        this.price=null;

    }
    

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
        return "for salad";
    }
    public Boolean getIsDrawing(){
        return isDrawing;
    }
    public boolean isMicrowaveSafe(){
        return false;
    }
    public String name(){
        return "Salad Bowl";
    }
    public void print(){
        System.out.println("==================");

        System.out.println("manufacturer:" + getManufacturer());
        System.out.println("style:" + getStyle());
        System.out.println("price:" + getPrice());
        System.out.println("isDrawing:" + getIsDrawing());
        name();
        System.out.println("==================");
    }
}
