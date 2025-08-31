package repository;

import domain.Visit;

import java.util.List;

public interface VisitRepository {

    Visit save(String name, String phoneNumber);

    List<Visit> findAll();
}
