# Library_System
# 멋쟁이사자처럼 백엔드 스쿨 과제 제출 - [1차 미니프로젝트] 도서 관리 시스템

## 수강생 정보
- **이름**: 허보미
- **기수**: 19기
- **트랙**: 백엔드 스쿨
- **제출일**: 2025-08-29
- **GitHub**: [https://github.com/likelion-backend-2025/homework-huhbomi-backend](https://github.com/likelion-backend-2025/homework-huhbomi-backend)

## 과제 개요
이번 과제는 **Java 기초와 객체지향 프로그래밍(OOP)** 개념을 활용하여  
**콘솔 기반 도서 관리 시스템**을 구현하는 프로젝트입니다.

## 개발 환경
- **언어**: Java 21
- **IDE**: IntelliJ IDEA
- **빌드 도구**: Gradle / Maven (선택)
- **운영체제**: Windows 11

## 파일 구조

<pre>
Library_System/ 
├── app/ 
│ ├── Main.java 
│ ├── AdminConsole.java 
│ └── MemberConsole.java
│ 
├── domain/ 
│ ├── Book.java 
│ ├── Rental.java 
│ └── Member.java 
│ 
├── service/ 
│ ├── BookService.java 
│ ├── BookServiceImpl.java 
│ ├── RentalService.java 
│ └── RentalServiceImpl.java 
│ └── README.md
</pre>

## 기능별 구현 현황

### 관리자(Admin)
- **도서 등록**: 제목, 저자 입력 → 도서 생성 및 저장 ✅
- **도서 삭제**: ID로 조회 후 삭제 ✅
- **도서 전체 조회**: 현재 저장된 모든 도서 목록 출력 ✅
- **도서 검색(제목/작가)**: 키워드 기반 조회 ✅
- **대여 도서 전체 조회**: 연체 여부까지 표시 ✅

### 사용자(Member)
- **회원가입 / 로그인**: 아이디·비밀번호 기반 계정 관리 ✅
- **도서 전체 조회**: 등록된 도서 목록 확인 ✅
- **도서 검색(제목/작가)**: 키워드 기반 조회 ✅
- **도서 대여**: 특정 도서를 대여하고 대여ID 발급 ✅
- **도서 반납**: 대여ID 기반 반납 처리 ✅
- **내 대여 도서 조회**: 내가 빌린 도서와 연체 여부 확인 ✅

---

## 실행 방법
1. IntelliJ IDEA에서 `Main.java` 실행
2. 콘솔 메뉴 출력

===== 도서 관리 시스템 =====

1.관리자 로그인

2.사용자 로그인

3.회원가입

4.종료

3. 관리자 계정: `id=admin / pw=1234`  
   사용자 계정: `user1/1234`, `user2/1234` (또는 회원가입)

---

## 이번 과제 학습 성과

### 💪 잘한 점
- **OOP 활용**: 클래스 분리(app, domain, service)로 책임 명확히 함
- **컬렉션 사용**: `Map`, `List`, `Optional` 적극 활용
- **예외 처리 & 입력 검증**: 잘못된 입력에 대한 루프 처리로 안정성 확보

### 🤔 아쉬운 점
- 회원 관리 로직이 아직 in-memory(Map)에만 국한됨
- 파일/DB 영속성 미구현
- Rental/Book 상태 동기화 로직을 더 깔끔하게 정리할 필요 있음

### 📚 추가 학습 계획
- [ ] JDBC 또는 JPA를 활용한 DB 연동
- [ ] GUI(JavaFX/Swing) 버전 확장
- [ ] 서비스/도메인 테스트 코드 작성(JUnit)

---

## 참고 자료
- **Oracle Java Docs**: [https://docs.oracle.com/en/java/](https://docs.oracle.com/en/java/)
- **강의 자료**: 멋쟁이사자처럼 백엔드 스쿨 수업 자료


