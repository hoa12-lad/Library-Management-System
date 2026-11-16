package service;

import model.Book;
import repository.BookRepository;

public class BookService {
    private BookRepository bookRepository;

    // Constructor để inject BookRepository vào
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Hàm addBook() kiểm tra logic không trùng ISBN theo đúng yêu cầu task HTQLVLMS-3
    public Book addBook(Book book) {
        if (book == null || book.getIsbn() == null || book.getIsbn().trim().isEmpty()) {
            throw new IllegalArgumentException("Thông tin sách hoặc mã ISBN không hợp lệ!");
        }

        // Kiểm tra xem ISBN đã tồn tại trong hệ thống chưa
        if (bookRepository.existsByIsbn(book.getIsbn())) {
            throw new RuntimeException("Mã ISBN đã tồn tại trong hệ thống, không thể thêm mới!");
        }

        // Nếu chưa trùng thì tiến hành lưu qua repository
        return bookRepository.save(book);
    }
}
