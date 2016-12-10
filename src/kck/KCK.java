package kck;

import java.io.IOException;
import java.util.Arrays;
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

        // Karta dań
        for (MenuList.Menu tok : menuList.getMenu()) {
            System.out.println(tok.group +  ": " + tok.product + " " + tok.price+ " zł." + " " + Arrays.toString(tok.comment));
        }
        System.out.println(" ");


        // Tokeny, reakcje
        for (Tokenizer.TokenInfo tok : tokenizer.getTokensInfo()) {
            System.out.println(tok.regex + " " + tok.boss + " " + tok.token);
        }
        System.out.println(" ");

        // Proba
        proba1(tokenizer);
        System.out.println(" ");

        // Danie dnia
        dishOfTheDay("Zupy");
        dishOfTheDay("Dania główne");
        System.out.println(" ");

        // Danie, które zawiera
        String produkt = "mięso";
        containsProduct(produkt);
        System.out.println(" ");

        // Danie, które nie zawiera
        notcontainsProdukt(produkt);
        System.out.println(" ");


    }

    private static void notcontainsProdukt(String produkt) throws IOException
    {
        MenuList menuList = new MenuList();

        System.out.println("Dania nie zawierające " + produkt + ": ");
        for (MenuList.Menu tok : menuList.getMenu())
        {
            String s = Arrays.toString(tok.comment);
            Boolean found = s.contains(produkt);
            if(found == false){
                System.out.println("- " + tok.product);
            }
        }

    }

    private static void containsProduct(String produkt) throws IOException
    {
        MenuList menuList = new MenuList();

        System.out.println("Dania zawierające " + produkt + ": ");
        for (MenuList.Menu tok : menuList.getMenu())
        {
            String s = Arrays.toString(tok.comment);
            Boolean found = s.contains(produkt);
            if(found){
                System.out.println("- " + tok.product);
            }
        }
    }

    private static void dishOfTheDay(String group) throws IOException
    {

        MenuList menuList = new MenuList();

        LinkedList <String> listOf;
        listOf = new LinkedList < String >() ;

        //System.out.println(group);
        for (MenuList.Menu tok : menuList.getMenu())
        {
            if (tok.group.equals(group)== true)
            {
                int i = 0;
                listOf.add(i, tok.getProduct());
                i = i + 1;
            }

        }


        Random random= new Random();
        int index = random.nextInt(listOf.size());
        String item = listOf.get(index);
        System.out.println("Danie dnia to: " + item);

    }


    private static void proba1(Tokenizer tokenizer)
    {
        String s = "Dzień dobry. Poproszę mozzarella z pomidorami i schabowego z ziemniakami i wodę. ";
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