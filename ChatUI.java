/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javachatapp;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author asoliman
 */
public class ChatUI extends JFrame{
    
    private JTextArea ta;
    private JScrollPane scroll;
    private JTextField tf;
    private JButton okButton;
    
    public ChatUI(){
        
        this.setLayout(new FlowLayout());
        ta = new JTextArea(6,50);
        scroll = new JScrollPane(ta);
        scroll.setViewportView(ta);
        tf = new JTextField(30);
        okButton = new JButton("Send");
        okButton.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent ae){
            // take the input and store in a file.
            
        }
        });
        add(scroll);
        add(tf);
        add(okButton);
    }
    
    public JTextArea getJTextArea(){
        return ta;
    }
    
    public JTextField getTextField(){
        return tf;
    }
    
    public JButton getButton(){
        return okButton;
    }
    
}
