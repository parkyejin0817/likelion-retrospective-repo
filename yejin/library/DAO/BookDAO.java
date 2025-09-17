package library.DAO;

import library.DTO.BookDTO;
import library.common.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Book 테이블과 연결되는 DAO
public class BookDAO {

    // ---- 도서 추가 (INSERT) ----
    public int insertBook(BookDTO book) {
        // book 테이블에 맞춘 SQL
        String sql = "INSERT INTO book(title, author, isbn) VALUES(?, ?, ?)";

        // try-with-resources: 자원을 자동으로 닫아줌
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // DTO에서 값을 꺼내서 SQL에 바인딩
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getIsbn());

            // SQL 실행 후 영향받은 행(row) 반환
            return ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("도서 추가 중 오류 발생", e);
        }
    }

    // ---- 전체 도서 조회 (SELECT ALL) ----
    public List<BookDTO> selectAllBooks() {
        String sql = "SELECT id, title, author, isbn FROM book";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<BookDTO> list = new ArrayList<>();

        try {

            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new BookDTO(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("isbn")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("전체 도서 조회 중 오류 발생", e);
        } finally {
            // 자원 해제
            DBUtil.close(conn, ps, rs);
        }

        return list;
    }
}

