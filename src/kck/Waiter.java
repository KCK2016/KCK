package kck;

import kck.order.OrderHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adrian on 30/12/2016.
 */
public class Waiter {

    KCK kck = new KCK("");



String talk(Tokenizer tokenizer, OrderHandler orderHandler, MenuList menuList) throws IOException {
    String response = "";
    List<Tokenizer.Token> list = new ArrayList<Tokenizer.Token>();
    for(Tokenizer.Token tok: tokenizer.getTokens()){
        if(tok.token != 12) {
            list.add(tok);
            System.out.println(tok.token);
        }
    }
    if(list.size()>0) {
        response(list,  orderHandler,  menuList);
    }
    return response;
}


/*
######################
trochę dużawy ten if ... :(
a będzie tylko większy
#######################
 */
private void response(List<Tokenizer.Token> tokens, OrderHandler orderHandler, MenuList menuList) throws IOException {
    Boolean answer = false;
    for (int i=0; i<tokens.size(); i++) {
        if (tokens.get(i).token == 0) {
            System.out.println("Kelner: Dzień dobry");
            answer = true;
        }
        else if(tokens.get(i).token == 7){
            // zamówiono danie dnia
            String word = tokens.get(i).boss.substring(0,1).toUpperCase() + tokens.get(i).boss.substring(1);
            String product = kck.returndishOfTheDay(word);
            orderHandler.addToOrder(menuList.findProduct(product));
            System.out.println("Kelner: Czy podać coś jeszcze?");
            answer = true;
        }
        else if (tokens.get(i).token == 8) {
            //jedynie słowo rachunek

            System.out.println("Kelner: "+ orderHandler.buildReceipt());
            answer = true;
        }
        else if (tokens.get(i).token == 10) {
            //sama nazwa produktu

            System.out.println("Kelner: Zamówiono " + tokens.get(i).boss + ". Czy podać coś jeszcze?");
            orderHandler.addToOrder(menuList.findProduct(tokens.get(i).boss));
            answer = true;
        }
        else if(tokens.get(i).token == 13) {} // tak
        else if(tokens.get(i).token == 14) {} // nie
        else if (tokens.get(i).token == 1 || tokens.get(i).token == 2) {
            i++;
            if (i < tokens.size() & tokens.get(i).token == 10) {
                //zamówienie po pełnej nazwie

                System.out.println("Kelner: Zamówiono " + tokens.get(i).boss + ". Czy podać coś jeszcze?");
                orderHandler.addToOrder(menuList.findProduct(tokens.get(i).boss));
                answer = true;
            }
            else if(i < tokens.size() & tokens.get(i).token == 99) {
                //zamówienie po niepełnej nazwie

                Tokenizer tokenizer = new Tokenizer();
                Result ordered = tokenizer.checkproduct(tokens.get(i));
                if(ordered.getFound()) {

                    orderHandler.addToOrder(menuList.findProduct(ordered.getProduct()));
                    System.out.println("Kelner: Czy podać coś jeszcze?");
                    answer = true;
                }
            }
            else if(i<tokens.size() & tokens.get(i).token == 7 ){
                // zamówiono danie dnia
                String word = tokens.get(i).boss.substring(0,1).toUpperCase() + tokens.get(i).boss.substring(1);
                String product = kck.returndishOfTheDay(word);
                orderHandler.addToOrder(menuList.findProduct(product));
                System.out.println("Kelner: Czy podać coś jeszcze?");
                answer = true;
            }
            else if(i<tokens.size() & tokens.get(i).token == 8){
                //poproszono o rachunek

                System.out.println("Kelner: "+ orderHandler.buildReceipt());
                answer = true;
            }
            else {
                System.out.println("Kelner: Proszę wybrać produkt z menu.");
                answer = true;
            }
        }
        else if (tokens.get(i).token == 3 && tokens.get(i+1).token == 4) {
            //czy z mięsem itp.

            i +=2;
            kck.containsProduct(tokens.get(i).sequence);
            System.out.println("Kelner: Co podać?");
            answer = true;
        }
        else if (tokens.get(i).token == 3 && tokens.get(i+1).token == 15) {
            //czy z mięsem itp.
            i++;
            kck.containsProduct(tokens.get(i).boss);
            System.out.println("Kelner: Co podać?");
            answer = true;
        }
        else if (tokens.get(i).token == 3 && tokens.get(i+1).token == 7) {
            // danie dnia
            i++;
            kck.dishOfTheDay("Zupy");
            kck.dishOfTheDay("Dania główne");
            kck.dishOfTheDay("Desery");
            System.out.println("Kelner: Czy coś podać?");
            answer = true;
        }
        else if(tokens.get(i).token == 17) {
            System.out.println("Kelner: Polecam danie dnia.");
            answer = true;
        }
    }
    if(!answer){
        System.out.println("Kelner: Nie zrozuamialem. Proszę inaczej sformułować zdanie.");
    }
}


}
