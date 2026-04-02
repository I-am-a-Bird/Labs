package com.example.model;

public class Product {
    private String NameOfProduct;
    private Boolean availabillity;
    private Double price;
    public final static String MESSAGE="Your product is assembled!";

    

    public Product(String NameOfProduct, boolean availabillity, double price){
        this.NameOfProduct=NameOfProduct;
        this.availabillity=availabillity;
        this.price=price;
        }
    public Product(Product product){
        this.NameOfProduct=product.NameOfProduct;
        this.availabillity=product.availabillity;
        this.price=product.price;
    }
    public Product(){
        this.NameOfProduct = null;
        this.availabillity = null;
        this.price =null;
    }
    public String getNameOfProduct() {
        return NameOfProduct;
    }
    public double getPrice() {
        return price;
    }
    public boolean getAvailabillity(){
        return availabillity;
    }

    public void printInfo(){
        System.out.println("=====Product configuration=====");
        System.out.println("Name of product:"+getNameOfProduct());
        System.out.println("price:"+getPrice());
        System.out.println("availabillity:"+getAvailabillity());
        System.out.println("==========================");
    }
    public String CheckingPartsAbility(Product product){
       
        if(product == null) return "Error: product object is null";
        
        StringBuilder missingParts = new StringBuilder();
        
        if(product.NameOfProduct == null || product.NameOfProduct.isEmpty()) {
            missingParts.append("Product name, ");
        }
        if(product.price <= 0 || product.price==null) {
            missingParts.append("price, ");
        }
        if(product.availabillity == null) {
            missingParts.append("availability, ");
        }
        
        
        if(missingParts.length() > 0) {
            
            String missing = missingParts.substring(0, missingParts.length() - 2);
            return "Error: missing components - " + missing;
        }
        return MESSAGE;
    }
    public static int countOfReadyProducts(Product[] products){
        if(products == null) return 0;  
            int count = 0;
            for (var product : products) {
                if(product.CheckingPartsAbility(product).equals(MESSAGE)){
                    count++;
                }
            }
            return count;
        }
    public static int countOfUnreadyProducts(Product[] products){
        if(products == null) return 0;  
        int ready = countOfReadyProducts(products);
        int count = products.length-ready;
        return count;
    }
    public static void printStatistics(Product[] products){
        int countOfAllProducts=products.length;
        int countOfReadyProducts=countOfReadyProducts(products);
        int countOfUnreadyProducts=countOfUnreadyProducts(products);
        System.out.println("=====all statistics=====");
        System.out.println("count of all products:"+countOfAllProducts);
        System.out.println("count of ready PC:"+countOfReadyProducts);
        System.out.println("count of unready PC:"+countOfUnreadyProducts);
        System.out.println("========================");
    }
}

    