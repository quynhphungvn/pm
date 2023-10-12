<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Class Diagram</title>
  <link rel="stylesheet" href="/PM/libs/semantic-ui/semantic.min.css">
  <link rel="stylesheet" href="/PM/libs/bootstrap/css/bootstrap-utilities.min.css">
  <link rel="stylesheet" href="/PM/libs/css-custom/styles.css">
  <style><%@include file="./styles.css" %></style>
</head>

<body>
	<div id="main-wrapper" class="d-flex">
		<%@ include file="../fragment/left-nav/index.jsp"%>
		<aside class="left-bar border-end">
			<header class="d-flex align-items-center">
				<strong class="ui header">Usecase</strong>
			</header>
			<main>
				<div class="ui vertical menu w-100">
					<c:forEach var="usc" items="${usecases}">
						<a href="/PM/usecase?id=${usc.id}"
							class="item ${usc.id == selectedUsecase.id ? 'active':''}">
							${usc.name} </a>
					</c:forEach>
				</div>
			</main>
			<footer class="d-flex align-items-center">
				<div class="ui left pointing dropdown icon button">
					<i class="settings icon"></i>
					<div class="menu">
						<button class="item w-100" onclick="showModalAdd()">
							<i class="plus icon"></i> Add
						</button>
						<button
							class="item w-100 ${selectedUsecase == null ? 'disabled':''}"
							onclick="showModalEdit()">
							<i class="edit icon"></i> Edit
						</button>
						<button
							class="item w-100 ${selectedUsecase == null ? 'disabled':''}"
							onclick="deleteUsecase(${selectedUsecase.id},'${selectedUsecase.name }', ${selectedDomain.id})">
							<i class="trash icon"></i> Delete
						</button>
					</div>
				</div>
				<div class="ui mini modal add">
					<div class="header">Add Usecase</div>
					<div class="content">
						<form method="post" action="/PM/usecase/add" class="ui form"
							name="add">
							<input type="hidden" name="domain-id"
								value="${selectedDomain.id}"> <input type="hidden"
								name="selected-usecase-id" value="${selectedUsecase.id}">
							<div class="field">
								<label>Name</label> <input type="text" name="name"
									placeholder="Usecase Name">
							</div>
							<button class="ui button" type="submit">Add</button>
						</form>
					</div>
				</div>
				<div class="ui modal edit">
					<div class="header">Edit Usecase</div>
					<div class="content">
						<form method="post" action="/PM/usecase/update-name"
							class="ui form" name="edit">
							<input type="hidden" name="usecase-id"
								value="${selectedUsecase.id}">
							<div class="d-flex">
								<label>Name:</label> <strong class="ms-2">${selectedUsecase.name}</strong>
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
			<header class="d-flex align-items-center">
				<div class="ui breadcrumb ms-3">
					<a href="/PM/project?id=${selectedProject.id}" class="section">${selectedProject.name}</a>
					<i class="right angle icon divider"></i> <a
						href="/PM/domain/info?id=${selectedDomain.id}" class="section">${selectedDomain.name }</a>
					<i class="right angle icon divider"></i>
					<div class="active section">${selectedUsecase.name}</div>
				</div>
			</header>
			<main>
				<div class="ui top attached tabular menu">
					<a class="item active" data-tab="first">Specification</a> 
					<a class="item" data-tab="second">Activity</a> 
					<a class="item" data-tab="third">Sequence</a>
				</div>
				<div
					class="ui bottom attached tab segment active overflow-auto"
					data-tab="first">
					<button class="circular ui icon button position-absolute"
						style="top: .5rem; left: .5rem;"
						onclick="showOffCanvas('#canvas-spec')">
						<i class="pencil alternate icon"></i>
					</button>
					<div id="canvas-spec" class="offcanvas" data-location="left"
						data-width="440px">
						<div class="offcanvas__content">
							<header>
								<h3 class="offcanvas__title">Specification</h3>
								<button class="ui button icon" type="button"
									onclick="hideOffCanvas('#canvas-spec')">
									<i class="close icon"></i>
								</button>
							</header>
							<main>
								<form method="post" action="/PM/usecase/update-spec"
									class="ui form p-4">
									<input type="hidden" name="usecase-id"
										value="${selectedUsecase.id}">
									<div class="field">
										<label>Created by</label> <input type="text" name="created-by"
											value="${selectedUsecase.createdBy }">
									</div>
									<div class="field">
										<label>Actors</label> <input type="text" name="actors"
											value="${selectedUsecase.actors }">
									</div>
									<div class="field">
										<label>Trigger</label> <input type="text"
											name="trigger-context"
											value="${selectedUsecase.triggerContext }">
									</div>
									<div class="field">
										<label>Description</label> <input type="text"
											name="description" value="${selectedUsecase.description }">
									</div>
									<div class="field">
										<label>Precondition</label> <input type="text"
											name="preconditions"
											value="${selectedUsecase.preconditions }">
									</div>
									<div class="field">
										<label>Postcondition</label> <input type="text"
											name="postconditions"
											value="${selectedUsecase.postconditions }">
									</div>
									<div class="field">
										<label>Normal flow</label>
										<textarea rows="4" name="normal-flow">${selectedUsecase.normalFlow }</textarea>
									</div>
									<div class="field">
										<label>Alternative flow</label>
										<textarea rows="4" name="alternative-flow">${selectedUsecase.alternativeFlow}</textarea>
									</div>
									<div class="field">
										<label>Exceptions</label>
										<textarea rows="4" name="exceptions">${selectedUsecase.exceptions}</textarea>
									</div>
									<div class="field">
										<label>Priority</label> <input type="text" name="priority"
											value="${selectedUsecase.priority }">
									</div>
									<div class="field">
										<label>Frequency of Use</label> <input type="text"
											name="frequency-of-use"
											value="${selectedUsecase.frequencyOfUse }">
									</div>
									<div class="field">
										<label>Bussiness Rule</label> <input type="text"
											name="bussiness-rule"
											value="${selectedUsecase.bussinessRules }">
									</div>
									<div class="field">
										<label>Other Information</label> <input type="text"
											name="other-information"
											value="${selectedUsecase.otherInformations}">
									</div>
									<div class="field">
										<label>Assumptions</label>
										<textarea rows="4" name="assumptions">${selectedUsecase.assumptions}</textarea>
									</div>
									<div class="d-flex justify-content-between">
										<button class="ui button">Cancel</button>
										<button class="ui button" type="submit">Update</button>
									</div>
								</form>
							</main>
							<footer> </footer>
						</div>
					</div>

					<div class="spec-wrapper">
						<div class="mt-5">
							<table class="ui celled table">
								<thead>
									<tr>
										<th>Id and Name</th>
										<th>${selectedUsecase.name}</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>Created by</td>
										<td>${selectedUsecase.createdBy}</td>
									</tr>
									<tr>
										<td>Date Created</td>
										<td>${selectedUsecase.createdDate}</td>
									</tr>
									<tr>
										<td>Actors</td>
										<td>${selectedUsecase.actors}</td>
									</tr>
									<tr>
										<td>Trigger</td>
										<td>${selectedUsecase.triggerContext}</td>
									</tr>
									<tr>
										<td>Description</td>
										<td>${selectedUsecase.description}</td>
									</tr>
									<tr>
										<td>Preconditions</td>
										<td>${selectedUsecase.preconditions}</td>
									</tr>
									<tr>
										<td>Postconditions</td>
										<td>${selectedUsecase.postconditions}</td>
									</tr>
									<tr>
										<td>Normal Flow</td>
										<td>
											<div class="ui form">
												<div class="ui field">
													<textarea rows="5" readonly>${selectedUsecase.normalFlow}</textarea>
												</div>
											</div>
										</td>
									</tr>
									<tr>
										<td>Alternative flow</td>
										<td>
											<div class="ui form">
												<div class="ui field">
													<textarea rows="5" readonly>${selectedUsecase.alternativeFlow}</textarea>
												</div>
											</div>
										</td>
									</tr>
									<tr>
										<td>Exception</td>
										<td>
											<div class="ui form">
												<div class="ui field">
													<textarea rows="2" readonly>${selectedUsecase.exceptions}</textarea>
												</div>
											</div>
										</td>
									</tr>
									<tr>
										<td>Priority</td>
										<td>${selectedUsecase.priority}</td>
									</tr>
									<tr>
										<td>Frequency of Use</td>
										<td>${selectedUsecase.frequencyOfUse}</td>
									</tr>
									<tr>
										<td>Bussiness Rule</td>
										<td>${selectedUsecase.bussinessRules}</td>
									</tr>
									<tr>
										<td>Other Information</td>
										<td>${selectedUsecase.otherInformations}</td>
									</tr>
									<tr>
										<td>Assumptions</td>
										<td>
											<div class="ui form">
												<div class="ui field">
													<textarea rows="2" readonly>${selectedUsecase.assumptions}</textarea>
												</div>
											</div>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<!-- activity -->
				<div class="ui bottom attached tab segment overflow-auto"
					data-tab="second">
					<button
						class="circular ui icon button position-absolute bg-transparent border"
						style="top: .5rem; left: .5rem;"
						onclick="showOffCanvas('#canvas-activity')">
						<i class="pencil alternate icon"></i>
					</button>
					<div id="canvas-activity" class="offcanvas" data-location="left"
						data-width="440px">
						<div class="offcanvas__content">
							<header>
								<h3 class="offcanvas__title">Activity</h3>
								<button class="ui button icon" type="button"
									onclick="hideOffCanvas('#canvas-activity')">
									<i class="close icon"></i>
								</button>
							</header>
							<main>
								<form method="post" action="/PM/usecase/update-activity"
									class="ui form p-4">
									<input type="hidden" name="usecase-id"
										value="${selectedUsecase.id}">
									<div class="field">
										<label>Diagram</label>
										<textarea rows="35" name="activity-diagram">${selectedUsecase.activityDiagram}</textarea>
									</div>
									<div class="actions">
										<div class="d-flex justify-content-end">
											<button type="button" class="ui icon button"
												onclick="testActivityDiagram(${selectedUsecase.id})">
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
							<footer> </footer>
						</div>
					</div>
					<div class="diagram-wrapper">
						<img class="activity-image" src="${publicActivityDiagramURI}">
					</div>
				</div>
				<!-- sequence  -->
				<div class="ui bottom attached tab segment overflow-auto"
					data-tab="third">
					<button
						class="circular ui icon button position-absolute bg-transparent border"
						style="top: .5rem; left: .5rem;"
						onclick="showOffCanvas('#canvas-sequence')">
						<i class="pencil alternate icon"></i>
					</button>
					<div id="canvas-sequence" class="offcanvas" data-location="left"
						data-width="440px">
						<div class="offcanvas__content">
							<header>
								<h3 class="offcanvas__title">Sequence</h3>
								<button class="ui button icon" type="button"
									onclick="hideOffCanvas('#canvas-sequence')">
									<i class="close icon"></i>
								</button>
							</header>
							<main>
								<form method="post" action="/PM/usecase/update-sequence"
									class="ui form p-4">
									<input type="hidden" name="usecase-id"
										value="${selectedUsecase.id}">
									<div class="field">
										<label>Diagram</label>
										<textarea rows="35" name="sequence-diagram">${selectedUsecase.sequenceDiagram}</textarea>
									</div>
									<div class="actions">
										<div class="d-flex justify-content-end">
											<button type="button" class="ui icon button"
												onclick="testSequenceDiagram(${selectedUsecase.id})">
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
							<footer> </footer>
						</div>
					</div>
					<div class="diagram-wrapper">
						<img class="sequence-image" src="${publicSequenceDiagramURI}">
					</div>
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