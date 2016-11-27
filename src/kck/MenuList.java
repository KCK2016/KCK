package kck;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class MenuList {

    public class Menu
    {
        public String product;
        public String group;

        public Menu(String product, String group)
        {
            super();
            this.product = product;
            this.group = group;
        }
    }

    private LinkedList < Menu > menu;

    public MenuList() throws IOException {
        menu = new LinkedList < Menu >();
        try {
            MenuDownload();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void add(String product, String group)
    {
        menu.add(new Menu(product, group));
    }

    public LinkedList < Menu > getMenu()
    {
        return menu;
    }



    public void MenuDownload() throws IOException
    {
        try (
                FileReader fileReader = new FileReader("baza.txt")
            )
        {
            BufferedReader bufferReader = new BufferedReader(fileReader);

            while (bufferReader.read() != -1)
            {
                String[] snapArray = bufferReader.readLine().split("[:,]");
                for(int i = 1; i < snapArray.length; i = i + 1)
                    add(snapArray[i].trim(), snapArray[0]);

                String[] soupsArray = bufferReader.readLine().split("[:,]");
                for(int i = 1; i < soupsArray.length; i = i + 1)
                    add(soupsArray[i].trim(), soupsArray[0]);

                String[] secondDishesArray = bufferReader.readLine().split("[:,]");
                for(int i = 1; i < secondDishesArray.length; i = i + 1)
                    add(secondDishesArray[i].trim(), secondDishesArray[0]);

                String[] dessertArray = bufferReader.readLine().split("[:,]");
                for(int i = 1; i < dessertArray.length; i = i + 1)
                    add(dessertArray[i].trim(), dessertArray[0]);

                String[] coldDrinksArray = bufferReader.readLine().split("[:,]");
                for(int i = 1; i < coldDrinksArray.length; i = i + 1)
                    add(coldDrinksArray[i].trim(), coldDrinksArray[0]);

                String[] hotDrinksArray = bufferReader.readLine().split("[:,]");
                for(int i = 1; i < hotDrinksArray.length; i = i + 1)
                    add(hotDrinksArray[i].trim(), hotDrinksArray[0]);

                String[] alcoholArray = bufferReader.readLine().split("[:,]");
                for(int i = 1; i < alcoholArray.length; i = i + 1)
                    add(alcoholArray[i].trim(), alcoholArray[0]);
            }
        }

    }
}
