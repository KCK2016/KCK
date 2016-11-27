package kck;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.Collator;
import java.util.*;

/**
 *
 * @author Adrian
 */
public class KCK {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        Tokenizer tokenizer = new Tokenizer();
        addtoken(tokenizer);
        proba1(tokenizer);

    }

    private static void addtoken(Tokenizer tokenizer) throws IOException {

        File token_txt = new File("dictionary.txt");
        List<String> token_txt_list = new LinkedList<>();
        token_txt_list = Files.readAllLines(token_txt.toPath());


        // sortowanie po dlufgosci pierwszego slowa z lini z pliku dictionary.txt
        token_txt_list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String[] text1 = o1.split(",");
                String[] text2 = o2.split(",");
                if(text1[0].length() > text2[0].length()) return -1;
                else return 1;
            }
        });


        for(String line: token_txt_list) {
            String[] line_split = line.trim().split(",");

            // sortowanie tokentow wewnatrz lini
            if (line_split.length>2) {
                for (int i = 2; i < line_split.length-1; i++) {
                    if (line_split[i].length() < line_split[i + 1].length()) {
                        String temp = line_split[i];
                        line_split[i] = line_split[i + 1];
                        line_split[i + 1] = temp;
                    }
                }
            }

            for (int counter = 2; counter<line_split.length; counter++){
                tokenizer.add(line_split[counter],Integer.parseInt(line_split[1]), line_split[0]);
            }
        }
        tokenizer.add(",", 12, ",");
        tokenizer.add("[a-zA-Z][a-zA-Z0-9_]*", 99,"[a-zA-Z][a-zA-Z0-9_]*");

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