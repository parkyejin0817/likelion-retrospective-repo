package Library_System.service;

import Library_System.domain.Rental;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RentalService {
    // 대여: 성공 시 Rental 반환
    Optional<Rental> rentBook(long memberId, long bookId);

    // 반납: 해당 대여건이 회원 소유이고 반납 가능하면 true
    boolean returnBook(long rentalId, long memberId);

    // 조회
    List<Rental> listAllRentals();            // 전체 대여(반납 포함)
    List<Rental> listByMember(long memberId); // 회원의 "현재 대여중" 목록
    List<Rental> listOverdue(LocalDate today);// 연체 목록
}
