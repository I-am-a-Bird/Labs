class Product{
    private String product_name;
    private double price;
    private boolean availability;

    public Product(String product_name, double price, boolean availability){
        this.product_name=product_name;
        this.price=price;
        this.availability=availability;
    }
    public String getName(){
        return product_name;
    }
    public double getPrice(){
        return price;
    }
    public boolean getAvailability(){
        return availability;
    }   
}