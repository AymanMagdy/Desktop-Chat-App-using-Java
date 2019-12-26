/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javachatapp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author asoliman
 */
public class Client {
    
    Socket socket;
    DataInputStream dis;
    PrintStream ps;
    ChatUI ui;
    FileInputStream file;
    
    public static void main(String args[]){
        new Client();
    }
    
  
    public Client(){
        guiCreator();
        connect();
        BtnEvent();
        
    }
    
    
    public void guiCreator(){
        ui =new ChatUI();
        ui.setSize(400, 500);
        ui.setVisible(true);
    }
    
    
    public void connect(){
        try{
            socket = new Socket("127.0.0.1", 5300);
            dis = new DataInputStream(socket.getInputStream());
            ps = new PrintStream(socket.getOutputStream());
            TextAreaThread();
        } catch (IOException ex){
            ex.printStackTrace();
            
        }
    }
    
    
    public void BtnEvent(){
        ui.getButton().addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                FileWriter fileWriter = null;
                PrintWriter printWriter = null;
                BufferedReader bufferedReader=null;
                
                try{
                    fileWriter = new FileWriter("sample.txt", true);
                    printWriter = new PrintWriter(fileWriter);
                    printWriter.println();
                    String message = ui.getTextField().getText();
                    ui.getTextField().setText("");
                    printWriter.println(message);
                    ps.println(message);                    
                } catch (Exception ex){
                    ex.printStackTrace();
                }
                
                
                /*String message = ui.getTextField().getText();
                ps.println(message);
                ui.getTextField().setText("");*/
            }
        });
    }
    
    public void TextAreaThread(){
        new Thread(new Runnable() {
            
            @Override
            public void run() {
                try{
                    while(true){
                        String message = dis.readLine();
                        
                        if(message != null)
                            ui.getJTextArea().append(message + "\n");
                    }
                } catch (Exception ex){
                    ex.printStackTrace();
                    try{
                        
                        dis.close();
                        ps.close();
                        socket.close();
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                    
                }
            }
        }).start();
        
        
    }
    
}
