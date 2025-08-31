package org.skypro.skyshop.search;

import org.skypro.skyshop.exeptions.BestResultNotFound;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class SearchEngine {
    private final Set<Searchable> searchableItems = new HashSet<>();

    public SearchEngine() {
    }

    public void add(Searchable item) {
        searchableItems.add(item);
    }

    // ЗАМЕНА: TreeMap на TreeSet с компаратором
    public Set<Searchable> search(String query) throws BestResultNotFound {
        // TreeSet с компаратором для сортировки
        Set<Searchable> results = new TreeSet<>(new SearchableComparator());

        for (Searchable item : searchableItems) {
            if (item.getSearchTerm().toLowerCase().contains(query.toLowerCase())) {
                results.add(item); // Добавляем сам объект, а не в Map
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

    // Внутренний класс-компаратор для сортировки
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