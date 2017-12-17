<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:page_template title="Create new car" icon_class="fa fa-users">

    <jsp:attribute name="body">
        <button class="btn btn-primary" onclick="location.href='${pageContext.request.contextPath}/'">
            Home
        </button>

        <button class="btn btn-warning" onclick="location.href='${pageContext.request.contextPath}/cars/'">
            All cars
        </button>

        <hr>

        <form:form method="post"
                   action="${pageContext.request.contextPath}/cars/new"
                   modelAttribute="car"
                   cssClass="form-horizontal">

            <div class="row">
                <div class="form-group col-md-12 text-left">
                    <form:label path="driver" cssClass="col-md-12">Driver</form:label>
                    <div class="col-md-8">
                        <form:select path="driver" cssClass="form-control">
                            <c:forEach items="${drivers}" var="d">
                                <form:option value="${d.id}">
                                    Driver: ${d.name} ${d.surname}.
                                </form:option>
                            </c:forEach>
                        </form:select>
                        <form:errors path="driver" cssClass="help-block"/>
                    </div>
                </div>

                <div class="form-group col-md-12 text-left">
                    <form:label path="engine" cssClass="col-md-12">Engine</form:label>
                    <div class="col-md-8">
                        <form:select path="engine" cssClass="form-control">
                            <c:forEach items="${components}" var="c">
                                <form:option value="${c.id}">
                                    Component: ${c.name}.
                                </form:option>
                            </c:forEach>
                        </form:select>
                        <form:errors path="engine" cssClass="help-block"/>
                    </div>
                </div>

                <div class="form-group col-md-12 text-left">
                    <form:label path="brakes" cssClass="col-md-12">Brakes</form:label>
                    <div class="col-md-8">
                        <form:select path="brakes" cssClass="form-control">
                            <c:forEach items="${components}" var="c">
                                <form:option value="${c.id}">
                                    Component: ${c.name}.
                                </form:option>
                            </c:forEach>
                        </form:select>
                        <form:errors path="brakes" cssClass="help-block"/>
                    </div>
                </div>

                <div class="form-group col-md-12 text-left">
                    <form:label path="suspension" cssClass="col-md-12">Suspension</form:label>
                    <div class="col-md-8">
                        <form:select path="suspension" cssClass="form-control">
                            <c:forEach items="${components}" var="c">
                                <form:option value="${c.id}">
                                    Component: ${c.name}.
                                </form:option>
                            </c:forEach>
                        </form:select>
                        <form:errors path="suspension" cssClass="help-block"/>
                    </div>
                </div>

                <div class="form-group col-md-12 text-left">
                    <form:label path="aerodynamics" cssClass="col-md-12">Aerodynamics</form:label>
                    <div class="col-md-8">
                        <form:select path="aerodynamics" cssClass="form-control">
                            <c:forEach items="${components}" var="c">
                                <form:option value="${c.id}">
                                    Component: ${c.name}.
                                </form:option>
                            </c:forEach>
                        </form:select>
                        <form:errors path="aerodynamics" cssClass="help-block"/>
                    </div>
                </div>

                <div class="form-group col-md-12 text-left">
                    <form:label path="transmission" cssClass="col-md-12">Transmission</form:label>
                    <div class="col-md-8">
                        <form:select path="transmission" cssClass="form-control">
                            <c:forEach items="${components}" var="c">
                                <form:option value="${c.id}">
                                    Component: ${c.name}.
                                </form:option>
                            </c:forEach>
                        </form:select>
                        <form:errors path="transmission" cssClass="help-block"/>
                    </div>
                </div>
            </div>

          <button class="btn btn-success" type="submit">Create new car</button>
        </form:form>
    </jsp:attribute>
</my:page_template>