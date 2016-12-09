package kck;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class MenuList {

// Lista (nazwa produktu, grupa, cena, komentarze)


    public class Menu
    {
        public String product;
        public String group;
        public String price;
        public String comment;

        public Menu(String product, String group, String price, String comment)
        {
            super();
            this.product = product;
            this.group = group;
            this.price = price;
            this.comment = comment;
        }

        @Override
        public String toString() {
            return "product='" + product + '\'' +
                    ", group='" + group + '\'' +
                    '}';
        }

        public String getProduct() {
            return product;
        }

        public void setProduct(String product) {
            this.product = product;
        }

        public String getGroup() {
            return group;
        }

        public void setGroup(String group) {
            this.group = group;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }
    }


    public LinkedList < Menu > menu;

    public MenuList() throws IOException {
        menu = new LinkedList < Menu >();
        try {
            MenuDownload();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void add(String product, String group, String price, String comment)
    {
        menu.add(new Menu(product, group, price, comment));
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
                String[] snapArray = bufferReader.readLine().split("[:,/]");
                for (int i = 0; i < snapArray.length - 3; i = i + 3)
                    add(snapArray[i+1].trim(), snapArray[0].trim(), snapArray[i+2].trim(), snapArray[i+3].trim());

                String[] soupsArray = bufferReader.readLine().split("[:,/]");
                for (int i = 0; i < soupsArray.length - 3; i = i + 3)
                    add(soupsArray[i+1].trim(), soupsArray[0].trim(), soupsArray[i+2].trim(), soupsArray[i+3].trim());

                String[] secondDishesArray = bufferReader.readLine().split("[:,/]");
                for (int i = 0; i < secondDishesArray.length - 3; i = i + 3)
                    add(secondDishesArray[i+1].trim(), secondDishesArray[0].trim(), secondDishesArray[i+2].trim(), secondDishesArray[i+3].trim());

                String[] dessertArray = bufferReader.readLine().split("[:,/]");
                for (int i = 0; i < dessertArray.length - 3; i = i + 3)
                    add(dessertArray[i+1].trim(), dessertArray[0].trim(), dessertArray[i+2].trim(), dessertArray[i+3].trim());

                String[] coldDrinksArray = bufferReader.readLine().split("[:,/]");
                for (int i = 0; i < coldDrinksArray.length - 3; i = i + 3)
                    add(coldDrinksArray[i+1].trim(), coldDrinksArray[0].trim(), coldDrinksArray[i+2].trim(), coldDrinksArray[i+3].trim());

                String[] hotDrinksArray = bufferReader.readLine().split("[:,/]");
                for (int i = 0; i < hotDrinksArray.length - 3; i = i + 3)
                    add(hotDrinksArray[i+1].trim(), hotDrinksArray[0].trim(), hotDrinksArray[i+2].trim(), hotDrinksArray[i+3].trim());

                String[] alcoholArray = bufferReader.readLine().split("[:,/]");
                for (int i = 0; i < alcoholArray.length - 3; i = i + 3)
                    add(alcoholArray[i+1].trim(), alcoholArray[0].trim(), alcoholArray[i+2].trim(), alcoholArray[i+3].trim());

            }

        }
    }


}
