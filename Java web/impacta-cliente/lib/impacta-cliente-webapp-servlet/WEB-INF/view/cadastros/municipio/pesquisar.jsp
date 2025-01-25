<%@ page
 language="java"
 contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib
 uri="http://java.sun.com/jsp/jstl/core"
 prefix="c"%>
<fieldset>
 <legend>Filtro</legend>
 <form
  action="/impacta/municipio/listar"
  method="post">
  UF: <select
   name="uf"
   style="width: 100px;">
   <c:forEach
    var="row"
    items="${ufs}">
    <option
     value="${row}"
     <c:if test="${ row == uf }">selected</c:if>>${row}</option>
   </c:forEach>
  </select> <input
   type="submit"
   value="Filtrar" />
 </form>
</fieldset>
<br />