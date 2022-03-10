import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PriceListTests {
    private final PriceList test = new PriceList();

    @BeforeEach
    public void setUp() {
        test.addProduct(0, "Жабургер", 50.0);
        test.addProduct(1, "Доллар", 120.38);
        test.addProduct(2, "Сансет Саспарилла", 300.0);
    }

    @Test
    public void addProduct() {
        //Проверка на добавление
        assertTrue(test.getName(0).equals("Жабургер") && test.getCost(0) == 50.0);
        assertTrue(test.getName(1).equals("Доллар") && test.getCost(1) == 120.38);
        assertTrue(test.getName(2).equals("Сансет Саспарилла") && test.getCost(2) == 300.0);
        //Проверка на добавление занятого кода
        assertThrows(
                IllegalArgumentException.class,
                () -> test.addProduct(0, "Хлеб", 35.0)
        );
        //Проверка на добавление товара с отрицательной ценой
        assertThrows(
                IllegalArgumentException.class,
                () -> test.addProduct(3, "Б. Ю. Александров", -55.0)
        );
    }

    @Test
    public void removeProduct() {
        //Проверка на удаление существующего товара
        test.removeProduct(0);
        assertThrows(
                IllegalArgumentException.class,
                () -> test.getName(0)
        );
        //Проверка на удаление несуществующего товара
        assertThrows(
                IllegalArgumentException.class,
                () -> test.removeProduct(0)
        );
    }

    @Test
    public void changeName() {
        //Проверка на изменение имени
        test.changeName(0, "Шлакоблокунь");
        assertEquals("Шлакоблокунь", test.getName(0));
        //Проверка на изменение имени несуществующего товара
        assertThrows(
                IllegalArgumentException.class,
                () -> test.changeName(4, "Капибара")
        );
    }

    @Test
    public void changeCost() {
        //Проверка на изменение цены
        test.changeCost(1, 150.42);
        assertEquals(150.42, test.getCost(1));
        //Проверка на изменение цены несуществующего товара
        assertThrows(
                IllegalArgumentException.class,
                () -> test.changeCost(4, 420.1)
        );
    }

    @Test
    public void determinePrice() {
        //Проверка на вычисление цены
        assertEquals(3000.0, test.determinePrice(2, 10));
        //Проверка на корректность числа
        assertThrows(
                IllegalArgumentException.class,
                () -> test.determinePrice(2, -10)
        );
        //Проверка на несуществующий товар
        assertThrows(
                IllegalArgumentException.class,
                () -> test.determinePrice(5, 1)
        );
    }
}
