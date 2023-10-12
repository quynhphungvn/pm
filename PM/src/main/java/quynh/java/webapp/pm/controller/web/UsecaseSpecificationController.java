package quynh.java.webapp.pm.controller.web;

import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import quynh.java.webapp.pm.model.Domain;
import quynh.java.webapp.pm.model.Project;
import quynh.java.webapp.pm.model.Usecase;
import quynh.java.webapp.pm.service.DomainService;
import quynh.java.webapp.pm.service.UsecaseService;
import quynh.java.webapp.pm.service.impl.DomainServiceImpl;
import quynh.java.webapp.pm.service.impl.UsecaseServiceImpl;
import quynh.java.webapp.pm.util.RequestUtil;
import quynh.java.webapp.pm.util.plantuml.DiagramType;
import quynh.java.webapp.pm.util.plantuml.DiagramUtil;

/**
 * Servlet implementation class UsecaseSpecificationController
 */
public class UsecaseSpecificationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DomainService domainService = new DomainServiceImpl();
	UsecaseService usecaseService = new UsecaseServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsecaseSpecificationController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Project selectedProject = null;
		Domain selectedDomain = null;
		Usecase selectedUsecase = null;
		List<Usecase> usecases = null;
		String publicActivityDiagramURI = null;
		String publicSequenceDiagramURI = null;
		int usecaseId = RequestUtil.getParameterInt(request, "id");
		if (usecaseId == 0) {
			int domainId = RequestUtil.getParameterInt(request, "domain-id");
			if (domainId != 0) {
				selectedDomain = domainService.getById(domainId);
				if (selectedDomain != null) {
					selectedProject = selectedDomain.getProject();
					usecases = usecaseService.getAll(selectedDomain);
					processFowardRequest(selectedProject, selectedDomain, selectedUsecase, usecases, 
										publicActivityDiagramURI, publicSequenceDiagramURI, request, response);
				} else {
					response.sendRedirect("/PM/project");
				}
			} else {
				response.sendRedirect("/PM/project");
			}
		} else {
			selectedUsecase = usecaseService.getById(usecaseId);
			if (selectedUsecase != null)
			{
				selectedDomain = selectedUsecase.getDomain();
				if (selectedDomain != null) {
					selectedProject = selectedDomain.getProject(); 
					if (selectedProject != null) {
						String appDiskPath = (String) request.getServletContext().getAttribute("rootPath");
						publicActivityDiagramURI = "/PM" + DiagramUtil
																.createDiagramImage(appDiskPath, selectedProject.getId(), selectedDomain.getId(),
																					selectedUsecase.getActivityDiagram(), DiagramType.ACTIVITY)
														+ "?t=" + LocalTime.now().getNano();
						publicSequenceDiagramURI = "/PM" + DiagramUtil
																.createDiagramImage(appDiskPath, selectedProject.getId(), selectedDomain.getId(),
																					selectedUsecase.getSequenceDiagram(), DiagramType.SEQUENCE)
																	+ "?t=" + LocalTime.now().getNano();
						usecases = usecaseService.getAll(selectedDomain);
					}
				}
				
			}
			processFowardRequest(selectedProject, selectedDomain, selectedUsecase, usecases, 
								publicActivityDiagramURI, publicSequenceDiagramURI, request, response);
		}
	}
	private void processFowardRequest(Project selectedProject, Domain selectedDomain, Usecase selectedUsecase, 
										List<Usecase> usecases, String publicActivityDiagramURI, String publicSequenceDiagramURI,
										HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("selectedProject", selectedProject);
		request.setAttribute("selectedDomain", selectedDomain);
		request.setAttribute("selectedUsecase", selectedUsecase);
		request.setAttribute("usecases",usecases);
		request.setAttribute("publicActivityDiagramURI", publicActivityDiagramURI);
		request.setAttribute("publicSequenceDiagramURI", publicSequenceDiagramURI);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/usecase-specification/index.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String reqUri = request.getRequestURI();
		if (reqUri.contains("/usecase/add")) {
			processAddUsecase(request, response);
		} else if (reqUri.contains("/usecase/delete")) {
			processDeleteUsecase(request, response);
		} else if (reqUri.contains("/usecase/update-name")) {
			processUpdateUsecaseName(request, response);
		} else if (reqUri.contains("/usecase/update-spec")) {
			processUpdateUsecaseSpec(request, response);
		} else if (reqUri.contains("/usecase/update-activity")) {
			processUpdateActivity(request, response);
		} else if (reqUri.contains("/usecase/update-sequence")) {
			processUpdateSequence(request, response);
		} else if (reqUri.contains("/usecase/test-activity")) {
			processTestActivity(request, response);
		} else if (reqUri.contains("/usecase/test-sequence")) {
			processTestSequence(request, response);
		} else {
			
		}
	}
	private void processTestSequence(HttpServletRequest request, HttpServletResponse response) {
		int usecaseId = RequestUtil.getParameterInt(request, "usecase-id");
		if (usecaseId > 0) {
			String sequenceDiagram = request.getParameter("sequence-diagram");
			String rootPath = (String) request.getServletContext().getAttribute("rootPath");
			String seqURI = usecaseService.testSequenceDiagram(rootPath, usecaseId, sequenceDiagram);
			response.setContentType("text/plain");
			try {
				response.getWriter().print(seqURI);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	private void processTestActivity(HttpServletRequest request, HttpServletResponse response) {
		int usecaseId = RequestUtil.getParameterInt(request, "usecase-id");
		if (usecaseId > 0) {
			String activityDiagram = request.getParameter("activity-diagram");
			String rootPath = (String) request.getServletContext().getAttribute("rootPath");
			String actURI = usecaseService.testActivityDiagram(rootPath, usecaseId, activityDiagram);
			response.setContentType("text/plain");
			try {
				response.getWriter().print(actURI);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	private void processUpdateSequence(HttpServletRequest request, HttpServletResponse response) {
		int usecaseId = RequestUtil.getParameterInt(request, "usecase-id");
		if (usecaseId > 0) {
			String sequenceDiagram = request.getParameter("sequence-diagram");
			usecaseService.updateSequenceDiagram(usecaseId, sequenceDiagram);
			try {
				response.sendRedirect("/PM/usecase?id=" + usecaseId);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	private void processUpdateActivity(HttpServletRequest request, HttpServletResponse response) {
		int usecaseId = RequestUtil.getParameterInt(request, "usecase-id");
		if (usecaseId > 0) {
			String activityDiagram = request.getParameter("activity-diagram");
			usecaseService.updateActivityDiagram(usecaseId, activityDiagram);
			try {
				response.sendRedirect("/PM/usecase?id=" + usecaseId);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	private void processUpdateUsecaseName(HttpServletRequest request, HttpServletResponse response) {
		int usecaseId = RequestUtil.getParameterInt(request, "usecase-id");
		if (usecaseId > 0) {
			String name = request.getParameter("new-name");
			usecaseService.updateName(usecaseId, name);
			try {
				response.sendRedirect("/PM/usecase?id=" + usecaseId);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	private void processUpdateUsecaseSpec(HttpServletRequest request, HttpServletResponse response) {
		int usecaseId = RequestUtil.getParameterInt(request, "usecase-id");
		if (usecaseId > 0) {
			String createdBy = request.getParameter("created-by");
			String actors = request.getParameter("actors");
			String trigger = request.getParameter("trigger");
			String description = request.getParameter("description");
			String preconditions = request.getParameter("preconditions");
			String postconditions = request.getParameter("postconditions");
			String normalFlow = request.getParameter("normal-flow");
			String alternativeFlow = request.getParameter("alternative-flow");
			String exceptions = request.getParameter("exceptions");
			String priority = request.getParameter("priority");
			String frequencyOfUse = request.getParameter("frequency-of-use");
			String bussinessRules = request.getParameter("bussiness-rules");
			String otherInformations = request.getParameter("other-infomations");
			String assumptions = request.getParameter("assumptions");
			Usecase usecaseTemp = new Usecase(usecaseId, null, createdBy,null, actors, 
					trigger, description, preconditions, postconditions, normalFlow,
					alternativeFlow, exceptions, priority, frequencyOfUse, bussinessRules,
					otherInformations, assumptions, null, null, null);
			usecaseService.updateSpec(usecaseTemp);
			try {
				response.sendRedirect("/PM/usecase?id=" + usecaseId);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	private void processDeleteUsecase(HttpServletRequest request, HttpServletResponse response) {
		int usecaseId = RequestUtil.getParameterInt(request, "usecase-id");
		if (usecaseId > 0)
			usecaseService.delete(usecaseId);
		String domainId = request.getParameter("domain-id");
		try {
			response.sendRedirect("/PM/usecase?domain-id=" + domainId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void processAddUsecase(HttpServletRequest request, HttpServletResponse response) {
		int domainId = RequestUtil.getParameterInt(request, "domain-id");
		if (domainId > 0) {
			String name = request.getParameter("name");
			if (name.isBlank() == false)
			{
				usecaseService.add(name, domainId);
				String selectedUsecaseId = request.getParameter("selected-usecase-id");
				try {
					response.sendRedirect("/PM/usecase?id=" + selectedUsecaseId + "&domain-id=" + domainId);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
