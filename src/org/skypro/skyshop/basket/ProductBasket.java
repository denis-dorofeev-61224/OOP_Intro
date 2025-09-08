package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;
import java.util.*;
import java.util.stream.Collectors; // Добавляем импорт

public class ProductBasket {
    private final Map<String, List<Product>> productsMap = new HashMap<>();

    public void addProduct(Product product) {
        String productName = product.getNameOfProduct();
        if (productsMap.containsKey(productName)) {
            productsMap.get(productName).add(product);
        } else {
            List<Product> productList = new ArrayList<>();
            productList.add(product);
            productsMap.put(productName, productList);
        }
    }

    // ПЕРЕПИСАННЫЙ МЕТОД: Общая стоимость через Stream API
    public int getTotalPrice() {
        return productsMap.values() // Collection<List<Product>>
                .stream()           // Stream<List<Product>>
                .flatMap(Collection::stream) // Stream<Product> (преобразуем вложенные списки в плоский поток)
                .mapToInt(Product::getPriceOfProduct) // IntStream (преобразуем каждый продукт в его цену)
                .sum(); // Суммируем все цены
    }

    // ПЕРЕПИСАННЫЙ МЕТОД: Печать содержимого через Stream API
    public void printBasket() {
        if (productsMap.isEmpty()) {
            System.out.println("В корзине пусто");
            return;
        }

        System.out.println("--- Содержимое корзины ---");

        // Создаем плоский поток всех продуктов и выводим их
        productsMap.values() // Collection<List<Product>>
                .stream()    // Stream<List<Product>>
                .flatMap(Collection::stream) // Stream<Product>
                .forEach(product -> // Для каждого продукта выполняем вывод
                        System.out.println(product.getNameOfProduct() + ": " + product.getPriceOfProduct() + " руб.")
                );

        System.out.println("--------------------------");
        System.out.println("Итого: " + getTotalPrice() + " руб."); // Используем переписанный getTotalPrice()
    }

    public boolean containsProduct(String name) {
        return productsMap.containsKey(name);
    }

    public void clearBasket() {
        productsMap.clear();
        System.out.println("Корзина очищена!");
    }

    public List<Product> removeProductByName(String name) {
        if (productsMap.containsKey(name)) {
            return productsMap.remove(name);
        }
        return new ArrayList<>();
    }
}