<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions' %>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>User Registration Form</title>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet">
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>
<div id="mainWrapper">
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
                                        class="btn btn-success custom-width manageEmployer">Edit
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

<form id="userForm" method="post" class="form-horizontal" style="display: none;">
    <div class="form-group">
        <label class="col-xs-3 control-label">ID</label>
        <div class="col-xs-3">
            <input type="text" class="form-control" name="id" disabled="disabled"/>
        </div>
    </div>

    <div class="form-group">
        <label class="col-xs-3 control-label">Full name</label>
        <div class="col-xs-5">
            <input type="text" class="form-control" name="name"/>
        </div>
    </div>

    <div class="form-group">
        <label class="col-xs-3 control-label">Email</label>
        <div class="col-xs-5">
            <input type="text" class="form-control" name="email"/>
        </div>
    </div>

    <div class="form-group">
        <label class="col-xs-3 control-label">Website</label>
        <div class="col-xs-5">
            <input type="text" class="form-control" name="website"/>
        </div>
    </div>

    <div class="form-group">
        <div class="col-xs-5 col-xs-offset-3">
            <button type="submit" class="btn btn-default">Save</button>
        </div>
    </div>
</form>

<div class="modal fade" id="allTasksModal" role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Description</th>
                    <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
                        <th width="100"></th>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ADMIN')">
                        <th width="100"></th>
                    </sec:authorize>
                </tr>
                </thead>
                <tbody id="items">
                <tr id="tr0">
                    <td>
                        <div class="id">Id</div>
                    </td>
                    <td>
                        <div class="name">Name</div>
                    </td>
                    <td>
                        <div class="desc">Description</div>
                    </td>
                    <td>
                        <button type="button" data-id="${employer.ssoId}"
                                class="btn btn-success custom-width editTask">Edit
                        </button>
                    </td>
                    <td><a href="<c:url value='/delete-employer-${employer.ssoId}' />"
                           class="btn btn-danger custom-width">delete</a></td>
                </tr>
                </tbody>
            </table>
            <div class="modal">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

</body>

<script>
$(document).ready(function() {
    $('#userForm')
    $('.manageEmployer').on('click', function() {
        // Get the record's ID via attribute
        var id = $(this).attr('data-id');
        var list = null;

        $.ajax({
            type: "GET",
            url: 'http://localhost:8080/tasks-' + id,
            dataType : 'json',
            contentType : 'application/json',
            success: function (tasks) {

            $.each(tasks, function(i, val) {
            var currRow = $("#tr0").clone().appendTo($('#items'));
            currRow.find('.id').html(val.id);
            currRow.find('.name').html(val.name);
            currRow.find('.desc').html(val.description);

            });

            $("#tr0").remove();
                  $("#allTasksModal").append(tasks);
                  $("#allTasksModal").modal('show');
            }
        });
    });
});

function showUsers(data) {
    // and here you show users on page
    //following code just example

    $('#allUsers').append("<option value='-1'>Select User</option>");
        for ( var i = 0, len = data.length; i < len; ++i) {
            var user = data[i];
            $('#allUsers').append("<option value=\"" + user.userId + "\">" + user.userName+ "</option>");
    }
}



</script>
</html>