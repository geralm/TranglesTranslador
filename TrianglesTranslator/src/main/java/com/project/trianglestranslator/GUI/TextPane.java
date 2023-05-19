/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.trianglestranslator.GUI;

import com.project.trianglestranslator.Configurations.Configurations;
import com.project.trianglestranslator.Key.TextCode;
import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.lang.module.Configuration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author Esteb
 */
public class TextPane extends JPanel{

    public TextPane(JPanel parent) {
        this.setPreferredSize(new Dimension(750,parent.getHeight() ));

    }


    @Override
    public void paint(Graphics graphics){
        Graphics2D g = (Graphics2D) graphics ;
        g.setStroke(new BasicStroke((float) 1.5));
        System.out.println("Painted");
        for(int[] pos : TextCode.getInstance().getKeysPositions()){
            try {

                drawLine(pos, g);
            } catch (IOException ex) {
                Logger.getLogger(TextPane.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    public void drawLine(int[] pos, Graphics2D g) throws IOException{
        float scaleFactor = Configurations.getInstance().getSCALE_fACTOR();
        g.drawLine((int) (pos[0] * scaleFactor), (int) (pos[1]* scaleFactor), (int) (pos[2]* scaleFactor), (int) (pos[3]* scaleFactor));
    }

}

