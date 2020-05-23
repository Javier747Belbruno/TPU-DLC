<%-- 
    Document   : index
    Created on : 10 may. 2020, 17:29:28
    Author     : Georgina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio</title>
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
            <ul>
                <li><a class="navbar-brand" href="addFile.jsp">Agregar documento</a></li>
                <li><a class="navbar-brand" href="settings.jsp">Configuración</a></li>
            </ul>
        </nav>
        </header>
        <section>
            <div class="page-header" >
                <h1 class="title">Motor de Búsqueda Vectorial</h1>
            </div>
            <form class="form" name="searchForm" accept-charset="UTF-8" method="post" action="buscar">
                <div class="col-lg-6">
                    <div class="input-group">
                        <input name="txt_buscar" required type="text" class="form-control" placeholder="Ingrese una palabra" > 
                        <input type="hidden" name="amountRetrive" value="${amountRetrive}">
                        <input type="hidden" name="typeSearch" value="${typeSearch}">
                        <span class="input-group-btn">
                            <input type="submit" class="btn btn-primary" id ="btn" value="Search" />
                        </span>
                    </div>
                </div>
            </form>
        </section>
        <script>            
            $(document).ready(function () {
                $(".btn").click(function () {
                    if(validateForm()){
                        $(this).button('loading');
                    }
                });
            }); 
            
            function validateForm(){
            var tb = document.searchForm.txt_buscar.value;
            if (tb.length === 0) {
              return false;
            }
            return true;
          }

        </script>
    </body>
</html>
