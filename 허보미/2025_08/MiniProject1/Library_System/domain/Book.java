package Library_System.domain;

public class Book {
    private final long id;
    private final String title;
    private final String author;
    private boolean rented;

    public Book(long id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.rented = false;
    }

    //Getter : Alt+Insert 단축키
    public long getId() {return id;}

    public String getTitle() {return title;}

    public String getAuthor() {return author;}

    public boolean isRented() {return rented;}

    // 대여/반납 (상태만 변경) — 날짜는 Rental이 관리
    public boolean rent() {
        if (rented) return false;
        this.rented = true;
        return true;
    }

    public boolean giveBack() {           // ← 기존 returned() 명명은 동사형으로 변경 권장
        if (!rented) return false;
        this.rented = false;
        return true;
    }

    // ===== 상태 출력 =====
    @Override
    public String toString() {
        return "[ID=" + id + "] " + title + " / " + author +
                " / 상태: " + (rented ? "대여중" : "대여가능");
    }
}
