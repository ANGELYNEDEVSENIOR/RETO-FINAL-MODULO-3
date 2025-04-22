package com.devsenior.angelynegonzalez;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.devsenior.angelynegonzalez.model.Book;

public class Biblioteca {
  private List<Book> books;

public Biblioteca() {
    this.books = new ArrayList<>();
}
  private final Scanner scanner = new Scanner(System.in);
  public void mostrarMenu() {
    System.out.println("\nMenú de la Biblioteca");
    System.out.println("1. Agregar Libros");
    System.out.println("2. Consultar un libro");
    System.out.println("3. Prestar Libros");
    System.out.println("4. Devolver Libros");
    System.out.println("5. Registrar una cuenta de usuario");
    System.out.println("6. Consultar mi biblioteca");
    System.out.println("7. Salir");
    System.out.print("Seleccione una opción: ");
  }

  public static void main(String[] args) {
    Biblioteca biblioteca = new Biblioteca();
    while (true) {
      biblioteca.mostrarMenu();
      int opcion = biblioteca.scanner.nextInt();
      biblioteca.scanner.nextLine(); // Consumir la nueva línea

      switch (opcion) {
        case 1:
          biblioteca.AddBook();
          break;
        case 2:
          biblioteca.ConsultBook();
          break;
        case 3:
          biblioteca.LoanBook();
          break;
        case 4:
          biblioteca.ReturnBook();
          break;
        case 5:
          biblioteca.RegisterUserAccounta();
          break;
        case 6:
          biblioteca.ViewMyLibrary();
          break;
        case 7:
          biblioteca.Exit();
          break;
        default:
          System.out.println("Opción inválida");
      }
    }
  }

  private void Exit() {
    try {
      scanner.close();
    } catch (Exception e) {
      System.out.println("Error al cerrar el Scanner: " + e.getMessage());
    }
  }


  private void ViewMyLibrary() {
    if (books.isEmpty()) {
      System.out.println("La biblioteca está vacía.");
  } else {
      System.out.println("\nLibros en la biblioteca:");
      for (Book book : books) {
          System.out.println(book);
      }
    }
  }

  private void RegisterUserAccounta() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'RegisterUserAccounta'");
  }

  private void ReturnBook() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'ReturnBook'");
  }

  private void LoanBook() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'LoanBook'");
  }

  private void ConsultBook() {
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
            // Si no se encontraron libros con el título buscado
            if (!found) {
              System.out.println("No se encontraron libros con el título '" + searchTitle + "'.");
          }
          // Si se encontraron libros con el título buscado
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

  private void AddBook() {
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