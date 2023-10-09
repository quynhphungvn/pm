package quynh.java.webapp.pm.service;

import java.util.List;

import quynh.java.webapp.pm.model.Domain;
import quynh.java.webapp.pm.model.SqlQuery;

public interface SqlQueryService {

	SqlQuery getById(int queryId);

	List<SqlQuery> getAll(Domain domain);

	SqlQuery add(String name, int domainId);

	SqlQuery updateName(String newName, int queryId);

	SqlQuery updateContent(String content, int queryId);

	SqlQuery delete(int queryId);
	
}
