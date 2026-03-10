package com.example.dishes;

public class Pan extends Dishes {
    private String coating;
    private double handle_length;
    
    public Pan(String manufacturer,String style,double price,String coating,double handle_length){
        super(manufacturer,price,style);
        this.coating=coating;
        this.handle_length=handle_length;
    }
    public Pan(String manufacturer,double price,String coating,double handle_length){
        super(manufacturer,price);
        this.coating=coating;
        this.handle_length=handle_length;
    }
    public Pan(){
    }
    
    public String getCoating() {
        return coating;
    }
    
    public double getHandle_length() {
        return handle_length;
    }


    @Override
    public String name(){
        return "Pan with " + coating + " coating and handle length of " + handle_length + " centimetres"; 
    }
}
/*Создайте класс «Сковорода» производный от «Посуда» содержит
дополнительную информацию: длина ручки, покрытие.*/