<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List, quynh.java.webapp.pm.model.Project,quynh.java.webapp.pm.model.Domain" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate">
  <meta http-equiv="Pragma" content="no-cache">
  <meta http-equiv="Expires" content="0">
  <title>Projects</title>
  <link rel="stylesheet" href="libs/bootstrap/css/bootstrap-utilities.css">
  <link rel="stylesheet" href="libs/fontawesome-free-6.4.2-web/css/fontawesome.min.css">
  <link rel="stylesheet" href="libs/fontawesome-free-6.4.2-web/css/brands.min.css">
  <link rel="stylesheet" href="libs/fontawesome-free-6.4.2-web/css/solid.min.css">
  <link rel="stylesheet" href="libs/css-framework/self/styles.css">
  <link rel="stylesheet" href="libs/common/styles.css">
  <style><%@include file="./styles.css" %></style>
</head>

<body onload="setupPage()">
    <%
        List<Project> projects = (List<Project>) request.getAttribute("projects");
        List<Domain> domains = (List<Domain>) request.getAttribute("domains");
        Project selectedProject = (Project) request.getAttribute("selectedProject");
    %>
    <div class="container">
        <aside id="aside">
      <div class="aside-block">
        <div class="aside-block__content">
            <header>
              <div class="header-content">
                <h3 class="fs-2">Projects</h3>
              </div>    
            </header>
            <main>
              <div class="main-content">
                <ul class="list-item-link-edit">
                  <% 
                  if (projects != null) {
                  for (Project proj : projects) {
                      if (selectedProject != null) {
                          if (proj.getId() == selectedProject.getId())
                              out.print("<li class=\"item active\">");
                          else out.print("<li class=\"item\">");
                      }
                      else out.print("<li class=\"item\">");
                      
                      out.print("<a href=\"" + "project?id=" + proj.getId() + "\">" + proj.getName() + "</a>");
	                  out.print("<div class=\"dropdown\">"
	                          +  "<div class=\"dropdown__trigger\">"
	                          +"<button type=\"button\" class=\"btn-icon\">"
	                          +  "  <i class=\"fa-solid fa-ellipsis-vertical\"></i>"
	                          +"</button>"
	                          +"</div>"
	                          + "<div class=\"dropdown__content\">"                              
	                          +   " <div class=\"list-item-button\">"                      
	                          +       "<button type=\"button\" class=\"item\" data-target=\"#modal-el-editproject\" onclick=\"openModalEl(this)\")"
	                          +                        "data-project-id=\""+ proj.getId() + "\" data-project-name=\""+ proj.getName() + "\">"
	                          +            "<i class=\"fa-solid fa-pen\"></i>"
	                          +        "</button> "                          
	                          +        "<button type=\"button\" class=\"item\"><i class=\"fa-solid fa-trash\"></i></button> "                              
	                          +     "</div>"
	                          +     "</div>"
	                          +"</div>"
	                          +"</li>" ); 
                      }        
                  }%>                
              </ul>   
              </div>
            </main>
            <footer>
              <div class="footer-content">
                <button class="btn-icon-round" data-target="#modal-el-addproject" onclick="openModalEl(this)"><i class="fa-solid fa-plus"></i></button>
              </div>
            </footer>
        </div>
      </div>
      <dialog id="modal-el-addproject" class="modal-el">
        <!-- require the parent: position: relative | absolute -->
        <div class="modal-el__container">
            <div class="modal-el__content">
                <header>
                    <h5 class="modal-el__title">Add project</h5>
                    <button type="button" class="btn-icon" data-target="#modal-el-addproject" onclick="closeModalEl(this)">
                        <i class="fa-solid fa-xmark"></i>
                    </button>
                </header>
                <main>
                  <form>
                    <div>
                      <label class="form-label">Project name</label>
                      <input class="form-control" />
                    </div>
                    <div class="control">
                      <button type="reset" class="btn">Clear</button>
                      <button type="submit" class="btn">Add</button>
                    </div>
                  </form>
                </main>
                <footer>
    
                </footer>
            </div>
        </div>
      </dialog>
      <dialog id="modal-el-editproject" class="modal-el">
        <!-- require the parent: position: relative | absolute -->
        <div class="modal-el__container">
            <div class="modal-el__content">
                <header>
                    <h5 class="modal-el__title">Edit project</h5>
                    <button type="button" class="btn-icon" data-target="#modal-el-editproject" onclick="closeModalEl(this)">
                        <i class="fa-solid fa-xmark"></i>
                    </button>
                </header>
                <main>
                  <form>
                    <form>
                      <div>
                        <label class="form-label">Name</label>
                        <input class="form-control" />
                      </div>
                      <div>
                        <label class="form-label">New name</label>
                        <input class="form-control" />
                      </div>
                      <div class="control">
                        <button type="reset" class="btn">Reset</button>
                        <button type="submit" class="btn">Update</button>
                      </div>
                    </form>
                  </form>
                </main>
                <footer>
    
                </footer>
            </div>
        </div>
      </dialog>
      <dialog id="modal-el-editoverview" class="modal-el">
        <!-- require the parent: position: relative | absolute -->
        <div class="modal-el__container">
            <div class="modal-el__content">
                <header>
                    <h5 class="modal-el__title">Edit Overview</h5>
                    <button type="button" class="btn-icon" data-target="#modal-el-editoverview" onclick="closeModalEl(this)">
                        <i class="fa-solid fa-xmark"></i>
                    </button>
                </header>
                <main>
                  <form>
                    <form>
                      <div>
                        <label class="form-label">Overview</label>
                        <textarea class="form-control" cols="35" rows="30">
                            <%
                              if (selectedProject != null)
                               out.print(selectedProject.getOverview()); 
                             %>
                        </textarea>
                      </div>
                      <div class="control">
                        <button type="reset" class="btn">Reset</button>
                        <button type="submit" class="btn">Update</button>
                      </div>
                    </form>
                  </form>
                </main>
                <footer>
    
                </footer>
            </div>
        </div>
      </dialog>
      <dialog id="modal-el-editscope" class="modal-el">
        <!-- require the parent: position: relative | absolute -->
        <div class="modal-el__container">
            <div class="modal-el__content">
                <header>
                    <h5 class="modal-el__title">Edit Scope</h5>
                    <button type="button" class="btn-icon" data-target="#modal-el-editscope" onclick="closeModalEl(this)">
                        <i class="fa-solid fa-xmark"></i>
                    </button>
                </header>
                <main>
                  <form>
                    <form>
                      <div>
                        <label class="form-label">Scope</label>
                        <textarea class="form-control" cols="35" rows="30">
                             <%
                              if (selectedProject != null)
                               out.print(selectedProject.getScope()); 
                             %>
                        </textarea>
                      </div>
                      <div class="control">
                        <button type="reset" class="btn">Reset</button>
                        <button type="submit" class="btn">Update</button>
                      </div>
                    </form>
                  </form>
                </main>
                <footer>
    
                </footer>
            </div>
        </div>
      </dialog>
      <dialog id="modal-el-edittechusing" class="modal-el">
        <!-- require the parent: position: relative | absolute -->
        <div class="modal-el__container">
            <div class="modal-el__content">
                <header>
                    <h5 class="modal-el__title">Edit tech using</h5>
                    <button type="button" class="btn-icon" data-target="#modal-el-edittechusing" onclick="closeModalEl(this)">
                        <i class="fa-solid fa-xmark"></i>
                    </button>
                </header>
                <main>
                  <form>
                    <form>
                      <div>
                        <label class="form-label">Tech using</label>
                        <textarea class="form-control" cols="35" rows="30">
                        <%
                           if (selectedProject != null)
                              out.print(selectedProject.getTechnology()); 
                        %>                        
                        </textarea>
                      </div>
                      <div class="control">
                        <button type="reset" class="btn">Reset</button>
                        <button type="submit" class="btn">Update</button>
                      </div>
                    </form>
                  </form>
                </main>
                <footer>
    
                </footer>
            </div>
        </div>
      </dialog>
    </aside>
        <main id="main">
      <div class="main-content">
        <header>       
          <span class="title-header">
          <%
          if (selectedProject != null)
              out.print(selectedProject.getName());
          %>
          </span> 
          <div class="d-flex align-items-center">
            <div class="dropdown">
              <div class="dropdown__trigger">
                  <button type="button" class="btn-icon">
                      <i class="fa-solid fa-ellipsis-vertical"></i>
                  </button>
              </div>
              <div class="dropdown__content">
                  <!-- content -->
                  <div class="list-item-button">
                      <button type="button" class="item"><i class="fa-solid fa-plus"></i></button>                        
                      <button type="button" class="item"><i class="fa-solid fa-pen"></i></button>                           
                      <button type="button" class="item"><i class="fa-solid fa-trash"></i></button>                               
                  </div>
              </div>
            </div>
            <select class="domain-selector" onchange="setLinkToDomain()">
              <option value="-1">Domain ...</option>
              <%
              if (domains != null)
                  for (Domain domain : domains) 
                      out.print("<option value=\"" + domain.getId() + "\">" + domain.getName() + "</option>");
              
              %>
            </select>
            <a class="domain-link" href="#" class="btn-icon ms-2"><i class="fa-solid fa-right-long fs-2"></i></a>
          </div>
        </header>
        <main>
          <div class="overview">           
            <div class="title-edit">
              <span class="title-edit__text">Overview</span>
              <div class="title-edit__icon">
                <div>
                  <button type="button" data-target="#modal-el-editoverview" onclick="openModalEl(this)">
                    <i class="fa-solid fa-pen"></i>
                  </button>
                </div>    
              </div>
            </div>
            <p>
              <%
                 if (selectedProject != null)
                  out.print(selectedProject.getOverview()); 
              %>
            </p>
          </div>
          <div class="scope">        
            <div class="title-edit">
              <span class="title-edit__text">Scope</span>
              <div class="title-edit__icon">
                <div>
                  <button type="button" data-target="#modal-el-editscope" onclick="openModalEl(this)">
                    <i class="fa-solid fa-pen"></i>
                  </button>
                </div>    
              </div>
            </div>
            <p>
              <%
                 if (selectedProject != null)
                    out.print(selectedProject.getScope()); 
              %>
            </p>
          </div>
          <div class="techusing">           
            <div class="title-edit">
              <span class="title-edit__text">Tech Using</span>
              <div class="title-edit__icon">
                <div>
                  <button type="button" data-target="#modal-el-edittechusing" onclick="openModalEl(this)">
                    <i class="fa-solid fa-pen"></i>
                  </button>
                </div>    
              </div>
            </div>
            <p>
                <%
                 if (selectedProject != null)
                    out.print(selectedProject.getTechnology()); 
                %>
            </p>
          </div>
        </main>
      </div>
    </main>
    </div>
  <script src="libs/css-framework/self/scripts.js"></script>
  <script src="libs/common/scripts.js"></script>
  <script>
  function setupPage() {
      setupCssFramework();
  }
  function setLinkToDomain() {
	    let domainId = document.querySelector("select.domain-selector").value; 
	    let linkEl = document.querySelector("a.domain-link");
	    if (domainId != -1) {
	        let hrefValue = "domain";
	        linkEl.setAttribute("href", hrefValue + "?id=" + domainId);
	    } else {
	        linkEl.setAttribute("href", "#");
	    }
	}
  </script>
</body>

</html>