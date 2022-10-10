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
<h1 class="text-center text-primary">Project Manager</h1>
<div class="row justify-content-center">
  <div class="col-auto">
    <div class="card">
      <div class="card-body">
        <div class="card-title">
          <h2>Edit Project</h2>
          <p>Title -> ${project.title}</p>
          <p>Leader -> ${project.leader}</p>
          <p>Assignees -> ${project.assignees}</p>

        </div>
        <%--@elvariable id="project" type=""--%>
        <form:form action="/projects/${project.id}" method="post" modelAttribute="project">
          <input type="hidden" name="_method" value="put">
          <form:hidden path="leader" value="${project.leader.id}"/>
<%--          <form:errors path="assignees" cssClass="text-danger"/>--%>
<%--          <form:hidden path="assignees" value="${}"/>--%>
          <p class="form-control d-flex flex-column">
            <form:label path="title">Title</form:label>
            <form:errors path="title" cssClass="text-danger"/>
            <form:input path="title"/>
          </p>
          <p class="form-control d-flex flex-column">
            <form:label path="description">Description</form:label>
            <form:errors path="description" cssClass="text-danger"/>
            <form:input path="description"/>
          </p>
          <input class="mt-3 btn btn-primary" type="submit" value="Submit">
        </form:form>
      </div>
    </div>
  </div>
  <a class="btn btn-secondary" href="/projects">Cancel</a>
</div>
</body>
</html>