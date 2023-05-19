/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.trianglestranslator.Key;

import java.util.LinkedList;

/**
 *
 * @author Esteb
 */
public class Key {
    private LinkedList<ECode> code;
    public Key() {
        code = new LinkedList<>();
    }
    public Key add(ECode value){
       code.add(value);
       return this;
    }
    public void remove(ECode value){
        code.remove(value);
    }  
    public LinkedList<ECode> getCode() {
        return code;
    }
    
 
}
