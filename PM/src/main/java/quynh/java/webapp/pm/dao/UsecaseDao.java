package quynh.java.webapp.pm.dao;

import java.util.List;

import quynh.java.webapp.pm.model.Domain;
import quynh.java.webapp.pm.model.Usecase;

public interface UsecaseDao {
    List<Usecase> getAll(Domain domain);
    Usecase getById(int id);
    Usecase add(Usecase usecase);
    Usecase update(Usecase usecase);
    Usecase delete(Usecase usecase);
}
