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
            for (int counter = 2; counter<line_split.length; counter++){
                tokenizer.add(line_split[counter],Integer.parseInt(line_split[1]), line_split[0]);
            }
        }
        tokenizer.add(",", 12, ",");
        tokenizer.add("[a-zA-Z][a-zA-Z0-9_]*", 99,"[a-zA-Z][a-zA-Z0-9_]*");

    }
    private static List<String> sort(List<String> list) {
        List<String> out_list = new LinkedList<>();
        String longest;
        int lenght;
        for (int i=0; i<list.size(); i++){
            String line = list.get(i);
            String[] words = line.split(",");
            longest = line;
            lenght = words[0].length();

            for(int a=i+1; a<list.size(); a++) {
                String line2 = list.get(a);
                String[] words2 = line2.split(",");
                if(words2[0].length() > lenght) {
                    longest = line2;
                    lenght = words2[0].length();
                }
            }
            out_list.add(longest);
            System.out.println(out_list.size());
        }
        for(String cos: out_list) System.out.println(cos);
        return out_list;
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