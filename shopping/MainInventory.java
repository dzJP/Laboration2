package laborationer.shopping;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainInventory {

    public static void main(String[] args) {
        List<Inventory> list = initializeInventoryList();

        printInventoryList(list);
        System.out.println();

        serializeInventoryList(list);
    }

    private static List<Inventory> initializeInventoryList() {
        List<Inventory> list = new ArrayList<Inventory>();

        list.add(new Inventory("1000", "Banana", 30, 2.50, 1.25));
        list.add(new Inventory("1001", "Apple", 40, 2, 1.00));
        list.add(new Inventory("2001", "Fanta", 10, 2.39, 1.50));
        list.add(new Inventory("2002", "Orange Juice", 20, 1.99, 1.25));
        list.add(new Inventory("3001", "Coca Cola", 10, 2.25, 1.50));
        list.add(new Inventory("3002", "Cheddar", 20, 2.79, 1.60));
        list.add(new Inventory("4001", "Chocolate", 40, 2.99, 1.70));
        list.add(new Inventory("4002", "Candy", 30, 0.99, 0.50));
        list.add(new Inventory("5001", "Beef", 10, 5.00, 3.00));
        list.add(new Inventory("5002", "Chicken", 10, 4.00, 2.00));

        return list;
    }

    public static void printInventoryList(List<Inventory> list) {
        for (Inventory inv : list) {
            System.out.println(inv);
        }
    }

    private static void serializeInventoryList(List<Inventory> list) {
        ObjectOutputStream file = null;
        try {
            file = new ObjectOutputStream(new FileOutputStream(ShoppingCart.INVENTORY_LIST_FILE));
            file.writeObject(list);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (file != null) {
                try {
                    file.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
