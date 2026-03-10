package com.example.writerinfo;
import com.example.dishes.Dishes;
public class WriterInfo {
    public void printInfo(Dishes dishes) {
        System.out.println("=====information=====");
        System.out.println(dishes.name());
    }
}