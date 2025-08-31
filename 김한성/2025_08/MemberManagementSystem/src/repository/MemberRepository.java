package repository;

import domain.Member;

import java.util.List;

public interface MemberRepository {

    Member save(Member member);

    void delete(Long id);

    Member findByPhoneNumber(String phoneNumber);

    List<Member> findAll();

}
