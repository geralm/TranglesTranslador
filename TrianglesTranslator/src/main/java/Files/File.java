/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Files;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.util.Matrix;

/**
 *
 * @author Esteb
 */
public class File {
    public static final String filePath = ".//texto.txt";
    public static final String pdfName = "decode.pdf";
    private static File instance;
    private PDDocument document;
    private PDPage page;
    private PDPageContentStream contentStream;
    private File() throws IOException { 
        instance = null;
      
    }

    
    public static File getInstance() throws IOException{
        if(instance ==null){
            instance = new File();
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
    public void drawPdf(int[] pos) throws IOException{
        contentStream.moveTo(pos[0], page.getMediaBox().getHeight() - pos[1]);
        contentStream.lineTo(pos[2], page.getMediaBox().getHeight() - pos[3]);
        contentStream.stroke();
    }
    
    public void convertToPdf(String name, LinkedList<int[]> positions) throws IOException{
            page = new PDPage();
            
            System.out.println("Rotacion "+ page.getRotation());
            document= new PDDocument();
            
            document.addPage(page);
            contentStream = new PDPageContentStream(document, page);   
            for(int[] pos : positions){
                drawPdf(pos);
            }
            contentStream.close();
            document.save(name);
            document.close();
            System.out.println("El archivo PDF ha sido generado exitosamente...");
    }
    
}
