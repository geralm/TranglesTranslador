/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.trianglestranslator.Key;

import java.util.HashMap;

/**
 *
 * @author Esteb
 */
public class CodeParser {
    private HashMap<String, Key> codesData;

    private HashMap<ECode, int[]> drawlinesData;
   
    private static CodeParser instance;
    
    private CodeParser(){
        codesData  = new HashMap<>();
        drawlinesData = new HashMap<>();
        init_codes();
        init_lineParser();
        instance = null;
    }
    
    public static CodeParser getInstance(){
        if(instance==null){
            instance = new CodeParser();
        }
        return instance;
    }
    /*
    *   
    *  @Return Key 
    */
    public Key parseCode(String k )throws NullPointerException{
        //Use this to get a Key and then get the code of the key to parse
        if(codesData.containsKey(k)){
            return  codesData.get(k);
        }else{
            throw new NullPointerException("The letter\""+k+"\"cannot be parse");
        }

    }
    public int [] parseDraw(ECode value){
        //Use this to get one line to draw
        return drawlinesData.get(value);
    } 
    
    private void init_lineParser(){
        drawlinesData.put(ECode.LEFT_UP, new int[] {10,30, 20, 15});
        drawlinesData.put(ECode.RIGHT_UP, new int[] {30,30, 20, 15});        
        drawlinesData.put(ECode.LEFT_DOWN, new int[] {10,30, 20, 45});
        drawlinesData.put(ECode.RIGHT_DOWN, new int[] {30,30, 20, 45});
        drawlinesData.put(ECode.CENTER, new int[] {10,30, 30, 30});
    }
   
    private void init_codes(){
        Key a = new Key().add(ECode.LEFT_UP).add(ECode.CENTER).add(ECode.LEFT_DOWN).add(ECode.RIGHT_DOWN);
        Key b = new Key().add(ECode.LEFT_UP).add(ECode.LEFT_DOWN);
        Key c = new Key().add(ECode.RIGHT_UP).add(ECode.RIGHT_DOWN);
        Key d = new Key().add(ECode.RIGHT_UP).add(ECode.CENTER).add(ECode.LEFT_DOWN).add(ECode.RIGHT_DOWN);
        Key e = new Key().add(ECode.LEFT_UP).add(ECode.CENTER).add(ECode.LEFT_DOWN).add(ECode.RIGHT_DOWN).add(ECode.RIGHT_UP);
        Key f= new Key().add(ECode.CENTER).add(ECode.LEFT_DOWN);
        Key g = new Key().add(ECode.LEFT_UP).add(ECode.LEFT_DOWN).add(ECode.CENTER);
        Key h = new Key().add(ECode.CENTER).add(ECode.RIGHT_UP).add(ECode.RIGHT_DOWN);
        Key i = new Key().add(ECode.LEFT_UP).add(ECode.LEFT_DOWN).add(ECode.RIGHT_DOWN);
        Key j = new Key().add(ECode.CENTER).add(ECode.RIGHT_DOWN);
        Key k = new Key().add(ECode.CENTER).add(ECode.LEFT_UP).add(ECode.RIGHT_UP);
        Key l = new Key().add(ECode.CENTER).add(ECode.RIGHT_UP).add(ECode.LEFT_DOWN);
        Key m = new Key().add(ECode.LEFT_DOWN).add(ECode.LEFT_UP).add(ECode.RIGHT_UP);
        Key n = new Key().add(ECode.LEFT_UP).add(ECode.LEFT_DOWN).add(ECode.RIGHT_UP).add(ECode.RIGHT_DOWN);
        Key o = new Key().add(ECode.CENTER).add(ECode.RIGHT_DOWN).add(ECode.LEFT_UP);
        Key p = new Key().add(ECode.RIGHT_UP).add(ECode.LEFT_DOWN);
        Key q = new Key().add(ECode.LEFT_DOWN).add(ECode.RIGHT_DOWN);
        Key r = new Key().add(ECode.CENTER).add(ECode.LEFT_DOWN).add(ECode.LEFT_UP).add(ECode.RIGHT_UP);
        Key s = new Key().add(ECode.RIGHT_UP).add(ECode.RIGHT_DOWN).add(ECode.LEFT_UP);
        Key t = new Key().add(ECode.CENTER).add(ECode.RIGHT_DOWN).add(ECode.RIGHT_UP).add(ECode.LEFT_UP);
        Key u = new Key().add(ECode.CENTER).add(ECode.LEFT_DOWN).add(ECode.RIGHT_DOWN);
        Key v = new Key().add(ECode.LEFT_DOWN).add(ECode.RIGHT_DOWN).add(ECode.RIGHT_UP);
        Key w = new Key().add(ECode.LEFT_UP).add(ECode.RIGHT_DOWN);
        Key x = new Key().add(ECode.LEFT_UP).add(ECode.RIGHT_UP);
        Key y = new Key().add(ECode.LEFT_UP).add(ECode.CENTER);
        Key z = new Key().add(ECode.CENTER).add(ECode.RIGHT_UP);
        Key coma = new Key().add(ECode.RIGHT_DOWN);
        Key punto = new Key().add(ECode.LEFT_DOWN);
        Key guion = new Key().add(ECode.CENTER);
        Key blank = new Key();


        codesData.put("a",a);
        codesData.put("b",b);
        codesData.put("c",c);
        codesData.put("d",d);
        codesData.put("e",e);
        codesData.put("f",f);
        codesData.put("g",g);
        codesData.put("h",h);
        codesData.put("i",i);
        codesData.put("j",j);
        codesData.put("k",k);
        codesData.put("l",l);
        codesData.put("m",m);
        codesData.put("n",n);
        codesData.put("o",o);
        codesData.put("p",p);
        codesData.put("q",q);
        codesData.put("r",r);
        codesData.put("s",s);
        codesData.put("t",t);
        codesData.put("u",u);
        codesData.put("v",v);
        codesData.put("w",w);
        codesData.put("x",x);
        codesData.put("x",x);
        codesData.put("y",y);
        codesData.put("z",z);
        codesData.put(",",coma);
        codesData.put(".",punto);
        codesData.put("-",guion);
        codesData.put(" ", blank);
        codesData.put("\n", blank);

    }
    
    
}
