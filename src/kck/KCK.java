package kck;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;


public class KCK {
   static String s;
    public KCK(String s){
        this.s = s;
    }
    public static void main(String[] args) throws IOException {

        Tokenizer tokenizer = new Tokenizer();
        MenuList menuList = new MenuList();

        for (MenuList.Menu tok : menuList.getMenu()) {
            System.out.println(tok.group +  ": " + tok.product + " " + tok.price + " " + tok.comment);
        }


        /*
        for (Tokenizer.TokenInfo tok : tokenizer.getTokensInfo()) {
            System.out.println(tok.regex + " " + tok.boss + " " + tok.token);
        }


        proba1(tokenizer);
        */
        dishOfTheDay("Zupy");
        dishOfTheDay("Dania główne");


    }

    private static void dishOfTheDay(String group) throws IOException
    {

        MenuList menuList = new MenuList();

        LinkedList <String> listOf;
        listOf = new LinkedList < String >() ;

        System.out.println(group);
        for (MenuList.Menu tok : menuList.getMenu())
        {
            if (group.equals(tok.group))
            {
                int i = 0;
                listOf.add(i, tok.getProduct());
                i = i + 1;

            }
        }

        Random random= new Random();
        int index = random.nextInt(listOf.size());
        String item = listOf.get(index);
        System.out.println(item);

    }


    private static void proba1(Tokenizer tokenizer)
    {
        String s = "Dzień dobry. Poproszę mozzarella z pomidorami i schabowego z ziemniakami i wodę gazowaną. ";
        s=s.toLowerCase();
        tokenizer.tokenize(s);

        System.out.println("Klient zamówił: ");
        for (Tokenizer.Token tok : tokenizer.getTokens())
        {
          if (tok.token == 10)
          {
                System.out.println(" - " + tok.boss);
          }
        }
    }

}