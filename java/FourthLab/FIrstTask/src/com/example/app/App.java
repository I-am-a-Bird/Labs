package com.example.app;
import java.io.*;
import java.net.*;
import java.util.Scanner;
public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String clientMessage; 
        Socket socket = null;
        PrintWriter out = null;
        BufferedReader in=null;
        
        try {
            socket = new Socket("localhost", 3000);
            System.out.println("подключение к серверу");

            out = new PrintWriter(socket.getOutputStream(),true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            String message = in.readLine();
            System.out.println(message);
            
            clientMessage = scanner.nextLine();
            out.println(clientMessage);
            
            message=in.readLine();
            System.out.println(message);

            message=in.readLine();
            System.out.println(message);

            message=in.readLine();
            System.out.println(message);

            message=in.readLine();
            System.out.println(message);

            message=in.readLine();
            System.out.println(message);
        } catch (Exception e) {
            System.out.println("ошибка! "+ e.getMessage());
        }finally{
            try {
                if(in!=null) in.close();
                if(out!=null) out.close();
                if(socket!=null) socket.close();
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
    scanner.close();
    }
}


/*public
class SimpleClient {
    public static void main(String[] args) {
        Socket socket = null;
        BufferedReader in = null;
        
        try {
            // 1. Подключаемся к серверу
            socket = new Socket("localhost", 8080);
            System.out.println("Подключен к серверу!");
            
            // 2. Читаем сообщение от сервера
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message = in.readLine();
            
            // 3. Выводим сообщение
            System.out.println("Сервер сказал: " + message);
            
        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } finally {
            // 4. Закрываем ресурсы
            try {
                if (in != null) in.close();
                if (socket != null) socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}*/