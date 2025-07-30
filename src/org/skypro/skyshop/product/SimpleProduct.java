package org.skypro.skyshop.product;

public class SimpleProduct extends Product {
    private final int simplePrice;  // Чёткое именование для "чистой" цены

    public SimpleProduct(String nameOfProduct, int simplePrice) {
        super(nameOfProduct);
        this.simplePrice = simplePrice;
    }

    @Override
    public int getPriceOfProduct() {
        return simplePrice;  // Возвращаем цену "как есть"
    }
    //переопределяем новые абстрактные методы isSpecial+toString СОГЛАСНО п.6 УСЛОВИЯ!!!

    @Override
    public boolean isSpecial() {
        return false;
    }

    @Override
    public String toString() {
        return getNameOfProduct() + ": " + getPriceOfProduct();
    }
}
