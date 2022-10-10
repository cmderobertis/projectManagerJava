<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) -->
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Tacos</title>
  <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
  <script src="/webjars/jquery/jquery.min.js"></script>
  <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container-xl">
  <h1  class="text-center text-primary">Project Manager</h1>
  <div class="row justify-content-center my-3">
    <div class="col-8">
      <div class="card">
        <div class="card-body">
          <div class="card-title">
            <h3><c:out value="${project.title}"/></h3>
          </div>
          <p><strong>Description:</strong><br><c:out value="${project.description}"/></p>
          <p><strong>Project Participants:</strong></p>
          <ul>
            <li style="color: #5e58ff">${project.leader.firstName} ${project.leader.lastName} (Leader)</li>
            <c:forEach items="${project.assignees}" var="assignee">
              <li>${assignee.firstName} ${assignee.lastName}</li>
            </c:forEach>
          </ul>
          <c:if test="${project.leader == user}">
            <div class="d-flex">
              <a class="btn btn-success me-3" href="/projects/${project.id}/edit">Edit</a>
              <form action="/projects/${project.id}" method="post">
                <input type="hidden" name="_method" value="delete">
                <input type="submit" value="Delete" class="btn btn-danger">
              </form>
            </div>
          </c:if>
        </div>
      </div>
    </div>
  </div>
  <a href="/projects">All Projects</a></div>
</body>
</html>