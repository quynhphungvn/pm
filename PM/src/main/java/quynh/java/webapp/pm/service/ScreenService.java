package quynh.java.webapp.pm.service;

import java.util.List;

import quynh.java.webapp.pm.model.Domain;
import quynh.java.webapp.pm.model.Screen;

public interface ScreenService {

	Screen getById(int screenId);

	List<Screen> getAll(Domain domain);

	Screen add(String screenName, int domainId);

	Screen updateName(String newName, int screenId);

	Screen updateWireframeImage(byte[] wireframeImage, int screenId);

	Screen updateUsecaseDiagram(String diagram, int screenId);

	Screen deleteScreen(int screenId);

}
