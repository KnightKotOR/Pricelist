import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PriceListTest {
    PriceList test = new PriceList();

    @Test
        //Проверка на добавление
    void addProduct() {
        test.addProduct(0, "Хлеб", 50.0);
        assertTrue(test.priceList.get(0).equals(50.0) && test.productList.get(0).equals("Хлеб"));
    }

    @Test
        //Проверка на удаление
    void removeProduct() {
        test.addProduct(0, "Хлеб", 50.0);
        test.addProduct(1, "Чай", 380.0);
        test.removeProduct(0);
        assertTrue(!test.productList.containsKey(0) && !test.priceList.containsKey(0));
    }

    @Test
        //Проверка на изменение параметров товара
    void change() {
        test.addProduct(0, "Хлеб", 50.0);
        test.changeName(0, "Батон");
        assertTrue(test.priceList.get(0).equals(50.0) && test.productList.get(0).equals("Батон"));
        test.changeCost(0, 65.0);
        assertTrue(test.priceList.get(0).equals(65.0) && test.productList.get(0).equals("Батон"));
    }

    @Test
        //Проверка на расчет цены
    void determinePrice() {
        test.addProduct(0, "Сахар", 60.0);
        assertEquals(300.0, test.determinePrice(0, 5));
    }

    @Test
        //Проверка на исключения
    public void exceptions() {
        //Удаление несуществующего товара
        assertThrows(
                IllegalArgumentException.class,
                () -> test.removeProduct(0)
        );
        //Добавление существующего товара
        test.addProduct(0, "Хлеб", 50.0);
        assertThrows(
                IllegalArgumentException.class,
                () -> test.addProduct(0, "Хлеб", 50.0)
        );
        test.removeProduct(0);
        //Изменение имени/цены несуществующего товара
        assertThrows(
                IllegalArgumentException.class,
                () -> test.changeName(0, "Батон")
        );
        assertThrows(
                IllegalArgumentException.class,
                () -> test.changeCost(0, 65.0)
        );
        //Расчет цены несуществующего товара
        assertThrows(
                IllegalArgumentException.class,
                () -> test.determinePrice(0, 5)
        );
    }
}
