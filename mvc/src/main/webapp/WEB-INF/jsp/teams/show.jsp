<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<my:page_template title="${team.name}" icon_class="fa fa-users">
    <jsp:attribute name="body">

        <button class="btn btn" onclick="location.href='${pageContext.request.contextPath}/teams'">
            Return
        </button>

        <div class="row">
            <div class="col-md-12">
                <my:car_template car="${team.carOne}" name="carOne"/>
                <my:car_template car="${team.carTwo}" name="carTwo"/>
        </div>
    </jsp:attribute>
</my:page_template>