package quynh.java.webapp.pm.dao;

import java.util.List;

import quynh.java.webapp.pm.model.Domain;
import quynh.java.webapp.pm.model.Screen;

public interface ScreenDao {
    List<Screen> getAll(Domain domain);
    Screen get1(int id);
    Screen get1(String name, Domain domain);
    Screen add(Screen screen);
    Screen update(Screen screen);
    Screen delete(Screen screen);
}
