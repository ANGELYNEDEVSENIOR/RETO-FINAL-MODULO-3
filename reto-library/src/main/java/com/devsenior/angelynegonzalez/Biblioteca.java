package com.devsenior.angelynegonzalez;

import com.devsenior.angelynegonzalez.model.Book;
import com.devsenior.angelynegonzalez.model.Loan;
import com.devsenior.angelynegonzalez.model.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Biblioteca {

  private final Scanner scanner = new Scanner(System.in);
  private final Map<Integer, Book> books = new HashMap<>();
  private final Map<Integer, User> users = new HashMap<>();
  private final List<Loan> loans = new ArrayList<>();
  private int nextBookId = 1;
  private int nextUserId = 1;

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

  public void AddBook() {
    System.out.println("\n--- Agregar Nuevo Libro ---");
    System.out.print("Título: ");
    String title = scanner.nextLine();
    System.out.print("Autor: ");
    String author = scanner.nextLine();
    System.out.print("ISBN: ");
    String isbn = scanner.nextLine();

    Book newBook = new Book(nextBookId++, title, author, isbn);
    books.put(newBook.getId(), newBook);
    System.out.println("Libro agregado con ID: " + newBook.getId());
  }

  public void ConsultBook() {
    System.out.println("\n--- Consultar Libro ---");
    System.out.print("Ingrese el ID del libro: ");
    int id = scanner.nextInt();
    scanner.nextLine(); // Consumir la nueva línea

    Book book = books.get(id);
    if (book != null) {
      System.out.println("Información del Libro:");
      System.out.println(book);
    } else {
      System.out.println("No se encontró ningún libro con el ID: " + id);
    }
  }

  public void RegisterUserAccounta() {
    System.out.println("\n--- Registrar Nuevo Usuario ---");
    System.out.print("Nombre: ");
    String name = scanner.nextLine();
    System.out.print("Dirección: ");
    String address = scanner.nextLine();
    System.out.print("Teléfono: ");
    String phone = scanner.nextLine();

    User newUser = new User(nextUserId++, name, address, phone);
    users.put(newUser.getId(), newUser);
    System.out.println("Usuario registrado con ID: " + newUser.getId());
  }

  public void LoanBook() {
    System.out.println("\n--- Realizar Préstamo ---");
    System.out.print("Ingrese el ID del usuario: ");
    int userId = scanner.nextInt();
    scanner.nextLine(); // Consumir la nueva línea

    User user = users.get(userId);
    if (user == null) {
      System.out.println("No se encontró ningún usuario con el ID: " + userId);
      return;
    }

    System.out.print("Ingrese el ID del libro a prestar: ");
    int bookId = scanner.nextInt();
    scanner.nextLine(); // Consumir la nueva línea

    Book book = books.get(bookId);
    if (book == null) {
      System.out.println("No se encontró ningún libro con el ID: " + bookId);
      return;
    }

    // Verificar si el libro ya está prestado
    boolean isBorrowed = false;
    for (Loan loan : loans) {
      if (loan.getBookId() == bookId) {
        isBorrowed = true;
        break;
      }
    }
    if (isBorrowed) {
      System.out.println("El libro con ID " + bookId + " ya está prestado.");
      return;
    }

    System.out.print("Ingrese la fecha de préstamo (YYYY-MM-DD): ");
    String loanDate = scanner.nextLine();

    Loan newLoan = new Loan(bookId, userId, loanDate);
    loans.add(newLoan);

    // Actualizar la lista de libros prestados del usuario
    user.addBookBorrowed(bookId);

    System.out.println("Préstamo registrado exitosamente.");
  }

  public void ReturnBook() {
    System.out.println("\n--- Devolver Libro ---");
    System.out.print("Ingrese el ID del libro a devolver: ");
    int bookIdToReturn = scanner.nextInt();
    scanner.nextLine(); // Consumir la nueva línea

    Loan loanToRemove = null;
    for (Loan loan : loans) {
      if (loan.getBookId() == bookIdToReturn) {
        loanToRemove = loan;
        break;
      }
    }

    if (loanToRemove != null) {
      loans.remove(loanToRemove);
      int userId = loanToRemove.getUserId();
      User user = users.get(userId);
      if (user != null) {
        user.removeBookBorrowed(bookIdToReturn);
        System.out.println("Libro con ID " + bookIdToReturn + " devuelto exitosamente.");
      } else {
        System.out.println("Error: No se encontró el usuario asociado al préstamo.");
      }
    } else {
      System.out.println("No se encontró ningún préstamo para el libro con ID " + bookIdToReturn + ".");
    }
  }

  public void ViewMyLibrary() {
    System.out.println("\n--- Consultar mi biblioteca (Préstamos) ---");
    System.out.print("Ingrese su ID de usuario: ");
    int userId = scanner.nextInt();
    scanner.nextLine(); // Consumir la nueva línea

    User user = users.get(userId);
    if (user == null) {
      System.out.println("No se encontró ningún usuario con el ID: " + userId);
      return;
    }

    List<Integer> borrowedBookIds = user.getBorrowedBooks();
    if (borrowedBookIds.isEmpty()) {
      System.out.println(user.getName() + " no tiene ningún libro prestado actualmente.");
      return;
    }

    System.out.println("Libros que ha prestado " + user.getName() + ":");
    for (int borrowedBookId : borrowedBookIds) {
      Book book = books.get(borrowedBookId);
      if (book != null) {
        System.out.println("- " + book.getTitle() + " (ID: " + book.getId() + ")");
      } else {
        System.out.println("- ID de libro: " + borrowedBookId + " (Información no disponible)");
      }
    }
  }

  public void Exit() {
    System.out.println("Saliendo del sistema de la biblioteca. ¡Hasta luego!");
    scanner.close();
    System.exit(0);
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
}