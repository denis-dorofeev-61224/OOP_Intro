package org.skypro.skyshop.search;

public interface Searchable {
    // Возвращает текст для поиска (например: "Название товара" или "Название статьи Текст статьи")
    String getSearchTerm();

    // Возвращает тип контента ("PRODUCT" или "ARTICLE")
    String getContentType();

    // Возвращает имя объекта (например: название товара/статьи)
    String getName();

    // Дефолтная реализация (по заданию)
    default String getStringRepresentation() {
        return getName() + " — " + getContentType();
    }
}
