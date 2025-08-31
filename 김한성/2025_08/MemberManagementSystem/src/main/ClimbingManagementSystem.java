package main;

import domain.MemberShipType;
import domain.Visit;
import repository.*;
import service.ManagementService;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class ClimbingManagementSystem {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static MemberRepository memberRepository = new MemoryMemberRepository();
    public static VisitRepository visitRepository = new MemoryVisitRepository();
    public static ManagementService service = new ManagementService(memberRepository, visitRepository);
    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

        boolean flag = true;
        while (flag) {
            System.out.println("=============멋사 클라이밍==============");
            System.out.println("이용 방법 선택");
            System.out.println("1. 일일이용 \t2. 회원권 사용 \t3. 회원가입\t");
            System.out.println("4. 회원 탈퇴\t5. 일일이용 체크\t6. 나가기");
            int choice = scan.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("이름 : ");
                    String name = scan.next();
                    System.out.print("전화 번호 : ");
                    String phoneNumber = scan.next();
                    service.createVisitor(name, phoneNumber);
                    break;
                case 2:
                    System.out.print("전화번호 입력 : ");
                    String number = scan.next();
                    service.useMemberShip(number);
                    break;
                case 3:
                    System.out.println("[회원 가입을 위한 정보 입력]");
                    System.out.print("이름 : ");
                    String newName = scan.next();
                    System.out.print("전화번호 : ");
                    String newNum = scan.next();
                    if (service.duplicatedPhoneNumberCheck(newNum)) {
                        System.out.println("[오류] 이미 존재하는 회원입니다.");
                        break;
                    }
                    System.out.println("[회원권 선택]");
                    System.out.println("1. 1개월권 2. 3개월권 3. 10회권 4. 30회권");
                    String memberShip = scan.next();

                    MemberShipType type = switch (memberShip) {
                        case "1" -> MemberShipType.ONE_MONTH;
                        case "2" -> MemberShipType.THREE_MONTH;
                        case "3" -> MemberShipType.TEN_SESSION;
                        case "4" -> MemberShipType.THIRTY_SESSION;
                        default -> null;
                    };

                    if (type == null) {
                        System.out.println("[오류] 잘못된 선택입니다.");
                        continue;
                    }
                    service.createMemberShip(newName, newNum, type);
                    break;
                case 4:
                    System.out.println("[회원 정보 삭제]");
                    System.out.println("[전화번호 입력]");
                    String delete = scan.next();
                    service.deleteMember(delete);
                    break;

                case 5:
                    List<Visit> allVisits = service.getAllVisits();
                    for (Visit visitor : allVisits) {
                        System.out.println("이름 : " + visitor.getName() + ", 전화번호 : " + visitor.getPhone() + ", 방문날짜 : " + visitor.getVisitedAt().format(FORMATTER));
                    }
                    break;
                case 6:
                    flag = false;
                    break;
            }
        }
    }
}
