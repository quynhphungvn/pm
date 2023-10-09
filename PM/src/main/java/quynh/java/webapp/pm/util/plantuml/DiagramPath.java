package quynh.java.webapp.pm.util.plantuml;

public class DiagramPath {
	//assets/diagrams/projectid/domainid/plan/
	//assets/diagrams/projectid/domainid/erd/
	public static String createPlanDiagramURI(int projectId, int domainId) {
		return "/assets/diagrams/" + projectId + "/" + domainId + "/plan/";
	}
	public static String createDocumentDiagramURI(int projectId, int domainId) {
		return "/assets/diagrams/" + projectId + "/" + domainId + "/document/";
	}
}
