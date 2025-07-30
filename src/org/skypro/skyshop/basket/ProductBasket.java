package org.skypro.skyshop.basket;
import org.skypro.skyshop.product.Product;

public class ProductBasket {
    private Product[] products = new Product[3]; // Массив для хранения товаров
    private int count = 0;

    // Добавление продукта (без изменений оставляем как было)
    public void addProduct(Product product) {
        if (count < 3) {
            products[count] = product;
            count++;
        } else {
            System.out.println("Невозможно добавить продукт - корзина полна!");
        }
    }

    // Общая стоимость (без изменений-оставляем как было)
    public int getTotalPrice() {
        int total = 0;
        for (int i = 0; i < count; i++) {
            total += products[i].getPriceOfProduct();
        }
        return total;
    }

    // ПЕРЕРАБОТАННЫЙ МЕТОД ВЫВОДА КОРЗИНЫ-тут поменяли
    public void printBasket() {
        if (count == 0) {
            System.out.println("В корзине пусто");
            return;
        }

        System.out.println("--- Содержимое корзины ---");
        int specialCount = 0; // Счётчик специальных товаров

        for (int i = 0; i < count; i++) {
            Product p = products[i];
            // Используем переопределённый toString() каждого товара
            System.out.println(p.toString()); // <-- Главное изменение!

            if (p.isSpecial()) { // Увеличиваем счётчик для специальных товаров
                specialCount++;
            }
        }

        System.out.println("--------------------------");
        System.out.println("Итого: " + getTotalPrice() + " руб.");
        System.out.println("Специальных товаров: " + specialCount); // <-- Новый вывод
    }

    // Проверка наличия (без изменений)
    public boolean containsProduct(String name) {
        for (int i = 0; i < count; i++) {
            if (products[i].getNameOfProduct().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    // Очистка корзины (без изменений)
    public void clearBasket() {
        for (int i = 0; i < count; i++) {
            products[i] = null;
        }
        count = 0;
        System.out.println("Корзина очищена!");
    }
}