import java.util.ArrayList;
import java.util.List;

public class Order {
    private static int nextId = 1;
    private int orderId;
    private List<Product> products;

    public Order() {
        this.orderId = nextId++;
        this.products = new ArrayList<>();
    }

    public void addProduct(Product p) {
        products.add(p);
        System.out.println("Добавлен: " + p.getName());
    }

    public void removeProduct(int index) {
        if (index >= 0 && index < products.size()) {
            Product removed = products.remove(index);
            System.out.println("Удален: " + removed.getName());
        }
    }

    public double getTotalPrice() {
        double sum = 0;
        for (Product p : products) {
            sum += p.getPrice();
        }
        return sum;
    }

    public int getTotalWeight() {
        int sum = 0;
        for (Product p : products) {
            sum += p.getWeight();
        }
        return sum;
    }

    public void printOrder() {
        System.out.println("\n=== ЗАКАЗ №" + orderId + " ===");
        if (products.isEmpty()) {
            System.out.println("Корзина пуста");
            return;
        }
        for (int i = 0; i < products.size(); i++) {
            System.out.println((i + 1) + ". " + products.get(i));
        }
        System.out.println("Итого: " + getTotalPrice() + " руб.");
        System.out.println("Общий вес: " + getTotalWeight() + " г.");
    }

    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }

    @Override
    public String toString() {
        return "Заказ №" + orderId + ", товаров: " + products.size() + ", сумма: " + getTotalPrice() + " руб.";
    }
}