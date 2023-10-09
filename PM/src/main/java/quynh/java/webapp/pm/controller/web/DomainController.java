package quynh.java.webapp.pm.controller.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import quynh.java.webapp.pm.model.Domain;
import quynh.java.webapp.pm.model.PlanDiagram;
import quynh.java.webapp.pm.model.Project;
import quynh.java.webapp.pm.service.DomainService;
import quynh.java.webapp.pm.service.PlanDiagramService;
import quynh.java.webapp.pm.service.ProjectService;
import quynh.java.webapp.pm.service.impl.DomainServiceImpl;
import quynh.java.webapp.pm.service.impl.PlanDiagramServiceImpl;
import quynh.java.webapp.pm.service.impl.ProjectServiceImpl;
import quynh.java.webapp.pm.util.RequestUtil;
import quynh.java.webapp.pm.util.StringChecker;
import quynh.java.webapp.pm.util.plantuml.DiagramPath;
import quynh.java.webapp.pm.util.plantuml.DiagramUtil;

/**
 * Servlet implementation class DomainController
 */
public class DomainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProjectService projectService = new ProjectServiceImpl();
    private DomainService domainService = new DomainServiceImpl();  
    private PlanDiagramService planDiagramService = new PlanDiagramServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DomainController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String domainUri = request.getRequestURI();
		if (domainUri.contains("/domain/info")) {
			int domainId = RequestUtil.getParameterInt(request, "id");
			if (domainId != 0) {
				Domain domain = domainService.getById(domainId);
				Project selectedProject = null;
				if (domain != null)
					selectedProject = domain.getProject();
				List<Domain> domains = domainService.getAll(selectedProject.getId());
				request.setAttribute("selectedDomain", domain);
				request.setAttribute("selectedProject", selectedProject);
				request.setAttribute("domains", domains);
			} else {
				int projectId = RequestUtil.getParameterInt(request, "project-id");
				if (projectId != 0) {
					Project selectedProject = projectService.getById(projectId);
					List<Domain> domains = domainService.getAll(projectId);
					request.setAttribute("selectedProject", selectedProject);
					request.setAttribute("domains", domains);
				}
			}
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/domain/index.jsp");
			rd.forward(request, response);
		} else if (domainUri.contains("/domain/erd")) {
			int domainId = RequestUtil.getParameterInt(request, "domain-id");
			if (domainId != 0) {
				Domain domain = domainService.getById(domainId);
				if (domain != null) {
					Project project = domain.getProject();
					// Create image
					String diaPath = DiagramPath.createDocumentDiagramURI(project.getId(), domain.getId());
					String realRootPath = (String) request.getServletContext().getAttribute("rootPath");
					String imageDiagramName = "erd-diagram.png";
					DiagramUtil.createImage(domain.getErdDiagram(), realRootPath + diaPath, imageDiagramName);
					request.setAttribute("selectedDomain", domain);
					String publicDiagramURI = diaPath + imageDiagramName;
					request.setAttribute("publicDiagramURI", publicDiagramURI);
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/erd/index.jsp");
					rd.forward(request, response);
				}
			}
		} else if (domainUri.contains("/domain/class")) {
			int domainId = RequestUtil.getParameterInt(request, "domain-id");
			if (domainId != 0) {
				Domain domain = domainService.getById(domainId);
				if (domain != null) {
					Project project = domain.getProject();
					// Create image
					String diaPath = DiagramPath.createDocumentDiagramURI(project.getId(), domain.getId());
					String realRootPath = (String) request.getServletContext().getAttribute("rootPath");
					String imageDiagramName = "class-diagram.png";
					DiagramUtil.createImage(domain.getClassDiagram(), realRootPath + diaPath, imageDiagramName);
					request.setAttribute("selectedDomain", domain);
					String publicDiagramURI = diaPath + imageDiagramName;
					request.setAttribute("publicDiagramURI", publicDiagramURI);
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/class/index.jsp");
					rd.forward(request, response);
				}
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestPath = request.getRequestURI();
	    if (requestPath.contains("/domain/add"))
	        processAddDomain(request, response);
	    else if (requestPath.contains("/domain/delete"))
	        processDeleteDomain(request, response);
	    else if (requestPath.contains("/domain/update-info"))
	        processUpdateDomain(request, response);
	    else if (requestPath.contains("/domain/update-requirement"))
	        processEditRequirement(request, response);
	    else if (requestPath.contains("/domain/update-erd"))
	        processUpdateErd(request, response);
	    else if (requestPath.contains("/domain/update-class"))
	        processUpdateClassDiagram(request, response);
	    else if (requestPath.contains("/domain/test-erd"))
	        processTestErdDiagram(request, response);
	    else if (requestPath.contains("/domain/test-class"))
	        processTestClassDiagram(request, response);
	}
	private void processTestErdDiagram(HttpServletRequest request, HttpServletResponse response) {
		int domainId = RequestUtil.getParameterInt(request, "domain-id");
		String diagramText = request.getParameter("diagram-text");
		String imageTestUrl = "";
		if (domainId != 0)
		{
			Domain domain = domainService.getById(domainId);
			if (domain != null) {
				Project project = domain.getProject();
				String diaPath = DiagramPath.createPlanDiagramURI(project.getId(), domain.getId());
				String realRootPath = (String) request.getServletContext().getAttribute("rootPath");
				String imageDiagramName = "erd-diagram.png";
				DiagramUtil.createImage(diagramText, realRootPath + diaPath, imageDiagramName);
				imageTestUrl = "/PM" + diaPath + imageDiagramName;
			}
		}
		try {
			response.setContentType("text/plain");
			response.getWriter().write(imageTestUrl);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void processTestClassDiagram(HttpServletRequest request, HttpServletResponse response) {
		int domainId = RequestUtil.getParameterInt(request, "domain-id");
		String diagramText = request.getParameter("diagram-text");
		String imageTestUrl = "";
		if (domainId != 0)
		{
			Domain domain = domainService.getById(domainId);
			if (domain != null) {
				Project project = domain.getProject();
				String diaPath = DiagramPath.createPlanDiagramURI(project.getId(), domain.getId());
				String realRootPath = (String) request.getServletContext().getAttribute("rootPath");
				String imageDiagramName = "class-diagram.png";
				DiagramUtil.createImage(diagramText, realRootPath + diaPath, imageDiagramName);
				imageTestUrl = "/PM" + diaPath + imageDiagramName;
			}
		}
		try {
			response.setContentType("text/plain");
			response.getWriter().write(imageTestUrl);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void processUpdateClassDiagram(HttpServletRequest request, HttpServletResponse response) {
		int domainId = RequestUtil.getParameterInt(request, "domain-id");
		String classDiagram = request.getParameter("class-diagram");
		if (domainId != 0) {
			domainService.updateClassDiagram(domainId, classDiagram);
			try {
				response.sendRedirect("/PM/domain/class?domain-id=" + domainId);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void processUpdateErd(HttpServletRequest request, HttpServletResponse response) {
		int domainId = RequestUtil.getParameterInt(request, "domain-id");
		String erdDiagram = request.getParameter("erd");
		if (domainId != 0) {
			domainService.updateErd(domainId, erdDiagram);
			try {
				response.sendRedirect("/PM/domain/erd?domain-id=" + domainId);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void processEditRequirement(HttpServletRequest request, HttpServletResponse response) {
		int diagramId = RequestUtil.getParameterInt(request, "diagram-id");
		if (diagramId != 0) {
			PlanDiagram planDiagram = planDiagramService.getById(diagramId);
			Domain domain = planDiagram.getDomain();
			String requirement = request.getParameter("requirement");
			domainService.updateRequirement(domain, requirement);
			try {
				response.sendRedirect("/PM/plan?id=" + diagramId);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void processAddDomain(HttpServletRequest request, HttpServletResponse response) {
        String domainName = request.getParameter("name");
        int projectId = RequestUtil.getParameterInt(request, "project-id");
        if (projectId != 0) {
        	domainService.addDomain(domainName, projectId);
	        String sSelectedDomainId = request.getParameter("selected-domain-id");
	        if (sSelectedDomainId != null && StringChecker.isInteger(sSelectedDomainId) == true) {
	        	try {
					response.sendRedirect("/PM/domain/info?id=" + sSelectedDomainId);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        } else {
	        	try {
					response.sendRedirect("/PM/domain/info?project-id=" + projectId);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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

    private void processUpdateDomain(HttpServletRequest request, HttpServletResponse response) {
        int domainId = RequestUtil.getParameterInt(request, "domain-id");
        String newName = request.getParameter("new-name");
        String overview = request.getParameter("overview");
        String scope = request.getParameter("scope");
        String technology = request.getParameter("technology");
        String note = request.getParameter("note");
        if (domainId != 0) {
        	domainService.updateDomain(domainId, newName, overview, scope, technology, note);
	        try {
				response.sendRedirect("/PM/domain/info?id=" + domainId);
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

    private void processDeleteDomain(HttpServletRequest request, HttpServletResponse response) {
    	String sProjectId = request.getParameter("selected-project-id");
        int domainId = RequestUtil.getParameterInt(request, "domain-id");
        if (domainId != 0)
        	domainService.deleteDomain(domainId);
        try {
			response.sendRedirect("/PM/domain/info?project-id=" + sProjectId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
