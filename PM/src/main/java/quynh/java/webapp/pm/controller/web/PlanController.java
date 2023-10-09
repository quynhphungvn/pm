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
import quynh.java.webapp.pm.util.EnumUtil;
import quynh.java.webapp.pm.util.RequestUtil;
import quynh.java.webapp.pm.util.plantuml.DiagramPath;
import quynh.java.webapp.pm.util.plantuml.DiagramType;
import quynh.java.webapp.pm.util.plantuml.DiagramUtil;

/**
 * Servlet implementation class PlanController
 */
public class PlanController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ProjectService projectService = new ProjectServiceImpl();
    private DomainService domainService = new DomainServiceImpl();
    private PlanDiagramService planDiagramService = new PlanDiagramServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlanController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int diagramId = RequestUtil.getParameterInt(request, "id");
		if (diagramId != 0) {
			PlanDiagram pd = planDiagramService.getById(diagramId);
			if (pd != null) {
				Domain domain = pd.getDomain();
				List<PlanDiagram> planDiagrams = planDiagramService.getAll(domain.getId());
				Project project = domain.getProject();
				// Create image
				String diaPath = DiagramPath.createPlanDiagramURI(project.getId(), domain.getId());
				String realRootPath = (String) request.getServletContext().getAttribute("rootPath");
				String imageDiagramName = pd.getId() + "-diagram.png";
				DiagramUtil.createImage(pd.getDiagram(), realRootPath + diaPath, imageDiagramName);
				// Set attr
				request.setAttribute("selectedProject", project);
				request.setAttribute("selectedDomain", domain);
				request.setAttribute("selectedDiagram", pd);
				request.setAttribute("planDiagrams", planDiagrams);
				String[] diagramTypes = EnumUtil.getNames(DiagramType.class);
				request.setAttribute("diagramTypes", diagramTypes);
				String publicDiagramURI = diaPath + imageDiagramName;
				request.setAttribute("publicDiagramURI", publicDiagramURI);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/plan/index.jsp");
				rd.forward(request, response);
			}
		} else {
			int domainId = RequestUtil.getParameterInt(request, "domain-id");
			if (domainId != 0) {
				Domain domain = domainService.getById(domainId);
				List<PlanDiagram> planDiagrams = planDiagramService.getAll(domain.getId());
				Project project = domain.getProject();
				request.setAttribute("selectedProject", project);
				request.setAttribute("selectedDomain", domain);
				request.setAttribute("planDiagrams", planDiagrams);
				String[] diagramTypes = EnumUtil.getNames(DiagramType.class);
				request.setAttribute("diagramTypes", diagramTypes);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/plan/index.jsp");
				rd.forward(request, response);
			} else {
				response.sendRedirect("/PM/project");
			}
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestUri = request.getRequestURI();
		if (requestUri.contains("/plan/add")) {
			processAddPlanDiagram(request, response);
		} else if (requestUri.contains("/plan/update-name")) {
			processUpdatePlanDiagramName(request, response);
		} else if (requestUri.contains("/plan/update-diagram")) {
			processUpdatePlanDiagramText(request, response);
		} else if (requestUri.contains("/plan/delete")) {
			processDeletePlanDiagramText(request, response);
		} else if (requestUri.contains("/plan/test")) {
			processTestPlanDiagramText(request, response);
		}
	}
	
	private void processTestPlanDiagramText(HttpServletRequest request, HttpServletResponse response) {
		int diagramId = RequestUtil.getParameterInt(request, "diagram-id");
		String diagramText = request.getParameter("diagram-text");
		String imageTestUrl = "";
		if (diagramId != 0)
		{
			PlanDiagram planDiagram = planDiagramService.getById(diagramId);
			if (planDiagram != null) {
				Domain domain = planDiagram.getDomain();
				Project project = domain.getProject();
				String diaPath = DiagramPath.createPlanDiagramURI(project.getId(), domain.getId());
				String realRootPath = (String) request.getServletContext().getAttribute("rootPath");
				String imageDiagramName = planDiagram.getId() + "-diagram.png";
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

	private void processDeletePlanDiagramText(HttpServletRequest request, HttpServletResponse response) {
		int diagramId = RequestUtil.getParameterInt(request, "diagram-id");
		if (diagramId != 0)
			planDiagramService.delete(diagramId);
		response.setStatus(204);
	}

	private void processAddPlanDiagram(HttpServletRequest request, HttpServletResponse response) {
		int domainId = RequestUtil.getParameterInt(request, "domain-id");
		if (domainId != 0) {
			String planDiagramName = request.getParameter("name");
			String planDiagramType = request.getParameter("type");
			PlanDiagram p = planDiagramService.add(planDiagramName, planDiagramType, domainId);
			String selectedPlanDiagramId = request.getParameter("selected-diagram-id");
			try {
				response.sendRedirect("/PM/plan?id=" + selectedPlanDiagramId + "&domain-id=" + domainId);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	private void processUpdatePlanDiagramName(HttpServletRequest request, HttpServletResponse response) {
		int diagramId = RequestUtil.getParameterInt(request, "diagram-id");
		String newName = request.getParameter("new-name");
		if (diagramId != 0 && newName.isBlank() == false) {
			planDiagramService.updateName(diagramId, newName);
		}
		try {
			response.sendRedirect("/PM/plan?id=" + diagramId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void processUpdatePlanDiagramText(HttpServletRequest request, HttpServletResponse response) {
		int diagramId = RequestUtil.getParameterInt(request, "diagram-id");
		String diagramText = request.getParameter("diagram");
		if (diagramId != 0) {
			planDiagramService.updateText(diagramId, diagramText);
		}
		try {
			response.sendRedirect("/PM/plan?id=" + diagramId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
