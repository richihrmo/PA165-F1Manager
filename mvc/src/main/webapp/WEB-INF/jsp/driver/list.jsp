<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Drivers">
<jsp:attribute name="body">
    
    <form:form method="get" action="${pageContext.request.contextPath}/driver/" modelAttribute="filter">
           
        <form:label path="skill">Filter by Main Skill of Driver:</form:label>
        <form:select path="skill" items="${drivingType}" itemValue="name" itemLabel="urlAnnotation"/>
         <button class="btn btn-primary" type="submit">Filter</button>
         
    </form:form>

    <table class="table">
        <thead>
        <tr>
            <th>Name</th>
            <th>Surname</th>
            <th>Nationality</th>
            <th> </th>
            <th>Main Skill</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${drivers}" var="driver">
            <tr>
                <td><c:out value="${driver.name}"/></td>
                <td><c:out value="${driver.surname}"/></td>
                <td><c:out value="${driver.nationality}"/></td>
                <td><c:out value="${driver.mainDriver ? 'Main driver' : 'Test driver'}"/></td>
                <td><c:out value="${driver.specialSkill.urlAnnotation}"/></td>
                <td>
                    <form method="get" action="${pageContext.request.contextPath}/driver/edit/${driver.id}">
                        <button type="submit" class="btn btn-primary">Edit Driver</button>
                    </form>
                </td>
                <td>
                    <form method="post" action="${pageContext.request.contextPath}/driver/delete/${driver.id}">
                        <button type="submit" class="btn btn-primary">Delete driver</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
        
    <form method="get" action="${pageContext.request.contextPath}/driver/new">
        <button type="submit" class="btn btn-primary">
            Add new driver
        </button>
    </form>
        
</jsp:attribute>
</my:pagetemplate>
