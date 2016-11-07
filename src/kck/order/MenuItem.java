package kck.order;

/**
 * Created by JK on 2016-11-07.
 */
public class MenuItem {
    private String name;
    private Double price;

    public MenuItem() {
    }

    public MenuItem(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Nazwa: " + name +
                ", cena: " + price + "zł";
    }
}
