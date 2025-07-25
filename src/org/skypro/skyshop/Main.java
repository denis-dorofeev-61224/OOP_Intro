package org.skypro.skyshop;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.Product;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello JavaDeveloper!");

        // 1. Создаём продукты
        Product bag1 = new Product("Женская сумка ShellBlack", 9900);
        Product bag2 = new Product("Женская сумка HoboBeige", 8500);
        Product bag3 = new Product("Сумка для телефона NipperBlue", 8000);
        Product bag4 = new Product("Рюкзак SharkBlack", 16000); // Для теста переполнения

        // 2. Создаём корзину
        ProductBasket basket = new ProductBasket();

        // 3. Демонстрация работы методов:
        System.out.println("=== Тест 1: Добавление продуктов ===");
        basket.addProduct(bag1);
        basket.addProduct(bag2);
        basket.addProduct(bag3);
        basket.addProduct(bag4); // Попытка добавить 4-й продукт (корзина полна)

        System.out.println("\n=== Тест 2: Печать содержимого ===");
        basket.printBasket();

        System.out.println("\n=== Тест 3: Общая стоимость ===");
        System.out.println("Общая сумма: " + basket.getTotalPrice() + " руб.");

        System.out.println("\n=== Тест 4: Поиск товаров ===");
        System.out.println("Есть ли 'HoboBeige'? " + basket.containsProduct("HoboBeige"));
        System.out.println("Есть ли 'Рюкзак'? " + basket.containsProduct("Рюкзак"));

        System.out.println("\n=== Тест 5: Очистка корзины ===");
        basket.clearBasket();

        System.out.println("\n=== Тест 6: Проверка пустой корзины ===");
        basket.printBasket();
        System.out.println("Стоимость пустой корзины: " + basket.getTotalPrice() + " руб.");
        System.out.println("Есть ли 'ShellBlack' в пустой корзине? " + basket.containsProduct("ShellBlack"));
    }
}