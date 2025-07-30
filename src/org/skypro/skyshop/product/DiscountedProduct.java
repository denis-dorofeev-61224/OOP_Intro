package org.skypro.skyshop.product;

public class DiscountedProduct extends Product {
    private final int simplePrice;  // Чёткое именование для "чистой" цены
    private int discountInPercent;// скидочная цена  в процентах

    // конструктор

    public DiscountedProduct(String nameOfProduct, int simplePrice, int discountInPercent) {
        super(nameOfProduct);
        this.simplePrice = simplePrice;
        this.discountInPercent = discountInPercent;
    }
    // переопределяем метод getPriceOfProduct
    @Override
    public int getPriceOfProduct() {
        return simplePrice - (simplePrice * discountInPercent / 100);  // Возвращаем цену "как есть"
    }
    //переопределяем новые абстрактные методы isSpecial+toString СОГЛАСНО п.6 УСЛОВИЯ!!!
    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return getNameOfProduct() + ": " + getPriceOfProduct() + " (" + discountInPercent + "%)";
    }
}
