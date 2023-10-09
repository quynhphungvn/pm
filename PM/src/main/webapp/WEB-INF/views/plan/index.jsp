<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Plan</title>
  <link rel="stylesheet" href="/PM/libs/semantic-ui/semantic.min.css">
  <link rel="stylesheet" href="/PM/libs/bootstrap/css/bootstrap-utilities.min.css">
  <link rel="stylesheet" href="/PM/libs/css-custom/styles.css">
  <style><%@include file="./styles.css" %></style>
</head>

<body>
	<div id="main-wrapper" class="d-flex">
		<%@ include file="../fragment/left-nav/index.jsp"%>
		<aside class="left-bar border-end">
			<header
				class="d-flex align-items-center border-bottom justify-content-between px-2">
				<strong class="text-primary fs-4">Plan Diagram</strong>
				<div id="canvas-requirement" class="offcanvas" data-location="right"
					data-width="500px" style="z-index: 10; right: -550px;">
					<div class="offcanvas__content">
						<header>
							<h3 class="offcanvas__title">Requirement</h3>
							<button class="ui button icon" type="button"
								onclick="hideOffCanvas('#canvas-requirement')">
								<i class="close icon"></i>
							</button>
						</header>
						<main>
							<form method="post" action="/PM/domain/update-requirement"
									class="ui form p-2">
								<input type="hidden" name="diagram-id" value="${selectedDiagram.id}" >
								<div class="field">
									<div class="mb-2">
										<label>Content</label>
										<button class="ui button circular icon ms-2 p-2"
											type="button"
											onclick="enableEditRequirement()">
											<i class="pencil alternate icon"></i>
										</button>
									</div>
									<textarea rows="40" name="requirement" readonly>${selectedDomain.requirement}</textarea>
								</div>
								<div class="actions d-none">
									<div class="d-flex justify-content-between mt-3">
										<button class="ui button" type="submit">Update</button>
										<button class="ui button">Reset</button>
									</div>
								</div>
							</form>
						</main>
						<footer> </footer>
					</div>
				</div>
			</header>
			<main class="p-2">
				<div class="ui vertical menu w-100">
					<c:forEach var="dia" items="${planDiagrams}">
						<a href="/PM/plan?id=${dia.id}"
							class="item ${selectedDiagram.id == dia.id ? 'active' : '' }">
							${dia.name} </a>
					</c:forEach>
				</div>
			</main>
			<footer class="d-flex align-items-center px-2">
				<div class="ui left pointing dropdown icon button">
					<i class="settings icon"></i>
					<div class="menu">
						<button class="item w-100" onclick="showModalAdd()">
							<i class="plus icon"></i> Add
						</button>
						<button
							class="item w-100 ${selectedDiagram == null ? 'disabled' : ''}"
							onclick="showModalEdit()">
							<i class="edit icon"></i> Edit
						</button>
						<button
							class="item w-100 ${selectedDiagram == null ? 'disabled' : ''}"
							onclick="deleteDiagram(${selectedDiagram.id},'${selectedDiagram.name}', '${selectedDomain.id}')">
							<i class="trash icon"></i> Delete
						</button>
					</div>
				</div>
				<div class="ui modal add">
					<div class="header">Add Diagram</div>
					<div class="content">
						<form method="POST" action="/PM/plan/add" class="ui form"
							name="add">
							<input type="hidden" name="selected-diagram-id"
								value="${selectedDiagram.id}"> <input type="hidden"
								name="domain-id" value="${selectedDomain.id}">
							<div class="field">
								<label>Name</label> <input type="text" name="name"
									placeholder="Diagram Name">
							</div>
							<div class="field">
								<select name="type">
									<option value="">Type</option>
									<c:forEach var="type" items="${diagramTypes}">
										<option value="${type}">${type}</option>
									</c:forEach>
								</select>
							</div>
							<button class="ui button" type="submit">Add</button>
						</form>
					</div>
				</div>
				<div class="ui modal edit">
					<div class="header">Edit Diagram</div>
					<div class="content">
						<form method="post" action="/PM/plan/update-name" class="ui form"
							name="edit">
							<input type="hidden" name="diagram-id"
								value="${selectedDiagram.id}">
							<div class="d-flex align-items-center mb-4">
								<label>Name:</label> <strong class="ms-2">${selectedDiagram.name}</strong>
							</div>
							<div class="field mb-5">
								<label>New Name</label> <input type="text" name="new-name"
									placeholder="New Name">
							</div>
							<button class="ui button" type="submit">Update</button>
						</form>
					</div>
				</div>

			</footer>
		</aside>
		<main class="main-content">
			<button
				class="circular ui icon button position-absolute bg-transparent border"
				style="top: 5rem; left: 2rem;"
				onclick="showOffCanvas('#canvas-diagram')">
				<i class="pencil alternate icon"></i>
			</button>
			<div id="canvas-diagram" class="offcanvas" data-location="left"
				data-width="440px">
				<div class="offcanvas__content">
					<header>
						<h3 class="offcanvas__title">${selectedDiagram.name}</h3>
						<button class="ui button icon" type="button"
							onclick="hideOffCanvas('#canvas-diagram')">
							<i class="close icon"></i>
						</button>
					</header>
					<main>
						<form method="post" action="/PM/plan/update-diagram"
							class="ui form p-4">
							<input type="hidden" name="diagram-id"
								value="${selectedDiagram.id}">
							<div class="field">
								<label>Diagram</label>
								<textarea rows="38" name="diagram">${selectedDiagram.diagram}</textarea>
							</div>
							<div class="actions">
								<div class="d-flex justify-content-end">
									<button type="button" class="ui icon button"
										onclick="testDiagram(${selectedDiagram.id})">
										<i class="sync icon"></i>
									</button>
								</div>
								<div class="d-flex justify-content-between mt-3">
									<button type="button" class="ui button"
										onclick="reloadDiagram(${selectedDiagram.id})">Reset</button>
									<button type="submit" class="ui button">Update</button>
								</div>
							</div>
						</form>
					</main>
					<footer> </footer>
				</div>
			</div>
			<header class="d-flex align-items-center justify-content-between">
				<div class="ui breadcrumb ms-3">
					<a href="/PM/project?id=${selectedProject.id}" class="section">${selectedProject.name}</a>
					<i class="right angle icon divider"></i> <a
						href="/PM/domain/info?id=${selectedDomain.id}" class="section">${selectedDomain.name}</a>
					<i class="right angle icon divider"></i>
					<div class="active section">Diagram: ${selectedDiagram.name}</div>
				</div>
				<button class="ui icon button me-2"
					onclick="showOffCanvas('#canvas-requirement')">
					<i class="file alternate outline icon fs-5"></i>
				</button>
			</header>
			<main class="d-flex perfect-center">
				<c:if test="${selectedDiagram != null}">
					<div class="diagram-wrapper">
						<img class="diagram-image" src="/PM/${publicDiagramURI}"
							alt="diagram">
					</div>
				</c:if>
			</main>

		</main>
	</div>
	<script src="/PM/libs/jquery-3.7.1.min.js"></script>
	<script src="/PM/libs/semantic-ui/semantic.min.js"></script>
	<script src="/PM/libs/css-custom/scripts.js"></script>
	<script><%@include file="./script.js" %></script>
</body>
</html>