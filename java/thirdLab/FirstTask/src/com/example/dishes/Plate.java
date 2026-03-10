package com.example.dishes;

public class Plate extends Dishes {
    private double diameter;

    public Plate(String manufacturer,String style,double price,double diameter) {
        super(manufacturer, price, style);
        this.diameter=diameter;
    }
    public Plate(String manufacturer,double price,double diameter) {
        super(manufacturer, price);
        this.diameter=diameter;
    }
    public Plate() {}
    @Override
     public String name(){
        return "Plate with diameter "+ diameter+" santimetres";
    }

    public double getDiameter() {
        return diameter;
    }
}
/*
Создайте класс «Тарелка» производный от «Посуда» содержит
дополнительную информацию: диаметр.*/