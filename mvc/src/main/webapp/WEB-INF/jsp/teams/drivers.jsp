<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<my:page_template title="All team drivers" icon_class="fa fa-users">
    <jsp:attribute name="body">

        <button class="btn btn" onclick="location.href='${pageContext.request.contextPath}/teams'">
            Return
        </button>

        <div class="row">
            <div class="col-md-12">
                <c:forEach items="${drivers}" var="d">
                    <h3>Driver</h3>
                    <p><strong>Name: </strong>${d.name} &nbsp;<strong>Surname: </strong>${d.surname} &nbsp;<strong>Nationality: </strong> ${d.nationality}
                </c:forEach>
            </div>
        </div>
    </jsp:attribute>
</my:page_template>