import java.util.Objects;

public abstract class Product {
    protected String name;
    protected double price;
    protected int weight;

    public Product(String name, double price, int weight) {
        this.name = name;
        this.price = price;
        this.weight = weight;
    }

    public abstract int calculateCalories();

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return String.format("Товар: %s, Цена: %.2f руб., Вес: %d г., Калории: %d",
                name, price, weight, calculateCalories());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(price, product.price) == 0 &&
                weight == product.weight &&
                Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, weight);
    }
}