/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.PathSegment;
import main.entity.Posteo;
import main.entity.PosteoPK;


@Stateless
public class PosteoDao extends AbstractDao<Posteo> {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    public PosteoPK getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;documento=documentoValue;palabra=palabraValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        main.entity.PosteoPK key = new main.entity.PosteoPK();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> documento = map.get("documento");
        if (documento != null && !documento.isEmpty()) {
            key.setDocumento(documento.get(0));
        }
        java.util.List<String> palabra = map.get("palabra");
        if (palabra != null && !palabra.isEmpty()) {
            key.setPalabra(palabra.get(0));
        }
        return key;
    }

    public PosteoDao() {
        super(Posteo.class);
    }



    public void create(List<Posteo> entity) {
        int i=0;
        for (Posteo posteo : entity) {
            getEntityManager().persist(posteo);
            if ((i > 0) && (i % 20 == 0)) { // Flush in batches of 20 to keep caches from bogging.
                getEntityManager().flush();
                getEntityManager().clear();
            }
            i++;
        }
    }


    public void edit(PathSegment id, Posteo entity) {
        super.edit(entity);
    }


    public void remove(PathSegment id) {
        main.entity.PosteoPK key = getPrimaryKey(id);
        super.remove(super.find(key));
    }


    public Posteo find(PathSegment id) {
        main.entity.PosteoPK key = getPrimaryKey(id);
        return super.find(key);
    }


    @Override
    public List<Posteo> findAll() {
        return super.findAll();
    }


    public List<Posteo> findRange(Integer from,Integer to) {
        return super.findRange(new int[]{from, to});
    }


    public String countREST() {
        return String.valueOf(super.count());
    }
    
    
    
     public Long getAmountDocumentsByTerm(String term)
    {
        Long amount = (Long)em.createNamedQuery("Posteo.countByTermino")
                .setParameter("palabra", term)
                .getSingleResult();
        return amount;
    }
     
     public List<Posteo> retrievePostingList(String term)
    {
        
        List<Posteo> resp = em.createNamedQuery("Posteo.findByTerminoOrderByFrecuencia")
                .setParameter("palabra", term)
                .getResultList();
        if (resp.size() > 0) return resp;
        
        return null;
    }
     public Long getAmountDocuments() {
         Long amount = (Long)em.createNamedQuery("Posteo.countDocuments")
                 .getSingleResult();
        return amount;
    }
     
    public List<Object[]> getAllTerms() {
     
       try{
           
             List<Object[]> lp = em.createNamedQuery("Posteo.getTerms")
                .getResultList();
            return lp;
       }
       
       catch(Exception e){
           System.out.println(e);
       }
                
             return null;         
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public static class Termino {
        private String palabra;
        private Long cantidadDoc;

        public Termino() {
        }

        public Termino(String palabra, Long cantidadDoc) {
            this.palabra = palabra;
            this.cantidadDoc = cantidadDoc;
        }

        public String getPalabra() {
            return palabra;
        }

        public Long getCantidadDoc() {
            return cantidadDoc;
        }

        public void setPalabra(String palabra) {
            this.palabra = palabra;
        }

        public void setCantidadDoc(Long cantidadDoc) {
            this.cantidadDoc = cantidadDoc;
        }
       
    }

   

   
    
}
