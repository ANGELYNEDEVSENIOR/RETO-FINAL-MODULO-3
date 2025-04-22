package com.devsenior.angelynegonzalez.service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.devsenior.angelynegonzalez.exception.NotFoundException;
import com.devsenior.angelynegonzalez.model.Book;
import com.devsenior.angelynegonzalez.model.Loan;
import com.devsenior.angelynegonzalez.model.User;

public class LoanService {

    private List<Loan> loans = new ArrayList<>();
    private UserService userService;
    private BookService bookService; // Assuming you have a BookService

    public LoanService(UserService userService, BookService bookService) {
        this.userService = userService;
        this.bookService = bookService;
    }

    public void loanBook(String userId, String bookId, LocalDate loanDate, LocalDate dueDate)
    throws NotFoundException, IllegalStateException {
User user = userService.getUserById(userId);
Book book = bookService.getBookByIsbn(bookId); // Assuming this method exists

if (book.isBorrowed()) {
    throw new IllegalStateException("El libro con ID: " + bookId + " ya está prestado.");
}

loans.add(new Loan(generateLoanId(), user, book, loanDate, dueDate));
book.setBorrowed(true); // Update book status
}

public void returnBook(String loanId, LocalDate returnDate) throws NotFoundException, IllegalStateException {
Loan loan = getLoanById(loanId);
Book book = loan.getBook();

if (!book.isBorrowed()) {
    throw new IllegalStateException("El libro con ID: " + book.getIsbn() + " no está actualmente prestado.");
}

loan.setReturnDate(returnDate);
book.setBorrowed(false); // Update book status
}

public Loan getLoanById(String loanId) throws NotFoundException {
for (var loan : loans) {
    if (loan.getIsbn().equals(loanId)) {
        return loan;
    }
}
throw new NotFoundException("No se encontró ningún préstamo con el ID: " + loanId);
}

public List<Loan> getAllLoans() {
return loans;
}

public List<Loan> getLoansByUser(String userId) throws NotFoundException {
userService.getUserById(userId); // Check if user exists
List<Loan> userLoans = new ArrayList<>();
for (var loan : loans) {
    if (loan.getUser().getId().equals(userId) && loan.getLoanDate() == null) {
        userLoans.add(loan);
    }
}
return userLoans;
}

public List<Loan> getOverdueLoans(LocalDate currentDate) {
List<Loan> overdue = new ArrayList<>();
for (var loan : loans) {
    if (loan.getLoanDate() == null && loan.getLoanDate().isBefore(currentDate)) {
        overdue.add(loan);
    }
}
return overdue;
}

// Helper method to generate a unique loan ID (you might want a more robust implementation)
private String generateLoanId() {
return "LOAN-" + System.currentTimeMillis();
}
}

        