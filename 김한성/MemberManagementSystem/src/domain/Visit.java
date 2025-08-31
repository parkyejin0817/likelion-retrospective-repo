package domain;

import java.time.LocalDateTime;

public class Visit {
    private Long id;
    private String name;
    private String phone;
    private MemberShipType memberShipType;
    private LocalDateTime visitedAt;

    public static Visit oneDay(String name, String phone, LocalDateTime visitedAt) {
        Visit visit = new Visit();
        visit.phone = phone;
        visit.name = name;
        visit.memberShipType = MemberShipType.ONE_DAY;
        visit.visitedAt = visitedAt;
        return visit;
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

    public String getPhone() {
        return phone;
    }

    public LocalDateTime getVisitedAt() {
        return visitedAt;
    }
}
