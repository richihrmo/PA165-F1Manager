<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:page_template title="Component ${component.name}" icon_class="fa fa-wrench">
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

              <table class="table">
                  <thead>
                  <tr>
                   <th>Name</th>
                   <th>Component type</th>
                   <th>Available</th>
                   <my:protected>
                       <th>Delete</th>
                       <th>Update</th>
                   </my:protected>

                  </tr>
                  </thead>
                  <tbody>
                  <tr>
                   <td>
                    <c:out value="${component.name}"/>
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

                   <my:protected>
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
                     </my:protected>
                  </tr>
                  </tbody>
              </table>
         <button class="btn"
                 onclick="location.href='${pageContext.request.contextPath}/${end}'">
          Return
         </button>

    </jsp:attribute>
</my:page_template>