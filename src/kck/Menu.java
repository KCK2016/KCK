/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kck;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author s152483
 */

public class Menu {
            
        String przystawki;
	String zupy;
	String drugie_dania;
	String desery;
	String napoje_zimne;
	String napoje_cieple;
	String alkohole;
	
        String[] Tab_przystawki;
        String[] Tab_zupy;
        String[] Tab_drugie_dania;
        String[] Tab_desery;
        String[] Tab_napoje_zimne;
        String[] Tab_napoje_cieple;
        String[] Tab_alkohole;


	void pobieranie_karty_dan() throws IOException
	{
            try ( 
                //String args1 = "baza.txt";
                    FileReader fileReader = new FileReader("baza.txt")
                    ) {
                BufferedReader bufferReader = new BufferedReader(fileReader);
                
                while (bufferReader.read() != -1)
                {
                    przystawki = bufferReader.readLine();
                    zupy = bufferReader.readLine();
                    drugie_dania = bufferReader.readLine();
                    desery = bufferReader.readLine();
                    napoje_zimne = bufferReader.readLine();
                    napoje_cieple = bufferReader.readLine();
                    alkohole = bufferReader.readLine();
                    
                }
            }	
	}
        
        public void dania_jako_tokeny()
        {
                    Tab_przystawki = przystawki.split(",");
                    
                    for (String token : Tab_przystawki)
                    {
                        System.out.println(token);
                    }
                    
                    
                    Tab_zupy = zupy.split(",");
                    Tab_drugie_dania = drugie_dania.split(",");
                    Tab_zupy = zupy.split(",");
                    Tab_desery = desery.split(",");
                    Tab_napoje_zimne = napoje_zimne.split(",");
                    Tab_napoje_cieple = napoje_cieple.split(",");
                    Tab_alkohole = alkohole.split(",");
                    
        }

            
    
}
