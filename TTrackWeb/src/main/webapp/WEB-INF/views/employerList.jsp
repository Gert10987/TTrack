<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Users List</title>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>

<body>
 <div id="mainWrapper">
    <div class="generic-container">
        <%@include file="authHeader.jsp" %>
        <div class="panel panel-default">
              <!-- Default panel contents -->
            <div class="panel-heading"><span class="lead">List of Employers </span></div>
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Email</th>
                        <th>SSO ID</th>
                        <th>Phone</th>
                        <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
                            <th width="100"></th>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ADMIN')">
                            <th width="100"></th>
                        </sec:authorize>

                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${employers}" var="employer">
                    <tr>
                        <td>${employer.firstName}</td>
                        <td>${employer.lastName}</td>
                        <td>${employer.email}</td>
                        <td>${employer.ssoId}</td>
                        <td>${employer.phone}</td>
                        <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
                            <td><a href="<c:url value='/edit-user-${employer.ssoId}' />" class="btn btn-success custom-width">edit</a></td>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ADMIN')">
                            <td><a href="<c:url value='/delete-user-${employer.ssoId}' />" class="btn btn-danger custom-width">delete</a></td>
                        </sec:authorize>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <sec:authorize access="hasRole('ADMIN')">
            <div class="well">
                <a href="<c:url value='/newuser' />">Add New User</a>
            </div>
        </sec:authorize>
    </div>
   </div>
</body>
</html>