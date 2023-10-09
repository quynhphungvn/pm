package quynh.java.webapp.pm.dao;

import java.util.List;

import quynh.java.webapp.pm.model.Screen;
import quynh.java.webapp.pm.model.Usecase;

public interface UsecaseDao {
    List<Usecase> getAll(Screen screen);
    Usecase get1(int id);
    Usecase get1(String name, Screen screen);
    Usecase add(Usecase usecase);
    Usecase update(Usecase usecase);
    Usecase delete(Usecase usecase);
}
