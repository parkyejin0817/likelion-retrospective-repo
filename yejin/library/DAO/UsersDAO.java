package library.DAO;

import library.common.DBUtil;
import library.DTO.UsersDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * UsersDAO 클래스
 * - UsersDTO를 이용해 DB에 데이터를 넣거나 조회하는 DAO 클래스
 * - DB 연결은 DBUtil을 사용
 */
public class UsersDAO {

    // ---------------------------
    // 회원 추가 (INSERT)
    // ---------------------------
    public int insertUser(UsersDTO user) {
        // SQL 문 : users 테이블에 name, password, email 값 넣기
        String sql = "INSERT INTO users (name, password, email) VALUES (?, ?, ?)";

        // try-with-resources : Connection과 PreparedStatement 자동 close
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // 값 바인딩
            ps.setString(1, user.getName());
            ps.setString(2, user.getPassword()); // password 순서 주의
            ps.setString(3, user.getEmail());

            // 실행 후 영향받은 행(row) 수 반환
            return ps.executeUpdate();


        } catch (SQLException e) {
            // 예외 발생 시 RuntimeException으로 전환
            throw new RuntimeException(e);
        }
    }

    // ---------------------------
    // 전체 회원 조회 (SELECT)
    // ---------------------------
    public List<UsersDTO> selectAllUsers() {
        // SQL 문 : users 테이블에서 모든 컬럼 조회
        String sql = "SELECT id, name, password, email FROM users";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<UsersDTO> list = new ArrayList<>();

        try {
            // DB 연결
            conn = DBUtil.getConnection();

            // PreparedStatement 생성
            ps = conn.prepareStatement(sql);

            // SQL 실행
            rs = ps.executeQuery();

            // ResultSet → DTO 변환
            while (rs.next()) {
                list.add(new UsersDTO(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),   // 생성자 순서 : id, name, email, password
                        rs.getString("password")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // 오류 출력
        } finally {
            // DB 자원 반납
            DBUtil.close(conn, ps, rs);
        }

        return list;
    }
}

