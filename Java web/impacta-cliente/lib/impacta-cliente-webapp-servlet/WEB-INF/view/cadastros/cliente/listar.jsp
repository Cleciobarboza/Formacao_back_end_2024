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
</head>
<body>
 <jsp:include page="/WEB-INF/view/cadastros/header.jsp" />
 <jsp:include page="/WEB-INF/view/cadastros/cliente/menu.jsp" />

 <div class="container-fluid">
  <div class="row">
   <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
    <h1 class="page-header">Lista de clientes</h1>
    <div class="table-responsive">
     <table class="table table-striped">
      <thead>
       <tr>
        <th>Cliente</th>
        <th>Endereço</th>
        <th>E-Mail</th>
        <th>Contatos</th>
        <th />
       </tr>
      </thead>
      <tbody>
       <c:forEach
        var="row"
        items="${clientes}">
        <tr>
         <td>${row.nomeCompleto}</td>
         <td>${row.endereco}</td>
         <td>${row.email}</td>
         <td>${row.contatos}</td>
         <td>
          <a href="/impacta/cliente/alterar?id=0000">AL</a>
          <a href="/impacta/cliente/apagar?id=0000">AP</a>
         </td>
        </tr>
       </c:forEach>
      </tbody>
     </table>
    </div>
   </div>
  </div>
</body>
</html>