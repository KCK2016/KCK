package kck;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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
        for(String line: token_txt_list) {
            String[] line_split = line.trim().split(",");
            for (int counter = 2; counter<line_split.length; counter++){
                tokenizer.add(line_split[counter],Integer.parseInt(line_split[1]), line_split[0]);
            }
        }
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