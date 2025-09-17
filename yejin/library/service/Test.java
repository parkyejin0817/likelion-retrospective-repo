package library.service;

import library.DAO.BookDAO;
import library.DAO.UsersDAO;
import library.DTO.BookDTO;
import library.DTO.UsersDTO;

    public class Test {

        public static void main(String[] args) {

            UsersDAO userDAO = new UsersDAO();
            BookDAO bookDAO = new BookDAO();

            UsersDTO user = new UsersDTO(0, "park", "sk47304842@icloud.com", "1234");
            int userInserted = userDAO.insertUser(user);
            System.out.println("회원가입이 완료되었습니다.");

            BookDTO book = new BookDTO(0, "Java Programming", "978-1234567890", "홍길동");
            int bookInserted = bookDAO.insertBook(book);
            System.out.println("도서가 등록되었습니다.");

            System.out.println("전체 회원 목록 - ");
            userDAO.selectAllUsers().forEach(System.out::println);
        }
    }

