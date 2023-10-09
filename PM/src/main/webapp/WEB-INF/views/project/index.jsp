<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Project</title>
  <link rel="stylesheet" href="/PM/libs/semantic-ui/semantic.min.css">
  <link rel="stylesheet" href="/PM/libs/bootstrap/css/bootstrap-utilities.min.css">
  <style><%@include file="./styles.css" %></style>
</head>

<body>
  <div id="main-wrapper" class="d-flex">
    <aside class="left-bar">
      <header class="d-flex align-items-center ">
        <h3>Projects</h3>
      </header>
      <main>
        <div class="ui vertical menu w-100">
        	<c:forEach var="proj" items="${projects}">
	        	<a href="/PM/project?id=${proj.id}" class="item ${selectedProject.id == proj.id ? 'active' : ''}">
	            	${proj.name}
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
            <button class="item w-100 ${selectedProject == null ? 'disabled' : ''}" onclick="showModalEdit()">
              <i class="edit icon"></i>
              Edit
            </button>
            <button class="item w-100 ${selectedProject == null ? 'disabled' : ''}" onclick="deleteProject(${selectedProject.id},'${selectedProject.name}')">
              <i class="trash icon"></i>
              Delete
            </button>
          </div>
        </div>
        <div class="ui mini modal add">
          <div class="header">
            Add Project
          </div>
          <div class="content">
            <form method="post" action="/PM/project/add" class="ui form" name="add">
            	<input type="hidden" name="selected-project-id" value="${selectedProject.id}" > 
              <div class="field">
                <label>New Project</label>
                <input type="text" name="name" placeholder="Project Name">
              </div>
              <button class="ui button" type="submit">Add</button>
            </form>
          </div>
        </div>
        <div class="ui modal edit">
          <div class="header">
            Update project
          </div>
          <div class="content">
            <form method="post" action="/PM/project/update" class="ui form" name="edit">
            	<input type="hidden" name="project-id" value="${selectedProject.id}" > 
              <div class="d-flex mb-3">
                <label>Name: </label>
                <strong class="ms-2">${selectedProject.name}</strong>
              </div>
              <div class="field">
                <label>New Name</label>
                <input type="text" name="new-name" placeholder="New Name">
              </div>
              <div class="field">
                <label>Overview</label>
                <textarea rows="4" name="overview">${selectedProject.overview}</textarea>
              </div>
              <div class="field">
                <label>Scope</label>
                <textarea rows="4" name="scope">${selectedProject.scope}</textarea>
              </div>
              <div class="field">
                <label>Technology</label>
                <textarea rows="4" name="technology">${selectedProject.technology}</textarea>
              </div>
              <button class="ui button" type="submit">Update</button>
            </form>
          </div>
        </div>

      </footer>
    </aside>
    <main class="main-content">
      <div class="content-wrapper flow p-4">
        <div class="d-flex justify-content-between align-items-center">
          <p class="ui header bg-success text-white p-1 rounded">${selectedProject != null ? selectedProject.name : '...'}</p>
          <a href="/PM/domain/info?project-id=${selectedProject.id}" 
          	 target="_blank"
          	 class="me-4 ui button ${selectedProject == null ? 'disabled' : ''}">Domains</a>
        </div>
        
        <div class="bg-light p-3 border">
          <h4 class="ui header">Overview</h4>
          <p>${selectedProject.overview}</p>
        </div>
        <div class="bg-light p-3 border">
          <h4 class="ui header">Scope</h4>
          <p>${selectedProject.scope}</p>
        </div>
        <div class="bg-light p-3 border">
          <h4 class="ui header">Technology</h4>
          <p>${selectedProject.technology}</p>
        </div>
        <div>       
        </div>
      </div>
    </main>
  </div>
  <script src="/PM/libs/jquery-3.7.1.min.js"></script>
  <script src="/PM/libs/semantic-ui/semantic.min.js"></script>
  <script><%@include file="./script.js" %></script>
</body>
</html>