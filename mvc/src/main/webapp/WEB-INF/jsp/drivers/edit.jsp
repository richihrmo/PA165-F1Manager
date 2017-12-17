<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<my:page_template title="${driver.id == null ? 'Add new driver' : 'Edit driver'}">
<jsp:attribute name="body">
  
<form method="get" action="${pageContext.request.contextPath}/drivers">
    <button type="submit" class="btn">Return</button>
</form>
    
<div>   
    
    <form:form id="${driver.id}" method="post" modelAttribute="driver"
               action="${pageContext.request.contextPath}/drivers/save">
    <form:hidden path="id"  style="display:none"/>
    
    <div class="form-group">
        <label for="name">Name</label>
        <form:input path="name" cssClass="form-control" id="name" placeholder="Name"/>
    </div>
    
    <div class="form-group">
        <label for="surname">Surname</label>
        <form:input path="surname" cssClass="form-control" id="surname" placeholder="Surname"/>
    </div>
    
    <div class="form-group">
        <label for="nationality">Nationality</label>
        <form:input path="nationality" cssClass="form-control" id="nationality" placeholder="Nationality"/>
    </div>
    
    <div class="form-group">
        <label for="specialSkill">Main Skill</label>
        <form:select path="specialSkill" class="form-control" items="${Skills}" itemValue="name" itemLabel="urlAnnotation"/>
    </div>
    
    <button type="submit" class="btn btn-primary">Submit</button>
    </form:form>
    
</div>
    
</jsp:attribute>
</my:page_template>
