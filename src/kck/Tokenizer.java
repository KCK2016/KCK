package kck;

/**
 * Created by s152483 on 17.11.2016.
 */

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tokenizer
{
    private class TokenInfo
    {
        public final Pattern regex;
        public final int token;
        public final String boss;

        public TokenInfo(Pattern regex, int token, String boss)
        {
            super();
            this.regex = regex;
            this.token = token;
            this.boss = boss;
        }
    }


    public class Token
    {
        public final int token;
        public final String sequence;
        public final String boss;

        public Token(int token, String sequence, String boss)
        {
            super();
            this.token = token;
            this.sequence = sequence;
            this.boss = boss;
        }

    }

    private LinkedList < TokenInfo > tokenInfos;
    private LinkedList < Token > tokens;

    public Tokenizer()
    {
        tokenInfos = new LinkedList < TokenInfo >();
        tokens = new LinkedList < Token >();
    }

    public void add(String regex, int token, String boss)
    {
        tokenInfos.add(new TokenInfo(Pattern.compile("^("+regex+")"), token, boss));
    }



    //-----------------------------------
    // ta funkcja ma pewien blad logiczny
    //jesli w slowniku wystepuje "kotlet" przed "kotleta" i w zdaniu ktos uzywa "kotleta"
    // to tokenizer dopasowuje "kotlet" i zostawia "a" po czym sie zapetla....
    // oraz co w sytuacji jesli nie ma jakiegos slowa w slowniku?
    // ---------------------------
    public void tokenize(String str)
    {
        String string = str.trim();
        tokens.clear();
        while (!string.equals(""))
        {
            for (TokenInfo info : tokenInfos)
            {
                Matcher m = info.regex.matcher(string);
                if (m.find()) {
                    String tok = m.group().trim();
                    string = m.replaceFirst("").trim();
                    tokens.add(new Token(info.token, tok, info.boss));
                    break;
                }
                System.out.println(string);

            }
        }
    }

    public LinkedList < Token > getTokens()
    {
        return tokens;
    }

}
