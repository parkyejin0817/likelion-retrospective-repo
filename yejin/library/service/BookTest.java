package library.service;

import library.DTO.BookDTO;
import library.repository.BookDAOImpl;

import java.util.List;

public class BookTest {
    public static void main(String[] args) {

        BookDAOImpl bookDAO = new BookDAOImpl();

        System.out.println("=== 도서 등록 ===");
        BookDTO book1 = new BookDTO(0, "aa", "aa", "1111");
        BookDTO book2 = new BookDTO(0, "hi", "박예진", "1234");

        bookDAO.addBook(book1);
        bookDAO.addBook(book2);

        System.out.println("=== 전체 도서 조회 ===");
        List<BookDTO> books = bookDAO.getBooks();
        books.forEach(System.out::println);

        System.out.println("=== 도서 수정 ===");
        if (!books.isEmpty()) {
            BookDTO firstBook = books.get(0);
            firstBook.setTitle("Java Advanced");
            bookDAO.updateBook(firstBook);
        }

        System.out.println("=== 전체 도서 조회 (수정 후) ===");
        bookDAO.getBooks().forEach(System.out::println);

        System.out.println("=== 마지막 도서 삭제 ===");
        if (!books.isEmpty()) {
            BookDTO lastBook = books.get(books.size() - 1);
            bookDAO.deleteBook(lastBook.getId());
        }

        System.out.println("=== 최종 도서 목록 ===");
        bookDAO.getBooks().forEach(System.out::println);
    }
}






