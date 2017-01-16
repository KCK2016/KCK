package kck;

import kck.order.OrderHandler;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by s416094 on 05.12.2016.
 */
public class KCKParser {

    private static OrderHandler orderHandler = new OrderHandler();

    public static String getTokenizedText(String userText) throws IOException {
        Tokenizer tokenizer = tokenize(userText);
        MenuList menuList = new MenuList();
        return Parser.talk(tokenizer, orderHandler, menuList);
    }

    private static Tokenizer tokenize(String userText) {
        Tokenizer tokenizer = getTokenizer();
        userText = userText.toLowerCase();
        tokenizer.tokenize(userText);
        return tokenizer;
    }

    private static Tokenizer getTokenizer() {
        Tokenizer tokenizer = null;
        try {
            tokenizer = new Tokenizer();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tokenizer;
    }

    public String getDishOfTheDay(String group) throws IOException {
        LinkedList<String> listOf = getProductsListFromGroup(group);
        Random random = new Random();
        int index = random.nextInt(listOf.size());
        return listOf.get(index);
    }

    private LinkedList<String> getProductsListFromGroup(String group) throws IOException {
        MenuList menuList = new MenuList();
        LinkedList<String> listOf = new LinkedList<>();
        filterProductsAndAddToList(group, menuList, listOf);
        return listOf;
    }

    private void filterProductsAndAddToList(String group, MenuList menuList, LinkedList<String> listOf) {
        final int[] i = {0};
        menuList.getMenu().stream().filter(tok -> tok.group.equals(group)).forEach(tok -> listOf.add(i[0]++, tok.getProduct()));
    }


}
