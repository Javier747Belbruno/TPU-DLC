
package main.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import main.dao.PosteoDao;
import main.entity.Posteo;
import main.entity.PosteoPK;



@ManagedBean
@RequestScoped
public class PersistenceClass {
    
   @EJB private PosteoDao pdao;
    
public void LoadDataInDB(String nameDocument,HashMap<String,Integer> terms){
    
    List<Posteo> entityList = new ArrayList();
       for (String key : terms.keySet()) {
        
           //persist a Posting
           Posteo p = new Posteo();
           p.setPosteoPK(new PosteoPK(nameDocument,key));
           p.setFrecuencia(terms.get(key));
           entityList.add(p);
       }
       pdao.create(entityList);
    }
    
    public List<Posteo> getAllPosting(){
    return pdao.findAll();
    }
    /*
    public Term getTerm(String term){
    return tdao.retrieveTerm(term);
    }
    
    */
    public Long getAmountDocuments(){
    return pdao.getAmountDocuments();
    }
    
   

    public List<Object[]> getAllTerms(){
    return pdao.getAllTerms();
    }
    
    public Long getAmountDocumentsByTerm(String term){
    return pdao.getAmountDocumentsByTerm(term);
    }
    
    public List<Posteo> getAllPostingByTerm(String term){
    return pdao.retrievePostingList(term);
    }

}