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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
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
        <div class="photo-generic-container">
            <div class="post-thumb"><img src="/static/test" width="130" height="150" align="right"/></div>
            <div class="post-content">
                <h1 class="well wellCustom">${employer.firstName} ${employer.lastName}</h1>

                <div class="form-actions floatCenter">

                    <input type="submit" value="Update" class="btn btn-primary custom-width"/>

                    <button type="button" data-id="${employer.ssoId}"
                            class="btn btn-warning custom-width showAll">ShowAll
                    </button>

                </div>
            </div>

            <div class="row marginTop">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="startOfTheRout">From:</label>
                    <div class="col-md-7">
                        <form:input type="text" path="startOfTheRout" id="startOfTheRout"
                                    class="form-control input-sm"/>

                        <div class="has-error">
                            <form:errors path="startOfTheRout" class="help-inline"/>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="endOfTheRout">To:</label>
                    <div class="col-md-7">
                        <form:input type="text" path="endOfTheRout" id="endOfTheRout" class="form-control input-sm"/>
                        <div class="has-error">
                            <form:errors path="endOfTheRout" class="help-inline"/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="emailOfClient">Email:</label>
                    <div class="col-md-7">
                        <form:input type="text" path="emailOfClient" id="emailOfClient" class="form-control input-sm"/>
                        <div class="has-error">
                            <form:errors path="emailOfClient" class="help-inline"/>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="phoneOfClient">Phone:</label>
                    <div class="col-md-7">
                        <form:input type="text" path="phoneOfClient" id="phoneOfClient" class="form-control input-sm"/>
                        <div class="has-error">
                            <form:errors path="phoneOfClient" class="help-inline"/>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="map-manage-generic-container">
            <div class="map-generic-container">
                <div id="map" style="width:100%; height: 100%;">
                    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyD2U93JXd0eI-qSD_dUCMa_MP4aSpJ1fAU&callback=myMap"></script>
                </div>
            </div>
        </div>

        <div class="taskDesc-generic-container">

            <a href="<c:url value='/manage-employer-${employer.ssoId}-task-${previouslyTaskId}'/>"
               class="btn btn-primary btn-sm btnMargin" align="left">Previously</a>
            <form:input type="text" path="description" id="description" class="well resizedTextbox"/>

            <div class="resizedGroup" align="right">
                &nbsp;
                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-3 control-lable" for="name">Name of Task:</label>
                        <div class="col-md-7">
                            <form:input type="text" path="name" id="name" class="form-control input-sm"/>

                            <div class="has-error">
                                <form:errors path="name" class="help-inline"/>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-3 control-lable" for="startDate">Start Date:</label>
                        <a href="<c:url value='/manage-employer-${employer.ssoId}-task-${nextTaskId}'/>"
                           class="btn btn-primary btn-sm btnMargin" align="left">Next</a>

                        <div class="col-md-7">
                            <form:input type="text" path="startDate" id="startDate" class="form-control input-sm"/>

                            <div class="has-error">
                                <form:errors path="startDate" class="help-inline"/>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-3 control-lable" for="endDate">End Date:</label>
                        <div class="col-md-7">
                            <form:input type="text" path="endDate" id="endDate" class="form-control input-sm"/>
                            <div class="has-error">
                                <form:errors path="endDate" class="help-inline"/>
                            </div>
                        </div>
                    </div>
                </div>
</div>
</div>
</form:form>
</div>
</body>
</html>
