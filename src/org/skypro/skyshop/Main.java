package org.skypro.skyshop;

import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.Searchable;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello JavaDeveloper!");

        // 1. Создаём продукты
        Product bag1 = new Product("Женская сумка ShellBlack", 9900);
        Product bag2 = new Product("Женская сумка HoboBeige", 8500);
        Product bag3 = new Product("Сумка для телефона NipperBlue", 8000);
        Product bag4 = new Product("Рюкзак SharkBlack", 16000);
        Product bag5 = new Product("Сумка-ракушка", 12500);
        Product bag6 = new Product("Сумка-лукошко", 13500);

        // 2. Создаём статьи
        Article article1 = new Article("Новинка!", "Встречаем сумку-ракушку со дна океана!!!");
        Article article2 = new Article("Новинка!", "Встречаем сумку-лукошко.Для любитилей торб.");

        // 3. Тестирование корзины (оригинальный код)
        ProductBasket basket = new ProductBasket();
        System.out.println("=== Тест 1: Корзина ===");
        basket.addProduct(bag1);
        basket.addProduct(bag2);
        basket.printBasket();

        // 4. Тестирование SearchEngine (ПРОСТОЙ И ПОНЯТНЫЙ ТЕСТ)
        System.out.println("\n=== Тест 2: SearchEngine ===");
        SearchEngine engine = new SearchEngine(10);

        // Добавляем всё в движок
        engine.add(bag1);
        engine.add(bag2);
        engine.add(bag3);
        engine.add(bag4);
        engine.add(bag5);
        engine.add(bag6);
        engine.add(article1);
        engine.add(article2);

        // Тест поиска - всё прямо здесь
        System.out.println("\nПоиск 'ракушка':");
        Searchable[] results = engine.search("ракушка");
        for (int i = 0; i < results.length; i++) {
            if (results[i] != null) {
                System.out.println((i+1) + ". " + results[i].getStringRepresentation());
            }
        }

        System.out.println("\nПоиск 'сумка':");
        results = engine.search("сумка");
        for (int i = 0; i < results.length; i++) {
            if (results[i] != null) {
                System.out.println((i+1) + ". " + results[i].getStringRepresentation());
            }
        }
    }
}