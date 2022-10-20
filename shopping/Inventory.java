package laborationer.shopping;

import java.io.Serializable;

public class Inventory implements Serializable {

    private String stockKeepingUnit;
    private Item item;
    private double cost;

    public Inventory(String stockKeepingUnit, String itemName, int quantity, double price, double cost) {
        this.stockKeepingUnit = stockKeepingUnit;
        item = new Item(itemName, price, quantity);
        this.cost = cost;
    }

    @Override
    public String toString() {
        return String.format("%s %-15s %2d €%.2f",
                stockKeepingUnit, item.getItemName(), item.getQuantity(), item.getPrice());
    }

    public Item getItem() {
        return item;
    }
}
