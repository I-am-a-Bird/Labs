package model.server;

import java.net.*;
import java.io.*;

public class UDPServer {
    public final static int DEFAULT_PORT = 8001;

    public void runServer() throws IOException {
        DatagramSocket socket = null;
        try {
            byte[] receiveBuffer = new byte[1024];
            
            socket = new DatagramSocket(DEFAULT_PORT);
            System.out.println("UDP Server started on port " + DEFAULT_PORT);
            System.out.println("Waiting for client requests...\n");
            
            while (true) {
                // Prepare packet for receiving
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                
                // Receive data from client (blocking)
                socket.receive(receivePacket);
                
                // Get client info
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();
                
                // Extract received message
                String received = new String(receivePacket.getData(), 0, receivePacket.getLength()).trim();
                System.out.println("Received from " + clientAddress + ":" + clientPort + " -> " + received);
                
                // Check for quit command
                if (received.equalsIgnoreCase("QUIT")) {
                    System.out.println("Quit command received. Closing server...");
                    String response = "Server shutting down...";
                    byte[] sendBuffer = response.getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, clientAddress, clientPort);
                    socket.send(sendPacket);
                    break;
                }
                
                // Parse and compute expression
                String[] parts = received.split(",");
                String response;
                
                if (parts.length != 2) {
                    response = "ERROR: Invalid format. Please send 'x,y'";
                } else {
                    try {
                        double x = Double.parseDouble(parts[0].trim());
                        double y = Double.parseDouble(parts[1].trim());
                        double result = calculateExpression(x, y);
                        response = String.format("Result: φ = %.6f", result);
                        System.out.println("Computed: φ(" + x + ", " + y + ") = " + result);
                    } catch (Exception e) {
                        response = "ERROR: " + e.getMessage();
                    }
                }
                
                // Send response back to client
                byte[] sendBuffer = response.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, clientAddress, clientPort);
                socket.send(sendPacket);
            }
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
                System.out.println("UDP Server stopped.");
            }
        }
    }
    
    private double calculateExpression(double x, double y) {
        // Check domain
        if (x <= 0) {
            throw new ArithmeticException("x must be positive");
        }
        if (y/x < 0) {
            throw new ArithmeticException("y/x must be non-negative for square root");
        }
        
        // Calculate: φ = | x^(y/x) - √(y/x) + (y-x)*(cos(y)-e^(y-x))/(1+(y-x)^2) |
        double term1 = Math.pow(x, y/x);
        double term2 = Math.sqrt(y/x);
        double diff = y - x;
        double numerator = Math.cos(y) - Math.exp(diff);
        double denominator = 1 + Math.pow(diff, 2);
        double term3 = diff * (numerator / denominator);
        
        return Math.abs(term1 - term2 + term3);
    }
    
    public static void main(String[] args) {
        try {
            UDPServer server = new UDPServer();
            server.runServer();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}