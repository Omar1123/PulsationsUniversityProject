/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lastfile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author root
 */
public class LastFile {

    private File LASTONE = new File("/root/NetBeansProjects/lastFile/src/lastfile/Time.txt");
    
    private int DEFAULT = 5;
    private int MAX = 158;
    private int MIN = 46;
    
    private int counter = 0;
    
    private Timer timer = new Timer();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
           LastFile lastFile = new LastFile();
           lastFile.run();
        } catch(Exception ex) {
            
        }
    }
    
    private void run() {
        System.out.println("Corriendo");
        firstTime();
    }
    
    private void firstTime() {                        
        writeToLogFile();   
        stopwatch();
    }
       
    private void stopwatch() {
        
        timer.schedule(new TimerTask() {
            @Override
            public void run() {  
                
                counter++;
                
                if(counter > 20) {
                    System.out.println("Deteniendo...");
                    timer.cancel();
                } else {
                    System.out.println(getDate() + " " + getTime());
                    writePulsations(pulsations(MAX,MIN), getTime());
                    stopwatch();                   
                }                                
            }
        }, 15*1000);                                
    }
    
    private void writeToLogFile() {            
        try {
                   
            BufferedWriter translate =new BufferedWriter(new OutputStreamWriter(new FileOutputStream(LASTONE, true), "utf-8"));            
            
            translate.write(getDate() + "," + getTime() + "," + DEFAULT + "\r\n");                    
            translate.close();                                  
        } catch (Exception ex) {
          //Captura un posible error le imprime en pantalla 
            System.out.println(ex.getMessage());
        }     
    }
    
    private void writePulsations(String pulsations, String time) {
        try {
                   
            BufferedWriter translate =new BufferedWriter(new OutputStreamWriter(new FileOutputStream(LASTONE, true), "utf-8"));            
            
            translate.write(pulsations + "," + time + "\r\n");                    
            translate.close();                                  
        } catch (Exception ex) {
          //Captura un posible error le imprime en pantalla 
            System.out.println(ex.getMessage());
        }    
    }
    
    private String getDate() {
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();        
        
        return dateFormat.format(date).toString();
    }

    private String getTime() {
        
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        
        return dateFormat.format(date).toString();            
    }
    
    private String pulsations(int max, int min) {
        
        Random rand = new Random();         
        int value = rand.nextInt((max - min) + 1) + min;                                
        
        return Integer.toString(value);
    }
}
