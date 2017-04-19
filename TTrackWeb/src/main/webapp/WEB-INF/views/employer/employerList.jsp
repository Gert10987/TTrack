<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions' %>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>User Registration Form</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.6/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.10.1/bootstrap-table.min.css">
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.10.1/bootstrap-table.min.js"></script>
    <script type="text/javascript" src="static/js/showModal.js"></script>
</head>

<body>
<div id="mainWrapper">
    <%@include file="authHeader.jsp" %>
    <div class="generic-container">
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
                            <td>
                                <button type="button" data-id="${employer.ssoId}"
                                        class="btn btn-success custom-width showAll">Tasks
                                </button>
                            </td>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ADMIN')">
                            <td><a href="<c:url value='/delete-employer-${employer.ssoId}' />"
                                   class="btn btn-danger custom-width">delete</a></td>
                        </sec:authorize>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <sec:authorize access="hasRole('ADMIN')">
            <div class="well">
                <a href="<c:url value='/newemployer' />">Add New Employer</a>
            </div>
        </sec:authorize>
    </div>
</div>

<div class="modal fade" id="allTasksModal" tabindex="-1" role="dialog" aria-hidden="true" >
    <div class="modal-dialog modal-lg" style="display:table;">
        <div class="modal-content">
            <div class="modal-body">
                <table id="tableTasks" class="table table-hover">
                    <thead>
                    <tr>
                        <th data-field="id" id="taskID">ID</th>
                        <th data-field="name">Name</th>
                        <th data-field="description">Description</th>
                        <th data-formatter="editButtonFormatter"></th>
                        <th data-formatter="deleteButtonFormatter"></th>
                    </tr>
                    </thead>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
</div>

</body>
</html>