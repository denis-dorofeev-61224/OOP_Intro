package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class ProductBasket {
    // Заменяем массив на список
    private final List<Product> products = new ArrayList<>();

    // Добавление продукта (теперь без ограничений)
    public void addProduct(Product product) {
        products.add(product);
    }

    // Общая стоимость
    public int getTotalPrice() {
        int total = 0;
        for (Product product : products) {
            total += product.getPriceOfProduct();
        }
        return total;
    }

    // Печать содержимого
    public void printBasket() {
        if (products.isEmpty()) {
            System.out.println("В корзине пусто");
            return;
        }

        System.out.println("--- Содержимое корзины ---");
        for (Product product : products) {
            System.out.println(product.getNameOfProduct() + ": " + product.getPriceOfProduct() + " руб.");
        }
        System.out.println("--------------------------");
        System.out.println("Итого: " + getTotalPrice() + " руб.");
    }

    // Проверка наличия
    public boolean containsProduct(String name) {
        for (Product product : products) {
            if (product.getNameOfProduct().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    // Очистка корзины
    public void clearBasket() {
        products.clear();
        System.out.println("Корзина очищена!");
    }

    // Удаление всех продуктов с указанным именем (без учёта регистра)
    public List<Product> removeProductByName(String name) {
        List<Product> removedProducts = new ArrayList<>();
        Iterator<Product> iterator = products.iterator();

        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getNameOfProduct().equalsIgnoreCase(name)) {
                removedProducts.add(product);
                iterator.remove();
            }
        }

        return removedProducts;
    }
}