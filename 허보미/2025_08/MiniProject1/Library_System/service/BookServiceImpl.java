package Library_System.service;

import Library_System.domain.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.util.HashMap;
//ID 자동 생성
import java.util.concurrent.atomic.AtomicLong;

public class BookServiceImpl implements BookService{
    //도서 저장소 : DB대신
    private final Map<Long, Book> store = new HashMap<>();
   //도서 ID 자동 생성
    private final AtomicLong seq = new AtomicLong(1L);

    @Override
    public Book addBook(String title, String author) {
        long id = seq.getAndIncrement();
        Book book = new Book(id, title, author);
        store.put(id,book);
        return book;
    }

    @Override
    public Optional<Book> removeBook(long bookId) {
        Book removed = store.remove(bookId); // 있으면 Book 반환, 없으면 null
        return Optional.ofNullable(removed);
    }

    @Override
    public List<Book> listAll() {
        //안정성을 위해 store 복사 후 반환
        return new ArrayList<>(store.values());
    }

    @Override
    public List<Book> searchByTitle(String keyword) {
        String lower_keyword = keyword.toLowerCase();
        List<Book> books = new ArrayList<>();
        for(Book b : store.values()){
            if(b.getTitle().toLowerCase().contains(lower_keyword)){
                books.add(b);
            }
        }
        return books;
    }

    @Override
    public List<Book> searchByAuthor(String keyword) {
        String lower_keyword = keyword.toLowerCase();
        List<Book> books = new ArrayList<>();
        for(Book b : store.values()){
            if(b.getAuthor().toLowerCase().contains(lower_keyword)){
                books.add(b);
            }
        }
        return books;
    }

    @Override
    public Optional<Book> findById(long bookId) {
        return Optional.ofNullable(store.get(bookId));
    }

//    @Override
//    public boolean rentBook(long bookId) {
//        Optional<Book> bookOpt = findById(bookId);
//
//        if (bookOpt.isPresent()){
//            Book book = bookOpt.get();
//            return book.rent();
//        }
//        else{
//            return false;
//        }
//    }
//
//    @Override
//    public boolean returnBook(long bookId) {
//        Optional<Book> bookOpt = findById(bookId);
//
//        if (bookOpt.isPresent()){
//            Book book = bookOpt.get();
//            return book.returned();
//        }
//        else{
//            return false;
//        }
//    }

    @Override
    public void  printBooks(List<Book> books){
        if (books.isEmpty()) {
            System.out.println("표시할 도서가 없습니다.");
            return;
        }
        System.out.printf("%-4s %-20s %-16s %-8s%n", "ID", "제목", "작가", "상태");
        System.out.println("--------------------------------------------------");
        for (Book b : books) {
            System.out.printf("%-4d %-20s %-16s %-8s%n",
                    b.getId(),
                    b.getTitle(),
                    b.getAuthor(),
                    (b.isRented() ? "대여중" : "대여가능")
            );
        }
    }
}
