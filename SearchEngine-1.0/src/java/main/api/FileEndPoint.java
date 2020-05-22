/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.api;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import main.controller.FileController;



@Path("file")
public class FileEndPoint {
    
    @Inject FileController fileController;
    
    @GET
    @Path("/{archivo}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response find(@PathParam("archivo") String pathArchivo) throws UnsupportedEncodingException {
           
        String pathDecoded = URLDecoder.decode(pathArchivo, "UTF-8");
        System.out.println("pathArchivo " + pathArchivo + " ! queryDecoded " + pathDecoded);
        try {
           String MensajeRespuesta = fileController.procesarFile(pathDecoded);
            return Response.ok(MensajeRespuesta).build();
        } catch (Exception e) {
            return Response.ok(e.getMessage()).build();
        }

    }
    
}
