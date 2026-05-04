import java.util.Objects;

public class Bread extends Product {
    private String flourType;

    public Bread(String name, double price, int weight, String flourType) {
        super(name, price, weight);
        this.flourType = flourType;
    }

    @Override
    public int calculateCalories() {
        return (int) (weight * 2.4);
    }

    @Override
    public String toString() {
        return super.toString() + ", Тип муки: " + flourType;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        Bread bread = (Bread) o;
        return Objects.equals(flourType, bread.flourType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), flourType);
    }
}