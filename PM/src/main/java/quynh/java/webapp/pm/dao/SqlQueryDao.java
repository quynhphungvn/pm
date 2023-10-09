package quynh.java.webapp.pm.dao;

import java.util.List;

import quynh.java.webapp.pm.model.Domain;
import quynh.java.webapp.pm.model.SqlQuery;

public interface SqlQueryDao {
    List<SqlQuery> getAll(Domain domain);
    SqlQuery getById(int id);
    SqlQuery get1(String name, Domain domain);
    SqlQuery add(SqlQuery sqlQuery);
    SqlQuery update(SqlQuery sqlQuery);
    SqlQuery delete(SqlQuery sqlQuery);
}
