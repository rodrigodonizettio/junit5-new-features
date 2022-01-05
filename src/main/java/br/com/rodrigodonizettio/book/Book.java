package br.com.rodrigodonizettio.book;

public class Book {
    private String title;
    private Integer numberOfChapters;


    private Book(String title) {
        this.title = title;
    }

    public static Book fromTitle(String title) {
        return new Book(title);
    }

    public String getTitle() {
        return title;
    }

    public Integer getNumberOfChapters() {
        return numberOfChapters;
    }
}
