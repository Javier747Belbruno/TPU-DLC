/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.components;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import main.dao.DocumentDao;
import main.dao.PostingDao;
import main.dao.TermDao;
import main.entity.Document;
import main.entity.Posting;
import main.entity.PostingPK;
import main.entity.Term;

/**
 *
 * @author Javier
 */
@ManagedBean
@RequestScoped
public class PersistenceClass {
    
   @EJB private DocumentDao ddao;
   @EJB private TermDao tdao;
   @EJB private PostingDao pdao;
    
public void LoadDataInDB(String nameDocument,HashMap<String,Integer> terms){
    Document d = new Document(nameDocument);    
    //DocumentDao ddao = new DocumentDao();
    int saveIDDoc = ddao.create(d).getIdDocumento();//Create and return the object.
    
     Iterator it = terms.keySet().iterator();
       while(it.hasNext()){
        String key = (String) it.next();
        
        int saveIDterm;
        
        if(tdao.retrieve(key)!=null){
        saveIDterm = tdao.retrieve(key);  
        }else{
        Term t = new Term(key);    
        saveIDterm = tdao.create(t).getIdTermino();//Create and return the object.
        }
        
        Posting p = new Posting(new PostingPK(saveIDDoc,saveIDterm),terms.get(key));
        pdao.create(p);
        }
    }
    
    public List<Posting> getAllPosting(){
    return pdao.findAll();
    }
    
    public Term getTerm(String term){
    return tdao.retrieveTerm(term);
    }
    
    
    public Long getAmountDocuments(){
    return ddao.getAmountDocuments();
    }
    
    
    public Document getDocument(Integer DocumentID){
    return ddao.find(DocumentID);
    }
    
    public List<Term> getAllTerms(){
    return tdao.findAll();
    }
    
    public Long getAmountDocumentsByTerm(Integer termID){
    return pdao.getAmountDocumentsByTerm(termID);
    }
    
    public List<Posting> getAllPostingByTerm(Integer termID){
    return pdao.retrievePostingList(termID);
    }
}