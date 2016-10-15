/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kck;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Adrian
 */
public class Dictionaty {
    
    /**
     *
     * @throws java.io.IOException
     */
    public List<String> loadDictionary() throws IOException {
        Files file;
        Path path = Paths.get("dictionary.txt");
        if (Files.exists(path)) {
        List<String> list = Files.readAllLines(path);
        //for (String a: list) System.out.println(a);
        return list;
        }
        else System.out.println("Zła ścieżka");
        return null;
    }
    
    public Map<String, Integer> dictionaryMap(List<String> list){
        Map<String, Integer> map = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        int counter = 1;
        for (String a : list) {
            String[] tab = a.trim().split(";");
            for (int i=0; i < tab.length; i++) map.put(tab[i], counter);
            counter ++;
        }
        
        return map;
    }
    
    
}
