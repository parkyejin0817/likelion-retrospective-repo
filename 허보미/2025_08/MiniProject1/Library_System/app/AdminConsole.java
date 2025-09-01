package Library_System.app;

import Library_System.domain.Book;
import Library_System.service.BookService;
import Library_System.service.RentalService;

import java.util.*;
import static java.lang.System.out;



public class AdminConsole {
    private final Scanner sc;
    private final BookService bookService;
    private final RentalService rentalService; // (없으면 null 가능)

    private static final Map<String, String> ADMIN_ACCOUNTS = Map.of(
            "admin", "1234"
    );

    public AdminConsole(Scanner sc, BookService bookService, RentalService rentalService) {
        this.sc = sc;
        this.bookService = bookService;
        this.rentalService = rentalService;
    }

    /** 메인에서 호출: 관리자 로그인 → 성공 시 관리자 메뉴 진입 */
    public void doAdminLogin() {
        println("[관리자 로그인]");
        String id = readLine("아이디: ");
        String pw = readLine("비밀번호: ");
        if (authenticateAdmin(id, pw)) {
            println("※관리자 로그인 완료※");
            adminLoop();
        } else {
            println("※관리자 로그인 실패※");
        }
    }

    /** 관리자 계정 로그인 검증 */
    private boolean authenticateAdmin(String username, String password) {
        String saved = ADMIN_ACCOUNTS.get(username);
        return saved != null && saved.equals(password);
    }

    /** 관리자 메뉴 루프 */
    private void adminLoop() {
        while (true) {
            println("\n[관리자 메뉴]");
            println("         1. 도서 등록 |     2. 도서 삭제 | 3. 도서 전체 조회");
            println("-------------------------------------------------------");
            println("4. 도서 검색(제목/작가) | 5. 대여 도서 조회 |      0. 로그아웃");
            String sel = readLine("선택 > ");

            switch (sel) {
                case "1" -> { // 도서 등록
                    String title = readLine("제목: ");
                    String author = readLine("작가: ");
                    var saved = bookService.addBook(title, author);
                    println("- 도서 등록: %s (%s)".formatted(saved.getTitle(), saved.getAuthor()));
                }
                case "2" -> { // 도서 삭제 (삭제된 책 정보 출력)
                    long id = readLong("삭제할 도서 ID: ");
                    var before = bookService.findById(id); // Optional<Book>
                    if (before.isEmpty()) {
                        println("- 해당 ID의 도서를 찾을 수 없습니다.");
                    } else {
                        var removedOpt = bookService.removeBook(id); // Optional<Book>// boolean 버전 사용 시
                        if (removedOpt.isPresent()) {
                            Book removed = removedOpt.get();
                            println("- 도서 삭제 완료 : " + removed.getTitle() + " (" + removed.getAuthor() + ")");
                        } else {
                            println("- 해당 ID의 도서를 찾을 수 없습니다.");
                        }
                    }
                    // ※ 만약 removeBook을 Optional<Book>을 반환하도록 바꿨다면,
                    //   그 반환값으로 바로 출력하면 됨 (이전 대화 참고)
                }
                case "3" -> { // 전체 조회
                    var all = bookService.listAll();
                    printBooks(all);
                }
                case "4" -> { // 검색(제목/작가)
                    String mode = readChoice("검색 기준 선택 (1.제목  2.작가, Q:취소) > ", "1", "2");
                    if (mode == null) { println("검색을 취소했습니다."); break; }
                    String keyword = readLine("검색어: ");
                    var result = "2".equals(mode)
                            ? bookService.searchByAuthor(keyword)
                            : bookService.searchByTitle(keyword);
                    println("'" + keyword + "' 검색 결과 (" + result.size() + "건)");
                    printBooks(result);          // bookService.printBooks(...) ❌
                }
                case "5" -> { // 대여 도서 조회 (간단: 대여중만)
                    var rented = new ArrayList<Book>();
                    for (Book b : bookService.listAll()) {
                        if (b.isRented()) rented.add(b);
                    }
                    if (rented.isEmpty()) println("- 대여 중인 도서가 없습니다.");
                    else printBooks(rented);
                    // ※ 회원별 대여내역이 필요하면 rentalService로 확장
                }
                case "0" -> {
                    println("관리자 로그아웃");
                    return;
                }
                default -> println("잘못된 입력입니다.");
            }
        }
    }

    // ====== 입력/출력 유틸 (인스턴스 메서드) ======
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
}
