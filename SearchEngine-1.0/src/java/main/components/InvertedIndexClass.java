package main.components;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import main.entity.Posting;


@ManagedBean
@RequestScoped
public class InvertedIndexClass {
  
    
private static List<List<Posting>> postingLists; // Posting List, x Postings by x Terms. Only lives in query time.
 
 
@Inject private VocabularyClass vc;
 
public InvertedIndexClass() {
    postingLists = new ArrayList<>();
}
    
public void preparePostingList() {
    postingLists = new ArrayList<>();
}
 
 
public void setList(List<Posting> pl){
    postingLists.add(pl);
}
 
public List<List<Posting>> getListofPostingList(){
    return postingLists;
}
 
//Hace la magia de ordenar la lista de las listas de posteo por idf de los terminos.
//Comparamos S2 con S1 y de esa forma nos lo ordena de la forma que queremos.
public void OrderPostingLists(){
    Collections.sort(postingLists,new Comparator<List<Posting>>(){
                     @Override
                     public int compare(List<Posting> s1,List<Posting> s2){
                        return vc.getVocabulary().get(s2.get(0).getPostingPK().getIdTermino()).compareTo(vc.getVocabulary().get(s1.get(0).getPostingPK().getIdTermino()));
                      }
                    });
}
 
public void doPrintPostingLists(){
    for (List<Posting> postingList : postingLists) {
        System.out.println("Palabra: " + postingList.get(0).getPostingPK().getIdTermino() 
                 + "idf " + vc.getVocabulary().get(postingList.get(0).getPostingPK().getIdTermino()));
         
         
        for (Posting posting : postingList) {
             System.out.println(" Documento: " + posting.getPostingPK().getIdDocumento() 
                     + "Frecuencia del termino " + posting.getFrecuencia());
        }
    }  
}

}