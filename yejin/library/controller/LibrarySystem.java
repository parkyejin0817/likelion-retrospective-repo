package library.controller;


import library.DAO.UsersDAO;
import library.DAO.BookDAO;
import library.DTO.UsersDTO;
import library.DTO.BookDTO;

import java.util.Scanner;

    public class LibrarySystem {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);

            UsersDAO userDAO = new UsersDAO();
            BookDAO bookDAO = new BookDAO();

            while (true) {
                System.out.println("===== 도서 관리 시스템 =====");
                System.out.println("1. 회원 가입");
                System.out.println("2. 모든 회원 조회");
                System.out.println("3. 도서 등록");
                System.out.println("4. 모든 도서 조회");
                System.out.println("0. 종료");
                System.out.print("메뉴 선택: ");

                int menu = sc.nextInt();
                sc.nextLine();

                switch (menu) {
                    case 1:
                        System.out.print("이름: ");
                        String name = sc.nextLine();
                        System.out.print("이메일: ");
                        String email = sc.nextLine();
                        System.out.print("비밀번호: ");
                        String password = sc.nextLine();

                        UsersDTO user = new UsersDTO(0, name, email, password);
                        int inserted = userDAO.insertUser(user);
                        System.out.println("회원가입 성공! (" + inserted + "행 추가됨)");
                        break;

                    case 2:
                        System.out.println("=== 전체 회원 목록 ===");
                        userDAO.selectAllUsers().forEach(System.out::println);
                        break;

                    case 3:
                        System.out.print("도서명: ");
                        String title = sc.nextLine();
                        System.out.print("저자: ");
                        String author = sc.nextLine();
                        System.out.print("isbn: ");
                        String isbn = sc.nextLine();

                        BookDTO book = new BookDTO(0, title, isbn, author);
                        int bookInserted = bookDAO.insertBook(book);
                        System.out.println("도서 등록 성공!");
                        break;

                    case 4:
                        System.out.println("=== 전체 도서 목록 ===");
                        bookDAO.selectAllBooks().forEach(System.out::println);
                        break;

                    case 0:
                        System.out.println("프로그램 종료!");
                        return;

                    default:
                        System.out.println("잘못된 입력입니다.");
                }
            }
        }
    }

