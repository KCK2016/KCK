package kck;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.regex.Pattern;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.text.Collator;
import java.util.*;

final class Result{
    private String product;
    private Boolean found;

    public Result(String product, Boolean found){
        this.product = product;
        this.found = found;
    }

    public String getProduct() { return product;}
    public Boolean getFound() { return found;}
}
public class Tokenizer
{
    public class TokenInfo
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

    public LinkedList < TokenInfo > tokenInfos;
    private LinkedList < Token > tokens;

    public Tokenizer() throws IOException
    {
        tokenInfos = new LinkedList < TokenInfo >();
        tokens = new LinkedList < Token >();
        addtoken();
    }


    public void add(String regex, int token, String boss)
    {
        tokenInfos.add(new TokenInfo(Pattern.compile("^("+regex+")"), token, boss));
    }


    public void tokenize(String str)
    {
        String string = str.trim();
        String lastchar = string.substring(string.length()-1, string.length());
        if(lastchar.equals(".")| lastchar.equals("?")|lastchar.equals("!"))
        {
            string = string.substring(0, string.length()-1);
        }
        tokens.clear();
        while (!string.equals(""))
        {
            boolean match = false;
            for (TokenInfo info : tokenInfos)
            {
                Matcher m = info.regex.matcher(string);
                if (m.find())
                {
                    match = true;
                    String tok = m.group().trim();
                    string = m.replaceFirst("").trim();
                    tokens.add(new Token(info.token, tok, info.boss));
                    break;
                }
            }
            if (!match)
            {
                    System.out.println("Unexpected character in input: " + string);
                    break;
            }

        }
    }


    public void addtoken() throws IOException
    {

        File token_txt = new File("dictionary.txt");
        List<String> token_txt_list = new LinkedList<>();
        token_txt_list = Files.readAllLines(token_txt.toPath());


        // sortowanie po dlufgosci pierwszego slowa z lini z pliku

        token_txt_list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String[] text1 = o1.split(",");
                String[] text2 = o2.split(",");
                if(text1[0].length() > text2[0].length()) return -1;
                else if (text1[0].length() == text2[0].length()) return 0;
                else return 1;
            }
        });



        for(String line: token_txt_list) {
            String[] line_split = line.trim().split(",");

            // sortowanie tokentow wewnatrz lini
            if (line_split.length>2) {
                for (int i = 2; i < line_split.length-1; i++) {
                    if (line_split[i].length() < line_split[i + 1].length()) {
                        String temp = line_split[i];
                        line_split[i] = line_split[i + 1];
                        line_split[i + 1] = temp;
                    }
                }
            }


            for (int counter = 2; counter<line_split.length; counter++){
                add(line_split[counter],Integer.parseInt(line_split[1]), line_split[0].toLowerCase());
            }
        }
        add(",", 12, ",");
        add("^[a-żA-Ż0-9_.-]*", 99,"^[a-żA-Ż0-9_.-]*");

    }


    public LinkedList < Token > getTokens()
    {
        return tokens;
    }

    public LinkedList < TokenInfo > getTokensInfo()
    {
        return tokenInfos;
    }

}



