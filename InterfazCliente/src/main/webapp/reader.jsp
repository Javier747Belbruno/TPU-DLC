<%-- 
    Document   : reader
    Created on : 23 may. 2020, 01:10:53
    Author     : Javier
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
       <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <title>Lector de Documento</title>
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
            <h1 class="title">Lector de documentos</h1>
        </div>
            <div style="text-align: center;">
                <div style="display: inline-block; text-align: left;">
                    <c:forEach items="${document}" var="i">
                        ${i}<br>
                    </c:forEach>
                </div>
            </div>
        </section>
    </body>
</html>