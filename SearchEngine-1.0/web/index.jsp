
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pagina Inicial - Mostrar Recursos</title>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
    <body>
        <section>
        <div class='container'>
            <div style="text-align: center;">
                <div style="display: inline-block; text-align: left;">
                    <h2>Descripción del Recurso de Servicio API REST</h2>
                     <ul>
                        <li>
                             <h3>Results EndPoint - Servicio de Búsqueda</h3>
                             <br>
                             <h5>results() : Response</h5>
                             <h5>Metodo @GET</h5>
                             <h5>Relative @Path("/{query}&{r}&{searchType}")</h5>
                             <h5>Parámetros: query(String) r(Number) searchType(Number)"0: Busqueda Extensiva","1: Busqueda Rápida"</h5>
                             <h5>URI: http://localhost:8080/SearchEngine-1.0/webresources/results/{query}&{r}&{searchType}</h5>
                             <h5>@Produces("application/json")</h5>
                             <h5>Contenido JSON : Array de Objetos de Tipo "ListObject"</h5>
                             <h5>Objeto Auxiliar Con Atributos "document"(String) e "ir"(Double).</h5>
                             <br>
                        </li>
                        <li> 
                             <h3>File EndPoint - Servicio de Procesamiento y Lectura de Documentos </h3>
                             <br>
                             <h5>processFile() : Response</h5>
                             <h5>@GET</h5>
                             <h5>Relative @Path("/{fileRelativePath}")</h5>
                             <h5>Parámetro: fileRelativePath (String)</h5>
                             <h5>URI: http://localhost:8080/SearchEngine-1.0/webresources/file/{documentName.txt}</h5>
                             <h5>@Produces("application/json")</h5>
                             <h5>Contenido JSON : Un objeto que tiene de Atributo Data(String)</h5>
                             <h5>Con información del procesamiento del archivo.</h5>
                             <br>
                             <h5>readerFile() : Response</h5>
                             <h5>@GET</h5>
                             <h5>Relative @Path ("/reader/{path}")</h5>
                             <h5>Parametro: path(String)</h5>
                             <h5>URI: http://localhost:8080/SearchEngine-1.0/webresources/file/reader/{documentName.txt}</h5>
                             <h5>@Produces("application/json")</h5>
                             <h5>Contenido JSON : Un objeto Array de Strings </h5>
                             <h5>Cada elemento del array es una oración del documento.</h5>
                             <br>
                        </li>
                    </ul>
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>
