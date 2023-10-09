package quynh.java.webapp.pm.controller.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import quynh.java.webapp.pm.exception.EntityExistedException;
import quynh.java.webapp.pm.model.Domain;
import quynh.java.webapp.pm.model.Project;
import quynh.java.webapp.pm.service.DomainService;
import quynh.java.webapp.pm.service.ProjectService;
import quynh.java.webapp.pm.service.impl.DomainServiceImpl;
import quynh.java.webapp.pm.service.impl.ProjectServiceImpl;
import quynh.java.webapp.pm.util.RequestUtil;
import quynh.java.webapp.pm.util.StringChecker;

/**
 * Servlet implementation class ProjectController
 */
@WebServlet({"/project", "/project/*"})
public class ProjectController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ProjectService projectService = new ProjectServiceImpl(); 
    private DomainService domainService = new DomainServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProjectController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int projectId = RequestUtil.getParameterInt(request, "id");
		Project selectedProject = null;
		if (projectId != 0) {
			selectedProject = projectService.getById(projectId);
		}
		List<Project> projects = projectService.getAll();
		request.setAttribute("projects", projects);
		request.setAttribute("selectedProject", selectedProject);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/project/index.jsp");
		rd.forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
	    String requestPath = request.getRequestURI();
	    if (requestPath.contains("/project/add"))
	        processAddProject(request, response);
	    else if (requestPath.contains("/project/delete"))
	        processDeleteProject(request, response);
	    else if (requestPath.contains("/project/update"))
	        processUpdateProject(request, response);
	}
	
	private void processAddProject(HttpServletRequest request, HttpServletResponse response) {
        String projectName = request.getParameter("name");
        Project newProject = projectService.addProject(projectName);
        String sSelectedProjectId = request.getParameter("selected-project-id");
        if (sSelectedProjectId != null && StringChecker.isInteger(sSelectedProjectId) == true) {
        	try {
				response.sendRedirect("/PM/project?id=" + sSelectedProjectId);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } else {
        	try {
				response.sendRedirect("/PM/project");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }

    private void processUpdateProject(HttpServletRequest request, HttpServletResponse response) {
        int projectId = RequestUtil.getParameterInt(request, "project-id");
        String newName = request.getParameter("new-name");
        String overview = request.getParameter("overview");
        String scope = request.getParameter("scope");
        String technology = request.getParameter("technology");
        Project project = projectService.updateProject(projectId, newName, overview, scope, technology);
        try {
			response.sendRedirect("/PM/project?id=" + projectId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    private void processDeleteProject(HttpServletRequest request, HttpServletResponse response) {
        int projectId = RequestUtil.getParameterInt(request, "project-id");
        if (projectId != 0)
        	projectService.deleteProject(projectId);
        try {
			response.sendRedirect("/PM/project");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
