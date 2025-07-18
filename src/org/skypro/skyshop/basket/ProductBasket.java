package org.skypro.skyshop.basket;
import org.skypro.skyshop.product.Product;

public class ProductBasket {
    private Product[] products = new Product[3]; // Все продукты будут в массиве
    private int count = 0;

    // Добавление продукта
    public void addProduct(Product product) {
        if (count < 3) {
            products[count] = product;
            count++;
        } else {
            System.out.println("Невозможно добавить продукт - корзина полна!");
        }
    }

    // Общая стоимость
    public int getTotalPrice() {
        int total = 0;
        for (int i = 0; i < count; i++) {
            total += products[i].getPriceOfProduct();
        }
        return total;
    }

    // Печать содержимого
    public void printBasket() {
        if (count == 0) {
            System.out.println("В корзине пусто");
            return;
        }

        System.out.println("--- Содержимое корзины ---");
        for (int i = 0; i < count; i++) {
            Product p = products[i];
            System.out.println(p.getNameOfProduct() + ": " + p.getPriceOfProduct() + " руб.");
        }
        System.out.println("--------------------------");
        System.out.println("Итого: " + getTotalPrice() + " руб.");
    }

    // Проверка наличия
    public boolean containsProduct(String name) {
        for (int i = 0; i < count; i++) {
            if (products[i].getNameOfProduct().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    // Очистка корзины
    public void clearBasket() {
        for (int i = 0; i < count; i++) {
            products[i] = null;
        }
        count = 0;
        System.out.println("Корзина очищена!");

    }
}
