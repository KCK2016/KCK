package kck;

import kck.order.OrderHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;


public class KCK {
    static String s;
    static long seed;

    public KCK(String s) {
        this.s = s;
        this.seed = System.currentTimeMillis();
    }

    public static void main(String[] args) throws IOException {

        Tokenizer tokenizer = new Tokenizer();
        MenuList menuList = new MenuList();

        // Karta dań
        for (MenuList.Menu tok : menuList.getMenu()) {
            System.out.println(tok.group + ": " + tok.product + " " + tok.price + " zł." + " " + Arrays.toString(tok.comment));
        }
        System.out.println(" ");


        // Tokeny, reakcje
        for (Tokenizer.TokenInfo tok : tokenizer.getTokensInfo()) {
            System.out.println(tok.regex + " " + tok.boss + " " + tok.token);
        }
        System.out.println(" ");

        // Danie dnia
        dishOfTheDay("Zupy");
        dishOfTheDay("Dania główne");
        System.out.println(" ");

        // Dania, które zawierają
        String group = "Dania główne";
        String ingredient = "mięso";
        containsProduct(group, ingredient);
        System.out.println(" ");

        // Dania, które nie zawierają
        group = "Zupy";
        notcontainsProduct(group, ingredient);
        System.out.println(" ");

        // Czy danie zawiera?
        String product = "szarlotka";
        ingredient = "jabłka";
        doescontains(product, ingredient);
        System.out.println(" ");

        // Proba
        proba1(tokenizer);
        System.out.println(" ");

    }

    private static void doescontains(String product, String ingredient) throws IOException {
        MenuList menuList = new MenuList();
        for (MenuList.Menu tok : menuList.getMenu()) {
            if (tok.product.equals(product)) {
                String s = Arrays.toString(tok.comment);
                Boolean found = s.contains(ingredient);
                if (found) {
                    System.out.println(product + " zawiera składnik " + ingredient);
                } else {
                    System.out.println(product + " nie zawiera składnika " + ingredient);
                }

            }
        }


    }

    private static void notcontainsProduct(String group, String ingredient) throws IOException
    {
        MenuList menuList = new MenuList();

        System.out.println(group + ",które nie zawierają " + ingredient + ": ");
        for (MenuList.Menu tok : menuList.getMenu())
        {
            if (tok.group.equals(group))
            {
                String s = Arrays.toString(tok.comment);
                Boolean found = s.contains(ingredient);
                if (found == false)
                {
                    System.out.println("- " + tok.product);
                }
            }
        }

    }

    private static void containsProduct(String group, String ingredient) throws IOException
    {
        MenuList menuList = new MenuList();

        System.out.println(group + ", które zawierają " + ingredient + ": ");
        for (MenuList.Menu tok : menuList.getMenu())
        {
            if (tok.group.equals(group))
            {
                String s = Arrays.toString(tok.comment);
                Boolean found = s.contains(ingredient);
                if (found)
                {
                    System.out.println("- " + tok.product);
                }
            }
        }
    }

    public void containsProduct(String ingredient) throws IOException
    {
        MenuList menuList = new MenuList();

        System.out.println("Dania, które zawierają " + ingredient + ": ");
        for (MenuList.Menu tok : menuList.getMenu())
        {
                String s = Arrays.toString(tok.comment);
                Boolean found = s.contains(ingredient);
                if (found)
                {
                    System.out.println("- " + tok.product);
                }
        }
    }

    public static void dishOfTheDay(String group) throws IOException
    {
        MenuList menuList = new MenuList();

        LinkedList<String> listOf;
        listOf = new LinkedList<String>();

        //System.out.println(group);
        for (MenuList.Menu tok : menuList.getMenu())
        {
            if (tok.group.equals(group) == true)
            {
                int i = 0;
                listOf.add(i, tok.getProduct());
                i = i + 1;
                // po co ta iteracja?
            }
        }
        Random random = new Random(seed);
        int index = random.nextInt(listOf.size());
        String item = listOf.get(index);
        System.out.println(group+" to: " + item);
    }

    public static String returndishOfTheDay(String group) throws IOException
    {
        MenuList menuList = new MenuList();

        LinkedList<String> listOf;
        listOf = new LinkedList<String>();

        //System.out.println(group);
        for (MenuList.Menu tok : menuList.getMenu())
        {
            if (tok.group.equals(group) == true)
            {
                int i = 0;
                listOf.add(i, tok.getProduct());
                i = i + 1;
                // po co ta iteracja?
            }
        }
        Random random = new Random(seed);
        int index = random.nextInt(listOf.size());
        String item = listOf.get(index);
       return  item;
    }


    private static void proba1(Tokenizer tokenizer) throws IOException
    {

        String s = "";
//        String s = "Dzień dobry";
//        System.out.println("Klient: " + s);

//        s = s.toLowerCase();
//        tokenizer.tokenize(s);
//        tokenizer.checkproduct();

        Waiter waiter = new Waiter();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Kelner: Dzień dobry. Czy moge przyjąć zamówinie?");
        while(!s.equals("Do widzenia")|!s.equals("do widzenia")) {
            s = scanner.nextLine();
            tokenizer.tokenize(s.toLowerCase());
            System.out.println(waiter.talk(tokenizer));
        }

        /*
        System.out.println("Klient zamówił: ");
        for (Tokenizer.Token tok : tokenizer.getTokens()) {
            if (tok.token == 10) {
                System.out.println(" - " + tok.boss);
            }
        }
        */

    }
}