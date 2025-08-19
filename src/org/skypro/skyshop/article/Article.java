package org.skypro.skyshop.article;
import org.skypro.skyshop.search.Searchable;

public class Article implements Searchable  {
    //добавляем поля по условию п.1
    private final String articleTitle;
    private final String textOfArticle;

    //задаём конструктор
    public   Article(String articleTitle,String textOfArticle){
        this.articleTitle=articleTitle;
        this.textOfArticle=textOfArticle;
    }
    //задаём геттеры
    public String getArticleTitle(){
        return articleTitle;
    }
    public String getTextOfArticle(){
        return textOfArticle;
    }

    // Добавим toString() по заданию
    @Override
    public String toString() {
        return articleTitle + "\n" + textOfArticle;
    }
    //ПЕРЕОПРЕДЕЛЯЕМ МЕТЫ ИНТЕРФЕЙСА Т.К. ОБЯЗАНЫ+по заданию так

    // Реализация методов интерфейса (строго по заданию)
    @Override
    public String getSearchTerm() {
        return toString(); // Как указано в задании: "можно просто возвращать строку из toString"
    }

    @Override
    public String getContentType() {
        return "ARTICLE"; // Как требуется в задании
    }

    @Override
    public String getName() {
        return articleTitle; // Возвращаем название статьи как имя
    }

}
