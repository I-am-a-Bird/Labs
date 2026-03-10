package com.example.app;

import com.example.dishes.Dishes;
import com.example.dishes.Pan;
import com.example.dishes.Plate;
import com.example.dishes.Pot;
import com.example.writerinfo.WriterInfo;

class App{
    public static void main(String[] args) {
        Dishes firstObj= new Dishes("производитель", 50.99, "r1");
        Dishes secondObj = new Pan("производитель","стиль",100,"покрытие",10);
        Dishes thirdObj= new Plate("производитель","стиль",50,30);
        Dishes fourthObj = new Pot("производитель","стиль",33,23,true);
        WriterInfo writer = new WriterInfo();
        writer.printInfo(firstObj);
        writer.printInfo(secondObj);
        writer.printInfo(thirdObj);
        writer.printInfo(fourthObj);
    }
}

/*  Определите в каждом
классе три перегруженных конструктора: с полным набором параметров, с
частью параметров и конструктор по умолчанию. Предусмотреть
переопределение методов базового класса в производных классах. Создайте
дополнительный класс WriterInfo, для вывода информации о классах.*/




