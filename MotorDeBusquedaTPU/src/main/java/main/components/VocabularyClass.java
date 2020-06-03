
package main.components;

import java.util.HashMap;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import main.entity.Posteo;



@ManagedBean
@ApplicationScoped
public class VocabularyClass {
    
 private static HashMap<String,Double> vocabulary; //-- Key IDTerm -- Value idf = log(N/nr).
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
     
List<Object[]> lt = pc.getAllTerms();
for (Object[] p : lt) {
    Double nr1;
    if(p[1] instanceof Long) 
         nr1 = ((Long) p[1]).doubleValue();
    double nr = ((Number)p[1]).doubleValue();//p[1].toString();//p.getFrecuencia().doubleValue();
    double N = amountDocuments.doubleValue();
    double idf = Math.log(N/nr);
    vocabulary.put(p[0].toString(), idf);
    }
 }
  
 public Long getAmountDocuments(){
    return amountDocuments;
 }
   

 public Double getIDF(String term){
    Double idf = vocabulary.get(term);
    return idf;
 }

 public HashMap<String,Double> getVocabulary(){
    return vocabulary;
 }
  
}