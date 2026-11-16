package repository;

import model.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookRepository {
    private final List<Book> books = new ArrayList<>();
    private int nextId = 1;

    public Book save(Book book) {
        if (book.getId() <= 0) {
            book.setId(nextId++);
        }
        books.add(book);
        return book;
    }

    public boolean existsByIsbn(String isbn) {
        if (isbn == null) {
            return false;
        }
        return books.stream()
                .anyMatch(b -> b.getIsbn() != null && isbn.trim().equalsIgnoreCase(b.getIsbn().trim()));
    }

    public Optional<Book> findByIsbn(String isbn) {
        if (isbn == null) {
            return Optional.empty();
        }
        return books.stream()
                .filter(b -> b.getIsbn() != null && isbn.trim().equalsIgnoreCase(b.getIsbn().trim()))
                .findFirst();
    }

    public List<Book> findAll() {
        return new ArrayList<>(books);
    }
}