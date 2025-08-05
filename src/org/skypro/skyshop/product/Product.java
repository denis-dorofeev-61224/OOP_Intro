package org.skypro.skyshop.product;
import org.skypro.skyshop.search.Searchable;

public class Product implements Searchable {
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

    // //ПЕРЕОПРЕДЕЛЯЕМ МЕТЫ ИНТЕРФЕЙСА Т.К. ОБЯЗАНЫ+по заданию так
    // Реализация методов интерфейса (строго по заданию)
    @Override
    public String getSearchTerm() {
        return nameOfProduct; // Как указано: "возвращать имя товара"
    }

    @Override
    public String getContentType() {
        return "PRODUCT"; // Как требуется
    }

    @Override
    public String getName() {
        return nameOfProduct; // Имя товара
    }

}
