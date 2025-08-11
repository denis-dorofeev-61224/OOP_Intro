package org.skypro.skyshop.search;

import org.skypro.skyshop.exeptions.BestResultNotFound;

public class SearchEngine {
    private final Searchable[] searchableItems;
    private int currentSize;

    public SearchEngine(int capacity) {
        this.searchableItems = new Searchable[capacity];
        this.currentSize = 0;
    }

    public void add(Searchable item) {
        if (currentSize < searchableItems.length) {
            searchableItems[currentSize] = item;
            currentSize++;
        }
    }

    public Searchable[] search(String query) throws BestResultNotFound {
        Searchable[] results = new Searchable[5];
        int resultCount = 0;

        for (int i = 0; i < currentSize && resultCount < 5; i++) {
            if (searchableItems[i] != null &&
                    searchableItems[i].getSearchTerm().toLowerCase()
                            .contains(query.toLowerCase())) {
                results[resultCount++] = searchableItems[i];
            }
        }

        if (resultCount == 0) {
            throw new BestResultNotFound(query);
        }

        Searchable[] finalResults = new Searchable[resultCount];
        System.arraycopy(results, 0, finalResults, 0, resultCount);
        return finalResults;
    }

    public Searchable findBestMatch(String search) throws BestResultNotFound {
        if (search == null || search.isBlank()) {
            throw new IllegalArgumentException("Поисковый запрос не может быть пустым");
        }

        Searchable bestMatch = null;
        int maxCount = -1;

        for (int i = 0; i < currentSize; i++) {
            Searchable item = searchableItems[i];
            if (item != null) {
                String searchTerm = item.getSearchTerm().toLowerCase();
                String searchLower = search.toLowerCase();
                int count = countSubstringOccurrences(searchTerm, searchLower);

                if (count > maxCount) {
                    maxCount = count;
                    bestMatch = item;
                }
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