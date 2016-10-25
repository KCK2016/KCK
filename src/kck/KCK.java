package kck;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Dictionary;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Adrian
 */
public class KCK {

    /**
     * @param args the command line arguments
     */


    static String[] inputParse(String input) throws IOException {
        DictionaryInput dictionaryInput = new DictionaryInput() {};
        List<String> dictionaryList = dictionaryInput.loadDictionary();

        //zamiana stringa na tablicę slow
        String[] inputTab;
        inputTab = input.trim().toLowerCase().split("[ ,.-]+");

        //TreeMap dzieki której zmienimy słowa odmienione na nieodmienione
        Map<String, Integer> dicMap = dictionaryInput.dictionaryMap(dictionaryList);
        // petla do zamiany slow na wartosci z Treemap

        for (int i=0; i<inputTab.length; i++) {
            if(dicMap.containsKey(inputTab[i]) ) {
                int key = dicMap.get(inputTab[i]);
                inputTab[i] = Integer.toString(key);
            }}
        return inputTab;
    }




    public static void main(String[] args) throws IOException {
        

        
        //Testowy string input
        String any;
        any = "prosze o Cześć do picia, oraz Saładkę. Dziękuję." ;

        String[] list = inputParse(any);
        for (String a: list) System.out.println(a);
        
        
        
        
       
    }
    
}
