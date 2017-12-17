<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:page_template title="Users">
    <jsp:attribute name="body">
      <c:set var="end" value="users"/>

        <my:protected>
        <table class="table table-striped">
            <caption>Users</caption>
            <thead>
            <tr>
                <th class="text-center">#</th>
                <th class="text-center">Name</th>
                <th class="text-center">Email</th>
                <th class="text-center">Role</th>
            </tr>
            </thead>
            <tbody class="text-center">
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>
                        <c:out value="${user.id}"/>
                    </td>
                    <td>
                        <c:out value="${user.name}"/>
                    </td>
                    <td>
                        <my:a href="/${end}/read/${user.id}"><c:out value="${user.email}"/></my:a>
                    </td>
                    <td>
                        <c:if test="${user.admin == true}">
                            <i class="fa fa-user-plus" aria-hidden="true" title="admin"></i>
                        </c:if>
                        <c:if test="${user.admin == false}">
                            <i class="fa fa-user" aria-hidden="true" title="user"></i>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

      <button class="btn"
              onclick="location.href='${pageContext.request.contextPath}'">
          Return
      </button>
        </my:protected>

    </jsp:attribute>
</my:page_template>
