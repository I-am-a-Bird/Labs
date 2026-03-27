public class App {

    public static void main(String[] args) {
     //нельзя создать обьекты абстрактного класса
        Snake lara = new Snake();
        lara.voice();
        Pickup car = new Pickup();
        car.transportCargo();
        car.transportPassangers();
        Sedan car1 = new Sedan();
        car1.transportPassangers();
    }
}
abstract class Pet {
    String name;
    int age;
    boolean hungry;
    abstract void voice();//абстрактный метод, который должен быть реализован в наследниках класса Pet

    void food(){
        hungry = false;
    }
}

class Snake extends Pet {
    double lenght;
    void voice(){
        System.out.println("SHHHHHH");
    }
}
class Dog extends Pet {
    void voice(){
        System.out.println("arf-arf");
    }
}
class PatrolDog extends Pet {
    void voice(){
        System.out.println("rrrrrrr");
    }
}



interface PassangersAuto{
    void transportPassangers();
}

interface CargoAuto {
    void transportCargo();
}
class Truck implements CargoAuto{
   final static int a=1;
    public void transportCargo(){
        System.out.println("везу груз");
    }
}
class Sedan implements PassangersAuto{
    public void transportPassangers(){
        System.out.println("везу пассажиров");
    }
}
class Pickup implements PassangersAuto,CargoAuto{
    
    public void transportCargo(){
        System.out.println("везу груз");
    }
    public void transportPassangers(){
        System.out.println("везу пассажиров");
    }
}
/*  Далее создать 3 класса,
реализующих оба интерфейса. Один из этих классов нужно представить как
абстрактный (не реализовать абстрактные методы). Нереализованные методы
реализуются в двух классах-наследниках абстрактного класса.
Задание выполняется в соответствие со схемой, вместо букв латинского
алфавита студент подставляет данные, согласно своему варианту
индивидуального задания. */


/*Вариант 5 A - Посуда, B - Тарелка, C – Салатница, D - Блюдце, E -
Кастрюля , G -Сковорода */


/*Создайте класс «Посуда», который содержит следующую
информацию: производитель, цена и стиль. Предусмотреть get методы.
Метод класса «Наименование», который потом будут переопределять
производные классы.
Создайте класс «Кастрюля» производный от «Посуда» содержит
дополнительную информацию: объем и наличие крышки.
Создайте класс «Сковорода» производный от «Посуда» содержит
дополнительную информацию: длина ручки, покрытие.
Создайте класс «Тарелка» производный от «Посуда» содержит
дополнительную информацию: диаметр.*/