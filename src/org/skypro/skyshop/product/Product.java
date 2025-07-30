package org.skypro.skyshop.product;

public abstract class Product {
    private final String nameOfProduct;
    //создаём конструктр с параметрами
    public Product(String nameOfProduct){

        this.nameOfProduct=nameOfProduct;
    }
    //теперь обозначим геттеры для полей
    // Геттеры
    public String getNameOfProduct() {

        return nameOfProduct;
    }

    // Абстрактный метод для получения цены (реализуется в наследниках)
    public abstract int getPriceOfProduct();{
        //согласно условию сделали метод
        //getPriceOfProduct- абстрактным
}
    public abstract boolean isSpecial();{

    }
    public abstract String toString();{

    }

}
