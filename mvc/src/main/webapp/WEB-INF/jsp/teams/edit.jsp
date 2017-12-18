<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:page_template title="Edit team" icon_class="fa fa-users">
    <jsp:attribute name="body">
        <my:protected>
            <button class="btn btn" onclick="location.href='${pageContext.request.contextPath}/teams/'">
                Return
            </button>

            <form:form method="post"
                       action="${pageContext.request.contextPath}/teams/edit/${teamEdit.id}"
                       modelAttribute="teamEdit"
                       cssClass="form-horizontal">


                    <div class="form-group">
                        <form:label path="name" cssClass="col-sm-3 control-label">Name</form:label>
                        <div class="col-md-8">
                            <form:input path="name" cssClass="form-control" value="${teamEdit.name}"/>
                            <form:errors path="name" cssClass="help-block"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <form:label path="carOneId" cssClass="col-sm-3 control-label">Car one</form:label>
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

                    <div class="form-group">
                        <form:label path="carTwoId" cssClass="col-sm-3 control-label">Car two</form:label>
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


                <button class="btn btn-primary" type="submit">Edit team</button>
            </form:form>
        </my:protected>
    </jsp:attribute>
</my:page_template>