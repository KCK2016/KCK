package kck;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adrian on 30/12/2016.
 */
public class Waiter {


String talk(Tokenizer tokenizer) throws IOException {
    String response = "";
    List<Tokenizer.Token> list = new ArrayList<Tokenizer.Token>();
    for(Tokenizer.Token tok: tokenizer.getTokens()){
        list.add(tok);
    }

    if(list.size()>0) {
        response = response(list);
    }

    return response;
}

String response(List<Tokenizer.Token> tokens) throws IOException {
    String respon = "";

    for (int i=0; i<tokens.size(); i++) {
        if (tokens.get(i).token == 0) {
            respon = "Dzień dobry";
        } else if (tokens.get(i).token == 1 || tokens.get(i).token == 2) {
            i++;
            if (i < tokens.size() & tokens.get(i).token == 10) {
                respon = "Zamówiono " + tokens.get(i).boss + ". Czy podać coś jeszcze?";
            } else respon = "Co podać?";
        }
        else if (tokens.get(i).token == 3 && tokens.get(i+1).token == 4) {
            KCK kck = new KCK("");
            i +=2;
            kck.containsProduct(tokens.get(i).sequence);
        }
    }

    return respon;
}


}
