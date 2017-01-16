package kck;


import kck.order.OrderHandler;

import java.io.IOException;
import java.util.*;
import java.util.Random;
import java.util.stream.Collectors;

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
                response = response(tokenizer, orderHandler, menuList);
            }

            return response;
    }



    private static String response(Tokenizer tokenizer, OrderHandler orderHandler, MenuList menuList) throws IOException
    {
        //String zdanie = tokenizer.getTokens().toString().substring(1,tokenizer.getTokens().toString().length()-1);
        //zdanie = zdanie.replace(",", "");
        String zdanie = "";
        for (Tokenizer.Token tok : tokenizer.getTokens())
        {
            zdanie = zdanie + tok.token + " ";
        }
        zdanie = zdanie.substring(0,zdanie.length()-1);

        System.out.println(zdanie);

        Tokens tokens = new Tokens();

        String dopytaj = "";

        Boolean przywitanie = zdanie.equals(String.valueOf(tokens.powitanie)) && (zdanie.length() == 1);

        Boolean danie_dnia = zdanie.equals
        (String.valueOf(tokens.danie_dnia)) && (zdanie.length() == 1) || zdanie.equals
        (tokens.zapytanie +" "+ + tokens.danie_dnia);

        Boolean zupa_dnia = zdanie.equals
        (String.valueOf(tokens.zupa_dnia)) && (zdanie.length() == 2)|| zdanie.equals
        (tokens.zapytanie +" "+ + tokens.zupa_dnia);

        Boolean danie_glowne_dnia = zdanie.equals
        (String.valueOf(tokens.danie_glowne_dnia)) && (zdanie.length() == 2) || zdanie.equals
        (tokens.zapytanie +" "+ + tokens.danie_glowne_dnia);;

        Boolean zamowienie_produktu = zdanie.equals
        (tokens.powitanie +" "+ tokens.przecinek +" "+ tokens.zamownienie +" "+ tokens.produkt) || zdanie.equals
        (tokens.powitanie +" "+ tokens.przecinek +" "+ tokens.zamownienie +" "+ tokens.zamowic  +" "+ tokens.produkt) || zdanie.equals
        (tokens.powitanie +" "+ tokens.zamownienie +" "+ tokens.produkt)  || zdanie.equals
        (tokens.powitanie +" "+ tokens.przecinek +" "+ tokens.zamownienie +" "+ tokens.produkt)  || zdanie.equals
        (tokens.zamownienie +" "+ tokens.produkt)  || zdanie.equals
        (tokens.zamownienie +" "+ tokens.zamowic +" "+ tokens.produkt);

        Boolean zamowienie_dania_dnia = zdanie.equals
        (tokens.powitanie +" "+ tokens.przecinek +" "+ tokens.zamownienie +" "+ tokens.danie_dnia) || zdanie.equals
        (tokens.powitanie +" "+ tokens.przecinek +" "+ tokens.zamownienie +" "+ tokens.zamowic  +" "+ tokens.danie_dnia) || zdanie.equals
        (tokens.powitanie +" "+ tokens.zamownienie +" "+ tokens.danie_dnia)  || zdanie.equals
        (tokens.powitanie +" "+ tokens.przecinek +" "+ tokens.zamownienie +" "+ tokens.danie_dnia)  || zdanie.equals
        (tokens.zamownienie +" "+ tokens.danie_dnia)  || zdanie.equals
        (tokens.zamownienie +" "+ tokens.zamowic +" "+ tokens.danie_dnia);

        Boolean zamowienie_zupy_dnia = zdanie.equals
        (tokens.powitanie +" "+ tokens.przecinek +" "+ tokens.zamownienie +" "+ tokens.zupa_dnia) || zdanie.equals
        (tokens.powitanie +" "+ tokens.przecinek +" "+ tokens.zamownienie +" "+ tokens.zamowic  +" "+ tokens.zupa_dnia) || zdanie.equals
        (tokens.powitanie +" "+ tokens.zamownienie +" "+ tokens.zupa_dnia)  || zdanie.equals
        (tokens.powitanie +" "+ tokens.przecinek +" "+ tokens.zamownienie +" "+ tokens.zupa_dnia)  || zdanie.equals
        (tokens.zamownienie +" "+ tokens.zupa_dnia)  || zdanie.equals
        (tokens.zamownienie +" "+ tokens.zamowic +" "+ tokens.zupa_dnia);

        Boolean zamowienie_dania_glownego_dnia = zdanie.equals
        (tokens.powitanie +" "+ tokens.przecinek +" "+ tokens.zamownienie +" "+ tokens.danie_glowne_dnia) || zdanie.equals
        (tokens.powitanie +" "+ tokens.przecinek +" "+ tokens.zamownienie +" "+ tokens.zamowic  +" "+ tokens.danie_glowne_dnia) || zdanie.equals
        (tokens.powitanie +" "+ tokens.zamownienie +" "+ tokens.danie_glowne_dnia)  || zdanie.equals
        (tokens.powitanie +" "+ tokens.przecinek +" "+ tokens.zamownienie +" "+ tokens.danie_glowne_dnia)  || zdanie.equals
        (tokens.zamownienie +" "+ tokens.danie_glowne_dnia)  || zdanie.equals
        (tokens.zamownienie +" "+ tokens.zamowic +" "+ tokens.danie_glowne_dnia);

        Boolean zamowienie_grupy = zdanie.equals
        (tokens.powitanie +" "+ tokens.przecinek +" "+ tokens.zamownienie +" "+ tokens.grupa) || zdanie.equals
        (tokens.powitanie +" "+ tokens.przecinek +" "+ tokens.zamownienie +" "+ tokens.zamowic  +" "+ tokens.grupa) || zdanie.equals
        (tokens.powitanie +" "+ tokens.zamownienie +" "+ tokens.grupa)  || zdanie.equals
        (tokens.powitanie +" "+ tokens.przecinek +" "+ tokens.przecinek +" "+ tokens.zamownienie +" "+ tokens.produkt)  || zdanie.equals
        (tokens.zamownienie +" "+ tokens.grupa)  || zdanie.equals
        (tokens.zamownienie +" "+ tokens.zamowic +" "+ tokens.grupa);

        Boolean zamowienie_nieprecyzyjne = zdanie.equals
        (tokens.powitanie +" "+ tokens.przecinek +" "+ tokens.zamownienie +" "+ tokens.niewiadomy) || zdanie.equals
        (tokens.powitanie +" "+ tokens.przecinek +" "+ tokens.zamownienie +" "+ tokens.zamowic  +" "+ tokens.niewiadomy) || zdanie.equals
        (tokens.powitanie +" "+ tokens.zamownienie +" "+ tokens.niewiadomy)  || zdanie.equals
        (tokens.powitanie +" "+ tokens.przecinek +" "+ tokens.zamownienie +" "+ tokens.niewiadomy)  || zdanie.equals
        (tokens.zamownienie +" "+ tokens.niewiadomy)  || zdanie.equals
        (tokens.zamownienie +" "+ tokens.zamowic +" "+ tokens.niewiadomy);

        Boolean zapytanie_czy_produkt_zawiera = zdanie.equals
        (tokens.czy +" "+ tokens.produkt +" "+ tokens.zawiera +" " + tokens.skladnik_do_zapytania) || zdanie.equals
        (tokens.czy +" "+ tokens.produkt +" "+ tokens.zawiera +" " + tokens.niewiadomy)  || zdanie.equals
        (tokens.powitanie +" "+ tokens.czy +" "+ tokens.produkt +" "+ tokens.zawiera +" " + tokens.skladnik_do_zapytania) || zdanie.equals
        (tokens.powitanie +" "+ tokens.czy +" "+ tokens.produkt +" "+ tokens.zawiera +" " + tokens.niewiadomy);

        Boolean ktore_dania_zawierają = zdanie.equals
        (tokens.zapytanie +" "+ tokens.zawiera +" "+ tokens.skladnik_do_zapytania ) || zdanie.equals
        (tokens.zapytanie +" "+ tokens.zawiera +" "+ tokens.niewiadomy ) || zdanie.equals
        (tokens.powitanie +" "+ tokens.zapytanie +" "+ tokens.zawiera +" "+ tokens.skladnik_do_zapytania ) || zdanie.equals
        (tokens.powitanie +" "+ tokens.zapytanie +" "+ tokens.zawiera +" "+ tokens.niewiadomy );

        Boolean ktore_grupy_zawierają = zdanie.equals
        (tokens.zapytanie +" "+ tokens.grupa +" "+ tokens.zawiera +" "+ tokens.skladnik_do_zapytania ) || zdanie.equals
        (tokens.zapytanie +" "+ tokens.grupa +" "+ tokens.zawiera +" "+ tokens.niewiadomy ) || zdanie.equals
        (tokens.powitanie +" "+ tokens.zapytanie +" "+ tokens.grupa +" "+ tokens.zawiera +" "+ tokens.skladnik_do_zapytania ) || zdanie.equals
        (tokens.powitanie +" "+ tokens.zapytanie +" "+ tokens.grupa +" "+ tokens.zawiera +" "+ tokens.niewiadomy );

        Boolean ktore_grupy_nie_zawierają = zdanie.equals
        (tokens.zapytanie +" "+ tokens.grupa +" "+ tokens.nie +" "+ tokens.zawiera +" "+ tokens.skladnik_do_zapytania ) || zdanie.equals
        (tokens.zapytanie +" "+ tokens.grupa +" "+ tokens.nie +" "+ tokens.zawiera +" "+ tokens.niewiadomy ) || zdanie.equals
        (tokens.powitanie +" "+ tokens.zapytanie +" "+ tokens.grupa +" "+ tokens.nie +" "+ tokens.zawiera +" "+ tokens.skladnik_do_zapytania ) || zdanie.equals
        (tokens.powitanie +" "+ tokens.zapytanie +" "+ tokens.grupa +" "+ tokens.nie +" "+ tokens.zawiera +" "+ tokens.niewiadomy );


        Boolean zamowienie_grupy_ktore_nie_zawierają = zdanie.equals
        (tokens.zamownienie +" "+ tokens.grupa +" "+ tokens.przecinek +" "+ tokens.zapytanie +" "+ tokens.nie +" "+ tokens.zawiera +" "+ tokens.skladnik_do_zapytania ) || zdanie.equals
        (tokens.zamownienie +" "+ tokens.grupa +" "+ tokens.przecinek +" "+ tokens.zapytanie +" "+ tokens.nie +" "+ tokens.zawiera +" "+ tokens.niewiadomy ) || zdanie.equals
        (tokens.powitanie +" "+ tokens.zamownienie +" "+ tokens.grupa +" "+ tokens.przecinek +" "+ tokens.zapytanie +" "+ tokens.nie +" "+ tokens.zawiera +" "+ tokens.skladnik_do_zapytania ) || zdanie.equals
        (tokens.powitanie +" "+ tokens.zamownienie +" "+ tokens.grupa +" "+ tokens.przecinek +" "+ tokens.zapytanie +" "+ tokens.nie +" "+ tokens.zawiera +" "+ tokens.niewiadomy ) || zdanie.equals
        (tokens.zamownienie +" "+ tokens.zamowic +" "+ tokens.grupa +" "+ tokens.przecinek +" "+ tokens.zapytanie +" "+ tokens.nie +" "+ tokens.zawiera +" "+ tokens.skladnik_do_zapytania ) || zdanie.equals
        (tokens.zamownienie +" "+ tokens.zamowic +" "+ tokens.grupa +" "+ tokens.przecinek +" "+ tokens.zapytanie +" "+ tokens.nie +" "+ tokens.zawiera +" "+ tokens.niewiadomy ) || zdanie.equals
        (tokens.powitanie +" "+ tokens.zamownienie +" "+ tokens.zamowic +" "+ tokens.grupa +" "+ tokens.przecinek +" "+ tokens.zapytanie +" "+ tokens.nie +" "+ tokens.zawiera +" "+ tokens.skladnik_do_zapytania ) || zdanie.equals
        (tokens.powitanie +" "+ tokens.zamownienie +" "+ tokens.zamowic +" "+ tokens.grupa +" "+ tokens.przecinek +" "+ tokens.zapytanie +" "+ tokens.nie +" "+ tokens.zawiera +" "+ tokens.niewiadomy );


        Boolean wino_do_danie_glowne = zdanie.equals
        (tokens.zapytanie +" "+ tokens.wino +" "+ tokens.pasuje +" "+ tokens.produkt);

        Boolean prosba_o_rachunek = zdanie.equals
        (tokens.zamownienie +" "+ tokens.rachunek) || zdanie.equals
        (tokens.rachunek +" "+ tokens.zamownienie) || (zdanie.equals
        (String.valueOf(tokens.rachunek)) && zdanie.length() == 1);


        System.out.println("przywitanie" + przywitanie);
        System.out.println("danie_dnia" + danie_dnia);
        System.out.println("zupa_dnia" + zupa_dnia);
        System.out.println("danie_glowne_dnia" + danie_glowne_dnia);
        System.out.println("zamowienie_produktu" + zamowienie_produktu);
        System.out.println("zamowienie_dania_dnia" + zamowienie_dania_dnia);
        System.out.println("zamowienie_zupy_dnia" + zamowienie_zupy_dnia);
        System.out.println("zamowienie_dania_glownego_dnia" + zamowienie_dania_glownego_dnia);
        System.out.println("zamowienie_grupy" + zamowienie_grupy);
        System.out.println("zamowienie_nieprecyzyjne" + zamowienie_nieprecyzyjne);
        System.out.println("zapytanie_czy_produkt_zawiera" + zapytanie_czy_produkt_zawiera);
        System.out.println("ktore_dania_zawierają" + ktore_dania_zawierają);
        System.out.println("ktore_grupy_zawierają" + ktore_grupy_zawierają);
        System.out.println("ktore_grupy_nie_zawierają" + ktore_grupy_nie_zawierają);

        System.out.println("ktore_grupy_nie_zawierają" + zamowienie_grupy_ktore_nie_zawierają);
        System.out.println("wino_i_danie_glowne" + wino_do_danie_glowne);

        System.out.println("prosba_o_rachunek" + prosba_o_rachunek);

        String odpowiedz_zwrotna = "";


        if      (przywitanie)                    odpowiedz_zwrotna = przywitanie();
        else if (danie_dnia)                     odpowiedz_zwrotna = danie_dnia();
        else if (zupa_dnia)                      odpowiedz_zwrotna = zupa_dnia();
        else if (danie_glowne_dnia)              odpowiedz_zwrotna = danie_glowne_dnia();
        else if (zamowienie_produktu)            odpowiedz_zwrotna = zamowienie_produktu(tokenizer, orderHandler, menuList);
        else if (zamowienie_dania_dnia)          odpowiedz_zwrotna = zamowienie_dania_dnia(tokenizer,orderHandler,menuList);
        else if (zamowienie_zupy_dnia)           odpowiedz_zwrotna = zamowienie_zupy_dnia(tokenizer,orderHandler,menuList);
        else if (zamowienie_dania_glownego_dnia) odpowiedz_zwrotna = zamowienie_dania_glownego_dnia(tokenizer,orderHandler,menuList);
        else if (zamowienie_grupy)               odpowiedz_zwrotna = zamowienie_grupy(tokenizer,orderHandler,menuList);
        else if (zamowienie_nieprecyzyjne)       odpowiedz_zwrotna = zamowienie_nieprecyzyjne(tokenizer,orderHandler,menuList);
        else if (zapytanie_czy_produkt_zawiera)  odpowiedz_zwrotna = zapytanie_czy_produkt_zawiera(tokenizer,orderHandler,menuList);
        else if (ktore_dania_zawierają)          odpowiedz_zwrotna = ktore_dania_zawierają(tokenizer,orderHandler,menuList);
        else if (ktore_grupy_zawierają)          odpowiedz_zwrotna = ktore_grupy_zawierają(tokenizer,orderHandler,menuList);
        else if (ktore_grupy_nie_zawierają)      odpowiedz_zwrotna = ktore_grupy_nie_zawierają(tokenizer,orderHandler,menuList);

        else if (zamowienie_grupy_ktore_nie_zawierają) odpowiedz_zwrotna = zamowienie_grupy_ktore_nie_zawierają(tokenizer,orderHandler,menuList,dopytaj);
        else if (wino_do_danie_glowne)           odpowiedz_zwrotna = wino_do_danie_glowne(tokenizer,orderHandler,menuList);

        else if (prosba_o_rachunek)              odpowiedz_zwrotna = prosba_o_rachunek(tokenizer, orderHandler, menuList);
        else                                     odpowiedz_zwrotna = niezrozumialem();


        /*Tokens tokens = new Tokens();
        Boolean answer = false;

        int take_token_number = Integer.parseInt(queue_token.element().toString());
        //int take_token_number = (int) (int) queue_token.element();
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
                odpowiedz_zwrotna = przywitanie();
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
                    String grupa_boss = queue_boss.element().toString();
                    int grupa_token = Integer.parseInt(queue_token.element().toString());
                    String grupa_seqence = queue_sequence.element().toString();
                    delete_token(queue_boss, queue_sequence, queue_token);
                    delete_token(queue_boss, queue_sequence, queue_token);
                    if(queue_token.element().equals(tokens.zapytanie))
                    {
                        delete_token(queue_boss, queue_sequence, queue_token);
                        Queue queue_tokenprim = new LinkedList();
                        Queue queue_bossprim  = new LinkedList();
                        Queue queue_sequenceprim = new LinkedList();

                        queue_bossprim.add("która");
                        queue_sequenceprim.add("która");
                        queue_tokenprim.add("3");
                        queue_bossprim.add(grupa_boss);
                        queue_sequenceprim.add(grupa_seqence);
                        queue_tokenprim.add(grupa_token);
                        while (queue_boss.size() > 0)
                        {
                            queue_bossprim.add(queue_boss.element());
                            queue_sequenceprim.add(queue_sequence.element());
                            queue_tokenprim.add(queue_token.element());
                            delete_token(queue_boss, queue_sequence, queue_token);
                        }

                        //System.out.print(queue_tokenprim);
                        //System.out.print(queue_bossprim);
                        //System.out.print(queue_sequenceprim);
                        return response(queue_tokenprim, queue_bossprim, queue_sequenceprim, orderHandler, menuList);
                    }
                    else
                    {
                        System.out.print("Tu jestem");
                        odpowiedz_zwrotna = listof(grupa_boss);
                        answer = true;
                    }
                }
                else if (queue_token.element().equals(tokens.danie_dnia))
                {
                    orderHandler.addToOrder(menuList.findProduct(dishOfTheDay("zupy")));
                    orderHandler.addToOrder(menuList.findProduct(dishOfTheDay("dania główne")));
                    delete_token(queue_boss, queue_sequence, queue_token);
                }
                else if (queue_token.element().equals(tokens.zapytanie))
                {
                    String zap;
                    zap = response(queue_token, queue_boss, queue_sequence, orderHandler, menuList);
                    System.out.print(zap);
                }
                else if (queue_boss.element().equals(tokens.danie))
                {
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

*/
        return odpowiedz_zwrotna;
    }


    /*-------------
    REAKCJE
    -------------*/

    private static String zamowienie_produktu(Tokenizer tokenizer,OrderHandler orderHandler,MenuList menuList)
    {
        Tokens tokens = new Tokens();
        String produkt = "";
        for (Tokenizer.Token tok : tokenizer.getTokens())
        {
            if (tok.token == tokens.produkt)
            {
                produkt = tok.boss;
            }
        }

        orderHandler.addToOrder(menuList.findProduct(produkt));

        String odpowiedz_zwrotna = "Zamówiono";
        return odpowiedz_zwrotna;
    }

    private static String danie_dnia() throws IOException
    {
        String odpowiedz_zwrotna = "Zupa dnia to: " + dishOfTheDay("zupy") + ", danie główne dnia to: " + dishOfTheDay("dania główne");
        return odpowiedz_zwrotna;
    }

    private static String zupa_dnia() throws IOException
    {
        String odpowiedz_zwrotna = "Zupa dnia to: " + dishOfTheDay("zupy");
        return odpowiedz_zwrotna;
    }

    private static String danie_glowne_dnia() throws IOException
    {
        String odpowiedz_zwrotna = "Danie główne dnia to: " + dishOfTheDay("dania główne");
        return odpowiedz_zwrotna;
    }

    private static String prosba_o_rachunek(Tokenizer tokenizer, OrderHandler orderHandler, MenuList menuList)
    {
        String odpowiedz_zwrotna = orderHandler.buildReceipt();
        return odpowiedz_zwrotna;
    }

    private static String zamowienie_dania_dnia(Tokenizer tokenizer, OrderHandler orderHandler, MenuList menuList) throws IOException
    {
        String zupa = Parser.dishOfTheDay("zupy");
        String danie_glowne = Parser.dishOfTheDay("dania główne");
        orderHandler.addToOrder(menuList.findProduct(zupa));
        orderHandler.addToOrder(menuList.findProduct(danie_glowne));

        String odpowiedz_zwrotna = "Zamówiono";
        return odpowiedz_zwrotna;
    }

    private static String zamowienie_zupy_dnia(Tokenizer tokenizer, OrderHandler orderHandler, MenuList menuList) throws IOException
    {
        String zupa = Parser.dishOfTheDay("zupy");
        orderHandler.addToOrder(menuList.findProduct(zupa));

        String odpowiedz_zwrotna = "Zamówiono";
        return odpowiedz_zwrotna;
    }

    private static String zamowienie_dania_glownego_dnia(Tokenizer tokenizer, OrderHandler orderHandler, MenuList menuList) throws IOException
    {
        String danie_glowne = Parser.dishOfTheDay("dania główne");

        orderHandler.addToOrder(menuList.findProduct(danie_glowne));

        String odpowiedz_zwrotna = "Zamówiono";
        return odpowiedz_zwrotna;
    }

    private static String zamowienie_grupy(Tokenizer tokenizer, OrderHandler orderHandler, MenuList menuList) throws IOException
    {
        Tokens tokens = new Tokens();
        String grupa = "";

        for (Tokenizer.Token tok : tokenizer.getTokens())
        {
            if (tok.token == tokens.grupa)
            {
                grupa = tok.boss;
            }
        }

        String odpowiedz_zwrotna = listof_products_in_group(grupa);
        return odpowiedz_zwrotna;
    }

    private static String zamowienie_nieprecyzyjne(Tokenizer tokenizer, OrderHandler orderHandler, MenuList menuList) throws IOException
    {
        Tokens tokens = new Tokens();
        String niewiadoma = "";

        for (Tokenizer.Token tok : tokenizer.getTokens())
        {
            if (tok.token == tokens.niewiadomy)
            {
                niewiadoma = tok.sequence;
            }
        }

        String odpowiedz_zwrotna = checkproduct(niewiadoma);
        return odpowiedz_zwrotna;
    }

    private static String zapytanie_czy_produkt_zawiera(Tokenizer tokenizer, OrderHandler orderHandler, MenuList menuList) throws IOException
    {
        Tokens tokens = new Tokens();

        String produkt = "";
        String skladnik = "";
        for (Tokenizer.Token tok : tokenizer.getTokens())
        {
            if (tok.token == tokens.produkt)
            {
                produkt = tok.boss;
            }
        }

        for (Tokenizer.Token tok : tokenizer.getTokens())
        {
            if (tok.token == tokens.skladnik_do_zapytania)
            {
                skladnik = tok.boss;
            }
            else if (tok.token == tokens.niewiadomy)
            {
                skladnik = tok.sequence;
            }
        }

        String odpowiedz_zwrotna = does_product_contains_ingredient(produkt,skladnik);
        return odpowiedz_zwrotna;

    }

    private static String ktore_dania_zawierają(Tokenizer tokenizer, OrderHandler orderHandler, MenuList menuList) throws IOException
    {
        Tokens tokens = new Tokens();
        String skladnik = "";

        for (Tokenizer.Token tok : tokenizer.getTokens())
        {
            if (tok.token == tokens.skladnik_do_zapytania)
            {
                skladnik = tok.boss;
            }
            else if (tok.token == tokens.niewiadomy)
            {
                skladnik = tok.sequence;
            }
        }

        String odpowiedz_zwrotna = all_contains_ingredient(skladnik);

        return odpowiedz_zwrotna;
    }

    private static String ktore_grupy_zawierają(Tokenizer tokenizer, OrderHandler orderHandler, MenuList menuList) throws IOException
    {
        Tokens tokens = new Tokens();
        String grupa = "";
        String skladnik = "";

        for (Tokenizer.Token tok : tokenizer.getTokens())
        {
            if (tok.token == tokens.grupa)
            {
                grupa = tok.boss;
            }
        }


        for (Tokenizer.Token tok : tokenizer.getTokens())
        {
            if (tok.token == tokens.skladnik_do_zapytania)
            {
                skladnik = tok.boss;
            }
            else if (tok.token == tokens.niewiadomy)
            {
                skladnik = tok.sequence;
            }
        }

        String odpowiedz_zwrotna = all_products_from_group_contains_ingredient(grupa,skladnik);
        return odpowiedz_zwrotna;
    }

    private static String ktore_grupy_nie_zawierają(Tokenizer tokenizer, OrderHandler orderHandler, MenuList menuList) throws IOException
    {
        Tokens tokens = new Tokens();
        String grupa = "";
        String skladnik = "";

        for (Tokenizer.Token tok : tokenizer.getTokens())
        {
            if (tok.token == tokens.grupa)
            {
                grupa = tok.boss;
            }
        }


        for (Tokenizer.Token tok : tokenizer.getTokens())
        {
            if (tok.token == tokens.skladnik_do_zapytania)
            {
                skladnik = tok.boss;
            }
            else if (tok.token == tokens.niewiadomy)
            {
                skladnik = tok.sequence;
            }
        }

        String odpowiedz_zwrotna = all_products_from_group_not_contains_ingredient(grupa,skladnik);
        return odpowiedz_zwrotna;
    }

    private static String zamowienie_grupy_ktore_nie_zawierają(Tokenizer tokenizer, OrderHandler orderHandler, MenuList menuList, String dopytaj) throws IOException
    {
        Tokens tokens = new Tokens();

        String grupa = "";
        String skladnik = "";

        for (Tokenizer.Token tok : tokenizer.getTokens())
        {
            if (tok.token == tokens.grupa)
            {
                grupa = tok.boss;
            }
        }


        for (Tokenizer.Token tok : tokenizer.getTokens())
        {
            if (tok.token == tokens.skladnik_do_zapytania)
            {
                skladnik = tok.boss;
            }
            else if (tok.token == tokens.niewiadomy)
            {
                skladnik = tok.sequence;
            }
        }


        dopytaj = "Jakie danie z grupy: " + grupa + ", które nie zawiera składnika: " + skladnik +" życzy Pan/Pani sobie";
        return all_products_from_group_not_contains_ingredient(grupa,skladnik);
    }

    private static String wino_do_danie_glowne(Tokenizer tokenizer, OrderHandler orderHandler, MenuList menuList) throws IOException
    {

        Tokens tokens = new Tokens();
        String produkt = "";

        for (Tokenizer.Token tok : tokenizer.getTokens())
        {
            if (tok.token == tokens.produkt)
            {
                produkt = tok.boss;
            }
        }

        String odpowiedz_zwrotna = wine_to_product(produkt);
        return odpowiedz_zwrotna;
    }


    /*-------------
    FUNKCJE
    -------------*/

    private static String przywitanie()
    {
        String odpowiedz = "Dzień dobry. W czym mogę służyc?";
        return  odpowiedz;
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

    private static String listof_products_in_group(Object element) throws IOException
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

    private static String does_product_contains_ingredient(String product, String ingredient) throws IOException
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

    private static String all_products_from_group_contains_ingredient(String group, String ingredient) throws IOException
    {
        MenuList menuList = new MenuList();
        LinkedList groupcontainsProduct = new LinkedList();

        //System.out.println(group + ", które zawierają " + ingredient + ": ");
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

    private static String all_products_from_group_not_contains_ingredient(String grupa, String ingredient) throws IOException
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

    private static String all_contains_ingredient(String ingredient) throws IOException
    {
        MenuList menuList = new MenuList();
        LinkedList containsProduct = new LinkedList<>();
        //System.out.println("Produkty, które zawierają " + ingredient + ": ");
        for (MenuList.Menu tok : menuList.getMenu())
        {
            if (!tok.group.equals("alkohole"))
            {
                String s = Arrays.toString(tok.comment);
                Boolean found = s.contains(ingredient);
                if (found)
                {
                    containsProduct.add(tok.product);
                }
            }
            else
            {
                continue;
            }
        }

        String lista = containsProduct.toString();
        lista = lista.substring(1,lista.length()-1);

        String odpowiedz = "Produkty" + ", które zawierają " + ingredient + ": " + lista;

        return odpowiedz;
    }

    private static String all_not_contains_ingredient(String ingredient) throws IOException
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

    private static String wine_to_product(String produkt) throws IOException
    {
        MenuList menuList = new MenuList();
        LinkedList lista = new LinkedList();
        String komentarze_produktu = "";
        List<String> commentsProduct = new ArrayList<>();
        for (MenuList.Menu tok : menuList.getMenu())
        {
            if (tok.product.equals(produkt))
            {
                komentarze_produktu = tok.comment.toString();
                commentsProduct = new ArrayList<>(Arrays.asList(tok.comment));
            }
        }

        for (MenuList.Menu tok : menuList.getMenu())
        {
            if (tok.group.equals("alkohole"))
            {
                List<String> comments = new ArrayList<String>(Arrays.asList(tok.comment));
                List<String> finalCommentsProduct = commentsProduct;
                comments = comments.stream().filter(com ->
                      finalCommentsProduct.contains(com)).collect(Collectors.toList());
                if (!comments.isEmpty()) {
                    lista.add(tok);
                }
            }
        }


        String lista1 = lista.toString();
        lista1 = lista1.substring(1,lista1.length()-1);

        return "Wina, które pasują do: " + produkt + " to: " + lista1;
    }

    private static String niezrozumialem()
    {
        String odpowiedz = "Kelner: Nie zrozuamialem. Proszę inaczej sformułować zdanie.";
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
