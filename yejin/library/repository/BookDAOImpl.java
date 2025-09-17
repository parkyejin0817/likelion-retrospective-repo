package library.repository;

import library.DTO.BookDTO;
import library.common.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {

    @Override
    public boolean addBook(BookDTO book) {
        boolean result = false;
        String sql = "insert into book(title, author, isbn) values (?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getIsbn());

            int count = ps.executeUpdate();
            if (count > 0) result = true;

        } catch (SQLException e) {
            System.out.println("도서 등록 실패: " + e.getMessage());
        }

        return result;
    }

    @Override
    public boolean updateBook(BookDTO book) {
        boolean result = false;
        String sql = "update book set title = ?, author = ?, isbn = ? where book_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getIsbn());
            ps.setInt(4, book.getId());

            int count = ps.executeUpdate();
            if (count > 0) result = true;

        } catch (SQLException e) {
            System.out.println("도서 수정 실패: " + e.getMessage());
        }

        return result;
    }

    @Override
    public List<BookDTO> getBooks() {
        List<BookDTO> books = new ArrayList<>();
        String sql = "select * from book";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                BookDTO book = new BookDTO(0, "Hi", "예진", "1234");
                book.setId(rs.getInt("book_id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setIsbn(rs.getString("isbn"));
                books.add(book);
            }

        } catch (SQLException e) {
            System.out.println("도서 조회 실패: " + e.getMessage());
        }

        return books;
    }

    @Override
    public boolean deleteBook(int bookId) {
        boolean result = false;
        String sql = "delete from book where book_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, bookId);

            int count = ps.executeUpdate();
            if (count > 0) result = true;

        } catch (SQLException e) {
            System.out.println("도서 삭제 실패: " + e.getMessage());
        }

        return result;
    }
}


