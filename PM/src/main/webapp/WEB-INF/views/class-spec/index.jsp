<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Class Specification</title>
  <link rel="stylesheet" href="/PM/libs/semantic-ui/semantic.min.css">
  <link rel="stylesheet" href="/PM/libs/bootstrap/css/bootstrap-utilities.min.css">
  <link rel="stylesheet" href="/PM/libs/css-custom/styles.css">
  <style><%@include file="./styles.css" %></style>
</head>

<body>
	<div id="main-wrapper" class="d-flex">
		<%@ include file="../fragment/left-nav/index.jsp"%>
		<aside class="left-bar border-end">
			<header class="d-flex align-items-center border-bottom px-2">
				<h3 class="ui header">Class Specification</h3>
			</header>
			<main class="py-1 px-4">
				<div class="ui list mt-4">
					<c:forEach var="pkg" items="${packages}">
						<div class="item">
							<i class="folder icon"></i>
							<div class="content">
								<div class="header">
									<a href="/PM/class-spec?package-id=${pkg.id}"
										class="text-break ${selectedPackage.id == pkg.id ? '':'text-secondary'}">
										${pkg.name} </a>
								</div>
								<c:if test="${selectedPackage.id == pkg.id}">
									<div
										class="description d-flex justify-content-end border-bottom pb-1">
										<button class="ui button bg-transparent p-0"
											title="package info" data-info="${pkg.info}"
											onclick="showModalPackageInfo(this)">
											<i class="info circle icon"></i>
										</button>
									</div>
									<div class="list">
										<c:forEach var="cls" items="${classes}">
											<div class="item">
												<i class="file icon"></i>
												<div class="content">
													<div class="header">
														<a href="/PM/class-spec?id=${cls.id}"
															class="${cls.id == selectedClass.id ? '' : 'text-secondary'}">${cls.name}</a>
													</div>
												</div>
											</div>
										</c:forEach>
									</div>
								</c:if>
							</div>
						</div>
					</c:forEach>
				</div>
				<div class="ui modal package-info">
					<i class="close icon"></i>
					<div class="header">Package Info</div>
					<div class="content">
						<p class="info"></p>
					</div>
				</div>
			</main>
			<footer class="d-flex align-items-center px-2">
				<div class="ui left pointing dropdown icon button">
					<i class="settings icon"></i>
					<div class="menu">
						<button
							class="item w-100 ${selectedPackage == null ? 'disabled':''}"
							onclick="showModalAddClass()">
							<i class="plus icon"></i> Add Class
						</button>
						<button
							class="item w-100 ${selectedClass == null ? 'disabled':''}"
							onclick="showModalEditClass()">
							<i class="edit icon"></i> Edit Class
						</button>
						<button
							class="item w-100 ${selectedClass == null ? 'disabled':''}"
							onclick="deleteClass(${selectedClass.id},'${selectedClass.name}', ${selectedPackage.id})">
							<i class="trash icon"></i> Delete Class
						</button>
						<div class="ui divider"></div>
						<button class="item w-100" onclick="showModalAddPackage()">
							<i class="plus icon"></i> Add Package
						</button>
						<button
							class="item w-100 ${selectedPackage == null ? 'disabled':''}"
							onclick="showModalEditPackage()">
							<i class="edit icon"></i> Edit Package
						</button>
						<button
							class="item w-100 ${selectedPackage == null ? 'disabled':''}"
							onclick="deletePackage(${selectedPackage.id},'${selectedPackage.name}', ${selectedDomain.id})">
							<i class="trash icon"></i> Delete Package
						</button>
					</div>
				</div>
				<div class="ui mini modal add-class">
					<div class="header">Add Class</div>
					<div class="content">
						<%@ include file="./form-add-class/index.jsp"%>
					</div>
				</div>
				<div class="ui modal edit-class">
					<div class="header">Edit Class</div>
					<div class="content">
						<%@ include file="./form-edit-class/index.jsp"%>
					</div>
				</div>
				<div class="ui modal add-package">
					<div class="header">Add package</div>
					<div class="content">
						<%@ include file="./form-add-package/index.jsp"%>
					</div>
				</div>
				<div class="ui modal edit-package">
					<div class="header">Edit Package</div>
					<div class="content">
						<%@ include file="./form-edit-package/index.jsp"%>
					</div>
				</div>
			</footer>
		</aside>
		<main class="main-content">
			<header class="d-flex align-items-center">
				<%@include file="./breadcrumb/index.jsp"%>
			</header>
			<main class="p-4">
				<c:if test="${selectedClass != null}">
					<div class="ui top attached tabular menu">
						<a class="item ${(tabView == 'class' || tabView == null)?'active':''}" 
						   data-tab="first">Class Spec</a> 
						<a class="item ${(tabView == 'test')?'active':''}" data-tab="second">Unit Test</a>
					</div>
					<div
						class="ui bottom attached tab segment ${(tabView == 'class' || tabView == null)?'active':''}"
						data-tab="first">
						<div class="tab-content-wrapper">
							<section>
								<h4 class="ui header fs-3">${selectedClass.name}</h4>
								<%@ include file="./form-update-class-spec/index.jsp"%>
							</section>
						</div>
					</div>
					<div
						class="ui bottom attached tab segment ${(tabView == 'test')?'active':''}"
						data-tab="second">
						<div class="tab-content-wrapper d-flex">
							<div class="test-list pe-2 border-end d-flex flex-column">
								<header class="ui header">
									<h4>Test List</h4>
								</header>
								<main class="flex-grow-1">
									<div class="ui vertical menu w-100">
										<c:forEach var="tf" items="${testingFunctions}">
											<a href="/PM/class-spec?test-id=${tf.id}&tab-view=test"
												class="item ${tf.id == selectedTestingFunction.id ? 'active' : ''}">
												${tf.name} </a>
										</c:forEach>
									</div>
								</main>
								<footer>
									<div class="ui left pointing dropdown icon button">
										<i class="settings icon"></i>
										<div class="menu">
											<button class="item w-100"
												onclick="showModalAddTestingFunction()">
												<i
													class="plus icon ${selectedClass == null ? 'disabled' : ''}"></i>
												Add
											</button>
											<button
												class="item w-100 ${selectedTestingFunction == null ? 'disabled' : ''}"
												onclick="showModalEditTestingFunction()">
												<i class="edit icon"></i> Edit
											</button>
											<button
												class="item w-100 ${selectedTestingFunction == null ? 'disabled' : ''}"
												onclick="deleteTestingFunction(${selectedTestingFunction.id},'${selectedTestingFunction.name}', ${selectedClass.id})">
												<i class="trash icon"></i> Delete
											</button>
											<div class="ui modal add-testing-function">
												<div class="header">Add Testing Function</div>
												<div class="content">
													<%@ include file="./form-add-test/index.jsp"%>
												</div>
											</div>
											<div class="ui modal edit-testing-function">
												<div class="header">Edit Testing Function</div>
												<div class="content">
													<%@ include file="./form-edit-test-info/index.jsp"%>
												</div>
											</div>
										</div>
									</div>
								</footer>
							</div>
							<div class="info-table px-4">
								<h4 class="ui header">Function Info</h4>
								<%@ include file="./table-function-info/index.jsp"%>
							</div>
							<div class="testcase ms-2 flex-grow-1">
								<div>
									<%@ include file="./form-test-plan-input/index.jsp"%>
								</div>
								<div class="mt-4">
									<%@ include file="./form-test-plan-result/index.jsp"%>
								</div>
							</div>
						</div>
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