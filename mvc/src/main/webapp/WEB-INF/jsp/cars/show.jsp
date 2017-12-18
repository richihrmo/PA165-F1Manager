<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<my:page_template title="${car.id}" icon_class="fa fa-car">
    <jsp:attribute name="body">

        <div class="row">
            <div class="col-md-12">
                <my:car_template car="${car}"/>
            </div>
        </div>
            
        <button class="btn btn-secondary" onclick="location.href='${pageContext.request.contextPath}/cars'">
            Return
        </button>
            
    </jsp:attribute>
</my:page_template>