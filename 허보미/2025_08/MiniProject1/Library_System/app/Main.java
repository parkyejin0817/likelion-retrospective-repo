package Library_System.app;

import Library_System.service.BookService;
import Library_System.service.BookServiceImpl;
import Library_System.service.RentalService;
import Library_System.service.RentalServiceImpl;

import java.util.*;

/**
 * 콘솔 도서 관리 시스템 - 메인 콘솔 메뉴 + 로그인 루프 뼈대 (JDK 21)
 * - 관리자 / 사용자 로그인 분리
 * - 각 역할별 메뉴 루프 (로그아웃 시 메인으로 복귀)
 * - 향후 BookService / RentalService 연결 지점에 TODO 표기
 */
public class Main {
    private static final Scanner sc = new Scanner(System.in);

    private static final BookService bookService = new BookServiceImpl();
    private static final RentalService rentalService = new RentalServiceImpl(bookService);
    private static final AdminConsole adminConsole = new AdminConsole(sc, bookService, rentalService);
    private static final MemberConsole memberConsole = new MemberConsole(sc, bookService, rentalService);



    private static String readChoice(String prompt, String... allowed) {
        var set = new java.util.HashSet<String>(java.util.Arrays.asList(allowed));
        while (true) {
            String in = readLine(prompt);
            if (in.equalsIgnoreCase("Q")) return null; // 취소
            if (set.contains(in)) return in;           // 정상 입력
            System.out.println("잘못된 입력입니다. 가능한 값: " + set + " (또는 Q로 취소)");
        }
    }

    public static void main(String[] args) {
        while (true) {
            printMainMenu();
            String sel = readLine("선택 > ");
            switch (sel) {
                case "1" -> adminConsole.doAdminLogin();
                case "2" -> memberConsole.doMemberLogin();
                case "3" -> memberConsole.doRegister();
                case "0" -> {
                    println("프로그램을 종료합니다.");
                    return;
                }
                default -> println("잘못된 입력입니다. 다시 선택하세요.");
            }
            println("");
        }
    }

    // ============== 메인/로그인 ==============
    private static void printMainMenu() {
        println("==============================");
        println("  도서 관리 시스템");
        println("==============================");
        println("1. 관리자 로그인");
        println("2. 사용자 로그인");
        println("3. 사용자 회원가입");
        println("0. 종료");
    }

    // ============== 유틸 ==============
    private static String readLine(String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim();
    }
    private static void println(String s) { System.out.println(s); }

    private static long readLong(String prompt) {
        while (true) {
            try { return Long.parseLong(readLine(prompt)); }
            catch (NumberFormatException e) { println("숫자를 입력하세요."); }
        }
    }
    private static int readInt(String prompt) {
        while (true) {
            try { return Integer.parseInt(readLine(prompt)); }
            catch (NumberFormatException e) { println("숫자를 입력하세요."); }
        }
    }

    // 간단 Member 레코드 (추후 domain 패키지로 분리 권장)
    public record Member(long id, String name, String username, String password) {}
}
