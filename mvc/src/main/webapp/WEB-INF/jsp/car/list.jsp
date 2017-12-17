<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:page_template title="Cars">
<jsp:attribute name="body">

    <my:a href="/car/new" class="btn btn-primary">
        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
        New product
    </my:a>

    <table class="table">
        <thead>
        <tr>
            <th>id</th>
            <th>driver</th>
            <th>engine</th>
            <th>aerodynamics</th>
            <th>price</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${cars}" var="product">
            <tr>
                <td>${product.id}</td>
                <td><fmt:formatDate value="${product.addedDate}" pattern="yyyy-MM-dd"/></td>
                <td><c:out value="${product.name}"/></td>
                <td><c:out value="${product.color}"/></td>
                <td><c:out value="${product.currentPrice.value} ${product.currentPrice.currency}"/></td>
                <td>
                    <my:a href="/product/view/${car.id}" class="btn btn-primary">View</my:a>
                </td>
                <td>
                    <form method="post" action="${pageContext.request.contextPath}/product/delete/${product.id}">
                        <button type="submit" class="btn btn-primary">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>


</jsp:attribute>
</my:page_template>