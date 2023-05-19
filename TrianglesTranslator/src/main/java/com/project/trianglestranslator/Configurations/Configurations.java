/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.trianglestranslator.Configurations;

/**
 *
 * @author Esteb
 */
public class Configurations {
    private float SCALE_fACTOR = 1f;
    
    private boolean jumpLines = true ;
    private  boolean verifyWordSpace = true;
    private  int limitInLine  = 22;

    private static  Configurations instance;

    private Configurations() {
        instance = null;
    }
    public static Configurations getInstance(){
        if(instance == null){
            instance = new Configurations();
        }
        return instance;
    }

    public int getLimitInLine() {
        return limitInLine;
    }

    public float getSCALE_fACTOR() {
        return SCALE_fACTOR;
    }

    public void setSCALE_fACTOR(float SCALE_fACTOR) {
        this.SCALE_fACTOR = SCALE_fACTOR;
        calcCharsInLine(SCALE_fACTOR);
    }
    private void calcCharsInLine(float tammfuente){
         limitInLine = (int)( 18 * (1.5 / tammfuente))-1;

    }

    public boolean isJumpLines() {
        return jumpLines;
    }

    public void setJumpLines(boolean jumpLines) {
        this.jumpLines = jumpLines;
    }

    public boolean isVerifyWordSpace() {
        return verifyWordSpace;
    }

    public void setVerifyWordSpace(boolean verifyWordSpace) {
        this.verifyWordSpace = verifyWordSpace;
    }
}
