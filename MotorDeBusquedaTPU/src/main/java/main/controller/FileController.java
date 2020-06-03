
package main.controller;


import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import main.components.ManagerFilesClass;



@ManagedBean
@RequestScoped
public class FileController {
    
    @Inject private ManagerFilesClass mfc;
      
    
    public String procesarFile(String path){
        
        StringBuilder response = new StringBuilder();  
        try {
            String directoryName = System.getProperty("user.dir")+"/unprocessedfiles";
            String fileName = directoryName+"/"+path;
            File directory = new File(directoryName);
            File file = new File(fileName);
            if (! directory.exists()){
                directory.mkdir();
               throw new Exception("El directorio del archivo a procesar no existe");
            }
            if(!file.exists() ){
                 throw new Exception("El archivo a procesar no existe");
            }
            String directoryNameProcessed = System.getProperty("user.dir")+"/unprocessedfiles/processedFiles";
            String fileNameProcessed = directoryNameProcessed+"/"+path;
            File fileProcessed = new File(fileNameProcessed);
            if(fileProcessed.exists())
            {
                 throw new Exception("El archivo ya se ha procesado.");
            }
            File[] f = new File[1];f[0] = file;
            response.append(mfc.ProcessFiles(f));
            //vc.doFillVocabulary();//Actualizar vocab.
        } catch (Exception e) {
            response.append(e.getMessage());
        }
        return response.toString();
    }
    

    public List<String> devolverFile(String path){
        
        List<String> list = new LinkedList<>();
        try {
            String directoryNameProcessed = System.getProperty("user.dir")+"/unprocessedfiles/processedFiles";
            String fileNameProcessed = directoryNameProcessed+"/"+path;
            File fileProcessed = new File(fileNameProcessed);
            if(!fileProcessed.exists())
            {
                 throw new Exception("Error: El archivo en la carpeta /processedFiles no se encuentra para visualizar.");
            }
            Scanner scan = new Scanner(fileProcessed);
            while (scan.hasNextLine())
                list.add(scan.nextLine());
        } catch (Exception e) {
            list.add(e.getMessage());
        }
        return list;
    }
    
    
    public String generarJsonRespuesta(String r){
        String rSinCaracteresInvalidos  = r.replaceAll("[^a-zA-Z0-9\\.\\- /]", "_");
        return "{\"data\": \""+rSinCaracteresInvalidos+"\"}";
    }
}
