
package main.components;

import java.util.HashMap;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import main.entity.Term;


@ManagedBean
@ApplicationScoped
public class VocabularyClass {
    
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
 }
  
 public Long getAmountDocuments(){
    return amountDocuments;
 }
   

 public Double getIDF(int TermID){
    Double idf = vocabulary.get(TermID);
    return idf;
 }

 public HashMap<Integer,Double> getVocabulary(){
    return vocabulary;
 }
  
}