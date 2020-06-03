
package main.api;

import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.PathSegment;
import main.entity.Posteo;
import main.dao.PosteoDao;


@Path("posting")
public class PostingEndPoint {
    
    @EJB
    private PosteoDao pdao;
    
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") PathSegment id, Posteo entity) {
        pdao.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") PathSegment id) {
        main.entity.PosteoPK key = pdao.getPrimaryKey(id);
        pdao.remove(pdao.find(key));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Posteo find(@PathParam("id") PathSegment id) {
        main.entity.PosteoPK key = pdao.getPrimaryKey(id);
        return pdao.find(key);
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Posteo> findAll() {
        return pdao.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Posteo> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return pdao.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(pdao.count());
    }
}
