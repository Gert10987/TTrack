<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html xmlns:form="http://www.w3.org/1999/xhtml">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>User Registration Form</title>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>

<body>
<div id="mainWrapper">
    <div class="generic-container">

        <div class="well lead">User Registration Form</div>
        <form:form method="POST" modelAttribute="employer" class="form-horizontal">
            <form:input type="hidden" path="id" id="id"/>

            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="startDate">First Name</label>
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
                    <label class="col-md-3 control-lable" for="endDate">Last Name</label>
                    <div class="col-md-7">
                        <form:input type="text" path="endDate" id="endDate" class="form-control input-sm"/>
                        <div class="has-error">
                            <form:errors path="endDate" class="help-inline"/>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="name">SSO ID</label>
                    <div class="col-md-7">
                        <c:choose>
                            <c:when test="${edit}">
                                <form:input type="text" path="name" id="name" class="form-control input-sm"
                                            disabled="true"/>
                            </c:when>
                            <c:otherwise>
                                <form:input type="text" path="name" id="name" class="form-control input-sm"/>
                                <div class="has-error">
                                    <form:errors path="name" class="help-inline"/>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="description">Password</label>
                    <div class="col-md-7">
                        <form:input type="description" path="description" id="description" class="form-control input-sm"/>
                        <div class="has-error">
                            <form:errors path="description" class="help-inline"/>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="emailOfClient">Email</label>
                    <div class="col-md-7">
                        <form:input type="text" path="emailOfClient" id="emailOfClient" class="form-control input-sm"/>
                        <div class="has-error">
                            <form:errors path="emailOfClient" class="help-inline"/>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="form-actions floatRight">
                    <input type="submit" value="Register" class="btn btn-primary btn-sm"/> or <a
                        href="<c:url value='/list' />">Cancel</a>
                </div>
            </div>
        </form:form>
    </div>
</div>
</body>
</html>