package org.skypro.skyshop.search;

import org.skypro.skyshop.exeptions.BestResultNotFound;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors; //  Добавляем этот импорт

public class SearchEngine {
    private final Set<Searchable> searchableItems = new HashSet<>();

    public SearchEngine() {
    }

    public void add(Searchable item) {
        searchableItems.add(item);
    }

    // ПЕРЕПИСАННЫЙ МЕТОД с использованием Stream API
    public Set<Searchable> search(String query) throws BestResultNotFound {
        // Используем Stream API для фильтрации и коллекции
        Set<Searchable> results = searchableItems.stream() // Создаем стрим из всех элементов
                .filter(item -> item.getSearchTerm().toLowerCase().contains(query.toLowerCase())) // Фильтруем
                .collect(Collectors.toCollection( // Собираем в коллекцию
                        () -> new TreeSet<>(new SearchableComparator()) // Supplier: создаем TreeSet с компаратором
                ));

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

    // Внутренний класс-компаратор для сортировки (без изменений)
    private static class SearchableComparator implements Comparator<Searchable> {
        @Override
        public int compare(Searchable o1, Searchable o2) {
            // Сравниваем по длине имени (от большего к меньшему)
            int lengthCompare = Integer.compare(o2.getName().length(), o1.getName().length());

            if (lengthCompare != 0) {
                return lengthCompare; // Если длины разные - возвращаем результат сравнения длин
            }

            // Если длины одинаковые - сравниваем по алфавиту (натуральный порядок)
            return o1.getName().compareTo(o2.getName());
        }
    }
}