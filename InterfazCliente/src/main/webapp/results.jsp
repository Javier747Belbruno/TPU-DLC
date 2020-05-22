<%-- 
    Document   : results
    Created on : 10 may. 2020, 21:03:10
    Author     :  Georgina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <title>Resultados de la busqueda</title>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
       
    </head>
    <body>
       <header>    
            <nav class="nav nav-tabs nav-justified">
            <a href="index.jsp"><img src="https://pngimage.net/wp-content/uploads/2018/06/inicio-icono-png-6.png" class="logo"></a>
            <ul>
                <li><a class="navbar-brand" href="addDocument.jsp">Agregar documento</a></li>
                <li><a class="navbar-brand" href="addFolder.jsp">Agregar carpeta con documentos</a></li>
            </ul>
        </nav>
        </header>
        <section>
            <h1>${respuesta}</h1>
        <div class='container'>
            <h2>Resultado de la Busqueda: ${palabra}</h2>
            <table class="table">
                <thead>
                    <tr class="table-primary">
                        <th scope="col">Documento</th>
                        <th scope="col">Peso</th>
                        <th scope="col">Documento</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${lista}" var="ListObject">
                        <tr>
                            <td>${ListObject.document}</td>
                            <td>${ListObject.ir}</td>
                            <td>
                                <a href="<c:url value="/lectorDocumento?ruta=${ListObject.document}"/>" class="btn btn-primary">Abrir</a>
                            </td>
                        </tr>            
                    </c:forEach>
                </tbody>
            </table>

        </div>
        </section>
    </body>
</html>
