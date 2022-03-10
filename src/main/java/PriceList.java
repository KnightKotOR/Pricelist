

import java.util.HashMap;
import java.util.Map;


public class PriceList {
    private final Map<Integer, Product> priceList = new HashMap<>();

    String getName(int code) {
        checkExisting(priceList, code);
        return priceList.get(code).getName();
    }

    Double getCost(int code) {
        checkExisting(priceList, code);
        return priceList.get(code).getCost();
    }

    public void addProduct(int code, String name, Double cost) {
        checkNotExisting(priceList, code);
        if (cost < 0) throw new IllegalArgumentException("Wrong cost format");
        priceList.put(code, new Product(name, cost));
    }

    public void removeProduct(int code) {
        checkExisting(priceList, code);
        priceList.remove(code);
    }

    public void changeName(int code, String newName) {
        checkExisting(priceList, code);
        priceList.get(code).changeName(newName);
    }

    public void changeCost(int code, Double newCost) {
        checkExisting(priceList, code);
        if (newCost < 0) throw new IllegalArgumentException("Wrong cost format");
        priceList.get(code).changeCost(newCost);
    }

    public Double determinePrice(int code, int number) {
        checkExisting(priceList, code);
        if (number <= 0) throw new IllegalArgumentException("Wrong number format");
        Double cost = priceList.get(code).getCost();
        return cost * number;
    }

    private void checkNotExisting(Map A, int key) {
        if (A.containsKey(key)) throw new IllegalArgumentException("Product already exists");
    }

    private void checkExisting(Map A, int key) {
        if (!A.containsKey(key)) throw new IllegalArgumentException("Product does not exist");
    }

    private static class Product {
        private String name;
        private Double cost;

        Product(String name, Double cost) {
            this.name = name;
            this.cost = cost;
        }

        private String getName() {
            return name;
        }

        private Double getCost() {
            return cost;
        }

        private void changeName(String newName) {
            this.name = newName;
        }

        private void changeCost(Double newCost) {
            this.cost = newCost;
        }
    }
}
