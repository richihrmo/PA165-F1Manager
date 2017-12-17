<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:page_template title="Components">
    <jsp:attribute name="body">
        <c:set var="end" value="components"/>

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


        <form:form method="get" action="${pageContext.request.contextPath}/components" modelAttribute="filter" cssClass="form-horizontal">
            <div class="row">
                <div class="col-lg-12">
                    <div class="input-group">
                        <span class="input-group-addon">
                            <form:label path="available">Filter available</form:label>
                        </span>
                        <span class="form-control text-center">
                            <form:checkbox path="available"/>
                        </span>

                        <span class="input-group-addon">
                            <form:label path="type">Filter type</form:label>
                        </span>
                        <form:select class="form-control" path="type" items="${componentTypeSelect}" itemValue="name" itemLabel="urlAnnotation"/>

                        <div class="input-group-btn">
                            <button class="btn btn-secondary" type="submit">
                                <span class="glyphicon glyphicon-filter"></span>
                                Filter
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </form:form>


        <table class="table table-striped">
            <thead>
                <tr>
                    <th class="text-center">#</th>
                    <th class="text-center">Name</th>
                    <th class="text-center">Type</th>
                    <th class="text-center">Availability</th>
                    <%--<my:protected>--%>
                        <th class="text-center">Delete</th>
                        <th class="text-center">Update</th>
                    <%--</my:protected>--%>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${components}" var="component">
                <tr class="text-center">
                    <td>
                        <c:out value="${component.id}"/>
                    </td>

                    <td>
                        <my:a href="/${end}/read/${component.id}"><c:out value="${component.name}"/> </my:a>
                    </td>

                    <td>
                        <c:out value="${component.type.urlAnnotation}"/>
                    </td>

                    <td>
                        <c:if test = "${component.availability}">
                            <span class="glyphicon glyphicon-ok"></span>
                        </c:if>
                        <c:if test = "${!component.availability}">
                            <span class="glyphicon glyphicon-remove"></span>
                        </c:if>
                    </td>

                    <%--<my:protected>--%>
                    <td>
                        <span class="glyphicon glyphicon-trash" onclick=" openModal(${component.id}) "></span>

                        <my:modal_template suffix="${component.id}" title="Delete component">
                            <jsp:attribute name="body">
                                <strong>Are you sure you want to delete the component: <c:out value="${component.name}"/></strong>
                            </jsp:attribute>
                            <jsp:attribute name="footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal"
                                        onclick="closeModal(${component.id})">Close
                                </button>
                                <form style="float: right; margin-left: 10px" method="post"
                                      action="${pageContext.request.contextPath}/${end}/delete/${component.id}">
                                    <input type="submit" class="btn btn-primary" value="Delete"/>
                                </form>
                            </jsp:attribute>
                          </my:modal_template>

                    </td>
                    <td>
                        <span class="glyphicon glyphicon-pencil" onclick="location.href='${pageContext.request.contextPath}/${end}/edit/${component.id}'"></span>
                    </td>
                  <%--</my:protected>--%>


                </tr>
            </c:forEach>
            </tbody>
        </table>

      <%--<my:protected>--%>
              <button class="btn btn-primary" onclick="location.href='${pageContext.request.contextPath}/${end}/create'"> Add Component </button>
      <%--</my:protected>--%>
      <button class="btn btn-secondary" onclick="location.href='${pageContext.request.contextPath}/'"> Return </button>

    </jsp:attribute>
</my:page_template>