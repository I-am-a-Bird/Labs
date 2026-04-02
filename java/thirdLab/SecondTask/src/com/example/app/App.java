import com.example.dishes.*;
import com.example.object.*;
public class App {

    public static void main(String[] args) {
        Pan pan = new Pan();
        Pot pot = new Pot();
        SaladBowl bowl = new SaladBowl();
        Saucer saucer = new Saucer();

        System.out.println("first");
        pan.print();

        System.out.println("second");
        pot.print();
        System.out.println("Third");
        bowl.print();
        System.out.println("fourth");
        saucer.print();
    }
}