<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<my:page_template title="${driver.id == null ? 'Add new driver' : 'Edit driver'}" icon_class="fa fa-user">
    <jsp:attribute name="body">
        <my:protected>
            <form method="get" action="${pageContext.request.contextPath}/drivers">
                <button type="submit" class="btn">Return</button>
            </form>



            <form:form id="${driver.id}" method="post" modelAttribute="driver"
                       action="${pageContext.request.contextPath}/drivers/save" cssClass="form-horizontal">
            <form:hidden path="id"  style="display:none"/>

            <div class="form-group">
                <form:label path="name" cssClass="col-sm-3 control-label">Name</form:label>
                <div class="col-sm-9">
                    <form:input path="name" cssClass="form-control" id="name" placeholder="Name"/>
                </div>
            </div>

            <div class="form-group">
                <form:label path="surname" cssClass="col-sm-3 control-label">Surname</form:label>
                <div class="col-sm-9">
                    <form:input path="surname" cssClass="form-control" id="surname" placeholder="Surname"/>
                </div>
            </div>

            <div class="form-group">
                <form:label path="nationality" cssClass="col-sm-3 control-label">Nationality</form:label>
                <div class="col-sm-9">
                    <form:select path="nationality" class="form-control" items="${Countries}" itemValue="country" itemLabel="displayCountry"/>
                </div>
            </div>

            <div class="form-group">
                <form:label path="specialSkill" cssClass="col-sm-3 control-label">Main Skill</form:label>
                <div class="col-sm-9">
                    <form:select path="specialSkill" class="form-control" items="${Skills}" itemValue="name" itemLabel="urlAnnotation"/>
                </div>
            </div>

            <button type="submit" class="btn btn-primary">${driver.id == null ? 'Create driver' : 'Edit driver'}</button>
            </form:form>
        </my:protected>
    </jsp:attribute>
</my:page_template>
