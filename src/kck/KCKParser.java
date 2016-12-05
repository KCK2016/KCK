package kck;

import java.io.IOException;

/**
 * Created by s416094 on 05.12.2016.
 */
public class KCKParser {

    public String getText(String userText) {
        Tokenizer tokenizer = tokenize(userText);
        StringBuilder builder = new StringBuilder();
        buildResponse(tokenizer, builder);
        return builder.toString();

    }

    private Tokenizer tokenize(String userText) {
        Tokenizer tokenizer = getTokenizer();
        userText=userText.toLowerCase();
        tokenizer.tokenize(userText);
        return tokenizer;
    }

    private void buildResponse(Tokenizer tokenizer, StringBuilder builder) {
        tokenizer.getTokens().stream().filter(token -> token.token == 10).forEach(token -> builder.append(token.boss).append(" "));
    }

    private Tokenizer getTokenizer() {
        Tokenizer tokenizer = null;
        try {
            tokenizer = new Tokenizer();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tokenizer;
    }


}
