
package ui;

import model.Book;
import repository.BookRepository;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AddBookForm extends JFrame {

    private JTextField txtTitle;
    private JTextField txtAuthor;
    private JTextField txtIsbn;

    private JTable table;
    private DefaultTableModel tableModel;

    private BookRepository bookRepository;

    public AddBookForm() {
        bookRepository = new BookRepository();

        setTitle("LIBRARY MANAGEMENT SYSTEM - Thêm sách mới");
        setSize(850, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createInterface();
    }

    private void createInterface() {

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // TIÊU ĐỀ
        JLabel title = new JLabel("QUẢN LÝ THƯ VIỆN - THÊM SÁCH MỚI");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setHorizontalAlignment(JLabel.CENTER);

        mainPanel.add(title, BorderLayout.NORTH);

        // FORM NHẬP SÁCH
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 15));
        formPanel.setBorder(BorderFactory.createTitledBorder("Thông tin sách"));

        txtTitle = new JTextField();
        txtAuthor = new JTextField();
        txtIsbn = new JTextField();

        formPanel.add(new JLabel("Tên sách:"));
        formPanel.add(txtTitle);

        formPanel.add(new JLabel("Tác giả:"));
        formPanel.add(txtAuthor);

        formPanel.add(new JLabel("Mã ISBN:"));
        formPanel.add(txtIsbn);

        JButton btnAdd = new JButton("Thêm sách");
        JButton btnReset = new JButton("Làm mới");

        formPanel.add(btnAdd);
        formPanel.add(btnReset);

        // BẢNG HIỂN THỊ SÁCH
        String[] columns = {"Mã sách", "Tên sách", "Tác giả", "ISBN"};

        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(tableModel);
        table.setRowHeight(25);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(
            BorderFactory.createTitledBorder("Danh sách sách")
        );

        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.add(formPanel, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        add(mainPanel);

        // SỰ KIỆN NÚT THÊM
        btnAdd.addActionListener(e -> addBook());

        // SỰ KIỆN NÚT LÀM MỚI
        btnReset.addActionListener(e -> resetForm());
    }

    // CHỨC NĂNG THÊM SÁCH
    private void addBook() {

        String title = txtTitle.getText().trim();
        String author = txtAuthor.getText().trim();
        String isbn = txtIsbn.getText().trim();

        if (title.isEmpty() || author.isEmpty() || isbn.isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Vui lòng nhập đầy đủ thông tin sách!",
                "Thông báo",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        // KIỂM TRA ISBN TRÙNG
        if (bookRepository.existsByIsbn(isbn)) {
            JOptionPane.showMessageDialog(
                this,
                "Mã ISBN đã tồn tại!",
                "Lỗi",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        // TẠO ĐỐI TƯỢNG SÁCH
        Book book = new Book(title, author, isbn);

        // LƯU SÁCH
        bookRepository.save(book);

        // HIỂN THỊ LÊN BẢNG
        tableModel.addRow(new Object[]{
            book.getId(),
            book.getTitle(),
            book.getAuthor(),
            book.getIsbn()
        });

        JOptionPane.showMessageDialog(
            this,
            "Thêm sách thành công!"
        );

        resetForm();
    }

    // LÀM MỚI FORM
    private void resetForm() {
        txtTitle.setText("");
        txtAuthor.setText("");
        txtIsbn.setText("");

        txtTitle.requestFocus();
    }

    // CHẠY CHƯƠNG TRÌNH
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new AddBookForm().setVisible(true);
        });
    }
}
