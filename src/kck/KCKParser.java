package kck;

import java.io.IOException;

/**
 * Created by s416094 on 05.12.2016.
 */
public class KCKParser {

    public String getText(String userText) {
        Tokenizer tokenizer = null;
        MenuList menuList = null;
        try {
            tokenizer = new Tokenizer();
            menuList = new MenuList();
        } catch (IOException e) {
            e.printStackTrace();
        }


        userText=userText.toLowerCase();
        tokenizer.tokenize(userText);

        StringBuilder builder = new StringBuilder();
        for (Tokenizer.Token tok : tokenizer.getTokens()) {
            if (tok.token == 10) {
                builder.append(tok.boss).append(" ");
            }
        }
        return builder.toString();

    }


}
