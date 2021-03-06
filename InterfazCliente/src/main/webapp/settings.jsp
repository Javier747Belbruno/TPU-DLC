<%-- 
    Document   : settings
    Created on : 21 may. 2020, 12:30:16
    Author     : Javier
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Configuración</title>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
         <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> 
    </head>
    <body>
        <header>
            <nav class="nav nav-tabs nav-justified">
                <a href="index.jsp"><img src="https://pngimage.net/wp-content/uploads/2018/06/inicio-icono-png-6.png" class="logo"></a>
        </nav>
        </header>
    <section>
             <div class="page-header" >
            <h1 class="title">Configuración de Parámetros</h1>
        </div>
      <form class="formSettings" accept-charset="UTF-8" method="post" action="Settings">
            <div >
                <div >
                   <c:if test="${empty amountRetrive}">
                        <c:set var="amountRetrive" value="10" scope="session" />
                    </c:if>  
 

                   <label for="combAmountSearchID">Cantidad de documentos a recuperar</label>
                     <input type="range" min="1" max="100" value="${amountRetrive}" 
                    class="custom-range" id="combAmountSearchID" name="combAmountSearch">

                 
                  <c:if test="${empty typeSearch}">
                        <c:set var="typeSearch" value="1" scope="session" />
                  </c:if>  
                     
                 <label for="combTypeSearchID">Tipo de búsqueda</label>
                 <select  name="combTypeSearch" id="combTypeSearchID" value="${typeSearch}" class="form-control" >
                    <option value="0" ${typeSearch == '0' ? 'selected' : ''}>Búsqueda Extensiva</option>
                    <option value="1" ${typeSearch == '1' ? 'selected' : ''}>Búsqueda Rápida</option>
                 </select>
                   <br>
                </div>
                <br>
                    <span >
                        <button type="submit" class="btn btn-primary">Guardar</button>
                    </span>
            </div>      
        </form>
        </section>
    </body>
</html>
