package repository;

import domain.Visit;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryVisitRepository implements VisitRepository {

    private final static Map<Long, Visit> visitorStore = new HashMap<>();
    private static long sequence = 0L;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public Visit save(String name, String phoneNumber) {
        Visit visitor = Visit.oneDay(name, phoneNumber, LocalDateTime.now());
        visitor.setId(++sequence);
        visitorStore.put(visitor.getId(), visitor);
        return visitor;
    }

    @Override
    public List<Visit> findAll() {
        return new ArrayList<>(visitorStore.values());
    }
}
