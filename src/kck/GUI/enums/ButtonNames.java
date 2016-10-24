package kck.GUI.enums;

/**
 * Created by JK on 2016-10-20.
 */
public enum ButtonNames {
    WAITER_BUTTON("waiter"), TABLE1("table1"), TABLE2("table2"), TABLE3("table3"), KITCHEN("kitchen"), TEXT_BUTTON("type text");


    private String name;

    ButtonNames(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
