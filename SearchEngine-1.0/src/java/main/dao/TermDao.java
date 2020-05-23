
package main.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import main.entity.Term;


@Stateless
public class TermDao extends AbstractDao<Term> {

    @PersistenceContext(unitName = "WebApplication5PU")
    private EntityManager em;

    public TermDao() {
        super(Term.class);
    }


    @Override
    public Term create(Term entity) {
        return super.create(entity);
    }


    public void edit(Integer id, Term entity) {
        super.edit(entity);
    }


    public void remove(Integer id) {
        super.remove(super.find(id));
    }


    public Term find(Integer id) {
        return super.find(id);
    }


    @Override
    public List<Term> findAll() {
        return super.findAll();
    }


    public List<Term> findRange(Integer from, Integer to) {
        return super.findRange(new int[]{from, to});
    }


    public String countREST() {
        return String.valueOf(super.count());
    }
    

    public Integer retrieve(String term)
    {
        List<Term> resp = em.createNamedQuery("Term.findByPalabra")
                .setParameter("palabra", term)
                .getResultList();
        if (resp.size() == 1) return resp.get(0).getIdTermino();
        
        return null;
    }
    
    public Term retrieveTerm(String term)
    {
        List<Term> resp = em.createNamedQuery("Term.findByPalabra")
                .setParameter("palabra", term)
                .getResultList();
        if (resp.size() == 1) return resp.get(0);
        
        return null;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
