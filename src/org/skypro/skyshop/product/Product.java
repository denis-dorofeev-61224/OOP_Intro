package org.skypro.skyshop.product;
import org.skypro.skyshop.search.Searchable;
import java.util.Objects;//добавляем импорт без него не работает))

public class Product implements Searchable {
    private final String nameOfProduct;
    private final int priceOfProduct;

    // Модифицированный конструктор с валидацией
    public Product(String nameOfProduct, int priceOfProduct) {
        if (nameOfProduct == null || nameOfProduct.isBlank()) {
            throw new IllegalArgumentException(
                    "Название продукта не может быть null, пустым или состоять только из пробелов!"
            );
        }
        this.nameOfProduct = nameOfProduct;
        this.priceOfProduct = priceOfProduct;
    }

    // Геттеры остаются без изменений
    public String getNameOfProduct() {
        return nameOfProduct;
    }

    public int getPriceOfProduct() {
        return priceOfProduct;
    }

    // Реализация методов интерфейса Searchable
    @Override
    public String getSearchTerm() {
        return nameOfProduct;
    }

    @Override
    public String getContentType() {
        return "PRODUCT";
    }

    @Override
    public String getName() {
        return nameOfProduct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(nameOfProduct, product.nameOfProduct);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameOfProduct);
    }
}