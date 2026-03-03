public class app {
    public static void main(String[] args) {
        Product o1 = new Product("Milk", 3.99, true);
        WriterInfo writer = new WriterInfo();
        writer.printInfo(o1);
        
    }
}
