package com.project.trianglestranslator.Key;

import com.project.trianglestranslator.Configurations.Configurations;

import java.text.Normalizer;
import java.util.LinkedList;

public class TextCode {
    private int cursor;
    private int lettersCounter;
    private int line;
    private String text;
    private LinkedList<int[]> keysPositions;
    private LinkedList<Key> keys;

    private static TextCode instance;

    private TextCode() {
        this.cursor = 1;
        lettersCounter = 0 ;
        line  = 1;
        this.text = "";
        keysPositions = new LinkedList<>();
        keys = new LinkedList<>();
    }
    public static TextCode getInstance(){
        if(instance==null){
            instance = new TextCode();
        }
        return instance;
    }
    private void addLetter(String letter) throws NullPointerException{
        this.text+=letter;
        if(letter.equals("\n") && Configurations.getInstance().isJumpLines()){ nextLine();}
        else{
            Key key = CodeParser.getInstance().parseCode(letter);
            for(ECode value : key.getCode()){
                int[] parser = CodeParser.getInstance().parseDraw(value); //Get default form of the triangle
                int[] positions = setLetterPosition(parser); //Get the correct position of the triangle in text
                keysPositions.add(positions); //Each position is a char
            }
            if(cursor>Configurations.getInstance().getLimitInLine()){nextLine();}
            else{cursor++;}
            lettersCounter++;
        }
    }
    private void clean(){
        this.cursor = 1;
        lettersCounter = 0 ;
        line  = 1;
        keysPositions = new LinkedList<>();
        keys = new LinkedList<>();
    }
    public void parseText(String text)throws NullPointerException{
        String letter = "";
        clean();
        text = convertString(text);
        String[] words = text.split(" ");
        for(String word: words){ //Add every words
            wrapText(word);

            for(int i = 0; i<word.length(); i++){ //add word
                letter = String.valueOf(word.charAt(i)).toLowerCase();
                addLetter(letter);
            }
            addLetter(" "); //add blank space
        }
        System.out.println("Text parsed sucessfully!");
        System.out.println("Letters: "+lettersCounter);
        System.out.println("Words: "+words.length);
        System.out.println("-----------------------------------");

    }
    private void wrapText(String word){
        int lineLimit = Configurations.getInstance().getLimitInLine();
        if(word.length()+cursor >lineLimit && Configurations.getInstance().isVerifyWordSpace()){ //IF limit is larger needs a new line
            nextLine();
            System.out.println("");
        }
    }

    private String convertString(String text){
        // Remover acentos
        String textoSinAcentos = Normalizer.normalize(text, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

        // Convertir a minúsculas
        String textoMinisculas = textoSinAcentos.toLowerCase();

        return textoMinisculas;
    }


    private void nextLine(){line++; cursor=1;}
    private int[] setLetterPosition(int[] parser){
        int sx, sy, fx , fy; //Start x start y final x final y;
        sx = parser[0]+(20*cursor);
        sy = parser[1]+(30*line);
        fx = parser[2]+(20*cursor);
        fy = parser[3]+(30*line);
        return new int[] {sx, sy, fx, fy};

    }

    public LinkedList<int[]> getKeysPositions() {
        System.out.println("Keys positions: "+keysPositions.size());
        return keysPositions;
    }
}
