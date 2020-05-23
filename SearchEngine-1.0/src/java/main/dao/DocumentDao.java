
package main.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import main.entity.Document;

@Stateless
public class DocumentDao extends AbstractDao<Document> {

    @PersistenceContext(unitName = "WebApplication5PU")
    private EntityManager em;

    public DocumentDao() {
        super(Document.class);
    }

    @Override
    public Document create(Document entity) {
       return super.create(entity);   
    }

    public void edit(Integer id, Document entity) {
        super.edit(entity);
    }

    public void remove(Integer id) {
        super.remove(super.find(id));
    }

    public Document find( Integer id) {
        return super.find(id);
    }

    @Override
    public List<Document> findAll() {
        return super.findAll();
    }

    public List<Document> findRange(Integer from, Integer to) {
        return super.findRange(new int[]{from, to});
    }

    public String countREST() {
        return String.valueOf(super.count());
    }
    
    public Long getAmountDocuments()
    {
        Long amount = (Long)em.createNamedQuery("Document.getCount").getSingleResult();
        return amount;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
