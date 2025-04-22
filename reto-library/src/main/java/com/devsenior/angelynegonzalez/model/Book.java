package com.devsenior.angelynegonzalez.model;

public class Book {
 //estamos definiendo los atributos
    private String isbn;
    private String title;
    private String author;
 //estamos definiendo el constructor   
    public Book(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
    }
// estamos definiendo los getters
    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
    @Override
    public String toString() {
        return "Título: " + title + ", Autor: " + author + ", ISBN: " + isbn;
    }

}
