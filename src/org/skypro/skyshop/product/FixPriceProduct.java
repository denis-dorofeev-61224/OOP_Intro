package org.skypro.skyshop.product;

public enum FixPriceProduct {
    // Элементы enum с их фиксированными параметрами
    DOCKER_IVORY_NERPA("Обложка для паспорта нерпа- цвет молочный", 1700),
    DOCKER_STEEL_NERPA("Обложка для паспорта нерпа-цвет стальной", 1700);


    // Поля
    private final String nameOfProduct;  // Название товара
    private final int fixPrice;         // Фиксированная цена

    // Конструктор enum
    FixPriceProduct(String nameOfProduct, int fixPrice) {
        this.nameOfProduct = nameOfProduct;//работа с полем nameOfProduct согласно условию
        this.fixPrice = fixPrice;
    }

    // Геттеры
    public String getNameOfProduct() {
        return nameOfProduct;
    }

    public int getPrice() {// работа метода getPrice согласно условию п.4
        return fixPrice;
    }
}