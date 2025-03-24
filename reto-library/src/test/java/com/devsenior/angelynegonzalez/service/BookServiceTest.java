package com.devsenior.angelynegonzalez.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.devsenior.angelynegonzalez.exception.NotFoundException;

public class BookServiceTest {

    private BookService service;
    // con el beforeeach se ejecuta antes de cada test
     @BeforeEach
    void setup() {
        service = new BookService();
    }

    @Test
    void testAddBook() throws NotFoundException {
        // GIVEN - Preparar los datos de la prueba
        var isbn = "123456789";
        var title = "Aprendiendo Java";
        var author = "Cesar Diaz";

        // WHEN - Ejecutar el metodo a probar
        service.addBook(isbn, title, author);

        // THEN - Verificaciones que el metodo se ejecutó bien
        var book = service.getBookByIsbn(isbn);
        assertNotNull(book);
        assertEquals(title, book.getTitle());
        assertEquals(author, book.getAuthor());
    }

    @Test
    void testDeleteExistingBook() throws NotFoundException {
        // GIVEN
        var isbn = "12009654";
        var title = "Trono de cristal";
        var author = "Sara j Maas";
        service.addBook(isbn, title, author);

        // WHEN
        service.deleteBook(isbn);

        // THEN
        try {
            service.getBookByIsbn(isbn);
            fail();
        } catch (NotFoundException e) {
            assertTrue(true);
        }
    }

    @Test
    void testDeleteNonExistingBook() {
        // GIVEN
        var isbn = "12009654";

        // WHEN - THEN
        // el metodo debe lanzar una exception si el libro no existe
        assertThrows(NotFoundException.class,
                () -> {
                    service.deleteBook(isbn);
                });
    }

    @Test
    void testDeleteWithExistingBooksButNotGivenIsbn() {
        // GIVEN
        service.addBook("9876543210", "En busca de la felicidad", "Cesar Diaz");
        var isbn = "12009654";

        // WHEN - THEN
        assertThrows(NotFoundException.class,
                () -> service.deleteBook(isbn));
    }

    @Test
    void testGetAllBooks() {
        // GIVEN

        // WHEN
        var books = service.getAllBooks();

        // THEN
        assertNotNull(books);
        assertEquals(0, books.size());
    }

    @Test
    void testGetBookByIsbnWithWrongIsbn() {
        // GIVEN
        service.addBook("00123446", "El gato negro", "Edgar Allan Poe");

        var isbn = "0987621";

        // WHEN -THEN
        assertThrows(NotFoundException.class,
                () -> service.getBookByIsbn(isbn));
    }
}
