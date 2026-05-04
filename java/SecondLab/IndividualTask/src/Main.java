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
        Perishable[] perishables = new Perishable[4];
        perishables[0] = milk1;
        perishables[1] = milk2;
        perishables[2] = apple1;
        perishables[3] = apple2;

        for (int i = 0; i < perishables.length; i++) {
            perishables[i].checkFreshness();
        }

        System.out.println("\n--- Сравнение объектов (equals) ---");
        Apple apple3 = new Apple("Яблоко Гренни", 120.0, 180, 7, "Гренни Смит");
        System.out.println("apple1 equals apple3? " + apple1.equals(apple3));
        System.out.println("apple1 equals apple2? " + apple1.equals(apple2));
        System.out.println("hashCode apple1: " + apple1.hashCode());
        System.out.println("hashCode apple3: " + apple3.hashCode());

        System.out.println("\n--- Все товары в магазине (массив) ---");
        Product[] allProducts = new Product[5];
        allProducts[0] = milk1;
        allProducts[1] = milk2;
        allProducts[2] = bread;
        allProducts[3] = apple1;
        allProducts[4] = apple2;

        for (int i = 0; i < allProducts.length; i++) {
            System.out.println(allProducts[i]);
        }

        System.out.println("\n--- Калорийность заказа ---");
        int totalCalories = 0;
        Product[] orderProducts = order.getProducts();
        for (int i = 0; i < order.getProductCount(); i++) {
            totalCalories += orderProducts[i].calculateCalories();
        }
        System.out.println("Общая калорийность заказа: " + totalCalories + " ккал");
    }
}