
package main.api;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.LinkedList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import main.controller.FileController;



@Path("file")
public class FileEndPoint {
    
    @Inject FileController fileController;
    
    @GET
    @Path("/{archivo}")
    @Produces("application/json")
    public Response processFile(@PathParam("archivo") String pathArchivo) throws UnsupportedEncodingException {
        StringBuilder response = new StringBuilder();  
        String pathDecoded = URLDecoder.decode(pathArchivo, "UTF-8");
        try {
            response.append(fileController.procesarFile(pathDecoded));
            return Response.ok(fileController.generarJsonRespuesta(response.toString())).build();
        } catch (Exception e) {
            return Response.ok(fileController.generarJsonRespuesta(response.toString()+" Error en: " +e.getMessage())).build();
        }
    }
    
    
     @GET
    @Path("/reader/{path}")
    @Produces("application/json")
    public Response readerFile(@PathParam("path") String pathArchivo) throws UnsupportedEncodingException {
        List<String> list = new LinkedList<>();
        String pathDecoded = URLDecoder.decode(pathArchivo, "UTF-8");
        try {
            list = fileController.devolverFile(pathDecoded);
            return Response.ok(list).build();
        } catch (Exception e) {
            list.add(e.getMessage());
            return Response.ok(list).build();
        }
    }
}
