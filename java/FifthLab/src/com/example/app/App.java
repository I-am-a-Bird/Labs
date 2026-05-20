package com.example.app;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class App extends JFrame {
    
    public App() {
        super("Пробное окно");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        
        JButton myButton = new JButton("Кнопка");
        getContentPane().add(myButton);  
    }
    
    public static void main(String[] args) {
        App testWindow = new App();
        testWindow.setVisible(true);
    }
}