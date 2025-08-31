# Climbing Management System (콘솔 버전)

## 📌 프로젝트 개요
클라이밍장을 위한 회원/회원권 관리 콘솔 프로그램입니다.

### 주요 기능
- 회원 등록 및 중복 검사
- 기간권 / 회수권 회원권 생성 및 사용
- 이용 내역(방문) 기록
- 메모리 기반 저장소를 통한 간단한 데이터 관리

---

## 📂 디렉토리 구조
```plaintext
src
├── domain                # 핵심 엔티티
│   ├── Member.java
│   ├── MemberShip.java
│   ├── MemberShipType.java
│   └── Visit.java
├── main                  # 실행 진입점
│   └── ClimbingManagementSystem.java
├── repository            # 저장소 계층
│   ├── MemberRepository.java
│   ├── MemberShipRepository.java
│   ├── MemoryMemberRepository.java
│   ├── MemoryMemberShipRepository.java
│   ├── MemoryVisitRepository.java
│   └── VisitRepository.java
└── service               # 서비스 계층
    └── ManagementService.java
```

---

## 📌 주요 클래스 설명

### 📂 domain
- **Member**: 회원 정보(이름, 전화번호, 회원권 리스트 등)
- **MemberShip**: 회원권 정보(시작일, 종료일, 남은 횟수 등)
- **MemberShipType**: 회원권 종류 Enum (1개월, 3개월, 10회, 30회)
- **Visit**: 회원 방문 기록

### 📂 repository
- **MemberRepository / MemoryMemberRepository**: 회원 데이터 저장소 인터페이스 및 메모리 구현체
- **MemberShipRepository / MemoryMemberShipRepository**: 회원권 데이터 저장소
- **VisitRepository / MemoryVisitRepository**: 방문 기록 저장소

### 📂 service
- **ManagementService**: 회원, 회원권, 방문 관리 로직 담당

### 📂 main
- **ClimbingManagementSystem**: 콘솔 기반 실행 진입점 (메뉴 출력 및 사용자 입력 처리)

---

## 💡 사용 예시
```plaintext
=============멋사 클라이밍==============
이용 방법 선택
1. 일일이용   2. 회원권 사용   3. 회원가입
> 3
[회원 가입을 위한 정보 입력]
이름 : 홍길동
전화번호 : 01012345678
[회원권 선택]
1. 1개월권 2. 3개월권 3. 10회권 4. 30회권
> 1
[가입 완료] 홍길동(01012345678) - ONE_MONTH, 금액: 100,000원
```

---

## 🚀 개선/추가 예정 기능
- [ ] 파일 기반 혹은 DB 기반 영속성 추가
- [ ] 회원 검색 및 상세 조회 기능
- [ ] 통계(방문 횟수, 회원권 사용률 등) 출력
- [ ] 회원권 연장 기능

