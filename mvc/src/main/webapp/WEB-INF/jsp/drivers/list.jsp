<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:page_template title="Drivers">
<jsp:attribute name="body">
    <script>
        function openModal(suffix) {
            var modal = $("#modal_" + suffix);
            if (modal)
                modal.modal('show');
        }

        function closeModal(suffix) {
            var modal = $("#modal_" + suffix);
            if (modal)
                modal.modal('hide');
        }
    </script>
    
    <form:form method="get" action="${pageContext.request.contextPath}/drivers" modelAttribute="filter">
           
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
            <th>Driver Type</th>
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
                    <form method="get" action="${pageContext.request.contextPath}/drivers/detail/${driver.id}">
                        <button type="submit" class="btn btn-primary">More details</button>
                    </form>
                </td>
                <td>
                    <form method="get" action="${pageContext.request.contextPath}/drivers/edit/${driver.id}">
                        <button type="submit" class="btn btn-primary">Edit Driver</button>
                    </form>
                </td>
                <td>
                    
                   <span class="glyphicon glyphicon-trash" onclick=" openModal(${driver.id}) "></span>

                   <my:modal_template suffix="${driver.id}" title="Delete driver">
                        <jsp:attribute name="body">
                            <strong>Are you sure you want to delete the driver: <c:out value="${driver.name}"/> <c:out value="${driver.surname}"/></strong>
                        </jsp:attribute>
                        <jsp:attribute name="footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal"
                                    onclick="closeModal(${drivert.id})">Close
                            </button>
                            <form style="float: right; margin-left: 10px" method="post"
                                  action="${pageContext.request.contextPath}/drivers/delete/${driver.id}">
                                <input type="submit" class="btn btn-primary" value="Delete"/>
                            </form>
                        </jsp:attribute>
                    </my:modal_template>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
        
    <form method="get" action="${pageContext.request.contextPath}/drivers/new">
        <button type="submit" class="btn btn-primary">
            Add new driver
        </button>
    </form>
        
</jsp:attribute>
</my:page_template>
         
<script>
    function openModal(suffix) {
        var modal = $("#modal_" + suffix);
        if (modal)
            modal.modal('show');
    }

    function closeModal(suffix) {
        var modal = $("#modal_" + suffix);
        if (modal)
            modal.modal('hide');
    }
</script>
