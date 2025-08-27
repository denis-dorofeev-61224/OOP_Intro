package org.skypro.skyshop.exeptions;

public class BestResultNotFound extends Exception {

    public BestResultNotFound(String query) {
        super("Для запроса '" + query + "' ничего не найдено!");
    }
}
