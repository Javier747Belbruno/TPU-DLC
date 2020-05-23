
package main.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import main.components.InvertedIndexClass;
import main.components.PersistenceClass;
import main.components.RelevantDocumentsClass;
import main.components.Tokenizer;
import main.components.VocabularyClass;
import main.entity.Term;

@ManagedBean
@RequestScoped
public class QueryController {
    
    @Inject private VocabularyClass vc;
    @Inject private PersistenceClass pc;
    @Inject private RelevantDocumentsClass rd;
    @Inject private InvertedIndexClass ii;
  
public List<RelevantDocumentsClass.ListObject> retriveDocuments(String query,String r,String searchtype)
{
    int rint = Integer.parseInt(r);
    int stint = Integer.parseInt(searchtype);
    try {
            //Ask if HashTable vocabulary is empty, fill it   
        if(vc.IsEmpty())
            vc.doFillVocabulary();

         Tokenizer t= new Tokenizer();
        //ii.preparePostingList();//prepare postingList for a new Query.
        //split a string by space
        String[] splited = query.split("\\s+");
        for (String splitedString : splited) {
            HashSet<String> hst = t.getTerms(splitedString);//CleanAndRemoveStopWords.
            //Find for each term of query its IDNumber in DB
            for (String string : hst) {
            //Por cada termino generar la lista de posteo y luego ordenar
                Term te = pc.getTerm(string);
                if(te!=null){
                    //Insert
                    ii.setList(pc.getAllPostingByTerm(te.getIdTermino()));
                    //Order
                    ii.OrderPostingLists();
                }
            }
        }
    //Damos la responsabilidad a la clase documentosRelevantes a que arme su lista.
    rd.addDocuments(rint,ii.getListofPostingList(),stint);
    } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
    }
    //Devolver lista de r documentos.
    return rd.getLD(rint);
    }
}
