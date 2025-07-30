package org.skypro.skyshop.product;
/**
 * Адаптер для преобразования FixPriceProduct (enum) в Product.
 */
public class FixPriceProductAdapter extends Product {
    private final FixPriceProduct fixPriceProduct; // Ссылка на элемент enum

    public FixPriceProductAdapter(FixPriceProduct product) {
        super(product.getNameOfProduct()); // Передаём имя в конструктор Product
        this.fixPriceProduct = product;
    }

    @Override
    public int getPriceOfProduct() {

        return fixPriceProduct.getPrice(); // Делегируем вызов enum
    }
    //переопределяем новые абстрактные методы isSpecial+toString СОГЛАСНО п.6 УСЛОВИЯ!!!
    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return getNameOfProduct() + ": Фиксированная цена " + getPriceOfProduct();
    }

}