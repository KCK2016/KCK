package kck;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adrian on 30/12/2016.
 */
public class Waiter {

    KCK kck = new KCK("");



String talk(Tokenizer tokenizer) throws IOException {
    String response = "";
    List<Tokenizer.Token> list = new ArrayList<Tokenizer.Token>();
    for(Tokenizer.Token tok: tokenizer.getTokens()){
        list.add(tok);
        System.out.println(tok.token);
    }

    if(list.size()>0) {
        response = "Kelner: "+response(list);
    }

    return response;
}

String response(List<Tokenizer.Token> tokens) throws IOException {
    String respon = "";
    for (int i=0; i<tokens.size(); i++) {
        if (tokens.get(i).token == 0) {
            respon = "Dzień dobry";
        }
        else if (tokens.get(i).token ==10) {
            //sama nazwa produktu

            respon = "Zamówiono " + tokens.get(i).boss + ". Czy podać coś jeszcze?";

            // handle order

        }
        else if (tokens.get(i).token == 1 || tokens.get(i).token == 2) {
            i++;
            if (i < tokens.size() & tokens.get(i).token == 10) {
                //zamówienie po pełnej nazwie

                respon = "Zamówiono " + tokens.get(i).boss + ". Czy podać coś jeszcze?";

                // handle order

            }
            else if(i < tokens.size() & tokens.get(i).token == 99) {
                //zamówienie po niepełnej nazwie

                Tokenizer tokenizer = new Tokenizer();
                Boolean ordered;
                ordered = tokenizer.checkproduct(tokens.get(i));
                if(ordered) {

                    //handle order
                    respon = "Czy podać coś jeszcze?";
                }
            }
            else if(i<tokens.size() & tokens.get(i).token == 7 ){
                // zamówiono danie dnia
                String product = "";

                String word = tokens.get(i).boss.substring(0,1).toUpperCase() + tokens.get(i).boss.substring(1);
                product = kck.returndishOfTheDay(word);

                // znaleźć token czy nazwa wystarczy?


                // order handler
                respon = "Czy podać coś jeszcze?";
            }
            else respon = "Proszę wybrać produkt z menu.";
        }
        else if (tokens.get(i).token == 3 && tokens.get(i+1).token == 4) {
            //czy z mięsem itp.

            i++;
            kck.containsProduct(tokens.get(i).sequence);
            respon = "Co podać?";
        }
        else if (tokens.get(i).token == 3 && tokens.get(i+1).token == 7) {
            // danie dnia
            i++;
            kck.dishOfTheDay("Zupy");
            kck.dishOfTheDay("Dania główne");
            kck.dishOfTheDay("Desery");
            respon = "Co podać?";

        }
        else{
            respon = "Nie zrozuamialem. Proszę inaczej sformułować zdanie.";
        }
    }

    return respon;
}


}
