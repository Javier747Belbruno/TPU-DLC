package main.components;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/* This class manages directories and files*/
@ManagedBean
@RequestScoped
public class ManagerFilesClass {
  
    @Inject PersistenceClass pc;
    @Inject FileClass fc;
    
    public void ProcessFiles(File[] f){
        
        for (File file : f) {
            
             try {
               HashMap<String,Integer> terms = fc.readFileAndReturnTerms(file.getCanonicalPath());
               String nameDocument = file.getName();
               
                //We got nameDocument,Terms and their frequencies. 
                //We are already to push data to db.
                 System.out.println("Entro a base con el archivo");
                 
                 
               pc.LoadDataInDB(nameDocument,terms);
               
               
               System.out.println("Document processed " + nameDocument + "..." + java.time.LocalDateTime.now()); 
               
               //MoveFile To processedFiles.
               
               moveFile("unprocessedfiles/"+file.getName(), "unprocessedfiles/processedFiles/" + file.getName());
               
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } catch (Exception ex) {
                Logger.getLogger(ManagerFilesClass.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
      //CHECK IF EXISTS FILE TO PROCESS.
    public File[] SearchDocuments()throws IOException {
        
        File f = new File("documents");//folder
        
        FilenameFilter textFilter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".txt");
            }
        };
        File[] files = f.listFiles(textFilter);
        return files;
    }
    
     private void moveFile(String src, String dest ) throws Exception {
      Path result = null;
      
           
      try {
        String directoryName = System.getProperty("user.dir")+"/unprocessedfiles/processedFiles";
            File directory = new File(directoryName);
            if (! directory.exists()){
                directory.mkdir();
                
               throw new Exception("El directorio del archivo a procesar no existe");
            }
         result =  Files.move(Paths.get(src), Paths.get(dest),StandardCopyOption.REPLACE_EXISTING);
         
      } catch (IOException e) {
         System.out.println("Exception while moving file: " + e.getMessage());
      }
      if(result != null) {
         System.out.println("File moved successfully.");
      }else{
         System.out.println("File movement failed.");
      }  
   }

}
