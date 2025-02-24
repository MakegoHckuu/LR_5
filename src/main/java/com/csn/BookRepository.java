package com.csn;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookRepository {
    private static BookRepository instance;
    private final List<Book> books;
    private int nextId = 1;

    private BookRepository() {
        books = new ArrayList<>();
        books.add(new Book(nextId++, "1984", "George Orwell"));
        books.add(new Book(nextId++, "Brave New World", "Aldous Huxley"));
    }

    public static synchronized BookRepository getInstance() {
        if (instance == null) {
            instance = new BookRepository();
        }
        return instance;
    }

    public List<Book> getAllBooks() {
        return books;
    }

    public Optional<Book> getBookById(int id) {
        return books.stream().filter(book -> book.getId() == id).findFirst();
    }

    public Book addBook(Book book) {
        book.setId(nextId++);
        books.add(book);
        return book;
    }

    public boolean updateBook(int id, Book newBook) {
        Optional<Book> existingBook = getBookById(id);
        if (existingBook.isPresent()) {
            Book book = existingBook.get();
            book.setTitle(newBook.getTitle());
            book.setAuthor(newBook.getAuthor());
            return true;
        }
        return false;
    }

    public boolean deleteBook(int id) {
        return books.removeIf(book -> book.getId() == id);
    }
}
