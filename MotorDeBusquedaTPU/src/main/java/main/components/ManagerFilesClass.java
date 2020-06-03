package main.components;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/* This class manages directories and files*/
@ManagedBean
@RequestScoped
public class ManagerFilesClass {
  
    @Inject PersistenceClass pc;
    @Inject FileClass fc;
    
    public String ProcessFiles(File[] f){
        StringBuilder response = new StringBuilder();  
        for (File file : f) {
             try {
               HashMap<String,Integer> terms = fc.readFileAndReturnTerms(file.getCanonicalPath());
               String nameDocument = file.getName();
               //We got nameDocument,Terms and their frequencies,We are already to push data to db.
               pc.LoadDataInDB(nameDocument,terms);

               response.append("Document processed ").append(nameDocument).append(" Time: ").append(java.time.LocalDateTime.now()).append(" "); 
               //MoveFile To processedFiles.
               response.append(moveFile("unprocessedfiles/"+file.getName(), "unprocessedfiles/processedFiles/" + file.getName()));
            } catch (IOException e) {
                response.append(e.getMessage());
            } catch (Exception ex) {
                response.append(ex.getMessage());
            }
        }
        return response.toString();
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
    
    
     private String moveFile(String src, String dest ) throws Exception {
      Path result = null;
      StringBuilder response = new StringBuilder();  
      try {
        String directoryName = System.getProperty("user.dir")+"/unprocessedfiles/processedFiles";
            File directory = new File(directoryName);
            if (! directory.exists()){
                directory.mkdir();
               throw new Exception("El directorio del archivo a procesar no existe");
            }
        result =  Files.move(Paths.get(src), Paths.get(dest),StandardCopyOption.REPLACE_EXISTING);
      } catch (IOException e) {
        response.append("Exception while moving file: ").append(e.getMessage());
      }
      if(result != null) {
        response.append("File moved successfully.");
      }else{
        response.append("File movement failed.");
      }  
      return response.toString();
   }

}
