package kck;


import kck.order.OrderHandler;

import java.io.IOException;
import java.util.*;
import java.util.Random;

public class Parser
{

    static String s;
    static long seed;

    public Parser(String s)
    {
        this.s = s;
        this.seed = System.currentTimeMillis();
    }


    static String talk(Tokenizer tokenizer, OrderHandler orderHandler, MenuList menuList) throws IOException
    {
        String response = "";

        for (Tokenizer.Token tok : tokenizer.getTokens())
        {
            System.out.println(tok.sequence + " " + tok.boss + " " + tok.token);
        }

        Queue queue_token = new LinkedList();
        Queue queue_boss = new LinkedList();
        Queue queue_sequence = new LinkedList();

            for (Tokenizer.Token tok : tokenizer.getTokens())
            {
                queue_token.add(tok.token);
                queue_boss.add(tok.boss);
                queue_sequence.add(tok.sequence);
            }

            if (queue_token.size() >= 0)
            {
                response = response(queue_token, queue_boss, queue_sequence, orderHandler, menuList);
            }

            return response;
    }


    private static String response(Queue queue_token, Queue queue_boss, Queue queue_sequence, OrderHandler orderHandler, MenuList menuList) throws IOException
    {
        Tokens tokens = new Tokens();
        Boolean answer = false;

        int take_token_number = (int) (int) queue_token.element();
        String take_token_boss = (String) (String) queue_boss.element();
        String take_token_sequence = (String) (String) queue_sequence.element();

        //System.out.print(take_token_boss + " ");
        //System.out.print(take_token_sequence + " ");
        //System.out.print(take_token_number + " ");

        String odpowiedz_zwrotna = "";

        if (take_token_number == tokens.powitanie)
        {
            if (queue_token.size() > 1)
            {
                delete_token(queue_boss, queue_sequence, queue_token);
                return response(queue_token, queue_boss, queue_sequence, orderHandler, menuList);
            }
            else
            {
                odpowiedz_zwrotna = "Kelner: Dzień dobry";
                answer = true;
            }
        }

        else if (take_token_number == tokens.zamownienie)
        {
            delete_token(queue_boss, queue_sequence, queue_token);

            while (queue_token.size() > 0)
            {
                if (queue_token.element().equals(tokens.produkt))
                {
                    orderHandler.addToOrder(menuList.findProduct(queue_boss.element().toString()));
                    delete_token(queue_boss, queue_sequence, queue_token);
                }
                else if (queue_token.element().equals(tokens.niewiadomy))
                {
                    odpowiedz_zwrotna = checkproduct((String) queue_sequence.element());
                    delete_token(queue_boss, queue_sequence, queue_token);
                }
                else if (queue_token.element().equals(tokens.przecinek))
                {
                    delete_token(queue_boss, queue_sequence, queue_token);
                }
                else if (queue_token.element().equals(tokens.spojnik))
                {
                    delete_token(queue_boss, queue_sequence, queue_token);
                }
                else if (queue_token.element().equals(tokens.zamowic))
                {
                    delete_token(queue_boss, queue_sequence, queue_token);
                }
                else if (queue_token.element().equals(tokens.rachunek))
                {
                    odpowiedz_zwrotna = "Kelner: " + orderHandler.buildReceipt();
                    answer = true;
                    delete_token(queue_boss, queue_sequence, queue_token);
                }
                else if (queue_token.element().equals(tokens.zupa_dnia))
                {
                    orderHandler.addToOrder(menuList.findProduct(dishOfTheDay("zupy")));
                    answer = true;
                    delete_token(queue_boss, queue_sequence, queue_token);
                }
                else if (queue_token.element().equals(tokens.danie_glowne_dnia))
                {
                    orderHandler.addToOrder(menuList.findProduct(dishOfTheDay("dania główne")));
                    answer = true;
                    delete_token(queue_boss, queue_sequence, queue_token);
                }
                else if (queue_token.element().equals(tokens.grupa))
                {
                    odpowiedz_zwrotna = listof(queue_boss.element());
                    answer = true;
                    delete_token(queue_boss, queue_sequence, queue_token);
                }

            }

        }

        else if (take_token_number == tokens.zamowic)
        {
                delete_token(queue_boss, queue_sequence, queue_token);
                return response(queue_token, queue_boss, queue_sequence, orderHandler, menuList);
        }

        else if (take_token_number == tokens.produkt)
        {
            orderHandler.addToOrder(menuList.findProduct(queue_boss.element().toString()));
            delete_token(queue_boss, queue_sequence, queue_token);
            if (queue_token.size() > 0)
            {
                return response(queue_token, queue_boss, queue_sequence, orderHandler, menuList);
            }
        }

        else if (take_token_number == tokens.rachunek)
        {
            odpowiedz_zwrotna = "Kelner: " + orderHandler.buildReceipt();
            answer = true;
            delete_token(queue_boss, queue_sequence, queue_token);
        }

        else if (take_token_number == tokens.zapytanie)                                                         // które produkty
        {
            delete_token(queue_boss, queue_sequence, queue_token);

            if (queue_token.size() == 0) niezrozumialem();

            else if ((queue_token.size() > 0) && queue_token.element().equals(tokens.zawiera))                       // zawierają
            {
                delete_token(queue_boss, queue_sequence, queue_token);
                if ((queue_token.size() > 0) && queue_token.element().equals(tokens.skladnik_do_zapytania))     // skladnik
                {
                    String ingredient = (String) queue_boss.element();
                    //System.out.print(ingredient);
                    odpowiedz_zwrotna = containsProduct(ingredient);
                    delete_token(queue_boss, queue_sequence, queue_token);
                    answer = true;
                }
                else if ((queue_token.size() > 0) && queue_token.element().equals(tokens.danie_dnia))
                {
                    return response(queue_token, queue_boss, queue_sequence, orderHandler, menuList);
                }
                else if ((queue_token.size() > 0) && queue_token.element().equals(tokens.zupa_dnia))
                {
                    delete_token(queue_boss, queue_sequence, queue_token);
                    odpowiedz_zwrotna = dishOfTheDay("zupy");
                    answer = true;
                }
                else if ((queue_token.size() > 0) && queue_token.element().equals(tokens.danie_glowne_dnia))
                {
                    delete_token(queue_boss, queue_sequence, queue_token);
                    odpowiedz_zwrotna = dishOfTheDay("dania główne");
                    answer = true;
                }
                else
                {
                    odpowiedz_zwrotna = niezrozumialem();
                    answer = true;
                }
            }

            else if ((queue_token.size() > 0) && queue_token.element().equals(tokens.skladnik_do_zapytania))
            {
                String ingredient = (String) queue_boss.element();
                //System.out.print(ingredient);
                odpowiedz_zwrotna = containsProduct(ingredient);
                answer = true;
                delete_token(queue_boss, queue_sequence, queue_token);
            }

            else if ((queue_token.size() > 0) && queue_token.element().equals((tokens.nie)))                        // nie
            {
                delete_token(queue_boss, queue_sequence, queue_token);
                if ((queue_token.size() > 0) && queue_token.element().equals(tokens.zawiera))                       // zawierają
                {
                    delete_token(queue_boss, queue_sequence, queue_token);
                    if ((queue_token.size() > 0) && queue_token.element().equals(tokens.skladnik_do_zapytania))     // składnika
                    {
                            String ingredient = (String) queue_boss.element();
                            //System.out.print(ingredient);
                            odpowiedz_zwrotna = notcontainsProduct(ingredient);
                            delete_token(queue_boss, queue_sequence, queue_token);
                            answer = true;
                    }
                    else
                    {
                            odpowiedz_zwrotna = niezrozumialem();
                            answer = true;
                    }
                }

                else if ((queue_token.size() > 0) && queue_token.element().equals(tokens.skladnik_do_zapytania))
                {
                        String ingredient = (String) queue_boss.element();
                        //System.out.print(ingredient);
                        odpowiedz_zwrotna = notcontainsProduct(ingredient);
                        answer = true;
                        delete_token(queue_boss, queue_sequence, queue_token);
                }
            }

            else if ((queue_token.size() > 0) && queue_token.element().equals(tokens.grupa))                        // która grupa produktów z menu
            {
                String grupa = (String) queue_boss.element();
                delete_token(queue_boss, queue_sequence, queue_token);
                if ((queue_token.size() > 0) && queue_token.element().equals(tokens.zawiera))                       // zawiera
                {
                    delete_token(queue_boss, queue_sequence, queue_token);
                    if ((queue_token.size() > 0) && queue_token.element().equals(tokens.skladnik_do_zapytania))     // skladnik
                    {
                        String ingredient = (String) queue_boss.element();
                        //System.out.print(ingredient);
                        odpowiedz_zwrotna = groupcontainsProduct(grupa, ingredient);
                        delete_token(queue_boss, queue_sequence, queue_token);
                        answer = true;
                    }
                    else
                    {
                        odpowiedz_zwrotna = niezrozumialem();
                        answer = true;
                    }
                }

                else if ((queue_token.size() > 0) && queue_token.element().equals((tokens.nie)))                    // nie
                {
                    delete_token(queue_boss, queue_sequence, queue_token);
                    if ((queue_token.size() > 0) && queue_token.element().equals(tokens.zawiera))                   // zawiera
                    {
                        delete_token(queue_boss, queue_sequence, queue_token);
                        if ((queue_token.size() > 0) && queue_token.element().equals(tokens.skladnik_do_zapytania)) // skladnika
                        {
                            String ingredient = (String) queue_boss.element();
                            //System.out.print(ingredient);
                            odpowiedz_zwrotna = groupnotcontainsProduct(grupa, ingredient);
                            delete_token(queue_boss, queue_sequence, queue_token);
                            answer = true;
                        }
                        else
                        {
                            odpowiedz_zwrotna = niezrozumialem();
                            answer = true;
                        }
                    }
                    else
                    {
                        odpowiedz_zwrotna = niezrozumialem();
                        answer = true;
                    }
                }
            }

           else if ((queue_token.size() > 0) && queue_token.element().equals(tokens.danie_dnia))
            {
                    odpowiedz_zwrotna = "Zupa to: " + dishOfTheDay("zupy") + ",danie główne to: " + dishOfTheDay("dania główne");
                    delete_token(queue_boss, queue_sequence, queue_token);
                    answer = true;
            }
            else
            {
                odpowiedz_zwrotna = niezrozumialem();
            }
        }

        else if (take_token_number == tokens.czy)                                       // czy
        {
                delete_token(queue_boss, queue_sequence, queue_token);
                if ((queue_token.size() > 0) && queue_token.element().equals(tokens.produkt))                           // produk z menu
                {
                    String produkt = (String) queue_boss.element();
                    delete_token(queue_boss, queue_sequence, queue_token);
                    if ((queue_token.size() > 0) && queue_token.element().equals(tokens.zawiera))                       // zawiera
                    {
                        delete_token(queue_boss, queue_sequence, queue_token);
                        if ((queue_token.size() > 0) && queue_token.element().equals(tokens.skladnik_do_zapytania))     // skladnik
                        {
                            String ingredient = (String) queue_boss.element();
                            //System.out.print(ingredient);
                            odpowiedz_zwrotna = doescontains(produkt, ingredient);
                            delete_token(queue_boss, queue_sequence, queue_token);
                            answer = true;
                        }
                        else if ((queue_token.size() > 0) && queue_token.element().equals(tokens.niewiadomy))     // skladnik
                        {
                            String ingredient = (String) queue_sequence.element();
                            //System.out.print(ingredient);
                            odpowiedz_zwrotna = doescontains(produkt, ingredient);
                            delete_token(queue_boss, queue_sequence, queue_token);
                            answer = true;
                        }
                        else
                        {
                            odpowiedz_zwrotna = niezrozumialem();
                            answer = true;
                        }
                    }
                    else
                    {
                        odpowiedz_zwrotna = niezrozumialem();
                        answer = true;
                    }
                }
                else
                {
                    odpowiedz_zwrotna = niezrozumialem();
                    answer = true;
                }
        }

        else if ((queue_token.size() > 0) && queue_token.element().equals(tokens.przecinek))
        {
            delete_token(queue_boss, queue_sequence, queue_token);
            return response(queue_token, queue_boss, queue_sequence, orderHandler, menuList);
        }

        else if ((queue_token.size() > 0) && queue_token.element().equals(tokens.danie_dnia))
        {
            odpowiedz_zwrotna = "Zupa to: " + dishOfTheDay("zupy") + ",danie główne to: " + dishOfTheDay("dania główne");
            delete_token(queue_boss, queue_sequence, queue_token);
            answer = true;
        }

        else if (queue_token.element().equals(tokens.niewiadomy))
        {
            odpowiedz_zwrotna = checkproduct((String) queue_sequence.element());
            delete_token(queue_boss, queue_sequence, queue_token);
        }


        else if (!answer)
        {
                odpowiedz_zwrotna = niezrozumialem();
                answer = true;
        }

        else
        {
                odpowiedz_zwrotna = niezrozumialem();
                answer = true;
        }

        return odpowiedz_zwrotna;
    }

    private static String listof(Object element) throws IOException
    {
        MenuList menuList = new MenuList();
        LinkedList lista = new LinkedList();
        for (MenuList.Menu tok : menuList.getMenu())
        {
            if (tok.group.equals(element))
            {
                lista.add(tok.product);
            }
        }

        String list = lista.toString();

        String odpowiedz = "Mamy: " + list.substring(1,list.length()-1);
        return odpowiedz;
    }


    private static String checkproduct(String produkt_nieznany) throws IOException
    {
        MenuList menuList = new MenuList();
        Tokenizer tokenizer = new Tokenizer();
        Vector tabelapodobnych = new Vector();
        int i = 0;
        Boolean found = false;
        String odpowiedz = "";

        for (Tokenizer.TokenInfo tok : tokenizer.getTokensInfo())
        {
            if(tok.token == 10)
            {

                found = tok.regex.toString().contains(produkt_nieznany);
                //System.out.println(tok.regex);

                if (found)
                {
                    tabelapodobnych.add(tok.boss);
                }
            }
        }

        removeDuplicatesProducts(tabelapodobnych);

        if (tabelapodobnych.size() > 0)
        {
            String lista = tabelapodobnych.toString();
            odpowiedz = "Mamy: " + lista.substring(1,lista.length()-1);
        }
        else
        {
            odpowiedz = "Nie mamy " + produkt_nieznany + " w karcie. Proszę wybrać coś z menu.";
        }

        return odpowiedz;
    }

    private static void removeDuplicatesProducts(Vector tabelapodobnych)
    {
        for(int i = 0; i < tabelapodobnych.size(); i++)
        {
            for(int j = 0; j < tabelapodobnych.size(); j++)
            {
                if(i != j)
                {
                    if ( tabelapodobnych.elementAt(i).equals(tabelapodobnych.elementAt(j)) )
                    {
                        tabelapodobnych.removeElementAt(j);
                    }
                }
            }
        }
    }

    private static String niezrozumialem()
    {
        String odpowiedz = "Kelner: Nie zrozuamialem. Proszę inaczej sformułować zdanie.";
        return odpowiedz;
    }

    public static String dishOfTheDay(String group) throws IOException
    {

        MenuList menuList = new MenuList();

        LinkedList <String> listOf;
        listOf = new LinkedList <String>();

        //System.out.println(group);
        for (MenuList.Menu tok : menuList.getMenu())
        {
            if (tok.group.equals(group) == true)
            {
                int i = 0;
                listOf.add(i, tok.getProduct());
                i = i + 1;
            }
        }
        /// kom


        Random random = new Random(seed);
        int index = random.nextInt(listOf.size());
        String item = listOf.get(index);
        return item;
    }

    private static String doescontains(String product, String ingredient) throws IOException
    {
        MenuList menuList = new MenuList();
        String odpowiedz = "";
        for (MenuList.Menu tok : menuList.getMenu())
        {
            if (tok.product.equals(product))
            {
                String s = Arrays.toString(tok.comment);
                Boolean found = s.contains(ingredient);
                if (found)
                {
                    odpowiedz = product + " zawiera składnik " + ingredient;
                }
                else
                {
                    odpowiedz = product + " nie zawiera składnika " + ingredient;
                }

            }
        }

        return odpowiedz;
    }

    private static String groupnotcontainsProduct(String grupa, String ingredient) throws IOException
    {
        MenuList menuList = new MenuList();
        LinkedList grouptnotcontainsProduct = new LinkedList();
        //System.out.println(grupa + ", które nie zawierają składnika " + ingredient + ": ");
        for (MenuList.Menu tok : menuList.getMenu())
        {
            if (tok.group.equals(grupa))
            {
                String s = Arrays.toString(tok.comment);
                Boolean found = s.contains(ingredient);
                if (found == false)
                {
                    grouptnotcontainsProduct.add(tok.product);
                }
            }
        }

        String lista = grouptnotcontainsProduct.toString();
        lista = lista.substring(1,lista.length()-1);

        String odpowiedz = grupa + ", które nie zawierają składnika " + ingredient + ": " + lista;

        return odpowiedz;
    }

    private static String groupcontainsProduct(String group, String ingredient) throws IOException
    {
        MenuList menuList = new MenuList();
        LinkedList groupcontainsProduct = new LinkedList();

        System.out.println(group + ", które zawierają " + ingredient + ": ");
        for (MenuList.Menu tok : menuList.getMenu())
        {
            if (tok.group.equals(group))
            {
                String s = Arrays.toString(tok.comment);
                Boolean found = s.contains(ingredient);
                if (found)
                {
                    groupcontainsProduct.add(tok.product);
                }
            }
        }

        String lista = groupcontainsProduct.toString();
        lista = lista.substring(1,lista.length()-1);

        String odpowiedz = group + ", które zawierają składnik " + ingredient + ": " + lista;

        return odpowiedz;

    }

    private static String notcontainsProduct(String ingredient) throws IOException
    {
        MenuList menuList = new MenuList();
        LinkedList notcontainsProduct = new LinkedList<>();

        //System.out.println("Produkty" + ", które nie zawierają " + ingredient + ": ");
        for (MenuList.Menu tok : menuList.getMenu())
        {
                String s = Arrays.toString(tok.comment);
                Boolean found = s.contains(ingredient);
                if (found == false)
                {
                    notcontainsProduct.add(tok.product);
                }
        }

        String lista = notcontainsProduct.toString();
        lista = lista.substring(1,lista.length()-1);

        String odpowiedz = "Produkty" + ", które nie zawierają " + ingredient + ": " + lista;

        return odpowiedz;

    }

    public static String containsProduct(String ingredient) throws IOException
    {
        MenuList menuList = new MenuList();
        LinkedList containsProduct = new LinkedList<>();
        System.out.println("Produkty, które zawierają " + ingredient + ": ");
        for (MenuList.Menu tok : menuList.getMenu())
        {
            String s = Arrays.toString(tok.comment);
            Boolean found = s.contains(ingredient);
            if (found)
            {
                containsProduct.add(tok.product);
            }
        }

        String lista = containsProduct.toString();
        lista = lista.substring(1,lista.length()-1);

        String odpowiedz = "Produkty" + ", które nie zawierają " + ingredient + ": " + lista;

        return odpowiedz;
    }

    private static void delete_token(Queue queue_boss, Queue queue_sequence, Queue queue_token)
    {
        if (queue_token.size() > 0)
        {
            queue_boss.remove();
            queue_sequence.remove();
            queue_token.remove();
        }
    }
}
