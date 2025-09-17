package library.controller;

import library.DTO.BookDTO;
import library.repository.BookDAO;
import library.repository.BookDAOImpl;

import java.util.List;
import java.util.Scanner;

public class LibraryController {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BookDAO bookDAO = new BookDAOImpl();

        while (true) {
            System.out.println("===== 도서 관리 시스템 =====");
            System.out.println("1. 도서 등록");
            System.out.println("2. 모든 도서 조회");
            System.out.println("3. 도서 정보 수정");
            System.out.println("4. 도서 삭제");
            System.out.println("0. 종료");
            System.out.print("메뉴 선택: ");

            int menu = sc.nextInt();
            sc.nextLine();

            switch (menu) {
                case 1:
                    System.out.println("ID: ");
                    System.out.print("도서명: ");
                    String title = sc.nextLine();
                    System.out.print("저자: ");
                    String author = sc.nextLine();
                    System.out.print("isbn: ");
                    String isbn = sc.nextLine();

                    BookDTO newBook = new BookDTO(0, title, author, isbn);
                    boolean added = bookDAO.addBook(newBook);
                    System.out.println(added ? "도서 등록 성공!" : "도서 등록 실패!");
                    break;

                case 2:
                    List<BookDTO> books = bookDAO.getBooks();
                    System.out.println("=== 전체 도서 목록 ===");
                    for (BookDTO b : books) {
                        System.out.println(b);
                    }
                    break;

                case 3:
                    System.out.print("수정할 도서 ID: ");
                    int updateId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("새 도서명: ");
                    String newTitle = sc.nextLine();
                    System.out.print("새 저자: ");
                    String newAuthor = sc.nextLine();
                    System.out.print("새 isbn: ");
                    String newIsbn = sc.nextLine();

                    BookDTO updateBook = new BookDTO(updateId, newTitle, newIsbn, newAuthor);
                    boolean updated = bookDAO.updateBook(updateBook);
                    System.out.println(updated ? "도서 수정 성공!" : "도서 수정 실패!");
                    break;

                case 4:
                    System.out.print("삭제할 도서 ID: ");
                    int deleteId = sc.nextInt();
                    sc.nextLine();
                    boolean deleted = bookDAO.deleteBook(deleteId);
                    System.out.println(deleted ? "도서 삭제 성공!" : "도서 삭제 실패!");
                    break;

                case 0:
                    System.out.println("프로그램 종료!");
                    sc.close();
                    return;

                default:
                    System.out.println("잘못된 입력입니다.");
            }
        }
    }
}


