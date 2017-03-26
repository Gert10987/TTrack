<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html xmlns:form="http://www.w3.org/1999/xhtml" xmlns:c="http://www.w3.org/1999/XSL/Transform">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>User Registration Form</title>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>

<body>
<div id="mainWrapper">

    <div class="photo-generic-container">
        <div class="post-thumb"><img src="/static/test" width="130" height="150" align="right"/></div>
        <div class="post-content">
            <h1 class="well wellCustom">${employer.firstName} ${employer.lastName}</h1>

            <div class="form-actions floatCenter">
                <input type="submit" value="Update" class="btn btn-primary btn-sm" align="right"/>
                <input type="submit" value="Add" class="btn btn-success btn-sm" align="right"/>
                <input type="submit" value="Delete" class="btn btn-danger btn-sm" align="right"/>
            </div>
        </div>
    </div>

    <div class="manage-generic-container">
        <form:form method="POST" modelAttribute="employer" class="form-horizontal">
            <form:input type="hidden" path="id" id="id"/>

            &nbsp;
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="firstName">From:</label>
                    <div class="col-md-7">
                        <form:input type="text" path="firstName" id="firstName" class="form-control input-sm"/>

                        <div class="has-error">
                            <form:errors path="firstName" class="help-inline"/>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="lastName">To:</label>
                    <div class="col-md-7">
                        <form:input type="text" path="lastName" id="lastName" class="form-control input-sm"/>
                        <div class="has-error">
                            <form:errors path="lastName" class="help-inline"/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="email">Email:</label>
                    <div class="col-md-7">
                        <form:input type="text" path="email" id="email" class="form-control input-sm"/>
                        <div class="has-error">
                            <form:errors path="email" class="help-inline"/>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="phone">Phone:</label>
                    <div class="col-md-7">
                        <form:input type="text" path="phone" id="phone" class="form-control input-sm"/>
                        <div class="has-error">
                            <form:errors path="phone" class="help-inline"/>
                        </div>
                    </div>
                </div>
            </div>
        </form:form>
    </div>
    <div class="map-manage-generic-container">
        <div class="map-generic-container">
            <div id="map" style="width:100%;height:434px;background:yellow"></div>

            <script>
  function myMap() {
    var mapCanvas = document.getElementById("map");
    var mapOptions = {
      center: new google.maps.LatLng(51.5, -0.2), zoom: 10
    };
    var map = new google.maps.Map(mapCanvas, mapOptions);
  }




            </script>
            <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyD2U93JXd0eI-qSD_dUCMa_MP4aSpJ1fAU&callback=myMap"></script>
        </div>
    </div>

    <div class="taskDesc-generic-container">

        <input type="submit" value="Previously" class="btn btn-primary btn-sm btnMargin" align="left"/>
        <input type="text" class="well resizedTextbox"/>

        <div class="resizedGroup" align="right">
            <form:form method="POST" modelAttribute="employer" class="form-horizontal">
                <form:input type="hidden" path="id" id="id"/>

                &nbsp;
                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-3 control-lable" for="firstName">Name of Task:</label>
                        <div class="col-md-7">
                            <form:input type="text" path="firstName" id="firstName" class="form-control input-sm"/>

                            <div class="has-error">
                                <form:errors path="firstName" class="help-inline"/>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-3 control-lable" for="lastName">Start Date:</label>
                        <input type="submit" value="Next" class="btn btn-primary btn-sm" align="right"/>

                        <div class="col-md-7">
                            <form:input type="text" path="lastName" id="lastName" class="form-control input-sm"/>

                            <div class="has-error">
                                <form:errors path="lastName" class="help-inline"/>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-3 control-lable" for="email">End Date:</label>
                        <div class="col-md-7">
                            <form:input type="text" path="email" id="email" class="form-control input-sm"/>
                            <div class="has-error">
                                <form:errors path="email" class="help-inline"/>
                            </div>
                        </div>
                    </div>
                </div>

            </form:form>
        </div>

    </div>
</div>

</body>
</html>
