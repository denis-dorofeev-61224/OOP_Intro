package org.skypro.skyshop;

import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.exeptions.BestResultNotFound;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.Searchable;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello JavaDeveloper!");

        // 1. Создаём продукты (как у тебя было)
        Product bag1 = new Product("Женская сумка ShellBlack", 9900);
        Product bag2 = new Product("Женская сумка HoboBeige", 8500);
        Product bag3 = new Product("Сумка для телефона NipperBlue", 8000);
        Product bag4 = new Product("Рюкзак SharkBlack", 16000);
        Product bag5 = new Product("Сумка-ракушка", 12500);
        Product bag6 = new Product("Сумка-лукошко", 13500);

        // 2. Создаём статьи (как у тебя было)
        Article article1 = new Article("Новинка!", "Встречаем сумку-ракушку со дна океана!!!");
        Article article2 = new Article("Новинка!", "Встречаем сумку-лукошко.Для любитилей торб.");

        // 3. Тест валидации Product (новое)
        System.out.println("\n=== Тест 1: Валидация Product ===");
        try {
            Product invalidProduct = new Product("", 1000); // Пустое имя!
            System.out.println("ОШИБКА: Исключение не сработало!");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        // 4. Тестирование корзины (как у тебя было)
        System.out.println("\n=== Тест 2: Корзина ===");
        ProductBasket basket = new ProductBasket();
        basket.addProduct(bag1);
        basket.addProduct(bag2);
        basket.printBasket();

        // 5. Добавляем наследников Product (новое)
        SimpleProduct simpleBag = new SimpleProduct("Простая сумка", 5000);
        DiscountedProduct discountedBag = new DiscountedProduct("Сумка со скидкой", 10000, 20);
        basket.addProduct(simpleBag);
        basket.addProduct(discountedBag);
        System.out.println("\nПосле добавления наследников:");
        basket.printBasket();

        // 6. Тестирование SearchEngine с обработкой исключений
        System.out.println("\n=== Тест 3: SearchEngine ===");
        SearchEngine engine = new SearchEngine(10);

// Добавляем все объекты (как было)
        engine.add(bag1);
        engine.add(bag2);
        engine.add(bag3);
        engine.add(bag4);
        engine.add(bag5);
        engine.add(bag6);
        engine.add(article1);
        engine.add(article2);
        engine.add(simpleBag);
        engine.add(discountedBag);

// Улучшенный блок поиска с обработкой исключений
        System.out.println("\n=== Тестирование поиска ===");

// Поиск 1: Успешный случай
        try {
            System.out.println("Поиск 'ракушка':");
            Searchable[] results = engine.search("ракушка");
            for (int i = 0; i < results.length && results[i] != null; i++) {
                System.out.println((i + 1) + ". " + results[i].getStringRepresentation());
            }
        } catch (BestResultNotFound e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

// Поиск 2: Успешный случай (широкий запрос)
        try {
            System.out.println("\nПоиск 'сумка':");
            Searchable[] results = engine.search("сумка");
            for (int i = 0; i < results.length && results[i] != null; i++) {
                System.out.println((i + 1) + ". " + results[i].getStringRepresentation());
            }
        } catch (BestResultNotFound e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

// Поиск 3: Специальный тест исключения
        try {
            System.out.println("\nПоиск 'несуществующий_запрос':");
            Searchable[] results = engine.search("несуществующий_запрос");
            for (int i = 0; i < results.length && results[i] != null; i++) {
                System.out.println((i + 1) + ". " + results[i].getStringRepresentation());
            }
        } catch (BestResultNotFound e) {
            System.out.println("Ожидаемое исключение: " + e.getMessage());
        }

// 7. Дополнительный тест (новый)
        System.out.println("\n=== Тест 4: Граничные случаи ===");

// Тест пустого запроса
        try {
            System.out.println("Поиск пустой строки:");
            Searchable[] results = engine.search("");
            for (int i = 0; i < results.length && results[i] != null; i++) {
                System.out.println((i + 1) + ". " + results[i].getStringRepresentation());
            }
        } catch (BestResultNotFound e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

// Тест null-запроса
        try {
            System.out.println("\nПоиск null:");
            Searchable[] results = engine.search(null);
            for (int i = 0; i < results.length && results[i] != null; i++) {
                System.out.println((i + 1) + ". " + results[i].getStringRepresentation());
            }
        } catch (BestResultNotFound e) {
            System.out.println("Ошибка: " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("Поймано NullPointerException: " + e.getMessage());
        }
        // 8. Проверка цен SimpleProduct и DiscountedProduct
        System.out.println("\n=== Тест 8: Проверка цен ===");

        // Проверка SimpleProduct
        System.out.println("--- SimpleProduct ---");
        try {
            SimpleProduct validSimple = new SimpleProduct("Корректная цена", 1500);
            System.out.println("OK: " + validSimple + " (цена: " + validSimple.getPriceOfProduct() + ")");

            SimpleProduct zeroPrice = new SimpleProduct("Нулевая цена", 0);
            System.out.println("ОШИБКА: Исключение не сработало для нулевой цены!");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: Поймано исключение для SimpleProduct - " + e.getMessage());
        }

        // Проверка DiscountedProduct
        System.out.println("\n--- DiscountedProduct ---");
        try {
            // Корректные данные
            DiscountedProduct validDiscounted = new DiscountedProduct("Корректные данные", 2000, 30);
            System.out.println("OK: " + validDiscounted + " (итоговая цена: " + validDiscounted.getPriceOfProduct() + ")");

            // Нулевая базовая цена
            DiscountedProduct zeroBasePrice = new DiscountedProduct("Нулевая база", 0, 10);
            System.out.println("ОШИБКА: Исключение не сработало для нулевой базовой цены!");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: Поймано исключение (базовая цена) - " + e.getMessage());
        }

        try {
            // Скидка < 0
            DiscountedProduct negativeDiscount = new DiscountedProduct("Отрицательная скидка", 1000, -5);
            System.out.println("ОШИБКА: Исключение не сработало для отрицательной скидки!");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: Поймано исключение (отрицательная скидка) - " + e.getMessage());
        }

        try {
            // Скидка > 100
            DiscountedProduct bigDiscount = new DiscountedProduct("Слишком большая скидка", 1000, 150);
            System.out.println("ОШИБКА: Исключение не сработало для скидки >100%!");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: Поймано исключение (скидка >100%) - " + e.getMessage());
        }

        try {
            // Граничные значения скидки (0% и 100%)
            DiscountedProduct zeroDiscount = new DiscountedProduct("Нулевая скидка", 1000, 0);
            DiscountedProduct fullDiscount = new DiscountedProduct("100% скидка", 1000, 100);
            System.out.println("OK: Граничные значения скидки работают (0% и 100%)");
        } catch (IllegalArgumentException e) {
            System.out.println("ОШИБКА: Не должно быть исключения для скидки 0% или 100%!");
        }

        // 9. Тестирование поиска лучшего совпадения
        System.out.println("\n=== Тест 9: Поиск лучшего совпадения ===");

// Создаем специальные тестовые данные
        Article article3 = new Article("hello world", "hello hello hello world");
        Article article4 = new Article("hello", "just hello");
        engine.add(article3);
        engine.add(article4);

        try {
            System.out.println("Поиск лучшего совпадения для 'hello':");
            Searchable bestMatch = engine.findBestMatch("hello");
            System.out.println("Лучшее совпадение: " + bestMatch.getStringRepresentation());

            System.out.println("\nПоиск лучшего совпадения для 'сумка':");
            bestMatch = engine.findBestMatch("сумка");
            System.out.println("Лучшее совпадение: " + bestMatch.getStringRepresentation());

            // Тест с несуществующим запросом
            System.out.println("\nПоиск лучшего совпадения для 'xyz':");
            bestMatch = engine.findBestMatch("xyz");
            System.out.println("Лучшее совпадение: " + bestMatch.getStringRepresentation());
        } catch (BestResultNotFound e) {
            System.out.println("Ошибка: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}