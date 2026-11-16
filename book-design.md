# Thiết kế Bảng Dữ Liệu: Book

## Cấu trúc các thuộc tính
* `id`: Khóa chính (Primary Key), định danh duy nhất cho sách.
* `title`: Tiêu đề sách (String, Not Null).
* `author`: Tác giả sách (String, Not Null).
* `isbn`: Mã số tiêu chuẩn quốc tế (String, Unique, Not Null).
