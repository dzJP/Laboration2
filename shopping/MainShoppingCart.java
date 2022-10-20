package laborationer.shopping;

public class MainShoppingCart {

    public static void main(String[] args) {

        ShoppingCart cart1 = new ShoppingCart("Joel", "17/10/2022");
        ShoppingCart cart2 = new ShoppingCart("Kurt", "12/10/2022");

        cart1.addItem("Banana", 5);
        cart1.addItem("Apple", 7);
        cart1.addItem("Fanta", 5);
        cart1.addItem("Coca Cola", 4);
        cart1.addItem("Candy", 15);
        cart1.removeItem("Candy", 5);

        cart1.viewCart();
        System.out.println();

        MainInventory.printInventoryList(ShoppingCart.getInventoryList());
        System.out.println();

        cart2.addItem("Banana", 5);
        cart2.addItem("Apple", 4);
        cart2.addItem("Fanta", 3);
        cart2.addItem("Coca Cola", 2);
        cart2.addItem("Cheddar", 3);
        cart2.addItem("Beef", 6);
        cart2.addItem("Candy", 25);
        cart2.addItem("Chocolate", 5);
        cart2.addItem("Chicken", 2);
        cart2.removeItem("Chocolate", 4);
        cart2.removeItem("Coca Cola", 1);

        cart2.viewCart();
        System.out.println();

        MainInventory.printInventoryList(ShoppingCart.getInventoryList());
        System.out.println();

        cart2.shuffleCart();
        cart2.viewCart();
        System.out.println();

        cart2.sortCartByPrice();
        cart2.viewCart();
        System.out.println();
    }
}
