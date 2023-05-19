/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Configurations.Configurations;
import Files.File;
import Key.Code;
import Key.ECode;
import Key.Key;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

/**
 *
 * @author Esteb
 */
public class TextPane extends JPanel{
    private int cursor = 1;
    private int lettersCounter = 0;
    private int line = 1;
    private int size = 20;
    private boolean isPainted = false;
    
    private String text;
    private LinkedList<int[]> allTextParsed;
    
    public TextPane(String text) {
        this.setPreferredSize(new Dimension(500, 900));
        this.text = text;
        allTextParsed = new LinkedList<>();
    }
    
    public void writeLetter(String letter, Graphics2D g)throws NullPointerException, IOException{
        if(letter.equals("\n") && Configurations.jumpLines){ nextLine();}
        else{
            Key key = Code.getInstance().parseCode(letter);
            for(ECode value: key.getCode()){
                int [] parser = Code.getInstance().parseDraw(value);
                int [] positions = setLetterPosition(parser);
                allTextParsed.add(positions);
                g.drawLine(positions[0],positions[1], positions[2],positions[3]);
            }
            if(cursor>Configurations.limitInLine){nextLine();}
            else{cursor++;}
            lettersCounter++;
        }
        
    }
    
    private void nextLine(){line++; cursor=1;};
    private int[] setLetterPosition(int[] parser){
        int sx, sy, fx , fy; //Start x start y final x final y; 
        sx = parser[0]+(20*cursor);
        sy = parser[1]+(30*line); 
        fx = parser[2]+(20*cursor);
        fy = parser[3]+(30*line);
        return new int[] {sx, sy, fx, fy};
    
    }
    @Override
    public void paint(Graphics graphics){
        Graphics2D g = (Graphics2D) graphics ;
        g.setStroke(new BasicStroke((float) 1.5));
        if(!isPainted){
            String l ="";
            try {  
                String[] words = text.split(" ");
                for(String word : words){
                   if(word.length()+cursor > Configurations.limitInLine && Configurations.verifyWordSpace){ //IF limit is larger needs a new line
                       nextLine();
                       System.out.println("");
                   }
                   for(int i = 0; i<word.length(); i++){
                        l = String.valueOf(word.charAt(i)).toLowerCase();
                        System.out.print(l);
                        writeLetter(l, g); 
                    }
                    writeLetter(" ", g); //Antoher word
                    System.out.print(" ");


                }
                System.out.println("-----------------------------------");
                System.out.println("Letras: "+lettersCounter);

            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(null, "Error la letra "+l+" no puede ser parseada");
            } catch (IOException ex) {
               JOptionPane.showMessageDialog(null,"Error al escribir en pdf->"+ex);
                System.out.println(ex);
            }
            isPainted = true;
             try {
            File.getInstance().convertToPdf(File.pdfName, allTextParsed);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null,"Error al guardar el pdf->"+ex);
            }
        }else{
            for(int[] pos :allTextParsed){
                try {
                    drawLine(pos, g);
                } catch (IOException ex) {
                    Logger.getLogger(TextPane.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    public void drawLine(int[] pos, Graphics2D g) throws IOException{
        g.drawLine(pos[0], pos[1], pos[2], pos[3]);
    }
    
    public LinkedList<int[]> getAllTextParsed() {
        return allTextParsed;
    }
   
    
    
    
}

