public class Order {
    private static int nextId = 1;
    private int orderId;
    private Product[] products;
    private int productCount;
    private static final int INITIAL_CAPACITY = 10;

    public Order() {
        this.orderId = nextId++;
        this.products = new Product[INITIAL_CAPACITY];
        this.productCount = 0;
    }

    public void addProduct(Product p) {
        if (productCount == products.length) {
            Product[] newArray = new Product[products.length * 2];
            for (int i = 0; i < products.length; i++) {
                newArray[i] = products[i];
            }
            products = newArray;
        }
        products[productCount] = p;
        productCount++;
        System.out.println("Добавлен: " + p.getName());
    }

    public void removeProduct(int index) {
        if (index >= 0 && index < productCount) {
            Product removed = products[index];
            for (int i = index; i < productCount - 1; i++) {
                products[i] = products[i + 1];
            }
            products[productCount - 1] = null;
            productCount--;
            System.out.println("Удален: " + removed.getName());
        }
    }

    public double getTotalPrice() {
        double sum = 0;
        for (int i = 0; i < productCount; i++) {
            sum += products[i].getPrice();
        }
        return sum;
    }

    public int getTotalWeight() {
        int sum = 0;
        for (int i = 0; i < productCount; i++) {
            sum += products[i].getWeight();
        }
        return sum;
    }

    public void printOrder() {
        System.out.println("\n=== ЗАКАЗ №" + orderId + " ===");
        if (productCount == 0) {
            System.out.println("Корзина пуста");
            return;
        }
        for (int i = 0; i < productCount; i++) {
            System.out.println((i + 1) + ". " + products[i]);
        }
        System.out.println("Итого: " + getTotalPrice() + " руб.");
        System.out.println("Общий вес: " + getTotalWeight() + " г.");
    }

    public Product[] getProducts() {
        Product[] result = new Product[productCount];
        for (int i = 0; i < productCount; i++) {
            result[i] = products[i];
        }
        return result;
    }

    public int getProductCount() {
        return productCount;
    }

    @Override
    public String toString() {
        return "Заказ №" + orderId + ", товаров: " + productCount + ", сумма: " + getTotalPrice() + " руб.";
    }
}