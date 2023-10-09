<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>ERD</title>
  <link rel="stylesheet" href="/PM/libs/semantic-ui/semantic.min.css">
  <link rel="stylesheet" href="/PM/libs/bootstrap/css/bootstrap-utilities.min.css">
  <link rel="stylesheet" href="/PM/libs/css-custom/styles.css">
  <style><%@include file="./styles.css" %></style>
</head>

<body>
	<div id="main-wrapper" class="d-flex">
		<%@ include file="../fragment/left-nav/index.jsp"%>
		<main class="main-content">
      <button class="circular ui icon button position-absolute bg-transparent" style="top: 5rem; left: 2rem;" onclick="showOffCanvas('#canvas-diagram')">
          <i class="pencil alternate icon"></i>
      </button>
      <div id="canvas-diagram" class="offcanvas" data-location="right" data-width="400px">
        <div class="offcanvas__content">
            <header>
                <h3 class="offcanvas__title">ERD Diagram</h3>
                <button class="ui button p-2" type="button" onclick="hideOffCanvas('#canvas-diagram')">
                  <i class="close icon"></i>
                </button>
            </header>
            <main>
              <form method="post" action="/PM/domain/update-erd"
              		class="ui form p-4" >   
              	<input type="hidden" name="domain-id" value="${selectedDomain.id}" >           
                <div class="field">
                  <label>Diagram</label>
                  <textarea rows="35" name="erd">${selectedDomain.erdDiagram}</textarea>
                </div>
                <div class="actions">
                  <div class="d-flex justify-content-start">
                    <button type="button" class="ui icon button" onclick="testErd(${selectedDomain.id})">
                    	<i class="sync icon"></i>
                    </button>
                  </div>
                  <div class="d-flex justify-content-between mt-3">
                    <button class="ui button" type="submit">Update</button>
                    <button type="reset" class="ui button">Reset</button>
                  </div>
                </div>
              </form>
            </main>
            <footer>
    
            </footer>
        </div>
      </div>
      <header class="d-flex align-items-center">
        <h3 class="ms-5 text-primary">ERD</h3>
      </header>
      <main class="d-flex perfect-center">
        <div class="diagram-wrapper"> 
          <img class="diagram-image" src="/PM${publicDiagramURI }">
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