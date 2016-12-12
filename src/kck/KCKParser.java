package kck;

import java.io.IOException;

/**
 * Created by s416094 on 05.12.2016.
 */
public class KCKParser {

    public String getTokenizedText(String userText) {
        Tokenizer tokenizer = tokenize(userText);
        StringBuilder builder = new StringBuilder();
        buildText(tokenizer, builder);
        return builder.toString().trim();
    }

    private Tokenizer tokenize(String userText) {
        Tokenizer tokenizer = getTokenizer();
        userText=userText.toLowerCase();
        tokenizer.tokenize(userText);
        return tokenizer;
    }

    private void buildText(Tokenizer tokenizer, StringBuilder builder) {
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
