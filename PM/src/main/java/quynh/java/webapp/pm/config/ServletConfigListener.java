package quynh.java.webapp.pm.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ServletConfigListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent servletContextEvent) {
    	setRootPathApp(servletContextEvent);
    }
    private void setRootPathApp(ServletContextEvent servletContextEvent) {
    	ServletContext ctx = servletContextEvent.getServletContext();
    	String path = ctx.getRealPath("").replace("\\", "/");
    	ctx.setAttribute("rootPath", path);
    }
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		//do cleanup if needed
	}
	
}
