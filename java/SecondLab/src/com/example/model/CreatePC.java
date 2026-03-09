package com.example.model;

public class CreatePC {
    private String CPU;
    private String GPU;
    private String motherboard;
    private String RAM;
    private String PCU;
    private String cooling_system;
    private String storage_device;
    private String Case;
    public final static String MESSAGE="Your PC is assembled!";

    

    public CreatePC(String CPU,String GPU,String motherboard,String RAM,String PCU,String cooling_system,String storage_device,String Case){
        this.CPU=CPU;
        this.GPU=GPU;
        this.motherboard=motherboard;
        this.PCU=PCU;
        this.cooling_system=cooling_system;
        this.storage_device=storage_device;
        this.Case=Case;
        this.RAM=RAM;
    }
    public CreatePC(CreatePC PC){
        this.CPU=PC.CPU;
        this.GPU=PC.GPU;
        this.motherboard=PC.motherboard;
        this.PCU=PC.PCU;
        this.cooling_system=PC.cooling_system;
        this.storage_device=PC.storage_device;
        this.Case=PC.Case; 
        this.RAM=PC.RAM;
    }
    public CreatePC(){
        this.CPU = "";
        this.GPU = "";
        this.motherboard = "";
        this.RAM = "";
        this.PCU = "";
        this.cooling_system = "";
        this.storage_device = "";
        this.Case = "";
    }

    public String getCPU(){
        return CPU;
    }
    public String getGPU(){
        return GPU;
    }
    public String getMotherboard(){
        return motherboard;
    }
    public String getPCU(){
        return PCU;
    }
    public String getCoolingSystem(){
        return cooling_system;
    }
    public String getRAM(){
        return RAM;
    }
    public String getStorageDevice(){
        return storage_device;
    }
    public String getCase(){
        return Case;
    }

    public void printInfo(){
        System.out.println("=====PC configuration=====");
        System.out.println("CPU:"+getCPU());
        System.out.println("GPU:"+getGPU());
        System.out.println("Motherboard:"+getMotherboard());
        System.out.println("RAM:"+getRAM());
        System.out.println("PCU:"+getPCU());
        System.out.println("Cooling system:"+getCoolingSystem());
        System.out.println("Storage device:"+getStorageDevice());
        System.out.println("Case:"+getCase());
        System.out.println("==========================");
    }
    public String CheckingPartsAbility(CreatePC PC){
       
        if(PC == null) return "Error: PC object is null";
        
        StringBuilder missingParts = new StringBuilder();
        
        if(PC.CPU == null || PC.CPU.isEmpty()) {
            missingParts.append("CPU, ");
        }
        if(PC.GPU == null || PC.GPU.isEmpty()) {
            missingParts.append("GPU, ");
        }
        if(PC.motherboard == null || PC.motherboard.isEmpty()) {
            missingParts.append("motherboard, ");
        }
        if(PC.RAM == null || PC.RAM.isEmpty()) {
            missingParts.append("RAM, ");
        }
        if(PC.PCU == null || PC.PCU.isEmpty()) {
            missingParts.append("PCU, ");
        }
        if(PC.cooling_system == null || PC.cooling_system.isEmpty()) {
            missingParts.append("cooling system, ");
        }
        if(PC.storage_device == null || PC.storage_device.isEmpty()) {
            missingParts.append("storage device, ");
        }
        if(PC.Case == null || PC.Case.isEmpty()) {
            missingParts.append("case, ");
        }
        
        if(missingParts.length() > 0) {
            
            String missing = missingParts.substring(0, missingParts.length() - 2);
            return "Error: missing components - " + missing;
        }
        return MESSAGE;
    }
    public static int countOfReadyPC(CreatePC[] pcs){
        if(pcs == null) return 0;  
            int count = 0;
            for (var pc : pcs) {
                if(pc.CheckingPartsAbility(pc).equals(MESSAGE)){
                    count++;
                }
            }
            return count;
        }
    public static int countOfUnreadyPC(CreatePC[] pcs){
        if(pcs == null) return 0;  
        int ready = countOfReadyPC(pcs);
        int count = pcs.length-ready;
        return count;
    }
    public static void printStatistics(CreatePC[] pcs){
        int countOfAllPC=pcs.length;
        int countOfReadyPC=countOfReadyPC(pcs);
        int countOfUnreadyPC=countOfUnreadyPC(pcs);
        System.out.println("=====all statistics=====");
        System.out.println("count of all PC:"+countOfAllPC);
        System.out.println("count of ready PC:"+countOfReadyPC);
        System.out.println("count of unready PC:"+countOfUnreadyPC);
        System.out.println("========================");
    }
}

    