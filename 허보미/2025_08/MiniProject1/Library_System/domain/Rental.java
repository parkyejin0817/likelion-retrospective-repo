package Library_System.domain;

import java.time.LocalDate;

public class Rental {
    private final long id;
    private final long memberId;
    private final long bookId;
    private final LocalDate rentedAt;
    private final LocalDate dueDate;
    private LocalDate returnedAt;

    public Rental(long id, long memberId, long bookId) {
        this.id = id;
        this.memberId = memberId;
        this.bookId = bookId;
        this.rentedAt = LocalDate.now();
        this.dueDate = rentedAt.plusDays(7); // 기본 7일 대여
    }

    public long getId() { return id; }
    public long getMemberId() { return memberId; }
    public long getBookId() { return bookId; }
    public LocalDate getRentedAt() { return rentedAt; }
    public LocalDate getDueDate() { return dueDate; }
    //반납 전이면 null
    public LocalDate getReturnedAt() { return returnedAt; }

    public boolean isReturned() { return returnedAt != null; }
    //연체 상태 확인
    public boolean isOverdue() { return !isReturned() && LocalDate.now().isAfter(dueDate); }

    public void markReturned() {
        if (!isReturned()) this.returnedAt = LocalDate.now();
    }

    @Override
    public String toString() {
        return "Rental{id=%d, memberId=%d, bookId=%d, rentedAt=%s, dueDate=%s%s}"
                .formatted(id, memberId, bookId, rentedAt, dueDate,
                        isReturned() ? ", returnedAt=" + returnedAt : (isOverdue() ? ", 연체" : ""));
    }
}
