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
    public static void main(String[] args) throws IOException {
        
        Dictionaty dic = new Dictionaty() {};
        List<String> dictionary = dic.loadDictionary();
        
        //Testowy string input
        String any;
        any = "chcę o Cześć do picia, oraz Saładkę. Dzięuję." ; 
        String[] list = any.trim().split("[ ,.-]+");
        
        
        //TreeMap dzieki której zmienimy słowa odmienione na nieodmienione
        
        Map<String, Integer> dicMap = dic.dictionaryMap(dictionary);
        
        
        
        // pętla do zamiany słów na wartości z Treemap
        
        for (int i=0; i<list.length; i++) {
           if(dicMap.containsKey(list[i]) ) {
               int key = dicMap.get(list[i]);
                 list[i] = Integer.toString(key);
           }}
        
        // Stworzyć kolejny plik .txt albo strukture danych w kótrych będą zawarte reakce na dane słowa lub zestawy słów
        
        for (String a: list) System.out.println(a);
        
        
        
        
       
    }
    
}
