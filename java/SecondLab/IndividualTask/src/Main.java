import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("========== ПРОДОВОЛЬСТВЕННЫЙ МАГАЗИН ==========\n");

        Milk milk1 = new Milk("Молоко Веселый Молочник", 89.90, 900, 7, 3.2);
        Milk milk2 = new Milk("Молоко Простоквашино", 79.90, 950, 2, 2.5);
        Bread bread = new Bread("Бородинский хлеб", 55.50, 400, "ржаная");
        Apple apple1 = new Apple("Яблоко Гренни", 120.0, 180, 7, "Гренни Смит");
        Apple apple2 = new Apple("Яблоко Антоновка", 89.90, 160, 3, "Антоновка");

        Order order = new Order();

        System.out.println("--- Собираем заказ ---");
        order.addProduct(milk1);
        order.addProduct(bread);
        order.addProduct(apple1);
        order.addProduct(milk2);
        order.addProduct(apple2);

        order.printOrder();

        System.out.println("\n--- Проверка свежести (полиморфизм) ---");
        List<Perishable> perishables = new ArrayList<>();
        perishables.add(milk1);
        perishables.add(milk2);
        perishables.add(apple1);
        perishables.add(apple2);

        for (Perishable p : perishables) {
            p.checkFreshness();
        }

        System.out.println("\n--- Сравнение объектов (equals) ---");
        Apple apple3 = new Apple("Яблоко Гренни", 120.0, 180, 7, "Гренни Смит");
        System.out.println("apple1 equals apple3? " + apple1.equals(apple3));
        System.out.println("apple1 equals apple2? " + apple1.equals(apple2));
        System.out.println("hashCode apple1: " + apple1.hashCode());
        System.out.println("hashCode apple3: " + apple3.hashCode());

        System.out.println("\n--- Все товары в магазине (коллекция) ---");
        List<Product> allProducts = new ArrayList<>();
        allProducts.add(milk1);
        allProducts.add(milk2);
        allProducts.add(bread);
        allProducts.add(apple1);
        allProducts.add(apple2);

        for (Product p : allProducts) {
            System.out.println(p);
        }

        System.out.println("\n--- Калорийность заказа ---");
        int totalCalories = 0;
        for (Product p : order.getProducts()) {
            totalCalories += p.calculateCalories();
        }
        System.out.println("Общая калорийность заказа: " + totalCalories + " ккал");
    }
}