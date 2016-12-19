package kck;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

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

    public String getDishOfTheDay(String group) throws IOException {
        MenuList menuList = new MenuList();
        LinkedList<String> listOf;
        listOf = new LinkedList<String>();

        for (MenuList.Menu tok : menuList.getMenu())
        {
            if (tok.group.equals(group) == true)
            {
                int i = 0;
                listOf.add(i, tok.getProduct());
                i = i + 1;
            }
        }

        Random random = new Random();
        int index = random.nextInt(listOf.size());
        String item = listOf.get(index);
       return item;
    }

}
