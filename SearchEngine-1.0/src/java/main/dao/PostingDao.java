
package main.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.PathParam;

import javax.ws.rs.core.PathSegment;
import main.entity.Posting;
import main.entity.PostingPK;

@Stateless
public class PostingDao extends AbstractDao<Posting> {

    @PersistenceContext(unitName = "WebApplication5PU")
    private EntityManager em;

    public PostingPK getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;idDocumento=idDocumentoValue;idTermino=idTerminoValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        main.entity.PostingPK key = new main.entity.PostingPK();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> idDocumento = map.get("idDocumento");
        if (idDocumento != null && !idDocumento.isEmpty()) {
            key.setIdDocumento(new java.lang.Integer(idDocumento.get(0)));
        }
        java.util.List<String> idTermino = map.get("idTermino");
        if (idTermino != null && !idTermino.isEmpty()) {
            key.setIdTermino(new java.lang.Integer(idTermino.get(0)));
        }
        return key;
    }

    public PostingDao() {
        super(Posting.class);
    }

    @Override
    public Posting create(Posting entity) {
        return super.create(entity);
    }

    public void edit(PathSegment id, Posting entity) {
        super.edit(entity);
    }

    public void remove(PathSegment id) {
        main.entity.PostingPK key = getPrimaryKey(id);
        super.remove(super.find(key));
    }

    public Posting find(PathSegment id) {
        main.entity.PostingPK key = getPrimaryKey(id);
        return super.find(key);
    }

    @Override
    public List<Posting> findAll() {
        return super.findAll();
    }

    public List<Posting> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    public String countREST() {
        return String.valueOf(super.count());
    }
    
     public Long getAmountDocumentsByTerm(Integer termID)
    {
     
        Long amount = (Long)em.createNamedQuery("Posting.countByIdTermino")
                .setParameter("idTermino", termID)
                .getSingleResult();
        return amount;
    }
     
     public List<Posting> retrievePostingList(Integer termId)
    {
        
        List<Posting> resp = em.createNamedQuery("Posting.findByIdTerminoOrderByFrecuencia")
                .setParameter("idTermino", termId)
                .getResultList();
        if (resp.size() > 0) return resp;
        
        return null;
    }
     
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
