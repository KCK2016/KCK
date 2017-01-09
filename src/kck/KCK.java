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


    public static void main(String[] args) throws IOException
    {

        Tokenizer tokenizer = new Tokenizer();
        MenuList menuList = new MenuList();
/*
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
*/
        mojafunkcja(tokenizer);


    }

    private static void mojafunkcja(Tokenizer tokenizer) throws IOException
    {
        String s = "";
        OrderHandler orderHandler = new OrderHandler();
        MenuList menuList = new MenuList();
        Scanner scanner = new Scanner(System.in);
        while(!s.equals("Do widzenia")|!s.equals("do widzenia"))
        {
            s = scanner.nextLine();
            try {
                tokenizer.tokenize(s.toLowerCase());
            }
            catch (Exception e)
            {
                System.out.println("Nie rozumiem.");
            }
            System.out.println(Parser.talk(tokenizer, orderHandler, menuList));
        }
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
        OrderHandler orderHandler = new OrderHandler();
        MenuList menuList = new MenuList();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Kelner: Dzień dobry. Czy moge przyjąć zamówinie?");
        while(!s.equals("Do widzenia")|!s.equals("do widzenia")) {
            s = scanner.nextLine();
            tokenizer.tokenize(s.toLowerCase());
            System.out.println(waiter.talk(tokenizer, orderHandler, menuList));
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