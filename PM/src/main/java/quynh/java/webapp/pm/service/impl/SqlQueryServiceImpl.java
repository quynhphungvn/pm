package quynh.java.webapp.pm.service.impl;

import java.util.List;

import quynh.java.webapp.pm.dao.DomainDao;
import quynh.java.webapp.pm.dao.SqlQueryDao;
import quynh.java.webapp.pm.dao.hql_impl.DomainDaoImpl;
import quynh.java.webapp.pm.dao.hql_impl.SqlQueryDaoImpl;
import quynh.java.webapp.pm.model.Domain;
import quynh.java.webapp.pm.model.SqlQuery;
import quynh.java.webapp.pm.service.SqlQueryService;

public class SqlQueryServiceImpl implements SqlQueryService {
	private SqlQueryDao sqlQueryDao = new SqlQueryDaoImpl();
	private DomainDao domainDao = new DomainDaoImpl();
	@Override
	public SqlQuery getById(int queryId) {
		return sqlQueryDao.getById(queryId);
	}

	@Override
	public List<SqlQuery> getAll(Domain domain) {
		return sqlQueryDao.getAll(domain);
	}

	@Override
	public SqlQuery add(String name, int domainId) {
		Domain domain = domainDao.getById(domainId);
		SqlQuery sqlQuery = null;
		if (domain != null)
		{
			sqlQuery = new SqlQuery();
			sqlQuery.setName(name);
			sqlQuery.setDomain(domain);
			sqlQueryDao.add(sqlQuery);
		}
		return sqlQuery;
	}

	@Override
	public SqlQuery updateName(String newName, int queryId) {
		SqlQuery sqlQuery = null;
		if (newName.isBlank() == false) {
			sqlQuery = sqlQueryDao.getById(queryId);
			if (sqlQuery != null)
			{
				sqlQuery.setName(newName);
				sqlQueryDao.update(sqlQuery);
			}
		}
		return sqlQuery;
	}

	@Override
	public SqlQuery updateContent(String content, int queryId) {
		SqlQuery sqlQuery = null;
		sqlQuery = sqlQueryDao.getById(queryId);
		if (sqlQuery != null)
		{
			sqlQuery.setQueryContent(content);
			sqlQueryDao.update(sqlQuery);
		}
		return sqlQuery;
	}

	@Override
	public SqlQuery delete(int queryId) {
		SqlQuery query = null;
		if (queryId != 0) {
			query = sqlQueryDao.getById(queryId);
			if (query != null)
				sqlQueryDao.delete(query);
		}
		return query;
	}
}
