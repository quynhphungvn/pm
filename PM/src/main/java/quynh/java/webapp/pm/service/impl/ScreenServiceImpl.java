package quynh.java.webapp.pm.service.impl;

import java.util.List;

import quynh.java.webapp.pm.dao.DomainDao;
import quynh.java.webapp.pm.dao.ScreenDao;
import quynh.java.webapp.pm.dao.hql_impl.DomainDaoImpl;
import quynh.java.webapp.pm.dao.hql_impl.ScreenDaoImpl;
import quynh.java.webapp.pm.model.Domain;
import quynh.java.webapp.pm.model.Screen;
import quynh.java.webapp.pm.service.ScreenService;
import quynh.java.webapp.pm.util.plantuml.DiagramType;
import quynh.java.webapp.pm.util.plantuml.DiagramUtil;

public class ScreenServiceImpl implements ScreenService {
	DomainDao domainDao = new DomainDaoImpl();
	ScreenDao screenDao = new ScreenDaoImpl();
	@Override
	public Screen getById(int screenId) {
		return screenDao.getById(screenId);
	}

	@Override
	public List<Screen> getAll(Domain domain) {
		return screenDao.getAll(domain);
	}

	@Override
	public Screen add(String screenName, int domainId) {
		Domain domain = domainDao.getById(domainId);
		Screen screen = new Screen();
		screen.setName(screenName);
		screen.setUsecaseDiagram(DiagramUtil.getInitText(DiagramType.USECASE.toString()));
		screen.setDomain(domain);
		return screenDao.add(screen);
	}

	@Override
	public Screen updateName(String newName, int screenId) {
		Screen screen = screenDao.getById(screenId);
		if (screen != null) {
			screen.setName(newName);
			screenDao.update(screen);
		}
		return screen;
	}
	
	@Override
	public Screen updateWireframeImage(byte[] wireframeImage, int screenId) {
		Screen screen = screenDao.getById(screenId);
		if (screen != null) {
			screen.setWireframeImage(wireframeImage);
			screenDao.update(screen);
		}
		return screen;
	}
	@Override
	public Screen updateUsecaseDiagram(String diagram, int screenId) {
		Screen screen = screenDao.getById(screenId);
		if (screen != null) {
			screen.setUsecaseDiagram(diagram);
			screenDao.update(screen);
		}
		return screen;
	}

	@Override
	public Screen deleteScreen(int screenId) {
		Screen screen = screenDao.getById(screenId);
		if (screen != null)
			screenDao.delete(screen);
		return screen;
	}
}
