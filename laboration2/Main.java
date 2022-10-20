package laborationer.laboration2;

import java.io.IOException;
import java.util.Map;

public class Main {
    private static StockList stockList = new StockList();

    public static void main(String[] args) throws IOException {

        StockItem temp = new StockItem("Hamburger", 90, 100);
        stockList.addStock(temp);

        temp = new StockItem("Apple", 10, 7);
        stockList.addStock(temp);

        temp = new StockItem("Luxury Burger", 120, 2);
        stockList.addStock(temp);

        temp = new StockItem("Banana", 5, 10);
        stockList.addStock(temp);

        temp = new StockItem("Fanta", 10, 200);
        stockList.addStock(temp);
        temp = new StockItem("Fanta",5 , 7);
        stockList.addStock(temp);

        temp = new StockItem("Cabbage", 40, 4);
        stockList.addStock(temp);

        temp = new StockItem("CocaCola", 10, 36);
        stockList.addStock(temp);

        temp = new StockItem("Rice", 96.99, 35);
        stockList.addStock(temp);

        temp = new StockItem("Pasta", 2.40, 80);
        stockList.addStock(temp);

        temp = new StockItem("Coffee", 8.76, 40);
        stockList.addStock(temp);

        System.out.println(stockList);

        Basket myBasket = new Basket("My Basket");

        sellItem(myBasket, "Luxury Burger", 1);
        System.out.println(myBasket);

        sellItem(myBasket, "Luxury Burger", 1);
        System.out.println(myBasket);

        if(sellItem(myBasket, "Luxury Burger", 1) != 1) {
            System.out.println("There are no more Luxury Burger in stock");
        }

        sellItem(myBasket, "unsellable items", 5);
        System.out.println(myBasket);


        sellItem(myBasket, "CocaCola", 4);
        sellItem(myBasket, "Fanta", 12);
        sellItem(myBasket, "Hamburger", 1);
        System.out.println(myBasket);

        System.out.println(stockList);

        Basket basket = new Basket("new customer");
        sellItem(basket, "Fanta", 100);
        sellItem(basket, "CocaCola", 5);
        removeItem(basket, "Fanta", 1);
        System.out.println(basket);

        removeItem(myBasket, "Luxury Burger", 1);
        removeItem(myBasket, "Fanta", 9);
        removeItem(myBasket, "Luxury Burger", 1);
        System.out.println("Luxury Burger removed: " + removeItem(myBasket, "Luxury Burger", 1));  // should not remove any

        System.out.println(myBasket);
//
//        // remove all items from myBasket
//        removeItem(myBasket, "Hamburger", 1);
//        removeItem(myBasket, "Fanta", 3);
//        removeItem(myBasket, "CocaCola", 4);
//        removeItem(myBasket, "Fanta", 3);
//        System.out.println(myBasket);
//
//        System.out.println("\nDisplay stock list before and after checkout");
//        System.out.println(basket);
//        System.out.println(stockList);
//        checkOut(basket);
//        System.out.println(basket);
//        System.out.println(stockList);
//
////        temp = new StockItem("New Item", 1.12);
////        stockList.Items().put(temp.getName(), temp);
//        StockItem Burger = stockList.Items().get("Luxury Burger");
//        if(Luxury Burger != null) {
//            car.adjustStock(2000);
//        }
//        if(Luxury Burger != null) {
//            stockList.get("Luxury Burger").adjustStock(-1000);
//        }
//
//        System.out.println(stockList);
//        for(Map.Entry<String, Double> price: stockList.PriceList().entrySet()) {
//            System.out.println(price.getKey() + " costs " + price.getValue());
//        }
//
        checkOut(myBasket);
        System.out.println("Successfully purchased" + myBasket);

    }

    public static int sellItem(Basket basket, String item, int quantity) {
        // retrieve the item from stock list
        StockItem stockItem = stockList.get(item);
        if(stockItem == null) {
            System.out.println("We don't sell " + item);
            return 0;
        }
        if(stockList.reserveStock(item, quantity) != 0) {
            return basket.addToBasket(stockItem, quantity);
        }
        return 0;
    }

    public static int removeItem(Basket basket, String item, int quantity) {
        // retrieve the item from stock list
        StockItem stockItem = stockList.get(item);
        if(stockItem == null) {
            System.out.println("We don't sell " + item);
            return 0;
        }
        if(basket.removeFromBasket(stockItem, quantity) == quantity) {
            return stockList.unreserveStock(item, quantity);
        }
        return 0;
    }


    public static void checkOut(Basket basket) {
        for (Map.Entry<StockItem, Integer> item : basket.Items().entrySet()) {
            stockList.sellStock(item.getKey().getName(), item.getValue());
        }
        basket.clearBasket();
    }
}
