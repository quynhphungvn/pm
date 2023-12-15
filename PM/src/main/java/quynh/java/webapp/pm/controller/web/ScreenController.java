package quynh.java.webapp.pm.controller.web;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import quynh.java.webapp.pm.model.Domain;
import quynh.java.webapp.pm.model.Project;
import quynh.java.webapp.pm.model.Screen;
import quynh.java.webapp.pm.service.DomainService;
import quynh.java.webapp.pm.service.ScreenService;
import quynh.java.webapp.pm.service.impl.DomainServiceImpl;
import quynh.java.webapp.pm.service.impl.ScreenServiceImpl;
import quynh.java.webapp.pm.util.RequestUtil;
import quynh.java.webapp.pm.util.StringChecker;
import quynh.java.webapp.pm.util.plantuml.DiagramPath;
import quynh.java.webapp.pm.util.plantuml.DiagramType;
import quynh.java.webapp.pm.util.plantuml.DiagramUtil;

/**
 * Servlet implementation class ScreenController
 */
@MultipartConfig
public class ScreenController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DomainService domainService = new DomainServiceImpl();
	private ScreenService screenService = new ScreenServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScreenController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int screenId = RequestUtil.getParameterInt(request, "id");
		if (screenId > 0) {
			Screen selectedScreen = screenService.getById(screenId);
			if (selectedScreen != null) {
				Domain domain = selectedScreen.getDomain();
				Project project = null;
				if (domain != null)
					project = domain.getProject();
				List<Screen> screens = screenService.getAll(domain);
				byte[] wireframeImageBytes = selectedScreen.getWireframeImage();
				if (wireframeImageBytes != null) {
					String wireframeBase64 = Base64.getEncoder().encodeToString(wireframeImageBytes);
					request.setAttribute("wireframeImage", wireframeBase64);
				}
				//create diagram image
				String realRootPath = (String) request.getServletContext().getAttribute("rootPath");
				String publicDiagramURI = DiagramUtil
											.createDiagramImage(realRootPath, project.getId(), domain.getId(), 
																selectedScreen.getUsecaseDiagram(), DiagramType.USECASE);
				request.setAttribute("publicDiagramURI", publicDiagramURI);
				request.setAttribute("selectedScreen", selectedScreen);
				request.setAttribute("screens", screens);
				request.setAttribute("selectedDomain", domain);
				request.setAttribute("selectedProject", project);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/screen/index.jsp");
				rd.forward(request, response);
			} else {
				int domainId = RequestUtil.getParameterInt(request, "domain-id");
				if (domainId != 0) {
					Domain domain = domainService.getById(domainId);
					if (domain != null)
					{
						Project project = domain.getProject();
						request.setAttribute("selectedDomain", domain);
						List<Screen> screens = screenService.getAll(domain);
						request.setAttribute("screens", screens);
						request.setAttribute("selectedProject", project);
						RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/screen/index.jsp");
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
					List<Screen> screens = screenService.getAll(domain);
					request.setAttribute("screens", screens);
					request.setAttribute("selectedProject", project);
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/screen/index.jsp");
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
		String requestURI = request.getRequestURI();
		if (requestURI.contains("/screen/add")) {
			processAddScreen(request, response);
		} else if (requestURI.contains("/screen/update-name")){
			processUpdateScreenName(request, response);
		} else if (requestURI.contains("/screen/update-wireframe")) {
			processUpdateWireframeImage(request, response);
		} else if (requestURI.contains("/screen/update-usecase")) {
			processUpdateUsecaseDiagram(request, response);
		} else if (requestURI.contains("/screen/test-usecase")) {
			processTestUsecaseDiagram(request, response);
		} else if (requestURI.contains("/screen/delete")) {
			processDeleteScreen(request, response);
		}
	}
	private void processDeleteScreen(HttpServletRequest request, HttpServletResponse response) {
		String domainId = request.getParameter("domain-id");
		int screenId = RequestUtil.getParameterInt(request, "screen-id");
		screenService.deleteScreen(screenId);
		try {
			response.sendRedirect("/PM/screen?domain-id=" + domainId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void processTestUsecaseDiagram(HttpServletRequest request, HttpServletResponse response) {
		int projectId = RequestUtil.getParameterInt(request, "project-id");
		int domainId = RequestUtil.getParameterInt(request, "domain-id");
		String usecaseDiagram = request.getParameter("usecase-diagram");
		//create diagram image
			String realRootPath = (String) request.getServletContext().getAttribute("rootPath");
			String publicImage = DiagramUtil.createDiagramImage(realRootPath, projectId, domainId, usecaseDiagram, DiagramType.USECASE);
			response.setContentType("text/plain");
			try {
				response.getWriter().print(publicImage);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 	
	}

	private void processUpdateUsecaseDiagram(HttpServletRequest request, HttpServletResponse response) {
		int screenId = RequestUtil.getParameterInt(request, "screen-id");
		String usecaseDiagram = request.getParameter("usecase-diagram");
		screenService.updateUsecaseDiagram(usecaseDiagram, screenId);
		try {
			response.sendRedirect("/PM/screen?id=" + screenId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void processUpdateWireframeImage(HttpServletRequest request, HttpServletResponse response) {
		String screenId = null;
		byte[] wireframeImage = null;
		try {
			screenId = RequestUtil.convertPartToString(request.getPart("screen-id"));
			wireframeImage = request.getPart("wireframe-image").getInputStream().readAllBytes();
		} catch (IOException | ServletException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (screenId != null && StringChecker.isInteger(screenId))
			screenService.updateWireframeImage(wireframeImage, Integer.parseInt(screenId));
		try {
			response.sendRedirect("/PM/screen?id=" + screenId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void processUpdateScreenName(HttpServletRequest request, HttpServletResponse response) {
		int screenId = RequestUtil.getParameterInt(request, "screen-id");
		String newName = request.getParameter("new-name");
		screenService.updateName(newName, screenId);
		try {
			response.sendRedirect("/PM/screen?id=" + screenId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void processAddScreen(HttpServletRequest request, HttpServletResponse response) {
		int domainId = RequestUtil.getParameterInt(request, "domain-id");
		String screenName = request.getParameter("name");
		if (domainId != 0 && screenName.isBlank() == false) {
			screenService.add(screenName, domainId);
			String selectedScreenId = request.getParameter("selected-screen-id");
			try {
				response.sendRedirect("/PM/screen?id=" + selectedScreenId + "&domain-id=" + domainId);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
