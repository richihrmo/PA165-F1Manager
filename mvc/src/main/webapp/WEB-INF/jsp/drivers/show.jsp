<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<my:page_template title="Driver details">
<jsp:attribute name="body">
  
    <form method="get" action="${pageContext.request.contextPath}/drivers">
        <button type="submit" class="btn">Return</button>
    </form>
    <div>
        <div>Name: <c:out value="${driver.name}"/></div>
        <div>Surname: <c:out value="${driver.surname}"/></div>
        <div>Nationality: <c:out value="${driver.nationality}"/></div>
        <div>Driver type: <c:out value="${driver.mainDriver ? 'Main driver' : 'Test driver'}"/></div>
        <div>Special skill: <c:out value="${driver.specialSkill.urlAnnotation}"/></div>
    </div>
    
    <c:if test="${driver.mainDriver}">
        <br>
        <h2>Car he drives:</h2>
        <div class="col-sm-3" style="padding-left: 0px;">
            <h4>Engine</h4>
            <p>
                <strong>Name:</strong>&nbsp;
                ${car.engine.name}
            </p>
        </div>

        <div class="col-sm-3">
            <h4>Aerodynamics</h4>
            <p>
                <strong>Name:</strong>&nbsp;
                ${car.aerodynamics.name}
            </p>
        </div>

        <div class="col-sm-6">
            <h4>Suspension</h4>
            <p>
                <strong>Name:</strong>&nbsp;
                ${car.suspension.name}
            </p>
        </div>

        <div class="col-sm-3" style="padding-left: 0px;">
            <h4>Transmission</h4>
            <p>
                <strong>Name:</strong>&nbsp;
                ${car.transmission.name}
            </p>
        </div>

        <div class="col-sm-9">
            <h4>Brakes</h4>
            <p>
                <strong>Name:</strong>&nbsp;
                ${car.brakes.name}
            </p>
        </div>
    </c:if>
</jsp:attribute>
</my:page_template>
