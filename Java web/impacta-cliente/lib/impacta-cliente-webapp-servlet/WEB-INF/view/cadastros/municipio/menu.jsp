<%@ page
 language="java"
 contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib
 uri="http://java.sun.com/jsp/jstl/core"
 prefix="c"%>
<div class="col-sm-3 col-md-2 sidebar">
 <ul class="nav nav-sidebar">
  <li <c:if test="${not empty listar}">class="active"</c:if>><a href="/impacta/municipio/listar">Municípios</a></li>
  <li <c:if test="${not empty inserir}">class="active"</c:if>><a href="/impacta/municipio/inserir">Novo município</a></li>
  <c:if test="${not empty alterar}"><li class="active"><a href="#">Alterando município</a></li></c:if>
 </ul>
</div>
