package com.example.app;
import com.example.model.Product;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("How many products`s do you have?");
        int listSize = scanner.nextInt();
        scanner.nextLine();

        
        System.out.println("default constructor-1");
        System.out.println("constructor with parameters-2");
        System.out.println("copy constructor-3");
        System.out.println("What constructor should I use to create objects?");

        int change = scanner.nextInt();
        scanner.nextLine();
        if (change < 1 || change > 3) {
            System.out.println("Invalid choice!");
            return;
            }
        Product list[] = new Product[listSize];

        
           switch (change) {
               case 1:
                    System.out.println("Using default constructor");
                    for(int i = 0;i<list.length;i++){
                        System.out.println("Creation of "+(i+1)+" product");
                        list[i]=new Product();
                    }
                    System.out.println("All product`s are made using the default constructor");
                    break;

                case 2:
                    System.out.println("Using constructor with parameters");
                    for(int i = 0;i<list.length;i++){
                        System.out.println("Creation of "+(i+1)+" product");
                        System.out.print("Enter the name of product:");
                        String NameOfProduct = scanner.nextLine();
                        System.out.print("Enter the price of product:");
                        Double price=scanner.nextDouble();
                        System.out.print("Enter the availabillity:");
                        Boolean availabillity=scanner.nextBoolean();
                        list[i]= new Product(NameOfProduct,availabillity,price);
                    }
                    System.out.println("All products are made using the constructor with parameters");
                    break;

                case 3:
                    System.out.println("Using copy constructor");
                    for(int i = 0; i < list.length; i++) {
                        System.out.println("Creation of " + (i+1) + " product");
                        if(i == 0) {
                            System.out.println("Cannot copy first Product. Using constructor with parameters.");
                            System.out.print("Enter the name of product:");
                            String NameOfProduct = scanner.nextLine();
                            System.out.print("Enter the price of product:");
                            Double price=scanner.nextDouble();
                            System.out.print("Enter the av");
                            Boolean availabillity=scanner.nextBoolean();
                            list[i]= new Product(NameOfProduct,availabillity,price);
                        }
                        else {
                            list[i] = new Product(list[i-1]);  
                        }
                    }
                    System.out.println("All products`s are made");
                    break;  
               default:
                   System.out.println("error");
        }


        System.out.println("Info about all product`s");
        for(int i=0;i<list.length;i++){
            if(list[i]!=null){
                list[i].printInfo();
                String status = list[i].CheckingPartsAbility(list[i]);
                System.out.println("status of product:"+status);
            }
        }
        Product.printStatistics(list);
    scanner.close();
    }
}
