package quynh.java.webapp.pm.controller.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import quynh.java.webapp.pm.model.Domain;
import quynh.java.webapp.pm.model.Project;
import quynh.java.webapp.pm.model.SqlQuery;
import quynh.java.webapp.pm.service.DomainService;
import quynh.java.webapp.pm.service.SqlQueryService;
import quynh.java.webapp.pm.service.impl.DomainServiceImpl;
import quynh.java.webapp.pm.service.impl.SqlQueryServiceImpl;
import quynh.java.webapp.pm.util.RequestUtil;

/**
 * Servlet implementation class SqlQuery
 */
public class SqlQueryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private SqlQueryService sqlQueryService = new SqlQueryServiceImpl();  
    private DomainService domainService = new DomainServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SqlQueryController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int queryId = RequestUtil.getParameterInt(request, "id");
		if (queryId != 0) {
			SqlQuery query = sqlQueryService.getById(queryId);
			if (query != null) {
				Domain domain = query.getDomain();
				Project project = null;
				if (domain != null)
					project = domain.getProject();
				List<SqlQuery> sqlQueries = sqlQueryService.getAll(domain);
				request.setAttribute("selectedQuery", query);
				request.setAttribute("sqlQueries", sqlQueries);
				request.setAttribute("selectedDomain", domain);
				request.setAttribute("selectedProject", project);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/sql-query/index.jsp");
				rd.forward(request, response);
			} else {
				int domainId = RequestUtil.getParameterInt(request, "domain-id");
				if (domainId != 0) {
					Domain domain = domainService.getById(domainId);
					if (domain != null)
					{
						Project project = domain.getProject();
						request.setAttribute("selectedDomain", domain);
						List<SqlQuery> sqlQueries = sqlQueryService.getAll(domain);
						request.setAttribute("sqlQueries", sqlQueries);
						request.setAttribute("selectedProject", project);
						RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/sql-query/index.jsp");
						rd.forward(request, response);
					} else {
						response.sendRedirect("/PM/project");
					}
				} 
			}
			
		} else {
			int domainId = RequestUtil.getParameterInt(request, "domain-id");
			if (domainId != 0) {
				Domain domain = domainService.getById(domainId);
				if (domain != null)
				{
					Project project = domain.getProject();
					request.setAttribute("selectedDomain", domain);
					List<SqlQuery> sqlQueries = sqlQueryService.getAll(domain);
					request.setAttribute("sqlQueries", sqlQueries);
					request.setAttribute("selectedProject", project);
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/sql-query/index.jsp");
					rd.forward(request, response);
				}
				else {
					response.sendRedirect("/PM/project");
				}
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestUri = request.getRequestURI();
		if (requestUri.contains("/sqlquery/add")) {
			processAddQuery(request, response);
		} else if (requestUri.contains("/sqlquery/update-name")) {
			processUpdateName(request, response);
		} else if (requestUri.contains("/sqlquery/update-content")) {
			processUpdateContent(request, response);
		} else if (requestUri.contains("/sqlquery/delete")) {
			processDeleteQuery(request, response);
		}
	}

	private void processAddQuery(HttpServletRequest request, HttpServletResponse response) {
		int domainId = RequestUtil.getParameterInt(request, "domain-id");
		int selectedQueryId = RequestUtil.getParameterInt(request, "selected-query-id");
		if (domainId != 0) {
			String name = request.getParameter("name");
			if (name.isBlank() == false) {
				sqlQueryService.add(name, domainId);				
			}
		}
		try {
			response.sendRedirect("/PM/sqlquery?id=" + selectedQueryId + "&domain-id=" + domainId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void processUpdateName(HttpServletRequest request, HttpServletResponse response) {
		int queryId = RequestUtil.getParameterInt(request, "query-id");
		if (queryId != 0) {
			String newName = request.getParameter("new-name");
			if (newName.isBlank() == false) {
				sqlQueryService.updateName(newName, queryId);				
			}
		}
		try {
			response.sendRedirect("/PM/sqlquery?id=" + queryId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void processUpdateContent(HttpServletRequest request, HttpServletResponse response) {
		int queryId = RequestUtil.getParameterInt(request, "query-id");
		if (queryId != 0) {
			String content = request.getParameter("content");
			sqlQueryService.updateContent(content, queryId);				
		}
		try {
			response.sendRedirect("/PM/sqlquery?id=" + queryId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void processDeleteQuery(HttpServletRequest request, HttpServletResponse response) {
		int queryId = RequestUtil.getParameterInt(request, "query-id");
		if (queryId != 0)
			sqlQueryService.delete(queryId);
	}

}
