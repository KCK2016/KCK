package kck.order;

/**
 * Created by JK on 2016-11-07.
 */
public class Test {
    public static void main(String[] args) {
        scenarioOne();
        scenarioTwo();
    }

    private static void scenarioOne() {
        MenuItem menuItem = new MenuItem("Kurczak", 4.0);
        MenuItem menuItem1 = new MenuItem("Zupa pomidorowa", 10.0);
        MenuItem menuItem2 = new MenuItem("Ziemniaki", 6.0);
        MenuItem menuItem3 = new MenuItem("Piwo", 5.0);

        OrderHandler orderHandler = new OrderHandler();
        orderHandler.addToOrder(menuItem);
        orderHandler.addToOrder(menuItem1);
        orderHandler.addToOrder(menuItem2);
        orderHandler.addToOrder(menuItem3);
        System.out.printf(orderHandler.finishOrder());
    }

    private static void scenarioTwo() {
        System.out.println("\n\n scenariusz z usunięciem pozycji");
        MenuItem menuItem = new MenuItem("Kurczak", 4.0);
        MenuItem menuItem1 = new MenuItem("Zupa pomidorowa", 10.0);
        MenuItem menuItem2 = new MenuItem("Ziemniaki", 6.0);
        MenuItem menuItem3 = new MenuItem("Piwo", 5.0);

        OrderHandler orderHandler = new OrderHandler();
        orderHandler.addToOrder(menuItem);
        orderHandler.addToOrder(menuItem1);
        orderHandler.addToOrder(menuItem2);
        orderHandler.deleteFromOrder(menuItem1);
        orderHandler.addToOrder(menuItem3);
        orderHandler.deleteFromOrder(menuItem3);
        System.out.printf(orderHandler.finishOrder());
    }
}
