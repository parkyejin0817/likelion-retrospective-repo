package Library_System.app;

import Library_System.domain.Book;
import Library_System.service.BookService;
import Library_System.service.RentalService;

import java.util.*;
import static java.lang.System.out;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// 필요시 Book, BookService, RentalService, Main.Member import
// import Library_System.domain.Book;
// import Library_System.service.BookService;
// import Library_System.service.RentalService;

public class MemberConsole {
    private final Scanner sc;
    private final BookService bookService;
    private final RentalService rentalService; // (없으면 null 가능)

    // 데모용: username -> Member
    private static final Map<String, Main.Member> MEMBER_ACCOUNTS = new HashMap<>();


    static {
        MEMBER_ACCOUNTS.put("user1", new Main.Member(1L, "사용자1", "user1", "1234"));
        MEMBER_ACCOUNTS.put("user2", new Main.Member(2L, "사용자2", "user2", "1234"));
    }

    private static long memberSeq = 3L;

    public MemberConsole(Scanner sc, BookService bookService, RentalService rentalService) {
        this.sc = Objects.requireNonNull(sc, "sc");
        this.bookService = Objects.requireNonNull(bookService, "bookService");
        this.rentalService = Objects.requireNonNull(rentalService, "rentalService");
    }

    /** 메인에서 호출: 사용자 로그인 → 성공 시 사용자 메뉴 진입 */
    public void doMemberLogin() {
        println("[사용자 로그인]");
        String id = readLine("아이디: ");
        String pw = readLine("비밀번호: ");
        Main.Member m = authenticateMember(id, pw);
        if (m != null) {
            println("※사용자 로그인 완료: " + m.name() + "※");
            memberLoop(m);
        } else {
            println("※사용자 로그인 실패※");
        }
    }

    /** 사용자 계정 로그인 검증 */
    private Main.Member authenticateMember(String username, String password) {
        Main.Member m = MEMBER_ACCOUNTS.get(username);
        if (m == null) return null;
        return m.password().equals(password) ? m : null;
    }

    /** 사용자 메뉴 루프 */
    private void memberLoop(Main.Member me) {
        while (true) {
            println("\n[사용자 메뉴] - " + me.name());
            println("1. 도서 전체 조회 | 2. 도서 검색(제목/작가) | 3. 도서 대여");
            println("---------------------------------------------------");
            println("4. 도서 반납     | 5. 대여 도서 조회       | 0. 로그아웃");
            String sel = readLine("선택 > ");

            switch (sel) {
                case "1" -> { // 전체 조회
                    var all = bookService.listAll();
                    printBooks(all);
                }
                case "2" -> { // 검색
                    String mode = readChoice("검색 기준 선택 (1.제목  2.작가, Q:취소) > ", "1", "2");
                    if (mode == null) { println("검색을 취소했습니다."); break; }
                    String keyword = readLine("검색어: ");
                    var result = "2".equals(mode)
                            ? bookService.searchByAuthor(keyword)
                            : bookService.searchByTitle(keyword);
                    println("'" + keyword + "' 검색 결과 (" + result.size() + "건)");
                    printBooks(result);
                }
                case "3" -> { // 대여
                    long bookId = readLong("대여할 도서 ID: ");
                    var rentedOpt = rentalService.rentBook(me.id(), bookId);

                    if (rentedOpt.isPresent()) {
                        var r = rentedOpt.get();
                        bookService.findById(r.getBookId()).ifPresent(book -> {
                            println("[성공] 대여 완료 : " + book.getTitle() + " (" + book.getAuthor() + ")");
                            println("대여ID=" + r.getId() + " | 반납예정=" + r.getDueDate());
                        });
                    } else {
                        println("[실패] 대여 불가(이미 대여 중이거나 존재하지 않음)");
                    }
                }
                case "4" -> { // 반납
                    var myRentals = rentalService.listByMember(me.id());
                    if (myRentals.isEmpty()) {
                        println("현재 대여 중인 도서가 없습니다.");
                        return;
                    } else {
                        println("[내 대여 도서]");
                        for (var r : myRentals) {
                            bookService.findById(r.getBookId()).ifPresent(book -> {
                                System.out.println("대여ID=" + r.getId()
                                        + " | " + book.getTitle() + " (" + book.getAuthor() + ")"
                                        + " | 대여일=" + r.getRentedAt()
                                        + " | 반납예정=" + r.getDueDate()
                                        + (r.isOverdue() ? " ⚠연체" : ""));
                            });
                        }
                    }

                    long rentalId = readLong("반납할 대여 ID: ");
                    boolean ok = rentalService.returnBook(rentalId, me.id());
                    println(ok ? "[성공] 반납 완료" : "[실패] 반납 불가(대여 내역 없음 또는 본인 대여 아님)");
                }
                case "5" -> { // 내 대여 도서 조회
                    var myRentals = rentalService.listByMember(me.id());
                    if (myRentals.isEmpty()) {
                        println("현재 대여 중인 도서가 없습니다.");
                    } else {
                        println("[내 대여 도서]");
                        for (var r : myRentals) {
                            bookService.findById(r.getBookId()).ifPresent(book -> {
                                System.out.println("대여ID=" + r.getId()
                                        + " | " + book.getTitle() + " (" + book.getAuthor() + ")"
                                        + " | 대여일=" + r.getRentedAt()
                                        + " | 반납예정=" + r.getDueDate()
                                        + (r.isOverdue() ? " ⚠연체" : ""));
                            });
                        }
                    }
                }
                case "0" -> {
                    println("사용자 로그아웃");
                    return;
                }
                default -> println("잘못된 입력입니다.");
            }
        }
    }

    // ====== 유틸 (인스턴스 메서드) ======
    private String readLine(String prompt) {
        out.print(prompt);
        return sc.nextLine().trim();
    }
    private void println(String s) { out.println(s); }

    private long readLong(String prompt) {
        while (true) {
            try { return Long.parseLong(readLine(prompt)); }
            catch (NumberFormatException e) { println("숫자를 입력하세요."); }
        }
    }

    private String readChoice(String prompt, String... allowed) {
        var set = new HashSet<>(Arrays.asList(allowed));
        while (true) {
            String in = readLine(prompt);
            if (in.equalsIgnoreCase("Q")) return null;
            if (set.contains(in)) return in;
            println("잘못된 입력입니다. 가능한 값: " + set + " (또는 Q로 취소)");
        }
    }

    private void printBooks(List<Book> books) {
        if (books.isEmpty()) {
            println("표시할 도서가 없습니다.");
            return;
        }
        System.out.printf("%-4s %-20s %-16s %-8s%n", "ID", "제목", "작가", "상태");
        System.out.println("--------------------------------------------------");
        for (Book b : books) {
            System.out.printf("%-4d %-20s %-16s %-8s%n",
                    b.getId(),
                    b.getTitle(),
                    b.getAuthor(),
                    (b.isRented() ? "대여중" : "가능")
            );
        }
    }

    public void doRegister() {
        println("[회원가입]");

        String name = readLine("이름: ");
        String username;
        while (true) {
            username = readLine("아이디: ");
            if (MEMBER_ACCOUNTS.containsKey(username)) {
                println("이미 존재하는 아이디입니다. 다시 입력해주세요.");
            } else break;
        }
        String password = readLine("비밀번호: ");

        Main.Member newMember = new Main.Member(memberSeq++, name, username, password);
        MEMBER_ACCOUNTS.put(username, newMember);

        println("회원가입 완료! 아이디: " + username + ", 이름: " + name);
    }
}
