package main.components;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;

/* This class manages one file*/
@ManagedBean
@RequestScoped
public class FileClass {
    

 
 /** Read one File And Return a String list of Terms
     * @param  filename. 
     * @return HashMap<String,Integer>
     * @throws java.io.IOException*/ 
 public HashMap<String,Integer> readFileAndReturnTerms(String filename) throws IOException {
      
    HashMap<String,Integer> terms = new HashMap<>(); //What I expect from this method.
    Tokenizer t = new Tokenizer();
    Scanner s = new Scanner(new File(filename));    
    while(s.hasNext ())
    {
      HashSet<String> TermsForEachRow = t.getTerms(s.next());
      TermsForEachRow.forEach((string) -> {
          
                if(terms.containsKey(string)){
                    terms.replace(string, terms.get(string)+1); //update word freq.
                }else{
                terms.put(string,1); //add word in hashtable.
                        } 
      });
    }
    /*
    FOR CHECK IF IT GOES WELL
    int sum = 0;
      Iterator it = terms.keySet().iterator();
       while(it.hasNext()){
          String key = (String) it.next();
          System.out.println("Key: " + key + " -> Value: " + terms.get(key));
           sum = sum +  terms.get(key);}
       System.out.println("sum = " + sum);
    */
   return terms; 
 }

}

      
        
        
        
 
