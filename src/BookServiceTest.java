package service;

import model.Book;
import repository.BookRepository;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BookServiceTest {
    private BookRepository bookRepository;
    private BookService bookService;

    @Before
    public void setUp() {
        bookRepository = new BookRepository();
        bookService = new BookService(bookRepository);
    }

    @Test
    public void testAddBookSuccess() {
        // 1. Arrange (Chuẩn bị dữ liệu)
        Book newBook = new Book("Lập trình Java", "Nguyễn Văn A", "978-604-123-456-7");

        // 2. Act (Thực thi)
        Book savedBook = bookService.addBook(newBook);

        // 3. Assert (Kiểm tra kết quả)
        assertNotNull(savedBook);
        assertEquals("Lập trình Java", savedBook.getTitle());
        assertEquals("978-604-123-456-7", savedBook.getIsbn());
    }

    @Test(expected = RuntimeException.class)
    public void testAddBookDuplicateIsbn() {
        // 1. Arrange (Thêm sẵn một cuốn sách có ISBN cụ thể)
        Book book1 = new Book("Cấu trúc dữ liệu", "Trần Văn B", "978-111-222-333-4");
        bookService.addBook(book1);

        // 2. Act & 3. Assert (Thêm cuốn thứ 2 trùng ISBN -> Mong đợi văng Exception)
        Book book2 = new Book("Giải thuật nâng cao", "Lê Thị C", "978-111-222-333-4");
        bookService.addBook(book2);
    }
}
