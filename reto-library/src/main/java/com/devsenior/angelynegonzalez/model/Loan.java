package com.devsenior.angelynegonzalez.model;

import java.time.LocalDate;

public class Loan {
  private User user;
    private Book book;
    private LocalDate loanDate;
    private LoanState state;

    public Loan(String string, User user, Book book, LocalDate loanDate2, LocalDate dueDate) {
        this(user, book, LocalDate.now(), LoanState.STARTED);
    }

    public Loan(User user, Book book, LocalDate loanDate) {
        this(user, book, loanDate, LoanState.STARTED);
    }

    public Loan(User user, Book book, LocalDate loanDate, LoanState state) {
        this.user = user;
        this.book = book;
        this.loanDate = loanDate;
        this.state = state;
    }

    public User getUser() {
        return user;
    }

    public Book getBook() {
        return book;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public LoanState getState() {
        return state;
    }
// es el unico que podemos modificar
    public void setState(LoanState state) {
        this.state = state;
    }

    public void setReturnDate(LocalDate returnDate) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setReturnDate'");
    }

    public Object getIsbn() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getIsbn'");
    }
    
}
