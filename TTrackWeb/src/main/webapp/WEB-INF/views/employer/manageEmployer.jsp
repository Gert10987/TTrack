<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html xmlns:form="http://www.w3.org/1999/xhtml" xmlns:c="http://www.w3.org/1999/XSL/Transform"
      xmlns:spring="http://jboss.org/xml/ns/javax/validation/mapping"
      xmlns:jsp="http://www.springframework.org/schema/aop">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>User Registration Form</title>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.10.1/bootstrap-table.min.css">
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.10.1/bootstrap-table.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="static/js/showModal.js"></script>
    <script type="text/javascript" src="static/js/myMap.js"></script>
</head>

<body>
<div id="mainWrapper">

    <%@include file="authHeader.jsp" %>
    <%@include file="taskPopup.jsp" %>

    <form:form method="POST" modelAttribute="currentTask" class="form-horizontal">
        <input type="hidden" id="currentTask" value="${currentTask}">
        <input type="hidden" id="currentTaskJson" value='${currentTaskJson}'/>
        <input type="hidden" id="employerCurrentPosition" value='${employer.currentPosition}'/>

        <form:input type="hidden" path="id" id="task_id"/>
        <form:input type="hidden" path="employer.id" id="employer"/>
        <form:input type="hidden" path="endOfRoutLocation" id="endOfRoutLocation"/>
        <form:input type="hidden" path="startOfRoutLocation" id="startOfRoutLocation"/>

        <div class="row">
            <div class="col-sm-6 col-md-6 col-lg-6">
                <div class="embed-responsive embed-responsive-16by9">
                    <div id="map" class="embed-responsive-item" style="border: 1px solid black"></div>
                    <script src="https://maps.googleapis.com/maps/api/js?key=KEY&callback=myMap"></script>
                </div>
            </div>
            <div class="col-sm-6 col-md-6 col-lg-6">
                <div class="row">
                    <div class="col-xs-9 col-sm-9 col-md-9 col-lg-9">
                        <h1 class="well">${employer.firstName} ${employer.lastName}</h1>
                        <div class="form-actions">
                            <input type="submit" value="Update" class="btn btn-primary"/>
                            <button type="button" data-id="${employer.ssoId}"
                                    class="btn btn-warning ShowAll">ShowAll
                            </button>
                        </div>
                        <div class="row marginTop">
                            <form class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label" for="startOfTheRout">From:</label>
                                    <div class="col-sm-10">
                                        <form:input type="text" path="startOfTheRout" id="startOfTheRout"
                                                    class="form-control input-sm"/>
                                        <div class="has-error">
                                            <form:errors path="startOfTheRout" class="help-inline"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label" for="endOfTheRout">To:</label>
                                    <div class="col-sm-10">
                                        <form:input type="text" path="endOfTheRout" id="endOfTheRout"
                                                    class="form-control input-sm"/>
                                        <div class="has-error">
                                            <form:errors path="endOfTheRout" class="help-inline"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label" for="emailOfClient">Email:</label>
                                    <div class="col-sm-10">
                                        <form:input type="text" path="emailOfClient" id="emailOfClient"
                                                    class="form-control input-sm"/>
                                        <div class="has-error">
                                            <form:errors path="emailOfClient" class="help-inline"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label" for="phoneOfClient">Phone:</label>
                                    <div class="col-sm-10">
                                        <form:input type="text" path="phoneOfClient" id="phoneOfClient"
                                                    class="form-control input-sm"/>
                                        <div class="has-error">
                                            <form:errors path="phoneOfClient" class="help-inline"/>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">.
                        <div class="img-responsive"><img src="/static/image/test"/></div>
                    </div>
                </div>
            </div>
        </div>

        <div class="row marginTop">
            <div class="col-sm-0 col-md-0 col-lg-0">
                <a href="<c:url value='/manage-employer-${employer.ssoId}-task-${previouslyTaskId}'/>"
                   class="btn btn-primary btn-md btnMargin">Previously</a>
                <a href="<c:url value='/manage-employer-${employer.ssoId}-task-${nextTaskId}'/>"
                   class="btn btn-primary btn-md floatRight">Next</a>
            </div>
        </div>

        <div class="row marginTop">
            <div class="col-sm-7 col-md-7 col-lg-7">
                <div class="form-group marginLeft marginRight">
                    <label class="col-lg-2 control-label">Description:</label>
                    <form:textarea path="description" id="description" class="form-control" rows="6"></form:textarea>
                </div>
            </div>
            <div class="col-sm-5 col-md-5 col-lg-5">
                <form class="form-horizontal marginRight">
                    <div class="form-group">
                        <div class="form-group">
                            <label class="col-lg-2 control-label" for="name">Task:</label>
                            <div class="col-sm-10">
                                <form:input type="text" path="name" id="name"
                                            class="form-control input-sm"/>
                                <div class="has-error">
                                    <form:errors path="name" class="help-inline"/>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="startDate">Start Date:</label>
                            <div class="col-sm-10">
                                <form:input type="text" path="startDate" id="startDate"
                                            class="form-control input-sm"/>
                                <div class="has-error">
                                    <form:errors path="startDate" class="help-inline"/>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="endDate">End Date:</label>
                            <div class="col-sm-10">
                                <form:input type="text" path="endDate" id="endDate"
                                            class="form-control input-sm"/>
                                <div class="has-error">
                                    <form:errors path="endDate" class="help-inline"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </form:form>
</div>
</body>
</html>
