package com.devsenior.angelynegonzalez;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.devsenior.angelynegonzalez.model.Book;

public class Biblioteca {
    private Scanner scanner = new Scanner(System.in);
public void mostrarMenu() {
    System.out.println("Menú de la Biblioteca");
    System.out.println("1. Agregar Libros");
    System.out.println("2. Consultar un libro");
    System.out.println("3. Prestar Libros");
    System.out.println("4. Devolver Libros");
    System.out.println("5. Registrar una cuenta de usuario");
    System.out.println("6. consultar mi biblioteca");
    System.out.println("7. salir");
}
public static void main(String[] args) {
    Biblioteca biblioteca = new Biblioteca();
    while (true) {
        biblioteca.mostrarMenu();
        int opcion = biblioteca.scanner.nextInt();
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
private void LoanBook() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'LoanBook'");
}
private void Exit() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'Exit'");
}
private void ViewMyLibrary() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'ViewMyLibrary'");
}
private void RegisterUserAccounta() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'RegisterUserAccounta'");
}
private void ReturnBook() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'ReturnBook'");
}
private void ConsultBook() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'ConsultBook'");
}
private void AddBook() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'AddBook'");
    }
}

