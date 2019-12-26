/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javachatapp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author asoliman
 */
public class Server {
    
    ServerSocket serverSocket;
    
    public static void main(String args[]) throws IOException{
        new Server();
    }
    
    public Server() {
        
        try{
            serverSocket = new ServerSocket(5300);
            while(true) {
                Socket s = serverSocket.accept();
                new ChatHandler(s);
            }
        } catch (IOException ex){
            ex.printStackTrace();
            try{
                
            } catch (Exception e){
                e.printStackTrace();
            }
            
        }
        
    }
}
