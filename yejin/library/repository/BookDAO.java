package library.repository;

import library.DTO.BookDTO;
import java.util.List;

public interface BookDAO {

    boolean addBook(BookDTO book);

    boolean updateBook(BookDTO book);

    List<BookDTO> getBooks();

    boolean deleteBook(int book_id);
}
