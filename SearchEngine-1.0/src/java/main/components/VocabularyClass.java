/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.components;


import java.util.HashMap;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import main.entity.Term;



/**
 *
 * @author Javier
 */
@ManagedBean
@ApplicationScoped
public class VocabularyClass {
     //all attributes from Inverted Index statics because one instance of attributes.   
 
//@EJB private PersistenceClass pc;
    
 private static HashMap<Integer,Double> vocabulary; //-- Key IDTerm -- Value idf = log(N/nr).
 private static Long amountDocuments; //How many documents we have in db
 
 @Inject PersistenceClass pc;
         
 private void doCountDocuments() {
     
        amountDocuments = pc.getAmountDocuments();
    }
 
 
 public VocabularyClass(){
    vocabulary = new HashMap<>();
    amountDocuments = 0L;
}
         
  public boolean IsEmpty() {
     boolean b = false;
     if(vocabulary.isEmpty())
         return true;
     return b;
}
  
 public void doFillVocabulary() {
    
     doCountDocuments();//set amountDocuments
     
     List<Term> lt = pc.getAllTerms();
     for (Term term : lt) {
         double nr = pc.getAmountDocumentsByTerm(term.getIdTermino()).doubleValue();
         double N = amountDocuments.doubleValue();
         double idf = Math.log(N/nr);
         
         vocabulary.put(term.getIdTermino(), idf);
 
     }
     System.out.println("Tama;o del vocab " + vocabulary.size());
}
  
   public Long getAmountDocuments(){
 return amountDocuments;
 }
   
    //Calculo su indice de relevancia de una Palabra
 public Double getIDF(int TermID){
   Double idf = vocabulary.get(TermID);
 return idf;
 }

  public HashMap<Integer,Double> getVocabulary(){
    return vocabulary;
 }
 
}