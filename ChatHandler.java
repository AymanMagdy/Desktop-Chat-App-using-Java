/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javachatapp;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author asoliman
 */
public class ChatHandler extends Thread {
    
    DataInputStream dis;
    PrintStream ps;
    Socket soc;
    BufferedReader bufferedReader;
    File fl = new File("sample.txt");
            
    static Vector<ChatHandler> clientsVector =
    new Vector<ChatHandler>();
    
    public ChatHandler(Socket cs) throws IOException{
        dis = new DataInputStream(cs.getInputStream());
        ps = new PrintStream(cs.getOutputStream());
        soc = cs;
        
        bufferedReader = new BufferedReader(new FileReader(fl));
        String str ;
        
        while((str = bufferedReader.readLine())!= null){
            ps.println(str);
        }
        
        clientsVector.add(this);
        start();
    }
    
    @Override
    public void run(){
        while(true){
            try{
                String str = dis.readLine();
                if(!str.isEmpty())
                    sendMessageToAll(str);
               
            } catch (Exception ex){
                try {
                    dis.close();
                    ps.close();
                    soc.close();
                } catch (IOException ex1) {
                    Logger.getLogger(ChatHandler.class.getName()).log(Level.SEVERE, null, ex1);
                }
                
                
            }
        }
    }
    
    void sendMessageToAll(String msg) throws IOException{
        
        FileWriter fileWriter = null;
        PrintWriter printWriter = null;
        
        
        try{
            fileWriter = new FileWriter("sample.txt", true);
            printWriter = new PrintWriter(fileWriter);
            printWriter.println();
            for(ChatHandler ch : clientsVector){
                ch.ps.println(msg);
                printWriter.println(msg);
            }
        } catch (IOException ex){
            ex.printStackTrace();
        } finally{ //Closing the resources
            try{
                printWriter.close();
                fileWriter.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }  
    }
}
