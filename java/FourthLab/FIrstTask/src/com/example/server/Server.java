package com.example.server;
import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        ServerSocket server = null;
        Socket client = null;
        PrintWriter out = null;
        BufferedReader in = null;
        
        try {
            server = new ServerSocket(3000);
            System.out.println("сервер запущен");
            client = server.accept();
            System.out.println("клиент подключился");

            out = new PrintWriter(client.getOutputStream(),true);
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            out.println("отправь мне строку и я скажу сколько в ней гласных и согласных букв!");
            
            
            String clientMessage = in.readLine();
            System.out.println(clientMessage);
            clientMessage=clientMessage.toLowerCase();

            int CountOfVowels=0;
            int CountOfConsontants=0;
            int CountofSpecSymbols=0;
            String vowels="аоуэыяеёюиaeiouy";
            String consonants = "бвгджзйклмнпрстфхцчшщъьbcdfghjklmnpqrstvwxz";
            String specSymbols=".,!&?;:0123456789@#$%^*()";
            for (int i = 0; i < clientMessage.length(); i++) {
            char ch = clientMessage.charAt(i);  
            if (vowels.indexOf(ch) != -1) CountOfVowels++;
            else if(specSymbols.indexOf(ch)!=-1) CountofSpecSymbols++;
            else if(consonants.indexOf(ch)!=-1) CountOfConsontants++;
        }       
        out.println("в вашей строке \""+clientMessage+"\" следующее количество символов:\n");
        out.println("гласные:"+CountOfVowels);
        out.println("согласные:"+CountOfConsontants);
        out.println("спец-символы:"+CountofSpecSymbols);
        

        } catch (Exception e) {
            System.out.println("Ошибка! "+ e.getMessage());
        }
        finally{
            try {
            if(out!=null) out.close();
            if(client!=null) client.close();
            if(server!=null) server.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}


/*Разработать приложение на основе TCP-соединения, позволяющее осу-
ществлять взаимодействие клиента и сервера по совместному решению задач

обработки информации. Приложение должно располагать возможностью пере-
дачи и модифицирования получаемых (передаваемых) данных. Предусмотреть

работу консольного приложения с файлом или файлами. Варианты реализации:
а) вести на сервере log-файл
б) исходная информация хранится в файле file1.txt, модифицированная
информация записывается в файл file2.txt.*/

/*Разработать приложение-счетчик букв. На клиентской части вводится

строка и передается серверу, а тот в свою очередь осуществляет подсчет глас-
ных и согласных букв и возвращает этот результат клиенту.*/