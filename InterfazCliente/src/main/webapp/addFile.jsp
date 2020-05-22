<%-- 
    Document   : addFile
    Created on : 10 may. 2020, 18:49:48
    Author     : Georgina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio</title>
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
        </nav>
        </header>
        <section>
            <div class="page-header">
            <h2 class="title">Agregar Documento</h2>
        </div>
        <form class="form" accept-charset="UTF-8" enctype="multipart/form-data" method="post" action="AddFile">
            <div class="col-lg-6">
                <div class="input-group">
                     <input type="file" accept="text/plain" id="myFile" name="fileName"/>
                    <span class="input-group-btn">
                        <button type="submit" id='btn' class="btn btn-primary">Agregar Documento</button>
                    </span>
                </div>
            </div>
        </form>
      </section>
    </body>
</html>
