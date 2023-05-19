/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Files.File;
import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Esteb
 */
public class MainFrame extends JFrame{
    String text = "";
    TextPane jpanelText;
    public MainFrame( ) {
        initFile();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 500);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        jpanelText = new TextPane(text);
        this.add(jpanelText);
        this.pack();
        this.setBackground(Color.white);
       
        //String nombArch = JOptionPane.showInputDialog(null, "Digite el nombre del archivo pdf");
        
    }
    public void initFile(){
        try {
           text = File.getInstance().readTextFile(File.filePath);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener el texto");
        }
    }
    
    
    
}
