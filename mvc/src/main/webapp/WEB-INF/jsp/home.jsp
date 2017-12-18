<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:page_template>
    <jsp:attribute name="body">
        <h1>
            Welcome!
        </h1>
        <h3>PA165 Enterprise Applications in Java: Formula One Team</h3>
        <h4>Feel free to browse!</h4>
        <b>Authors:</b>
                    <ul>
                        <li>Richard Hrmo</li>
                        <li>Matúš Macko</li>
                        <li>Róbert Tamáš</li>
                        <li>Lucie Kurečková</li>
                    </ul>
    </jsp:attribute>
</my:page_template>