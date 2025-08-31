package domain;

import java.time.LocalDate;

public class MemberShip {

    private MemberShipType type;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer remainingSessions;
    private Member member;

    public MemberShip() {}

    public static MemberShip create(Member owner, MemberShipType type, LocalDate start) {
        MemberShip memberShip = new MemberShip();
        memberShip.member = owner;
        memberShip.type = type;
        memberShip.startDate = start;
        memberShip.endDate = type.expireAt(start).orElse(null);
        memberShip.remainingSessions = type.isSessionBased() ? type.initialSessions() : null;
        return memberShip;
    }

    public boolean isActive(LocalDate today) {
        if (type.isSessionBased()) {
            return (remainingSessions != null && remainingSessions > 0) && !isExpired(today);
        }
        return isExpired(today);
    }

    public void consumeOne(){
        if(!type.isSessionBased()) return;
        if (remainingSessions == null || remainingSessions <= 0) {
            throw new IllegalStateException("남은 횟수 : 0");
        }

        remainingSessions -= 1;
    }

    private boolean isExpired(LocalDate today) {
        return endDate != null && today.isAfter(endDate);
    }

    public MemberShipType getType() {
        return type;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Integer getRemainingSessions() {
        return remainingSessions;
    }

}
