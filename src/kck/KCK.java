package kck;

import java.io.IOException;


public class KCK {
   static String s;
    public KCK(String s){
        this.s = s;
    }

    public static void main(String[] args) throws IOException {

        Tokenizer tokenizer = new Tokenizer();
        MenuList menuList = new MenuList();
        for (MenuList.Menu tok : menuList.getMenu()) {
            System.out.println(tok.product);
        }
        for (Tokenizer.TokenInfo tok : tokenizer.getTokensInfo()) {
            System.out.println(tok.regex + tok.boss);
        }
        proba1(tokenizer);

    }


    private static void proba1(Tokenizer tokenizer) {

       // String s = "Dzień dobry. Poproszę kotleta z ziemniakami, wodę oraz surówkę z marchewki. ";
        s=s.toLowerCase();
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