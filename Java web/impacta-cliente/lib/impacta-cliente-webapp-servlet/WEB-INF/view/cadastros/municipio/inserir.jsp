<?xml version="1.0" encoding="UTF-8" ?>
<%@ page
 language="java"
 contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib
 uri="http://java.sun.com/jsp/jstl/core"
 prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta
 http-equiv="Content-Type"
 content="text/html; charset=UTF-8" />
<title>[IMPACTA] Cadastro de clientes</title>
<script
 type='text/javascript'
 src='/impacta/js/jquery-1.10.2.js'></script>
<script
 type='text/javascript'
 src='/impacta/js/jquery-ui-1.10.4.custom.js'></script>
<script
 type='text/javascript'
 src='/impacta/js/municipio.js'></script>
<link
 rel="shortcut icon"
 href="/impacta/img/impacta.jpeg" />
<!-- Bootstrap -->
<link
 rel="stylesheet"
 type="text/css"
 href="/impacta/css/bootstrap.css" />
<link
 rel="stylesheet"
 type="text/css"
 href="/impacta/css/dashboard.css" />
<link
 rel="stylesheet"
 type="text/css"
 href="/impacta/css/jquery-ui-1.10.4.custom.css" />
</head>
<body>
 <jsp:include page="/WEB-INF/view/cadastros/header.jsp" />
 <jsp:include page="/WEB-INF/view/cadastros/municipio/menu.jsp" />

 <div class="container-fluid">
  <div class="row">
   <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
    <h1 class="page-header">Novo município</h1>

    <form
      id="municipioForm"
      name="municipioForm"
     action="/impacta/municipio/salvar"
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
     </select>
     Município: <input
      type="text"
      name="municipio"
      maxlength="80"
      size="40" value="${municipio}" />
     <input
      type="submit"
      value="OK" />
     <input id="buttonCancelarNovoMunicipio"
      type="button"
      value="Cancelar" />
    </form><br />

    <jsp:include page="/WEB-INF/view/cadastros/alert.jsp" />
   </div>
  </div>
 </div>
 <div id="dialog-confirm"></div>
</body>
</html>