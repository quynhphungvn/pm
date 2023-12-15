package quynh.java.webapp.pm.controller.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import quynh.java.webapp.pm.dao.DomainDao;
import quynh.java.webapp.pm.dao.hql_impl.DomainDaoImpl;
import quynh.java.webapp.pm.model.ClassPackage;
import quynh.java.webapp.pm.model.ClassSpec;
import quynh.java.webapp.pm.model.Domain;
import quynh.java.webapp.pm.model.Project;
import quynh.java.webapp.pm.model.TestingFunction;
import quynh.java.webapp.pm.service.ClassPackageService;
import quynh.java.webapp.pm.service.ClassSpecService;
import quynh.java.webapp.pm.service.TestingFunctionService;
import quynh.java.webapp.pm.service.impl.ClassPackageServiceImpl;
import quynh.java.webapp.pm.service.impl.ClassSpecServiceImpl;
import quynh.java.webapp.pm.service.impl.TestingFunctionServiceImpl;
import quynh.java.webapp.pm.util.RequestUtil;

/**
 * Servlet implementation class ClassSpecController
 */
public class ClassSpecController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClassSpecService classSpecService = new ClassSpecServiceImpl();
	private ClassPackageService classPackageService = new ClassPackageServiceImpl();
	private DomainDao domainDao = new DomainDaoImpl();
	private TestingFunctionService testingFunctionService = new TestingFunctionServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClassSpecController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Project selectedProject = null;
		Domain selectedDomain = null;
		List<ClassPackage> packages = null;
		ClassPackage selectedPackage = null;
		List<ClassSpec> classes = null;
		ClassSpec selectedClass = null;
		List<TestingFunction> testingFunctions = null;
		TestingFunction selectedTestingFunction = null;
		String tabView = request.getParameter("tab-view");
		int testId = RequestUtil.getParameterInt(request, "test-id"); 
		if (testId > 0) {
			selectedTestingFunction = testingFunctionService.getById(testId);
			if (selectedTestingFunction != null) {
				selectedClass = selectedTestingFunction.getClassSpec();
				testingFunctions = testingFunctionService.getAll(selectedClass);
				selectedPackage = selectedClass.getClassPackage();
				classes = classSpecService.getAll(selectedPackage);	
				selectedDomain = selectedPackage.getDomain();
				packages = classPackageService.getAll(selectedDomain);
				selectedProject = selectedDomain.getProject();
				processForward(selectedProject, selectedDomain, packages, selectedPackage, classes, 
								selectedClass, testingFunctions, selectedTestingFunction, tabView,  request, response);
			}
		} else {
			int classId = RequestUtil.getParameterInt(request, "id");
			if (classId > 0) {
				selectedClass = classSpecService.getById(classId);
				if (selectedClass != null) {
					testingFunctions = testingFunctionService.getAll(selectedClass);
					selectedPackage = selectedClass.getClassPackage();
					classes = classSpecService.getAll(selectedPackage);
					selectedDomain = selectedPackage.getDomain();
					packages = classPackageService.getAll(selectedDomain);
					selectedProject = selectedDomain.getProject();			
					processForward(selectedProject, selectedDomain, packages, selectedPackage, classes, 
							selectedClass, testingFunctions, selectedTestingFunction, tabView,  request, response);
				}
			} else {
				int packageId = RequestUtil.getParameterInt(request, "package-id");
				if (packageId > 0) {
					selectedPackage = classPackageService.getById(packageId);
					if (selectedPackage != null) {
						classes = classSpecService.getAll(selectedPackage);
						selectedDomain = selectedPackage.getDomain();
						packages = classPackageService.getAll(selectedDomain);
						selectedProject = selectedDomain.getProject();
						processForward(selectedProject, selectedDomain, packages, selectedPackage, classes, 
								selectedClass, testingFunctions, selectedTestingFunction, tabView, request, response);
					}
				} else {
					int domainId = RequestUtil.getParameterInt(request, "domain-id");
					if (domainId > 0) {
						selectedDomain = domainDao.getById(domainId);
						if (selectedDomain != null)
						{
							packages = classPackageService.getAll(selectedDomain);
							selectedProject = selectedDomain.getProject();
							processForward(selectedProject, selectedDomain, packages, selectedPackage, classes, 
									selectedClass, testingFunctions, selectedTestingFunction, tabView, request, response);
						}
					} else {
						response.sendRedirect("/PM/project");
					}
				}
			}
		}
	}
	public void processForward(Project selectedProject, Domain selectedDomain, List<ClassPackage> packages, ClassPackage selectedPackage, List<ClassSpec> classes, 
								ClassSpec selectedClass, List<TestingFunction> testingFunctions, TestingFunction selectedTestingFunction, String tabView,
								HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("selectedProject", selectedProject);
		request.setAttribute("selectedDomain", selectedDomain);
		request.setAttribute("selectedPackage", selectedPackage);
		request.setAttribute("selectedClass", selectedClass);
		request.setAttribute("selectedTestingFunction", selectedTestingFunction);
		request.setAttribute("packages", packages);
		request.setAttribute("classes", classes);
		request.setAttribute("testingFunctions", testingFunctions);
		request.setAttribute("tabView", tabView);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/class-spec/index.jsp");
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
		String pathURI = request.getRequestURI();
		if (pathURI.contains("/class-spec/add-class")) {
			processAddClass(request, response);
		} else if (pathURI.contains("/class-spec/update-class-name")) {
			processUpdateClassName(request, response);
		} else if (pathURI.contains("/class-spec/update-class-spec")) {
			processUpdateClassSpec(request, response);
		} else if (pathURI.contains("/class-spec/delete-class")) {
			processDeleteClass(request, response);
		} else if (pathURI.contains("/class-spec/add-package")) {
			processAddPackage(request, response);
		} else if (pathURI.contains("/class-spec/update-package-info")) {
			processUpdatePackageInfo(request, response);
		} else if (pathURI.contains("/class-spec/delete-package")) {
			processDeletePackage(request, response);
		} else if (pathURI.contains("/class-spec/add-test")) {
			processAddTestFunction(request, response);
		} else if (pathURI.contains("/class-spec/update-test-info")) {
			processUpdateTestInfo(request, response);
		} else if (pathURI.contains("/class-spec/update-test-input")) {
			processUpdateTestInput(request, response);
		} else if (pathURI.contains("/class-spec/update-test-result")) {
			processUpdateTestResult(request, response);
		} else if (pathURI.contains("/class-spec/delete-testing-function")) {
			processDeleteTestFunction(request, response);
		}
	}

	private void processDeleteTestFunction(HttpServletRequest request, HttpServletResponse response) {
		int testId = RequestUtil.getParameterInt(request, "test-id");
		if (testId > 0) {
			testingFunctionService.delete(testId);
		}	
	}
	private void processDeletePackage(HttpServletRequest request, HttpServletResponse response) {
		int classPackageId = RequestUtil.getParameterInt(request, "package-id");
		if (classPackageId > 0) {
			classPackageService.delete(classPackageId);
		}
	}

	private void processDeleteClass(HttpServletRequest request, HttpServletResponse response) {
		int classSpecId = RequestUtil.getParameterInt(request, "class-id");
		if (classSpecId > 0) {
			classSpecService.delete(classSpecId);
		}
	}

	private void processUpdateTestResult(HttpServletRequest request, HttpServletResponse response) {
		int testId = RequestUtil.getParameterInt(request, "selected-testing-function-id");
		if (testId > 0) {
			String planResult = request.getParameter("plan-result");
			testingFunctionService.updatePlanResult(planResult, testId);
			try {
				response.sendRedirect("/PM/class-spec?"
										+ "test-id=" + testId
										+ "&tab-view=test");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}

	private void processUpdateTestInput(HttpServletRequest request, HttpServletResponse response) {
		int testId = RequestUtil.getParameterInt(request, "selected-testing-function-id");
		if (testId > 0) {
			String planInput = request.getParameter("plan-input");
			testingFunctionService.updatePlanInput(planInput, testId);
			try {
				response.sendRedirect("/PM/class-spec?"
										+ "test-id=" + testId
										+ "&tab-view=test");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	private void processUpdateTestInfo(HttpServletRequest request, HttpServletResponse response) {
		int testId = RequestUtil.getParameterInt(request, "selected-testing-function-id");
		if (testId > 0) {
			String newName = request.getParameter("new-name");
			String testRequirement = request.getParameter("requirement");
			String functionParams = request.getParameter("params");
			String functionReturn = request.getParameter("return");
			String functionExceptions = request.getParameter("exceptions");
			String functionLogs = request.getParameter("logs");
			testingFunctionService.updateInfo(newName, testRequirement, 
					functionParams, functionReturn, functionExceptions, functionLogs, testId);
			try {
				response.sendRedirect("/PM/class-spec?"
										+ "test-id=" + testId
										+ "&tab-view=test");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	private void processUpdatePackageInfo(HttpServletRequest request, HttpServletResponse response) {
		int packageId = RequestUtil.getParameterInt(request, "selected-package-id");
		if (packageId > 0) {
			String newName = request.getParameter("new-name");	
			String info = request.getParameter("info");
			classPackageService.updateInfo(newName, info, packageId);
			String selectedTestId = request.getParameter("selected-testing-function-id");
			String selectedClassId = request.getParameter("selected-class-id");
			try {
				response.sendRedirect("/PM/class-spec?"
										+ "test-id=" + selectedTestId
										+ "&id=" + selectedClassId
										+ "&package-id=" + packageId);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void processUpdateClassSpec(HttpServletRequest request, HttpServletResponse response) {
		int classId = RequestUtil.getParameterInt(request, "selected-class-id");
		if (classId > 0) {
			String content = request.getParameter("content");
			classSpecService.updateClassSpec(content, classId);
			String selectedTestId = request.getParameter("selected-testing-function-id");
			try {
				response.sendRedirect("/PM/class-spec?"
										+ "test-id=" + selectedTestId 
										+ "&id=" + classId );
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	private void processUpdateClassName(HttpServletRequest request, HttpServletResponse response) {
		int classId = RequestUtil.getParameterInt(request, "selected-class-id");
		if (classId > 0) {
			String newName = request.getParameter("new-name");
			if (newName != null && newName.isBlank() == false)
				classSpecService.updateNewName(newName, classId);
			String selectedTestId = request.getParameter("selected-testing-function-id");
			try {
				response.sendRedirect("/PM/class-spec?"
										+ "test-id=" + selectedTestId 
										+ "&id=" + classId );
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void processAddTestFunction(HttpServletRequest request, HttpServletResponse response) {
		int classId = RequestUtil.getParameterInt(request, "selected-class-id");
		if (classId > 0) {
			String testName = request.getParameter("name");
			if (testName != null && testName.isBlank() == false) {
				String testRequirement = request.getParameter("requirement");
				String functionParams = request.getParameter("params");
				String functionReturn = request.getParameter("return");
				String functionExceptions = request.getParameter("exceptions");
				String functionLogs = request.getParameter("logs");
				testingFunctionService.add(testName, testRequirement, functionParams, functionReturn,
									functionExceptions, functionLogs, classId);
				String selectedTestId = request.getParameter("selected-testing-function-id");
				try {
					response.sendRedirect("/PM/class-spec?"
											+ "test-id=" + selectedTestId 
											+ "&id=" + classId
											+ "&tab-view=test");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	private void processAddPackage(HttpServletRequest request, HttpServletResponse response) {
		int domainId = RequestUtil.getParameterInt(request, "selected-domain-id");
		if (domainId > 0) {
			String packageName = request.getParameter("name");
			if (packageName != null && packageName.isBlank() == false) {
				String info = request.getParameter("info");
				classPackageService.add(packageName, info, domainId);
				String selectedTestId = request.getParameter("selected-testing-funtion-id");
				String selectedClassId = request.getParameter("selected-class-id");
				String selectedPackageId = request.getParameter("selected-package-id");
				String selectedDomainId = request.getParameter("selected-domain-id");
				try {
					response.sendRedirect("/PM/class-spec?"
							+ "test-id=" + selectedTestId 
							+ "&id=" + selectedClassId 
							+ "&package-id=" + selectedPackageId
							+ "&domain-id=" + selectedDomainId);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	private void processAddClass(HttpServletRequest request, HttpServletResponse response) {
		int packageId = RequestUtil.getParameterInt(request, "selected-package-id");
		if (packageId > 0) {
			String className = request.getParameter("name");
			if (className != null && className.isBlank() == false) {
				classSpecService.add(className, packageId);
				String selectedTestId = request.getParameter("selected-testing-funtion-id");
				String selectedClassId = request.getParameter("selected-class-id");
				try {
					response.sendRedirect("/PM/class-spec?"
											+ "test-id=" + selectedTestId
											+ "&id=" + selectedClassId
											+ "&package-id=" + packageId);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}	
	}
}
