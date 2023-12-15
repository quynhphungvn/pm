<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>SQL Query</title>
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
        <h3 class="ms-1">SQL Queries</h3>  
      </header>
      <main>
        <div class="ui vertical menu w-100 mt-2">
        	<c:forEach var="query" items="${sqlQueries}">
	        	<a href="/PM/sqlquery?id=${query.id}" class="item ${query.id == selectedQuery.id ? 'active' : ''}">
	            ${query.name}
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
            <button class="item w-100 ${selectedQuery == null ? 'disabled' : '' }" onclick="showModalEdit()">
              <i class="edit icon"></i>
              Edit
            </button>
            <button class="item w-100 ${selectedQuery == null ? 'disabled' : '' }" 
            		onclick="deleteQuery(${selectedQuery.id},'${selectedQuery.name}', '${selectedDomain.id}')">
              <i class="trash icon"></i>
              Delete
            </button>
          </div>
        </div>
        <div class="ui mini modal add">
          <div class="header">
            Add Query
          </div>
          <div class="content">
            <form method="post" action="/PM/sqlquery/add"
            		class="ui form" name="add">
            	<input type="hidden" name="domain-id" value="${selectedDomain.id}">
            	<input type="hidden" name="selected-query-id" value="${selectedQuery.id}">
              <div class="field">
                <label>Name</label>
                <input type="text" name="name" placeholder="Name">
              </div>
              <button class="ui button" type="submit">Add</button>
            </form>
          </div>
        </div>
        <div class="ui modal edit">
          <div class="header">
            Edit Query Name
          </div>
          <div class="content">
            <form method="post" action="/PM/sqlquery/update-name"
            	class="ui form" name="edit">
            	<input type="hidden" name="query-id" value="${selectedQuery.id}">
              <div class="mb-3">
                <label>Name: </label>
                <strong>${selectedQuery.name}</strong>
              </div>
              <div class="field">
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
          <div class="divider"> / </div>
          <a href="/PM/domain/info?id=${selectedDomain.id}" class="section">${selectedDomain.name}</a>
          <div class="divider"> / </div>
          <div class="active section">${selectedQuery.name}</div>
        </div> 
      </header>
      <main class="p-4">
        <div class="info-wrapper flow">        
          <section>
            <h4 class="ui header fs-3">Queries</h4>
            <form method="post" action="/PM/sqlquery/update-content" name="sql-content"
            	class="ui form">
            	<input type="hidden" name="query-id" value="${selectedQuery.id}">
              <div class="field">
                <textarea rows="38" readonly name="content">${selectedQuery.queryContent}</textarea>
              </div>   
              <div class="d-flex justify-content-end">
                <button class="ui button d-none js-update-content" type="submit">Update</button>
                <button type="button" class="ui icon button ${selectedQuery == null ? 'disabled' : ''}" onclick="enableEditMode()">
                		<i class="edit icon"></i>
                </button>    
              </div>         
            </form>
            
          </section>
        </div>
      </main>   
    </main>
	</div>
	<script src="/PM/libs/jquery-3.7.1.min.js"></script>
	<script src="/PM/libs/semantic-ui/semantic.min.js"></script>
	<script src="/PM/libs/css-custom/scripts.js"></script>
	<script><%@include file="./script.js" %></script>
</body>
</html>