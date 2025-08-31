package repository;

import domain.Member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository {

    private static final Map<Long, Member> memberStore = new HashMap<>();
    private static Long sequance = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequance);
        memberStore.put(member.getId(), member);
        return member;
    }

    @Override
    public void delete(Long id) {
        memberStore.remove(id);
    }

    @Override
    public Member findByPhoneNumber(String phoneNumber) {
        return findAll().stream()
                .filter(m -> m.getPhoneNumber().equals(phoneNumber))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(memberStore.values());
    }

}
