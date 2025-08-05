package org.skypro.skyshop.search;

public class SearchEngine {
    private final Searchable[] searchableItems;
    private int currentSize;

    // Конструктор с заданием размера массива
    public SearchEngine(int capacity) {
        this.searchableItems = new Searchable[capacity];
        this.currentSize = 0;
    }

    // Метод добавления элемента в массив
    public void add(Searchable item) {
        if (currentSize < searchableItems.length) {
            searchableItems[currentSize] = item;
            currentSize++;
        } else {
            System.out.println("Достигнут максимальный размер массива!");
        }
    }

    // Метод поиска (возвращает максимум 5 результатов)
    public Searchable[] search(String query) {
        Searchable[] results = new Searchable[5];
        int resultCount = 0;

        for (int i = 0; i < currentSize && resultCount < 5; i++) {
            Searchable item = searchableItems[i];
            if (item != null && item.getSearchTerm().contains(query)) {
                results[resultCount] = item;
                resultCount++;
            }
        }
        return results;
    }
}
