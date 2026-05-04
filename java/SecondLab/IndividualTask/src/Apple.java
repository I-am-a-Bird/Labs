import java.util.Objects;

public class Apple extends Product implements Perishable {
    private int shelfLife;
    private String variety;

    public Apple(String name, double price, int weight, int shelfLife, String variety) {
        super(name, price, weight);
        this.shelfLife = shelfLife;
        this.variety = variety;
    }

    @Override
    public int calculateCalories() {
        return (int) (weight * 0.52);
    }

    @Override
    public int getShelfLife() {
        return shelfLife;
    }

    @Override
    public void checkFreshness() {
        System.out.println(name + " (" + variety + ") - срок годности " + shelfLife + " дн.");
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", Сорт: %s, Срок хранения: %d дн.", variety, shelfLife);
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        Apple apple = (Apple) o;
        return shelfLife == apple.shelfLife && Objects.equals(variety, apple.variety);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), shelfLife, variety);
    }
}