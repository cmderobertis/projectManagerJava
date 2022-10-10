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
  <div class="row justify-content-between mt-3">
    <div class="col">
      <h2 class="text-start">Welcome, <c:out value="${user.firstName}!"/></h2>
    </div>
    <div class="col-auto"><a class="btn btn-danger" href="/logout">Logout</a></div>
  </div>
  <a class="btn btn-info my-3" href="/projects/new">New Project</a>
  <div class="row justify-content-center">
    <div class="col-auto">
      <div class="card">
        <div class="card-body">
          <div class="card-title">
            <h3>Projects Available to Join...</h3>
          </div>
          <table class="table table-hover table-striped">
            <tr>
              <th>Title</th>
              <th>Leader</th>
              <th>Actions</th>
            </tr>
            <c:forEach var="project" items="${unassignedProjects}">
              <tr>
                <td><a href="/projects/${project.id}"><c:out value="${project.title}"/></a></td>
                <td><c:out value="${project.leader.firstName}"/> <c:out value="${project.leader.lastName}"/></td>
                <td>
                  <c:choose>
                    <c:when test="${project.leader == user}">
                      <div class="d-flex">
                        <a class="btn btn-success me-3" href="/projects/${project.id}/edit">Edit</a>
                        <form action="/projects/${project.id}" method="post">
                          <input type="hidden" name="_method" value="delete">
                          <input type="submit" value="Delete" class="btn btn-danger">
                        </form>
                      </div>
                    </c:when>
                    <c:otherwise>
                      <c:if test="${!project.assignees.contains(user)}">
                        <form action="/projects/${project.id}/assign" method="post">
                          <input type="hidden" name="_method" value="put">
                          <input type="submit" value="Join Team" class="btn btn-warning">
                        </form>
                      </c:if>
                    </c:otherwise>
                  </c:choose>
                </td>
              </tr>
            </c:forEach>
          </table>
        </div>
      </div>
      <div class="card mt-3">
        <div class="card-body">
          <div class="card-title">
            <h3>Projects I've Joined...</h3>
          </div>
          <table class="table table-hover table-striped">
            <tr>
              <th>Title</th>
              <th>Leader</th>
              <th>Actions</th>
            </tr>
            <c:forEach var="project" items="${assignedProjects}">
              <tr>
                <td><a href="/projects/${project.id}"><c:out value="${project.title}"/></a></td>
                <td><c:out value="${project.leader.firstName}"/> <c:out value="${project.leader.lastName}"/></td>
                <td>
                  <form action="/projects/${project.id}/unassign" method="post">
                    <input type="hidden" name="_method" value="put">
                    <input type="submit" value="Leave Team" class="btn btn-warning">
                  </form>
                </td>
              </tr>
            </c:forEach>
          </table>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>