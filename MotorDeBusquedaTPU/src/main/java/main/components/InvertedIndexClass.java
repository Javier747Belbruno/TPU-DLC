package main.components;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import main.entity.Posteo;



@ManagedBean
@RequestScoped
public class InvertedIndexClass {
  
    
private static List<List<Posteo>> postingLists; // Posting List, x Postings by x Terms. Only lives in query time.

@Inject PersistenceClass pc;
@Inject VocabularyClass vc;
 
public InvertedIndexClass() {
    postingLists = new ArrayList<>();
}
    
public void preparePostingList() {
    postingLists = new ArrayList<>();
}



 
public void setInvertedIndex(List<Posteo> pl){
    postingLists.add(pl);
}

 
public List<List<Posteo>> getListofPostingList(){
    return postingLists;
}
 
//Hace la magia de ordenar la lista de las listas de posteo por idf de los terminos.
//Comparamos S2 con S1 y de esa forma nos lo ordena de la forma que queremos.
public void OrderPostingLists(){
    Collections.sort(postingLists,new Comparator<List<Posteo>>(){
                     @Override
                     public int compare(List<Posteo> s1,List<Posteo> s2){
                        return vc.getVocabulary().get(s2.get(0).getPosteoPK().getPalabra()).compareTo(vc.getVocabulary().get(s1.get(0).getPosteoPK().getPalabra()));
                      }
                    });
}
 
public void doPrintPostingLists(){
    for (List<Posteo> postingList : postingLists) {
        System.out.println("Palabra: " + postingList.get(0).getPosteoPK().getPalabra()
                 + "idf " + vc.getVocabulary().get(postingList.get(0).getPosteoPK().getPalabra()).toString());
         
         
        for (Posteo posting : postingList) {
             System.out.println(" Documento: " + posting.getPosteoPK().getDocumento()
                     + "Frecuencia del termino " + posting.getFrecuencia());
        }
    }  
}

}