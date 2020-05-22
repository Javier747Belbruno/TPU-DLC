/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.api;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import main.components.RelevantDocumentsClass;
import main.controller.QueryController;



/**
 *
 * @author Javier
 */

@Path("/results")
public class ResultsEndPoint {
    
    @Inject QueryController queryController;
    
    @GET
    @Path("/{query}&{r}&{st}")
    @Produces("application/json")
    public Response results(@PathParam("query") String query,@PathParam("r") String r,@PathParam("st") String st) throws UnsupportedEncodingException{
        
        String queryDecoded = URLDecoder.decode(query, "UTF-8");
        System.out.println("query " + query + " ! queryDecoded " + queryDecoded);
        
        List<RelevantDocumentsClass.ListObject>  lista = queryController.retriveDocuments(queryDecoded,r,st);
        return Response.ok(lista).build();
        
    }
}

