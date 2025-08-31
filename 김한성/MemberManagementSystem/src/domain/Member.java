package domain;

import java.util.ArrayList;
import java.util.List;

public class Member {
    private Long id;
    private String name;
    private String phoneNumber;
    private List<MemberShip> memberships = new ArrayList<>();

    public void addMemberShip(MemberShip memberShip) {
        memberships.add(memberShip);
    }

    public Member(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public List<MemberShip> getMemberships() {
        return memberships;
    }
}
