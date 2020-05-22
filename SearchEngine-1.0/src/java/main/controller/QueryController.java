/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import main.components.InvertedIndexClass;
import main.components.PersistenceClass;
import main.components.RelevantDocumentsClass;
import main.components.Tokenizer;
import main.components.VocabularyClass;
import main.entity.Term;

/**
 *
 * @author Javier
 */
@ManagedBean
@RequestScoped
public class QueryController {
    
//manejo de la query.
 
  @Inject private VocabularyClass vc;
  @Inject private PersistenceClass pc;
  @Inject private RelevantDocumentsClass rd;
  @Inject private InvertedIndexClass ii;
  
    public List<RelevantDocumentsClass.ListObject> retriveDocuments(String query,String r,String searchtype)
    {
        int rint = Integer.parseInt(r);
        Boolean stBoolean = Boolean.parseBoolean(searchtype);
    
         try {
          
        //Ask if HashTable vocabulary is empty, fill it   
        if(vc.IsEmpty())
            vc.doFillVocabulary();
        
        Tokenizer t= new Tokenizer();
        ii.preparePostingList();//prepare postingList for a new Query.
        
        //split a string by space
        String[] splited = query.split("\\s+");
        HashMap<Integer,Double> termHM = new HashMap<>();//First IDTerm, and its idf.
        for (String splitedString : splited) {
           HashSet<String> hst = t.getTerms(splitedString);//CleanAndRemoveStopWords.
            //Find for each term of query its IDNumber in DB
            
            for (String string : hst) {
            //Busco en dataBase y guardo Objeto.
            
                Term te = pc.getTerm(string);
                if(te!=null)
                {
                    //Insert
                    ii.setList(pc.getAllPostingByTerm(te.getIdTermino()));
                    //Order
                    ii.OrderPostingLists();
                }
        }
      }
        
        rd.addDocuments(rint,ii.getListofPostingList(),stBoolean);
        
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
          return rd.getLD(rint);
    }
}
