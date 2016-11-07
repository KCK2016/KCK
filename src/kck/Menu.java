/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kck;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author s152483
 */

public class Menu {
            
    String snap;
	String soups;
	String secondDishes;
	String dessert;
	String coldDrinks;
	String hotDrinks;
	String alcohol;
	
        String[] snapArray;
        String[] soupsArray;
        String[] secondDishesArray;
        String[] dessertArray;
        String[] coldDrinksArray;
        String[] hotDrinksArray;
        String[] alcoholArray;


	void getMenus() throws IOException
	{
            try ( 
                //String args1 = "baza.txt";
                    FileReader fileReader = new FileReader("baza.txt")
                    ) {
                BufferedReader bufferReader = new BufferedReader(fileReader);
                
                while (bufferReader.read() != -1)
                {
                    snap = bufferReader.readLine();
                    soups = bufferReader.readLine();
                    secondDishes = bufferReader.readLine();
                    dessert = bufferReader.readLine();
                    coldDrinks = bufferReader.readLine();
                    hotDrinks = bufferReader.readLine();
                    alcohol = bufferReader.readLine();
                    
                }
            }	
	}
        
        public void splitMenuIntoArray()
        {
                    snapArray = snap.split(",");
                    
                    for (String token : snapArray)
                    {
                        System.out.println(token);
                    }
                    
                    
                    soupsArray = soups.split(",");
                    secondDishesArray = secondDishes.split(",");
                    soupsArray = soups.split(",");
                    dessertArray = dessert.split(",");
                    coldDrinksArray = coldDrinks.split(",");
                    hotDrinksArray = hotDrinks.split(",");
                    alcoholArray = alcohol.split(",");
                    
        }

            
    
}
