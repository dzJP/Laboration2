package laborationer.shopping;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ShoppingCart {

    public static final String INVENTORY_LIST_FILE = "inventoryList.bin";
    private static List<Inventory> inventoryList;

    private String customerName;
    private String date;
    private List<Item> cart = new ArrayList<Item>();

    public ShoppingCart(String customerName, String date) {
        this.customerName = customerName;
        this.date = date;
    }

    public void addItem(String itemName, int quantity) {
        inventoryList = retrieveInventoryList();
        sortInventory();
        int inventoryIndex = searchInventory(itemName);
        int inventoryQuantity = inventoryList.get(inventoryIndex).getItem().getQuantity();
        if (inventoryQuantity == 0) {
            System.out.println(itemName + ": not available.");
            return;
        } else if (quantity > inventoryQuantity) {
            inventoryList.get(inventoryIndex).getItem().setQuantity(0);
            quantity = inventoryQuantity;
        } else {
            inventoryList.get(inventoryIndex).getItem().setQuantity(inventoryQuantity - quantity);
        }

        int cartIndex = searchCart(itemName);
        if (cartIndex < 0) {
            cart.add(-cartIndex - 1, new Item(itemName, inventoryList.get(inventoryIndex).getItem().getPrice() * quantity, quantity));
        } else {
            cart.get(cartIndex).setQuantity(cart.get(cartIndex).getQuantity() + quantity);
            cart.get(cartIndex).setPrice(cart.get(cartIndex).getPrice() + inventoryList.get(inventoryIndex).getItem().getPrice() * quantity);
        }
        serializeInventoryList();
    }

    public void removeItem(String itemName, int quantity) {

        inventoryList = retrieveInventoryList();
        sortInventory();
        int inventoryIndex = searchInventory(itemName);
        Item cartItem = cart.get(searchCart(itemName));

        int inventoryQuantity = inventoryList.get(inventoryIndex).getItem().getQuantity();
        int cartQuantity = cartItem.getQuantity();
        if (quantity > cartQuantity) {
            cartItem.setQuantity(0);
            quantity = cartQuantity;
        } else {
            cartItem.setQuantity(cartQuantity - quantity);
            cartItem.setPrice(cartItem.getQuantity() * inventoryList.get(inventoryIndex).getItem().getPrice());
        }
        inventoryList.get(inventoryIndex).getItem().setQuantity(inventoryQuantity + quantity);

        if (cartItem.getQuantity() == 0) {
            cart.remove(cartItem);
        }
        serializeInventoryList();
    }

    public static List<Inventory> getInventoryList() {
        return inventoryList;
    }

    private List<Inventory> retrieveInventoryList() {
        ObjectInputStream file = null;
        List<Inventory> list = null;
        try {
            file = new ObjectInputStream(new FileInputStream(INVENTORY_LIST_FILE));
            list = (List<Inventory>) file.readObject();
        } catch (IOException | ClassNotFoundException e) {
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
        return list;
    }

    private void serializeInventoryList() {
        ObjectOutputStream file = null;
        try {
            file = new ObjectOutputStream(new FileOutputStream(INVENTORY_LIST_FILE));
            file.writeObject(inventoryList);
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

    public int searchInventory(String itemName) {
        return Collections.binarySearch(inventoryList, new Inventory("", itemName, 0, 0, 0), Comparator.comparing(o -> o.getItem().getItemName()));
    }

    private int searchCart(String itemName) {
        Collections.sort(cart, Comparator.comparing(Item::getItemName));

        return Collections.binarySearch(cart, new Item(itemName, 0, 0), Comparator.comparing(Item::getItemName));
    }

    public void viewCart() {
        System.out.println(this);
    }

    @Override
    public String toString() {

        StringBuilder output = new StringBuilder(date + " Name: " + customerName + "\n");
        double total = 0;
        for (Item item : cart) {
            output.append(item + "\n");
            total += item.getPrice();
        }
        output.append(String.format("%20s%5.2f", "Total: €", total));
        return output.toString();
    }

    public void sortCartByPrice() {
        Collections.sort(cart, Comparator.comparingDouble(Item::getPrice));
    }

    private void sortInventory() {
        Collections.sort(inventoryList, Comparator.comparing(o -> o.getItem().getItemName()));
    }

    public void shuffleCart() {
        Collections.shuffle(cart);
    }
}