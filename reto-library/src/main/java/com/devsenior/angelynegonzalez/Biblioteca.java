package com.devsenior.angelynegonzalez;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.devsenior.angelynegonzalez.model.Book;

public class Biblioteca {
    private List<Book> books;
    private Map<String, String> users; // Almacena nombre de usuario -> contraseña
    private String loggedInUser = null; // Para rastrear el usuario logueado
    private final Scanner scanner = new Scanner(System.in);

    public Biblioteca() {
        this.books = new ArrayList<>();
        this.users = new HashMap<>();
    }

    public void mostrarMenu() {
        System.out.println("\nMenú de la Biblioteca");
        if (loggedInUser == null) {
            System.out.println("5. Registrar una cuenta de usuario");
            System.out.println("6. Iniciar sesión");
            System.out.println("7. Salir");
        } else {
            System.out.println("1. Agregar Libros");
            System.out.println("2. Consultar un libro");
            System.out.println("3. Prestar Libros");
            System.out.println("4. Devolver Libros");
            System.out.println("6. Consultar mi biblioteca");
            System.out.println("7. Cerrar sesión");
            System.out.println("0. Salir del programa");
        }
        System.out.print("Seleccione una opción: ");
    }

    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        while (true) {
            biblioteca.mostrarMenu();
            int opcion = biblioteca.scanner.nextInt();
            biblioteca.scanner.nextLine(); // Consumir la nueva línea

            if (biblioteca.loggedInUser == null) {
                switch (opcion) {
                    case 5:
                        biblioteca.registerUserAccount();
                        break;
                    case 6:
                        biblioteca.loginUser();
                        break;
                    case 7:
                        biblioteca.exit();
                        return; // Salir del main
                    default:
                        System.out.println("Opción inválida o requiere inicio de sesión.");
                }
            } else {
                switch (opcion) {
                    case 1:
                        biblioteca.addBook();
                        break;
                    case 2:
                        biblioteca.consultBook();
                        break;
                    case 3:
                        biblioteca.loanBook();
                        break;
                    case 4:
                        biblioteca.returnBook();
                        break;
                    case 6:
                        biblioteca.viewMyLibrary();
                        break;
                    case 7:
                        biblioteca.logoutUser();
                        break;
                    case 0:
                        biblioteca.exit();
                        return; // Salir del main
                    default:
                        System.out.println("Opción inválida.");
                }
            }
        }
    }

    private void exit() {
        try {
            scanner.close();
        } catch (Exception e) {
            System.out.println("Error al cerrar el Scanner: " + e.getMessage());
        }
        System.out.println("¡Hasta luego!");
    }

    private void logoutUser() {
        loggedInUser = null;
        System.out.println("Sesión cerrada.");
    }

    private void viewMyLibrary() {
        if (books.isEmpty()) {
            System.out.println("La biblioteca está vacía.");
        } else {
            System.out.println("\nLibros en la biblioteca:");
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    private void registerUserAccount() {
        System.out.println("\n--- Registro de Usuario ---");
        System.out.print("Ingrese un nombre de usuario: ");
        String username = scanner.nextLine();

        if (users.containsKey(username)) {
            System.out.println("El nombre de usuario '" + username + "' ya existe. Por favor, elija otro.");
            return;
        }

        System.out.print("Ingrese una contraseña: ");
        String password = scanner.nextLine();

        users.put(username, password);
        System.out.println("Usuario '" + username + "' registrado exitosamente.");
    }

    private void loginUser() {
        System.out.println("\n--- Inicio de Sesión ---");
        System.out.print("Ingrese su nombre de usuario: ");
        String username = scanner.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String password = scanner.nextLine();

        if (users.containsKey(username) && users.get(username).equals(password)) {
            loggedInUser = username;
            System.out.println("Inicio de sesión exitoso para el usuario '" + username + "'.");
        } else {
            System.out.println("Credenciales inválidas. Inténtelo de nuevo.");
        }
    }

    private void returnBook() {
        System.out.print("\nIngrese el título del libro que desea devolver: ");
        String titleToReturn = scanner.nextLine();

        Book bookToReturn = findBookByTitle(titleToReturn);

        if (bookToReturn != null) {
            if (bookToReturn.isBorrowed()) {
                bookToReturn.setBorrowed(false);
                System.out.println("El libro '" + bookToReturn.getTitle() + "' ha sido devuelto.");
            } else {
                System.out.println("El libro '" + bookToReturn.getTitle() + "' no estaba prestado.");
            }
        } else {
            System.out.println("No se encontró ningún libro con el título '" + titleToReturn + "'.");
        }
    }

    private void loanBook() {
        System.out.print("\nIngrese el título del libro que desea prestar: ");
        String titleToBorrow = scanner.nextLine();

        Book bookToBorrow = findBookByTitle(titleToBorrow);

        if (bookToBorrow != null) {
            if (!bookToBorrow.isBorrowed()) {
                bookToBorrow.setBorrowed(true);
                System.out.println("El libro '" + bookToBorrow.getTitle() + "' ha sido prestado.");
            } else {
                System.out.println("El libro '" + bookToBorrow.getTitle() + "' ya está prestado.");
            }
        } else {
            System.out.println("No se encontró ningún libro con el título '" + titleToBorrow + "'.");
        }
    }

    private Book findBookByTitle(String titleToFind) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(titleToFind)) {
                return book;
            }
        }
        return null;
    }

    private void consultBook() {
        System.out.println("\n¿Cómo desea buscar el libro?");
        System.out.println("1. Por título");
        System.out.println("2. Por ISBN");
        System.out.print("Seleccione una opción: ");
        int searchOption = scanner.nextInt();
        scanner.nextLine(); // Consume la nueva línea

        if (searchOption == 1) {
            System.out.print("Ingrese el título del libro a buscar: ");
            String searchTitle = scanner.nextLine();
            boolean found = false;
            for (Book book : books) {
                if (book.getTitle().toLowerCase().contains(searchTitle.toLowerCase())) {
                    System.out.println("Libro encontrado:");
                    System.out.println(book);
                    found = true;
                }
            }
            if (!found) {
                System.out.println("No se encontraron libros con el título '" + searchTitle + "'.");
            }
        } else if (searchOption == 2) {
            System.out.print("Ingrese el ISBN del libro a buscar: ");
            String searchIsbn = scanner.nextLine();
            for (Book book : books) {
                if (book.getIsbn().equals(searchIsbn)) {
                    System.out.println("Libro encontrado:");
                    System.out.println(book);
                    return; // Si se encuentra el libro por ISBN, podemos salir del método
                }
            }
            System.out.println("No se encontró ningún libro con el ISBN '" + searchIsbn + "'.");
        } else {
            System.out.println("Opción de búsqueda inválida.");
        }
    }

    private void addBook() {
        System.out.println("\nIngrese los detalles del nuevo libro:");
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Título: ");
        String title = scanner.nextLine();
        System.out.print("Autor: ");
        String author = scanner.nextLine();

        Book newBook = new Book(isbn, title, author);
        this.books.add(newBook);
        System.out.println("El libro '" + newBook.getTitle() + "' ha sido agregado a la biblioteca.");
    }
}
// final de programa