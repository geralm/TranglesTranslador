/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.trianglestranslator.Files;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import com.project.trianglestranslator.Configurations.Configurations;
import com.project.trianglestranslator.Key.TextCode;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

/**
 *
 * @author Esteb
 */
public class FileManagement {
    public static final String filePath = ".//texto.txt";
    public static final String pdfName = "decode.pdf";
    private static FileManagement instance;
    private PDDocument document;
    private PDPage page;
    private PDPageContentStream contentStream;
    private FileManagement() throws IOException {
        instance = null;
      
    }

    
    public static FileManagement getInstance() throws IOException{
        if(instance ==null){
            instance = new FileManagement();
        }
        return instance;
    }
    
    
    
    public String readTextFile(String filePath) throws FileNotFoundException, IOException{
        StringBuilder content = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            content.append(line).append("\n");
        }
        return content.toString();
    }
    private void drawPdf(int[] pos) throws IOException{
        float scaleFactor = Configurations.getInstance().getSCALE_fACTOR();
      
        contentStream.moveTo(pos[0] * scaleFactor, page.getMediaBox().getHeight() - pos[1] * scaleFactor);
        contentStream.lineTo(pos[2] * scaleFactor, page.getMediaBox().getHeight() - pos[3] * scaleFactor);
        contentStream.stroke();
    }
    
    public void convertToPdf(String name) throws IOException{
            page = new PDPage();
            
            document= new PDDocument();
            
            document.addPage(page);
            contentStream = new PDPageContentStream(document, page);
            LinkedList<int[]> positions =  TextCode.getInstance().getKeysPositions();
            for(int[] pos : positions){
                drawPdf(pos);
            }
            contentStream.close();
            document.save(name);
            document.close();
            System.out.println("El archivo PDF ha sido generado exitosamente...");
    }
    
}
