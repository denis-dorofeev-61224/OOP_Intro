package org.skypro.skyshop;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello JavaDeveloper!");

        // 1. Создаём продукты всех типов
        Product bag1 = new SimpleProduct("Женская сумка ShellBlack", 9900);
        Product bag2 = new DiscountedProduct("Женская сумка HoboBeige", 8500, 15);
        Product bag3 = new SimpleProduct("Сумка для телефона NipperBlue", 8000);
        Product nerpa = new FixPriceProductAdapter(FixPriceProduct.DOCKER_IVORY_NERPA);

        // 2. Создаём корзину
        ProductBasket basket = new ProductBasket();

        // 3. Демонстрация работы (все оригинальные тесты + новые проверки)
        System.out.println("\n=== Тест 1: Добавление разных типов товаров ===");
        basket.addProduct(bag1);
        basket.addProduct(bag2);
        basket.addProduct(nerpa);
        basket.addProduct(bag3); // Не добавится (корзина полна)

        System.out.println("\n=== Тест 2: Новый формат вывода корзины ===");
        basket.printBasket();

        System.out.println("\n=== Тест 3: Проверка общей стоимости ===");
        System.out.println("Ожидаемая сумма: " + (9900 + 7225 + 1700));
        System.out.println("Фактическая сумма: " + basket.getTotalPrice() + " руб.");

        System.out.println("\n=== Тест 4: Поиск товаров ===");
        System.out.println("Есть ли 'HoboBeige'? " + basket.containsProduct("HoboBeige"));
        System.out.println("Есть ли 'нерпа'? " + basket.containsProduct("нерпа"));

        System.out.println("\n=== Тест 5: Проверка специальных товаров ===");
        System.out.println("Ожидаем 2 специальных товара (со скидкой и фикс.цена)");

        System.out.println("\n=== Тест 6: Очистка корзины ===");
        basket.clearBasket();
        basket.printBasket();
    }
}