package org.skypro.skyshop.search;

import org.skypro.skyshop.exeptions.BestResultNotFound;
import java.util.ArrayList;
import java.util.List;

public class SearchEngine {
    // Заменяем массив на список
    private final List<Searchable> searchableItems = new ArrayList<>();

    // Конструктор теперь без параметров (емкость не нужна)
    public SearchEngine() {
    }

    // Добавление элемента (теперь без ограничений)
    public void add(Searchable item) {
        searchableItems.add(item);
    }

    // Поиск ВСЕХ результатов (без ограничения в 5)
    public List<Searchable> search(String query) throws BestResultNotFound {
        List<Searchable> results = new ArrayList<>();

        for (Searchable item : searchableItems) {
            if (item.getSearchTerm().toLowerCase().contains(query.toLowerCase())) {
                results.add(item);
            }
        }

        if (results.isEmpty()) {
            throw new BestResultNotFound(query);
        }

        return results;
    }

    // Метод findBestMatch остаётся почти без изменений
    public Searchable findBestMatch(String search) throws BestResultNotFound {
        if (search == null || search.isBlank()) {
            throw new IllegalArgumentException("Поисковый запрос не может быть пустым");
        }

        Searchable bestMatch = null;
        int maxCount = -1;

        for (Searchable item : searchableItems) {
            String searchTerm = item.getSearchTerm().toLowerCase();
            String searchLower = search.toLowerCase();
            int count = countSubstringOccurrences(searchTerm, searchLower);

            if (count > maxCount) {
                maxCount = count;
                bestMatch = item;
            }
        }

        if (bestMatch == null) {
            throw new BestResultNotFound(search);
        }

        return bestMatch;
    }

    // Этот метод остаётся без изменений
    private int countSubstringOccurrences(String str, String substring) {
        int count = 0;
        int index = 0;
        int substringLength = substring.length();

        if (substringLength == 0) return 0;

        while ((index = str.indexOf(substring, index)) != -1) {
            count++;
            index += substringLength;
        }
        return count;
    }
}