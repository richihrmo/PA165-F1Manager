<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:page_template title="Create new team" icon_class="fa fa-users">

    <jsp:attribute name="body">
        <button class="btn btn-primary" onclick="location.href='${pageContext.request.contextPath}/'">
            Home
        </button>

        <button class="btn btn-warning" onclick="location.href='${pageContext.request.contextPath}/teams/'">
            All teams
        </button>

        <hr>

        <form:form method="post"
                   action="${pageContext.request.contextPath}/teams/create"
                   modelAttribute="teamCreate"
                   cssClass="form-horizontal">

            <div class="row">
                <div class="form-group col-md-12 text-left">
                    <form:label path="name" cssClass="col-md-12">Name</form:label>
                    <div class="col-md-8">
                        <form:input path="name" cssClass="form-control"/>
                        <form:errors path="name" cssClass="help-block"/>
                    </div>
                </div>

                <div class="form-group col-md-12 text-left">
                    <form:label path="carOneId" cssClass="col-md-12">Car one</form:label>
                    <div class="col-md-8">
                        <form:select path="carOneId" cssClass="form-control">
                            <c:forEach items="${cars}" var="c">
                                <form:option value="${c.id}">
                                    Driver: ${c.driver.name} ${c.driver.surname}.
                                    Components: ${c.engine.name}, ${c.brakes.name}, ${c.suspension.name}, ${c.aerodynamics.name}, ${c.transmission.name}
                                </form:option>
                            </c:forEach>
                        </form:select>
                        <form:errors path="carOneId" cssClass="help-block"/>
                    </div>
                </div>

                <div class="form-group col-md-12 text-left">
                    <form:label path="carTwoId" cssClass="col-md-12">Car one</form:label>
                    <div class="col-md-8">
                        <form:select path="carTwoId" cssClass="form-control">
                            <c:forEach items="${cars}" var="c">
                                <form:option value="${c.id}">
                                    Driver: ${c.driver.name} ${c.driver.surname}.
                                    Components: ${c.engine.name}, ${c.brakes.name}, ${c.suspension.name}, ${c.aerodynamics.name}, ${c.transmission.name}
                                </form:option>
                            </c:forEach>
                        </form:select>
                        <form:errors path="carOneId" cssClass="help-block"/>
                    </div>
                </div>
            </div>

          <button class="btn btn-success" type="submit">Create new team</button>
        </form:form>
    </jsp:attribute>
</my:page_template>