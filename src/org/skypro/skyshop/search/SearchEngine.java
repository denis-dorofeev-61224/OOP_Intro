package org.skypro.skyshop.search;

import org.skypro.skyshop.exeptions.BestResultNotFound;
import java.util.*;

public class SearchEngine {
    private final List<Searchable> searchableItems = new ArrayList<>();

    public SearchEngine() {
    }

    public void add(Searchable item) {
        searchableItems.add(item);
    }

    // ИЗМЕНЕНИЕ: возвращаем Map вместо List
    public Map<String, Searchable> search(String query) throws BestResultNotFound {
        // Используем TreeMap для автоматической сортировки по ключам (именам)
        Map<String, Searchable> results = new TreeMap<>();

        for (Searchable item : searchableItems) {
            if (item.getSearchTerm().toLowerCase().contains(query.toLowerCase())) {
                // Ключ - имя объекта, значение - сам объект
                results.put(item.getName(), item);
            }
        }

        if (results.isEmpty()) {
            throw new BestResultNotFound(query);
        }

        return results;
    }

    // findBestMatch остаётся без изменений
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