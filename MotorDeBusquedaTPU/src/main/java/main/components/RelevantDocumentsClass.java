
package main.components;



import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import main.entity.Posteo;

@ManagedBean
@RequestScoped
public class RelevantDocumentsClass {
    
  private LinkedHashMap<String,Double> LD; //Document Name - Ir 
  
  

  @Inject PersistenceClass pc;
  @Inject VocabularyClass vc;
  
  public RelevantDocumentsClass(){
    LD = new LinkedHashMap<>();
  }

  private void cleanList(){
    LD = new LinkedHashMap<>();
  }
  
  
public void addDocuments(Integer r, List<List<Posteo>> llp,Integer searchtype) {
    cleanList();
    //Change r if search Type == false. Give a big number.
    if(searchtype==0)
        r=5000;
    
    for (List<Posteo> list : llp) {
        //Tomamos de la lista el primer posting ya que cualquiera de los posting tienen el mismo id termino.
        Double idf = vc.getIDF(list.get(0).getPosteoPK().getPalabra());
        for (Posteo posting : list) {

            if(list.indexOf(posting)< r){
                Double ir = idf * posting.getFrecuencia();
                String doc = posting.getPosteoPK().getDocumento();

                if(LD.containsKey(doc)){
                    LD.replace(doc, LD.get(doc) + ir); //Sumamos si documento ya existe en la lista.
                }else{
                 LD.put(doc, ir);
                }
            }else{
                break;//Cortamos si ya paso los R documentos
            }
        }
    }
}

 
 public List<ListObject> getLD(int r){
    //Obtener la lista ordenada.
    List<ListObject> l = new ArrayList<>();
    Iterator it = LD.keySet().iterator();
    int i=0;
       while(it.hasNext() && i < r){
           String key = (String) it.next();
           String nameDocument = key;
           ListObject io = new ListObject(nameDocument,LD.get(key));
           l.add(io);
           i++;
           }
    OrderList(l);
    return l;
    
       //Probar Lista en pantalla
//     System.out.println("");
//     System.out.println("Lista de " + r + " documentos." );
//     for (ListObject listObject : l) {
//         System.out.println("Document: -> " + listObject.getDocument() );
//         System.out.println("Indice de Relevancia : -> " + listObject.getIr() );
//     }
 
}
 
//Ordenar Lista comparando su indice de relevancia.
private void OrderList(List<ListObject> l){
    Collections.sort(l,new Comparator<ListObject>(){
        @Override
        public int compare(ListObject o1, ListObject o2) {
           return o2.getIr().compareTo(o1.getIr());
        }
  });
}

 
public class ListObject{//Objeto Auxiliar para pasar de LinkedHashMap a una lista Ordernada.
    
   private String document;
   private Double ir;
   
   public ListObject(String documentP,Double irP){
    this.document=documentP;
    this.ir=irP;
   }
   
   public String getDocument(){
      return document;
   }
   
   public Double getIr(){
       return ir;
   }

}

}
