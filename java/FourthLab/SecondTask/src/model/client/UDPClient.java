package model.client;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class UDPClient {
    public final static int SERVER_PORT = 8001;
    public final static String SERVER_HOST = "localhost";
    
    public void runClient() throws IOException {
        DatagramSocket socket = null;
        Scanner scanner = new Scanner(System.in);
        
        try {
            socket = new DatagramSocket();
            socket.setSoTimeout(5000); // 5 second timeout
            
            System.out.println("UDP Client started");
            System.out.println("Connected to server: " + SERVER_HOST + ":" + SERVER_PORT);
            System.out.println("\nEnter values to compute: φ = | x^(y/x) - √(y/x) + (y-x)*(cos(y)-e^(y-x))/(1+(y-x)^2) |");
            System.out.println("Format: x,y (e.g., 2.0,4.0)");
            System.out.println("Enter 'QUIT' to exit\n");
            
            while (true) {
                System.out.print("Enter x,y: ");
                String input = scanner.nextLine().trim();
                
                // Send data to server
                byte[] sendData = input.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, 
                                        InetAddress.getByName(SERVER_HOST), SERVER_PORT);
                socket.send(sendPacket);
                
                // Check for quit
                if (input.equalsIgnoreCase("QUIT")) {
                    System.out.println("Waiting for server response...");
                }
                
                // Receive response from server
                byte[] receiveBuffer = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                
                try {
                    socket.receive(receivePacket);
                    String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
                    System.out.println("Server: " + response);
                    System.out.println();
                    
                    if (input.equalsIgnoreCase("QUIT")) {
                        break;
                    }
                } catch (SocketTimeoutException e) {
                    System.out.println("ERROR: Server response timeout.\n");
                    if (input.equalsIgnoreCase("QUIT")) {
                        break;
                    }
                }
            }
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
            scanner.close();
            System.out.println("UDP Client stopped.");
        }
    }
    
    public static void main(String[] args) {
        try {
            UDPClient client = new UDPClient();
            client.runClient();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}