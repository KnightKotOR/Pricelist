import java.util.HashMap;
import java.util.Map;


public class PriceList {
    Map<Integer, Double> priceList = new HashMap<>();
    Map<Integer, String> productList = new HashMap<>();

    public void addProduct(int code, String name, Double cost) {
        if (priceList.containsKey(code) || productList.containsKey(code))
            throw new IllegalArgumentException("Product already exists");
        priceList.put(code, cost);
        productList.put(code, name);
    }

    public void removeProduct(int code) {
        if (!priceList.containsKey(code) || !productList.containsKey(code))
            throw new IllegalArgumentException("Product does not exist");
        productList.remove(code);
        priceList.remove(code);
    }

    public void changeName(int code, String newName) {
        if (!priceList.containsKey(code) || !productList.containsKey(code))
            throw new IllegalArgumentException("Product does not exist");
        productList.replace(code, newName);
    }

    public void changeCost(int code, Double newCost) {
        if (!priceList.containsKey(code) || !productList.containsKey(code))
            throw new IllegalArgumentException("Product does not exist");
        priceList.replace(code, newCost);
    }

    public Double determinePrice(int code, int number) {
        if (!priceList.containsKey(code) || !productList.containsKey(code))
            throw new IllegalArgumentException("Product does not exist");
        Double cost = priceList.get(code);
        return cost * number;
    }
}
