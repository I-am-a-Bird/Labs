package com.example.app;
import com.example.model.CreatePC;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("How many PC`s do you have?");
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
        CreatePC list[] = new CreatePC[listSize];

        
           switch (change) {
               case 1:
                    System.out.println("Using default constructor");
                    for(int i = 0;i<list.length;i++){
                        System.out.println("Creation of "+(i+1)+" PC");
                        list[i]=new CreatePC();
                    }
                    System.out.println("All PC`s are made using the default constructor");
                    break;

                case 2:
                    System.out.println("Using constructor with parameters");
                    for(int i = 0;i<list.length;i++){
                        System.out.println("Creation of "+(i+1)+" PC");
                        System.out.print("Enter the name of CPU:");
                        String CPU=scanner.nextLine();
                        System.out.print("Enter the name of GPU:");
                        String GPU=scanner.nextLine();
                        System.out.print("Enter the name of motherboard:");
                        String motherboard=scanner.nextLine();
                        System.out.print("Enter the name of RAM:");
                        String RAM=scanner.nextLine();
                        System.out.print("Enter the name of PCU:");
                        String PCU=scanner.nextLine();
                        System.out.print("Enter the name of cooling system:");
                        String cooling_system=scanner.nextLine();
                        System.out.print("Enter the name of storage device:");
                        String storage_device=scanner.nextLine();
                        System.out.print("Enter the name of case:");
                        String Case = scanner.nextLine();
                        list[i]= new CreatePC(CPU,GPU,motherboard,RAM,PCU,cooling_system,storage_device,Case);
                    }
                    System.out.println("All products are made using the constructor with parameters");
                    break;

                case 3:
                    System.out.println("Using copy constructor");
                    for(int i = 0; i < list.length; i++) {
                        System.out.println("Creation of " + (i+1) + " PC");
                        if(i == 0) {
                            System.out.println("Cannot copy first PC. Using constructor with parameters.");
                            System.out.print("Enter the name of CPU:");
                            String CPU=scanner.nextLine();
                            System.out.print("Enter the name of GPU:");
                            String GPU=scanner.nextLine();
                            System.out.print("Enter the name of motherboard:");
                            String motherboard=scanner.nextLine();
                            System.out.print("Enter the name of RAM:");
                            String RAM=scanner.nextLine();
                            System.out.print("Enter the name of PCU:");
                            String PCU=scanner.nextLine();
                            System.out.print("Enter the name of cooling system:");
                            String cooling_system=scanner.nextLine();
                            System.out.print("Enter the name of storage device:");
                            String storage_device=scanner.nextLine();
                            System.out.print("Enter the name of case:");
                            String Case = scanner.nextLine();
                            list[i]= new CreatePC(CPU,GPU,motherboard,RAM,PCU,cooling_system,storage_device,Case);
                        } else {
                            list[i] = new CreatePC(list[i-1]);  
                        }
                    }
                    System.out.println("All PC`s are made");
                    break;  
               default:
                   System.out.println("error");
        }


        System.out.println("Configuration of all PC`s");
        for(int i=0;i<list.length;i++){
            if(list[i]!=null){
                list[i].printInfo();
                String status = list[i].CheckingPartsAbility(list[i]);
                System.out.println("status of PC:"+status);
            }
        }
        CreatePC.printStatistics(list);
    scanner.close();
    }
}
