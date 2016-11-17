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

        /*

        DictionaryInput dic = new DictionaryInput() {};
        List<String> dictionary = dic.loadDictionary();
        
        //Testowy string input
        String any;
        any = "Dzień dobry, proszę  o coś do picia, oraz Saładkę. Dzięuję." ;
        String[] list = any.trim().split("[ ,.-]+");
        
        
        //TreeMap dzieki której zmienimy słowa odmienione na nieodmienione
        
        Map<String, Integer> dicMap = dic.dictionaryMap(dictionary);
        
        
        //cokolwiek



        // pętla do zamiany słów na wartości z Treemap
        
        for (int i=0; i<list.length; i++) {
           if(dicMap.containsKey(list[i]) ) {
               int key = dicMap.get(list[i]);
                 list[i] = Integer.toString(key);
           }}
        
        // Stworzyć kolejny plik .txt albo strukture danych w kótrych będą zawarte reakce na dane słowa lub zestawy słów
        
        for (String a: list) System.out.println(a);

        */

        Tokenizer tokenizer = new Tokenizer();
        addtoken(tokenizer);
        proba1(tokenizer);


    }

    private static void addtoken(Tokenizer tokenizer) {

        tokenizer.add("Dzień dobry", 0, "Dzień dobry");                 // przywitanie (ignore)
        tokenizer.add("Witam", 0, "Witam");

        tokenizer.add("Chcę", 1, "Chcę");                               // składanie zamowienia osoba
        tokenizer.add("Chciałbym", 1, "Chciałbym");
        tokenizer.add("Chciałabym", 1, "Chciałabym");
        tokenizer.add("Proszę", 1, "Proszę");
        tokenizer.add("Poproszę", 1, "Poproszę");
        tokenizer.add("Poprosimy", 1, "Poprosimy");

        tokenizer.add("Chcielibyśmy", 2, "Chcielibyśmy");               // składanie zamowienia (cały stolik)
        tokenizer.add("Chcemy", 2, "Chcemy");
        tokenizer.add("Prosimy", 2, "Prosimy");
        tokenizer.add("Poprosimy", 2, "Poprosimy");

        tokenizer.add("[.]", 4, "[.]");                                 // Kropka

        tokenizer.add("kotleta", 10, "kotlet");                         //Dania
        tokenizer.add("kotlet", 10, "kotlet");

        tokenizer.add("ziemniaki", 10, "ziemniaki");
        tokenizer.add("ziemniakami", 10, "ziemniaki");

        tokenizer.add("surówka z marchewki", 10, "surówka z marchewki");
        tokenizer.add("surówkę z marchewki", 10, "surówka z marchewki");
        tokenizer.add("surówką z marchewki", 10, "surówka z marchewki");

        tokenizer.add("wodę", 10, "woda");

        tokenizer.add("i", 12, "i");                                    // spójniki i znaki łączące
        tokenizer.add("oraz", 12, "oraz");
        tokenizer.add("z", 12, "z");
        tokenizer.add(",", 12, ",");
    }


    private static void proba1(Tokenizer tokenizer) {

        String s = "Dzień dobry. Poproszę kotleta z ziemniakami, wodę oraz surówkę z marchewki. ";

        tokenizer.tokenize(s);

        System.out.println(s);

        System.out.println("Klient zamówił: ");
        for (Tokenizer.Token tok : tokenizer.getTokens()) {
            if (tok.token == 10) {
                System.out.println(" - " + tok.boss);
            }
        }
    }

}