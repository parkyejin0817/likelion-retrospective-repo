package Library_System.service;

import Library_System.domain.Book;
import Library_System.domain.Rental;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class RentalServiceImpl implements RentalService {
    private final Map<Long, Rental> rentals = new HashMap<>();
    private final AtomicLong seq = new AtomicLong(1L);

    private final BookService bookService;

    public RentalServiceImpl(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public Optional<Rental> rentBook(long memberId, long bookId) {
        var bookOpt = bookService.findById(bookId);
        if (bookOpt.isEmpty()) return Optional.empty();

        Book book = bookOpt.get();
        // 이미 대여중이면 실패
        if (book.isRented()) return Optional.empty();

        // 책 상태 변경
        boolean ok = book.rent();
        if (!ok) return Optional.empty();

        // 대여 생성 & 저장
        long rentalId = seq.getAndIncrement();
        Rental rental = new Rental(rentalId, memberId, bookId);
        rentals.put(rentalId, rental);
        return Optional.of(rental);
    }

    @Override
    public boolean returnBook(long rentalId, long memberId) {
        Rental rental = rentals.get(rentalId);
        if (rental == null) return false;
        if (rental.isReturned()) return false;
        if (rental.getMemberId() != memberId) return false;

        // 반납 처리: 대여건 마킹 + 책 상태 복구
        rental.markReturned();
        bookService.findById(rental.getBookId())
                .ifPresent(Book::giveBack);
        return true;
    }

    @Override
    public List<Rental> listAllRentals() {
        return new ArrayList<>(rentals.values());
    }

    @Override
    public List<Rental> listByMember(long memberId) {
        List<Rental> result = new ArrayList<>();
        for (Rental r : rentals.values()) {
            if (r.getMemberId() == memberId && !r.isReturned()) {
                result.add(r);
            }
        }
        return result;
    }

    @Override
    public List<Rental> listOverdue(LocalDate today) {
        List<Rental> result = new ArrayList<>();
        for (Rental r : rentals.values()) {
            if (!r.isReturned() && today.isAfter(r.getDueDate())) {
                result.add(r);
            }
        }
        return result;
    }
}
