package org.skypro.skyshop.product;

public class SimpleProduct extends Product {
    private final int simplePrice;

    public SimpleProduct(String nameOfProduct, int simplePrice) {
        super(nameOfProduct, simplePrice);

        // Проверка цены (должна быть > 0)
        if (simplePrice <= 0) {
            throw new IllegalArgumentException(
                    "Цена продукта должна быть больше 0! Получено: " + simplePrice
            );
        }

        this.simplePrice = simplePrice;
    }

    @Override
    public int getPriceOfProduct() {
        return simplePrice;
    }

    @Override
    public String toString() {
        return getNameOfProduct() + ": " + getPriceOfProduct() + " руб.";
    }
}