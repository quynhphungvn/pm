<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Domain</title>
  <link rel="stylesheet" href="/PM/libs/semantic-ui/semantic.min.css">
  <link rel="stylesheet" href="/PM/libs/bootstrap/css/bootstrap-utilities.min.css">
  <style><%@include file="./styles.css" %></style>
</head>

<body>
	<div id="main-wrapper" class="d-flex">
		<aside class="left-bar border-end">
			<header class="d-flex align-items-center border-bottom p-1">
				<div class="ui breadcrumb fs-6 ms-2">
					<a href="/PM/project?id=${selectedProject.id}" class="section">${selectedProject.name}</a>
					<div class="divider">/</div>
					<a class="section">${selectedDomain == null ? '...' : selectedDomain.name}</a>
				</div>
			</header>
			<main class="p-1">
				<div class="ui vertical menu w-100">
					<c:forEach var="domain" items="${domains}">
						<a href="/PM/domain/info?id=${domain.id}"
							class="item ${domain.id == selectedDomain.id ? 'active' : '' }">
							${domain.name} </a>
					</c:forEach>
				</div>
			</main>
			<footer class="d-flex align-items-center p-1 border">
				<div class="ui left pointing dropdown icon button">
					<i class="settings icon"></i>
					<div class="menu">
						<button class="item w-100" onclick="showModalAdd()">
							<i class="plus icon"></i> Add
						</button>
						<button
							class="item w-100 ${selectedDomain == null ? 'disabled' : ''}"
							onclick="showModalEdit()">
							<i class="edit icon"></i> Edit
						</button>
						<button
							class="item w-100 ${selectedDomain == null ? 'disabled' : '' }"
							onclick="deleteDomain(${selectedDomain.id},'${selectedDomain.name}', '${selectedProject.id}')">
							<i class="trash icon"></i> Delete
						</button>
					</div>
				</div>
				<div class="ui modal add">
					<i class="close icon"></i>
					<div class="header">Add Domain</div>
					
					<div class="content">
						<form method="post" action="/PM/domain/add" class="ui form"
							name="add">
							<input type="hidden" name="selected-domain-id"
								value="${selectedDomain.id}"> <input type="hidden"
								name="project-id" value="${selectedProject.id}">
							<div class="field">
								<label>Name</label> <input type="text" name="name"
									placeholder="Domain Name">
							</div>
							<button class="ui button" type="submit">Add</button>
						</form>
					</div>
				</div>
				<div class="ui modal edit">
					<div class="header">Edit Domain</div>
					<i class="close icon"></i>
					<div class="content">
						<form method="post" action="/PM/domain/update-info" class="ui form"
							name="edit">
							<input type="hidden" name="domain-id"
								value="${selectedDomain.id}">
							<div class="d-flex mb-4">
								<label>Domain name:</label> <strong class="ms-2">${selectedDomain.name}</strong>
							</div>
							<div class="field">
								<label>New Name</label> <input type="text" name="new-name"
									placeholder="New Name">
							</div>
							<div class="field">
								<label>Overview</label>
								<textarea rows="4" name="overview">${selectedDomain.overview}</textarea>
							</div>
							<div class="field">
								<label>Scope</label>
								<textarea rows="4" name="scope">${selectedDomain.scope}</textarea>
							</div>
							<div class="field">
								<label>Technology</label>
								<textarea rows="4" name="technology">${selectedDomain.technology}</textarea>
							</div>
							<div class="field">
								<label>Note</label>
								<textarea rows="4" name="note">${selectedDomain.note}</textarea>
							</div>
							<button class="ui button" type="submit">Update</button>
						</form>
					</div>
				</div>
			</footer>
		</aside>
		<main class="main-content">
			<div class="content-wrapper flow p-4">
				<c:if test="${selectedDomain != null}">
					<div class="d-flex align-items-center">
						<strong class="ui bg-light text-success p-2 rounded border me-2">${selectedDomain.name}</strong>
						<button class="ui icon button" onclick="showDomainNote()">
							<i class="sticky note outline icon"></i>
						</button>
						<div class="ui modal note">
							<i class="close icon"></i>
							<div class="header">Note</div>
							<div class="content">
								<p>${selectedDomain.note}</p>
							</div>
						</div>
					</div>
					<div class="bg-light p-3">
						<h4 class="ui header">Overview</h4>
						<p>${selectedDomain.overview}</p>
					</div>
					<div class="bg-light p-3">
						<h4 class="ui header">Scope</h4>
						<p>${selectedDomain.scope}</p>
					</div>
					<div class="bg-light p-3">
						<h4 class="ui header">Technology</h4>
						<p>${selectedDomain.technology}</p>
					</div>
					<div class="text-end mt-5">
						<a href="/PM/plan?domain-id=${selectedDomain.id}"
							class="ui button ${selectedDomain == null ? 'disabled' : '' }">
							Design <i class="arrow circle right icon"></i>
						</a>
					</div>
				</c:if>
			</div>
		</main>
	</div>
	<script src="/PM/libs/jquery-3.7.1.min.js"></script>
	<script src="/PM/libs/semantic-ui/semantic.min.js"></script>
	<script><%@include file="./script.js" %></script>
</body>
</html>