<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<my:page_template title="Teams" icon_class="fa fa-users">
    <jsp:attribute name="body">

        <button class="btn btn-primary" onclick="location.href='${pageContext.request.contextPath}/'">
            Home
        </button>

        <button class="btn btn-warning" onclick="location.href='${pageContext.request.contextPath}/teams/create'">
            Create new team
        </button>

        <button class="btn btn-success" onclick="location.href='${pageContext.request.contextPath}/teams/drivers'">
            Show all team drivers
        </button>

        <hr>

        <c:set var="endpoint" value="teams"/>

        <div class="row">
            <div class="col-md-12">
                <table class="table">
                    <thead>
                    <tr>
                        <th class="col-md-6">Name</th>
                        <th class="col-md-2 text-center">Update</th>
                        <th class="col-md-2 text-center">Delete</th>
                    </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${teams}" var="team">
                            <tr>
                                <td>
                                    <my:a href="/${endpoint}/show/${team.id}">
                                        <c:out value="${team.name}"/>
                                    </my:a>
                                </td>

                                <td class="text-center">
                                    <my:a href="/${endpoint}/edit/${team.id}">
                                        <i class="fa fa-pencil-square-o fa-2x"
                                           aria-hidden="true"
                                           style="cursor: pointer; color: black;">
                                        </i>
                                    </my:a>
                                </td>

                                <td class="text-center">
                                    <i class="fa fa-trash fa-2x" aria-hidden="true" onclick="openModal(${team.id})" style="cursor: pointer;"></i>
                                    <my:modal_template suffix="${team.id}" title="Delete team">
                                        <jsp:attribute name="body">
                                            <strong>
                                                Are you sure you want to delete this team: <c:out value="${team.name}"/>
                                            </strong>
                                        </jsp:attribute>
                                        <jsp:attribute name="footer">
                                            <button type="button" class="btn btn-secondary"
                                                    data-dismiss="modal"
                                                    onclick="closeModal(${team.id})">
                                                Close
                                            </button>
                                          <form style="float: right; margin-left: 10px"
                                                method="post"
                                                action="${pageContext.request.contextPath}/${endpoint}/delete/${team.id}">
                                              <input type="submit" class="btn btn-primary" value="Delete"/>
                                          </form>
                                        </jsp:attribute>
                                    </my:modal_template>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <c:if test="${empty teams}">
                    <div class="col-md-12">
                        <h4>No teams are available right now.</h4>
                    </div>
                </c:if>
            </div>
        </div>
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