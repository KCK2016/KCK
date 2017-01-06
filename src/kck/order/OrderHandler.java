package kck.order;

import kck.MenuList;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by JK on 2016-11-07.
 */
public class OrderHandler {

    private List<MenuItem> orderList;

    public OrderHandler() {
        orderList = new ArrayList<>();
    }

    public void addToOrder(MenuItem menuItem) {
        orderList.add(menuItem);
    }

    public void addToOrder(MenuList.Menu menu) {MenuItem menuItem = new MenuItem(menu.getProduct(), Double.parseDouble(menu.getPrice()));
    orderList.add(menuItem);
    }

    public void deleteFromOrder(MenuItem menuItem) {
        orderList.remove(menuItem);
    }

    public String finishOrder() {
        return buildReceipt();
    }

    public String buildReceipt() {
        StringBuilder finishedOrder = new StringBuilder();
        finishedOrder.append("Rachunek: \n");
        finishedOrder.append(orderList.stream().map(MenuItem::toString).collect(Collectors.joining("\n")));
        finishedOrder.append("\nCałkowity koszt: ");
        finishedOrder.append(getTotalPrice());
        finishedOrder.append("zł");
        return finishedOrder.toString();
    }

    public Double getTotalPrice(){
        return orderList.stream().mapToDouble(MenuItem::getPrice).sum();
    }


}
