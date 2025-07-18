package org.skypro.skyshop.product;

public class Product {
    private final String nameOfProduct;
    private final int priceOfProduct;
    //создаём конструктр с параметрами
    public Product(String nameOfProduct,int priceOfProduct){
        this.nameOfProduct=nameOfProduct;
        this.priceOfProduct=priceOfProduct;
    }
    //теперь обозначим геттеры для полей
    // Геттеры
    public String getNameOfProduct() {
        return nameOfProduct;
    }

    public int getPriceOfProduct() {
        return priceOfProduct;
    }

}
