package Library_System.service;

import Library_System.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Book addBook(String title, String author);
    Optional<Book> removeBook(long bookId);
    List<Book> listAll();
    //없거나 하나 이상 검색
    List<Book> searchByTitle(String keyword);
    List<Book> searchByAuthor(String keyword);
    //없거나 하나 검색
    Optional<Book> findById(long bookId);

//    boolean rentBook(long bookId);
//    boolean returnBook(long bookId);

    void  printBooks(List<Book> books);
}
