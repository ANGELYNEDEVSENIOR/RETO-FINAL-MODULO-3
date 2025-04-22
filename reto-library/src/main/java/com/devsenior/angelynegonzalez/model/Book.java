package com.devsenior.angelynegonzalez.model;

public class Book {
 //estamos definiendo los atributos
    private String isbn;
    private String title;
    private String author;
    private boolean isBorrowed; // este atributo sirve para ver si el libro ya esta prestado
 //estamos definiendo el constructor   
    public Book(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.isBorrowed = false; // por defecto el libro no esta prestado
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
    ///////////////////AÑADIDO////////////////////
    public boolean isBorrowed() {
        return isBorrowed;
    }
    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }
    @Override
    public String toString() {
        String status = isBorrowed ? "PRESTADO" : "DISPONIBLE";
        return "ISBN: " + isbn + ", Título: " + title + ", Autor: " + author + " (" + status + ")";
    }
}
