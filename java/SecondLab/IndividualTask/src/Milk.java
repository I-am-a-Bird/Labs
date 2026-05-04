import java.util.Objects;

public class Milk extends Product implements Perishable {
    private int shelfLife;
    private double fat;

    public Milk(String name, double price, int weight, int shelfLife, double fat) {
        super(name, price, weight);
        this.shelfLife = shelfLife;
        this.fat = fat;
    }

    @Override
    public int calculateCalories() {
        return (int) (weight * 0.65);
    }

    @Override
    public int getShelfLife() {
        return shelfLife;
    }

    @Override
    public void checkFreshness() {
        if (shelfLife >= 5) {
            System.out.println(name + " - свежее");
        } else {
            System.out.println(name + " - скоро испортится!");
        }
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", Жирность: %.1f%%, Срок годности: %d дн.", fat, shelfLife);
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        Milk milk = (Milk) o;
        return shelfLife == milk.shelfLife && Double.compare(fat, milk.fat) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), shelfLife, fat);
    }
}