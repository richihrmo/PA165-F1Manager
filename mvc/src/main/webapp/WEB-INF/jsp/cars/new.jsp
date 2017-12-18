<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:page_template title="Create new car" icon_class="fa fa-car">

    <jsp:attribute name="body">

        <my:protected>
            <button class="btn btn-secondary" onclick="location.href='${pageContext.request.contextPath}/cars/'">
                Return
            </button>

            <form:form method="post"
                       action="${pageContext.request.contextPath}/cars/new"
                       modelAttribute="car"
                       cssClass="form-horizontal">

                <div class="form-group">
                    <form:label path="driverId" cssClass="col-sm-3 control-label">Driver</form:label>
                    <div class="col-sm-7">
                        <form:select path="driverId" cssClass="form-control">
                            <c:forEach items="${drivers}" var="d">
                                <form:option value="${d.id}">
                                    ${d.name}&nbsp;${d.surname}
                                </form:option>
                            </c:forEach>
                        </form:select>
                    </div>
                </div>

                <div class="form-group">
                    <form:label path="engineId" cssClass="col-sm-3 control-label">Available Engines</form:label>
                    <div class="col-md-7">
                        <form:select path="engineId" cssClass="form-control">
                            <c:forEach items="${engines}" var="c">
                                <form:option value="${c.id}">
                                    ${c.name}
                                </form:option>
                            </c:forEach>
                        </form:select>
                        <form:errors path="engineId" cssClass="help-block"/>
                    </div>
                </div>

                <div class="form-group">
                    <form:label path="brakesId" cssClass="col-sm-3 control-label">Available Brakes</form:label>
                    <div class="col-md-7">
                        <form:select path="brakesId" cssClass="form-control">
                            <c:forEach items="${brakes}" var="c">
                                <form:option value="${c.id}">
                                    ${c.name}
                                </form:option>
                            </c:forEach>
                        </form:select>
                        <form:errors path="brakesId" cssClass="help-block"/>
                    </div>
                </div>

                <div class="form-group">
                    <form:label path="suspensionId" cssClass="col-sm-3 control-label">Available Suspension</form:label>
                    <div class="col-md-7">
                        <form:select path="suspensionId" cssClass="form-control">
                            <c:forEach items="${suspension}" var="c">
                                <form:option value="${c.id}">
                                    ${c.name}
                                </form:option>
                            </c:forEach>
                        </form:select>
                        <form:errors path="suspensionId" cssClass="help-block"/>
                    </div>
                </div>

                <div class="form-group">
                    <form:label path="aerodynamicsId" cssClass="col-sm-3 control-label">Available Aerodynamics</form:label>
                    <div class="col-md-7">
                        <form:select path="aerodynamicsId" cssClass="form-control">
                            <c:forEach items="${aerodynamics}" var="c">
                                <form:option value="${c.id}">
                                    ${c.name}
                                </form:option>
                            </c:forEach>
                        </form:select>
                        <form:errors path="aerodynamicsId" cssClass="help-block"/>
                    </div>
                </div>

                <div class="form-group">
                    <form:label path="transmissionId" cssClass="col-sm-3 control-label">Available Transmissions</form:label>
                    <div class="col-md-7">
                        <form:select path="transmissionId" cssClass="form-control">
                            <c:forEach items="${transmission}" var="c">
                                <form:option value="${c.id}">
                                    ${c.name}
                                </form:option>
                            </c:forEach>
                        </form:select>
                        <form:errors path="transmissionId" cssClass="help-block"/>
                    </div>
                </div>

              <button class="btn btn-primary" type="submit">Create car</button>
            </form:form>
        </my:protected>
    </jsp:attribute>
</my:page_template>