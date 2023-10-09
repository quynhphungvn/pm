package quynh.java.webapp.pm.dao;

import java.util.List;

import quynh.java.webapp.pm.model.DesignImage;
import quynh.java.webapp.pm.model.Domain;

public interface DesignImageDao {
    List<DesignImage> getAll(Domain domain);
    DesignImage get1(int id);
    DesignImage get1(String name, Domain domain);
    DesignImage add(DesignImage designImage);
    DesignImage update(DesignImage designImage);
    DesignImage delete(DesignImage designImage);
}
