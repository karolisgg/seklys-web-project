<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>

   <meta name="viewport" content="initial-scale=1,maximum-scale=1,user-scalable=no"/>
   <%-- Bootstrap, Map --%>
   <link rel="stylesheet" href="${pageContext.request.contextPath}/css/4.1.3v.bootstrap.min.css">
   <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/leaflet/1.0.3/leaflet.css"/>
   <link rel="stylesheet" href="https://cdn.klokantech.com/mapbox-gl-js/v0.43.0/mapbox-gl.css"/>
   <%-- Date time picker --%>
   <link rel="stylesheet" href="${pageContext.request.contextPath}/css/datetimepicker.css"/>
   <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
   <%-- My Style --%>
   <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>

</head>
<body>

<div class="sidebar">

   <h2>List of devices:</h2>
   <div class="list-group">
      <c:forEach items="${devices}" var="dev">
         <button type="button" class="list-group-item list-group-item-action btn btn-secondary deviceBtn"
                 data-toggle="button" aria-pressed="false" autocomplete="off"
                 data-device-id="${dev.deviceId}">${dev.deviceId}
               ${dev.name}</button>
      </c:forEach>
   </div>

   <h3>Show Path by Date:</h3>
   <form id="devicePathForm">
      <div class="form-group">
         <label for="deviceSelect">Select device:</label>
         <select class="form-control" id="deviceSelect" style="max-width: 250px;">
            <c:forEach items="${devices}" var="dev">
               <option value="${dev.id}">${dev.deviceId} ${dev.name}</option>
            </c:forEach>
         </select>
      </div>

      <div class="form-group">
         <label for="result1">Select start date:</label>
         <div style="max-width: 250px;">
            <div id="picker1"></div>
            <input type="hidden" id="result1" value=""/>
         </div>
      </div>

      <div class="form-group">
         <label for="result2">Select end date:</label>
         <div style="max-width: 250px;">
            <div id="picker2"></div>
            <input type="hidden" id="result2" value=""/>
         </div>
      </div>

      <button id="showPath" type="submit" class="btn btn-primary">Show Path</button>
   </form>
</div>
<div id="map"></div>

<script>var ctx = "${pageContext.request.contextPath}"</script>

<%-- JQuery, Bootstrap --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<%--<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"--%>
        <%--integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"--%>
        <%--crossorigin="anonymous"></script>--%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/4.1.3v.bootstrap.min.js"></script>

<%-- Map --%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/leaflet/1.0.3/leaflet.js"></script>
<script src="https://cdn.klokantech.com/mapbox-gl-js/v0.43.0/mapbox-gl.js"></script>
<script src="https://cdn.klokantech.com/mapbox-gl-leaflet/latest/leaflet-mapbox-gl.js"></script>

<%-- Date time picker --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/moment-with-locales.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/datetimepicker.js"></script>

<%-- My Script --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/script.js"></script>

</body>
</html>