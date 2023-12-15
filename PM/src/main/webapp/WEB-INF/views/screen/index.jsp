<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Screen</title>
  <link rel="stylesheet" href="/PM/libs/semantic-ui/semantic.min.css">
  <link rel="stylesheet" href="/PM/libs/bootstrap/css/bootstrap-utilities.min.css">
  <link rel="stylesheet" href="/PM/libs/css-custom/styles.css">
  <style><%@include file="./styles.css" %></style>
</head>

<body>
	<div id="main-wrapper" class="d-flex">
		<%@ include file="../fragment/left-nav/index.jsp"%>
		<aside class="left-bar border-end">
      <header class="d-flex align-items-center border-bottom">
        <h3 class="ms-1 text-primary">Screen</h3>     
      </header>
      <main>
        <div class="ui vertical menu w-100">
        	<c:forEach var="screen" items="${screens}">
        		<a href="/PM/screen?id=${screen.id}" 
        			class="item ${selectedScreen.id == screen.id? 'active' : ''}">
		            ${screen.name}
		        </a>	
        	</c:forEach>     
        </div>
      </main>
      <footer class="d-flex align-items-center">
        <div class="ui left pointing dropdown icon button">
          <i class="settings icon"></i>
          <div class="menu">
            <button class="item w-100" onclick="showModalAdd()">
              <i class="plus icon"></i>
              Add
            </button>
            <button class="item w-100 ${selectedScreen == null? 'disabled' : '' }" onclick="showModalEdit()">
              <i class="edit icon"></i>
              Edit
            </button>
            <button class="item w-100 ${selectedScreen == null? 'disabled' : '' }" 
            		onclick="deleteScreen(${selectedScreen.id},'${selectedScreen.name}', '${selectedDomain.id}')">
              <i class="trash icon"></i>
              Delete
            </button>
          </div>
        </div>
        <div class="ui mini modal add">
          <div class="header">
            Add Screen
          </div>
          <div class="content">
            <form method="post" action="/PM/screen/add"
            	 class="ui form" name="add">
            	 <input type="hidden" name="selected-screen-id" value="${selectedScreen.id}" >
            	<input type="hidden" name="domain-id" value="${selectedDomain.id}" > 
              <div class="field">
                <label>Name</label>
                <input type="text" name="name" placeholder="Screen Name">
              </div>
              <button class="ui button" type="submit">Add</button>
            </form>
          </div>
        </div>
        <div class="ui modal edit">
          <div class="header">
            Edit Screen
          </div>
          <div class="content">
            <form method="post" action="/PM/screen/update-name"
            	class="ui form" name="edit">
            	<input type="hidden" name="screen-id" value="${selectedScreen.id }">
              <div class="d-flex mb-2">
                <label>Name:</label>
                <strong class="ms-2">${selectedScreen.name}</strong>
              </div>
              <div class="field mb-5">
                <label>New Name</label>
                <input type="text" name="new-name" placeholder="New Name">
              </div>
              <button class="ui button" type="submit">Update</button>
            </form>
          </div>
        </div>

      </footer>
    </aside>
    <main class="main-content">
      <header class="d-flex align-items-center">
        <div class="ui breadcrumb ms-3">
          <a href="/PM/project?id=${selectedProject.id}" class="section">${selectedProject.name}</a>
          <i class="right angle icon divider"></i>
          <a href="/PM/domain/info?id=${selectedDomain.id}" class="section">${selectedDomain.name }</a>
          <i class="right angle icon divider"></i>
          <div class="active section">Screen: ${selectedScreen.name}</div>
        </div>
      </header>
      <main class="d-flex">
	      <c:if test="${selectedScreen != null}">
	        <div class="diagram-wrapper resize-both">
	          <button class="circular ui icon button position-absolute" style="top: 1rem; left: 1rem;"
	            onclick="showModalImageWireframe()">
	            <i class="pencil alternate icon"></i>
	          </button>
	          <div class="ui modal image-wireframe">
	            <i class="close icon"></i>
	            <div class="header">
	              Change Wireframe Image
	            </div>
	            <div class="content">
	              <form method="post" action="/PM/screen/update-wireframe" 
	              		enctype="multipart/form-data">
	              	<input type="hidden" name="screen-id" value="${selectedScreen.id}"/>
	                <div class="field input mb-3">
	                  <input type="file" name="wireframe-image" />
	                </div>
	                <div>
	                  <button class="ui button" type="submit">Upload</button>
	                </div>
	              </form>
	            </div>
	          </div>
	          <div class="img-wrapper">
	            <img src="data:image/png;base64,${wireframeImage}" alt="wireframe-image">
	          </div>
	
	        </div>
	        <div class="diagram-wrapper resize-both">
	          <button class="circular ui icon button position-absolute" style="top: 1rem; left: 1rem;"
	            onclick="showOffCanvas('#canvas-usecase')">
	            <i class="pencil alternate icon"></i>
	          </button>
	          <div id="canvas-usecase" class="offcanvas" data-location="left" data-width="440px">
	            <div class="offcanvas__content">
	              <header>
	                <h3 class="offcanvas__title">Usecase</h3>
	                <button class="ui icon button" type="button" onclick="hideOffCanvas('#canvas-usecase')">
	                  <i class="close icon"></i>
	                </button>
	              </header>
	              <main>
	                <form method="post" action="/PM/screen/update-usecase"
	                	class="ui form p-4">
	                  <input type="hidden" name="screen-id" value="${selectedScreen.id}">
	                  <div class="field">
	                    <label>Diagram</label>
	                    <textarea rows="35" name="usecase-diagram">${selectedScreen.usecaseDiagram}</textarea>
	                  </div>
	                  <div class="actions">
	                    <div class="d-flex justify-content-end">
	                      <button type="button" class="ui icon button" 
	                      			onclick="testUsecaseDiagram(${selectedScreen.id}, ${selectedDomain.id}, ${selectedProject.id})">
	                      	<i class="sync icon"></i>
	                      </button>
	                    </div>
	                    <div class="d-flex justify-content-between mt-3">
	                      <button class="ui button">Reset</button>
	                      <button class="ui button" type="submit">Update</button>
	                    </div>
	                  </div>
	                </form>
	              </main>
	              <footer>
	
	              </footer>
	            </div>
	          </div>
	          <div class="img-wrapper">
	            <img class="diagram-image" src="/PM${publicDiagramURI}">
	          </div>
	        </div>
	      </c:if>
      </main>	
	</div>
	<script src="/PM/libs/jquery-3.7.1.min.js"></script>
	<script src="/PM/libs/semantic-ui/semantic.min.js"></script>
	<script src="/PM/libs/css-custom/scripts.js"></script>
	<script><%@include file="./script.js" %></script>
</body>
</html>