package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;
import java.util.*;

public class ProductBasket {
    // ЗАМЕНА: List<Product> -> Map<String, List<Product>>
    private final Map<String, List<Product>> productsMap = new HashMap<>();

    // Добавление продукта
    public void addProduct(Product product) {
        String productName = product.getNameOfProduct();

        // НОВАЯ ЛОГИКА: проверяем есть ли уже такое имя в мапе
        if (productsMap.containsKey(productName)) {
            // Если есть - добавляем в существующий список
            productsMap.get(productName).add(product);
        } else {
            // Если нет - создаем новый список и кладем в мапу
            List<Product> productList = new ArrayList<>();
            productList.add(product);
            productsMap.put(productName, productList);
        }
    }

    // Общая стоимость
    public int getTotalPrice() {
        int total = 0;
        // ИЗМЕНЕНИЕ: перебираем значения мапы (каждый значение - список продуктов)
        for (List<Product> productList : productsMap.values()) {
            for (Product product : productList) {
                total += product.getPriceOfProduct();
            }
        }
        return total;
    }

    // Печать содержимого
    public void printBasket() {
        if (productsMap.isEmpty()) {
            System.out.println("В корзине пусто");
            return;
        }

        System.out.println("--- Содержимое корзины ---");
        // ИЗМЕНЕНИЕ: перебираем все записи мапы
        for (Map.Entry<String, List<Product>> entry : productsMap.entrySet()) {
            String productName = entry.getKey();
            List<Product> productList = entry.getValue();

            // Для каждого продукта в списке выводим информацию
            for (Product product : productList) {
                System.out.println(productName + ": " + product.getPriceOfProduct() + " руб.");
            }
        }
        System.out.println("--------------------------");
        System.out.println("Итого: " + getTotalPrice() + " руб.");
    }

    // Проверка наличия
    public boolean containsProduct(String name) {
        // УЛУЧШЕНИЕ: теперь проверка за O(1) вместо O(n)
        return productsMap.containsKey(name);
    }

    // Очистка корзины
    public void clearBasket() {
        productsMap.clear();
        System.out.println("Корзина очищена!");
    }

    // Удаление всех продуктов с указанным именем
    public List<Product> removeProductByName(String name) {
        // УЛУЧШЕНИЕ: теперь удаление за O(1) вместо O(n)
        if (productsMap.containsKey(name)) {
            return productsMap.remove(name);
        }
        return new ArrayList<>();
    }
}