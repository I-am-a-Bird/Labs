package com.example.dishes;

public class Pot extends Dishes{
    private double volume;
    private boolean presence_of_a_cover;

    public Pot(String manufacturer,String style,double price,double volume, boolean presence_of_a_cover){
        super(manufacturer,price,style);
        this.volume=volume;
        this.presence_of_a_cover=presence_of_a_cover;
    }
    public Pot(String manufacturer,double price,double volume, boolean presence_of_a_cover){
        super(manufacturer,price);
        this.volume=volume;
        this.presence_of_a_cover=presence_of_a_cover;
    }
    public Pot(){}

    public boolean getPresence_of_a_cover(){
        return presence_of_a_cover;
    }

    public double getVolume() {
        return volume;
    }
    @Override
    public String name(){
        String coverInfo = presence_of_a_cover?"with cover":"without cover";
        return "Pot with volume " + volume + " litres, " + coverInfo;
    }
    
    

}
/*Создайте класс «Кастрюля» производный от «Посуда» содержит
дополнительную информацию: объем и наличие крышки.*/