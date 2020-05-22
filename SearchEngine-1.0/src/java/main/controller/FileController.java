/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.controller;

import java.io.File;
import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import main.components.ManagerFilesClass;

/**
 *
 * @author Javier
 */
@ManagedBean
@RequestScoped
public class FileController {
    
    @Inject private ManagerFilesClass mfc;
            
    public String procesarFile(String path){
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

            File[] f = new File[1];
            f[0] = file;
            mfc.ProcessFiles(f);
            
        } catch (Exception e) {
            return e.getMessage();
        }
           

        return "Archivo Procesado Exitosamente";
    }
}
