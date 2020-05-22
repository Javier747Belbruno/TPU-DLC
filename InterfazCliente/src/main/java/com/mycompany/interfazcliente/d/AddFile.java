/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.interfazcliente.d;

import com.mycompany.interfazcliente.d.HTTP.httpGet;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author Javier
 */
public class AddFile extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
    
    FileItemFactory factory = new DiskFileItemFactory();
    ServletFileUpload upload = new ServletFileUpload(factory);


    // req es la HttpServletRequest que recibimos del formulario.
    // Los items obtenidos serán cada uno de los campos del formulario,
    // tanto campos normales como ficheros subidos.
    List items = upload.parseRequest(request);

    // Se recorren todos los items, que son de tipo FileItem
    for (Object item : items) {
       FileItem uploaded = (FileItem) item;

       // Hay que comprobar si es un campo de formulario. Si no lo es, se guarda el fichero
       // subido donde nos interese
       if (!uploaded.isFormField()) {
          // No es campo de formulario, guardamos el fichero en algún sitio
            String directoryName = System.getProperty("user.dir")+"/unprocessedfiles";
            File directory = new File(directoryName);
            if (! directory.exists()){
                directory.mkdir();
                // If you require it to make the entire directory path including parents,
                // use directory.mkdirs(); here instead.
            }
           String fileNameShort = uploaded.getName();
            if (fileNameShort != null) {
                fileNameShort = FilenameUtils.getName(fileNameShort);
            }
            File fichero = new File(System.getProperty("user.dir")+"/unprocessedfiles", fileNameShort);
         
          uploaded.write(fichero);
          
          request.setAttribute("cargaLocal", fichero.getAbsolutePath());
          
          String urlEncoded = URLEncoder.encode(fichero.getName(), "UTF-8");
          uploaded.delete();
          //Enviar Peticion para procesar.
          httpGet.httpGetResponseFile("http://localhost:8080/WebApplication5/webresources/file/"+urlEncoded);
          
       } else {
          // es un campo de formulario, podemos obtener clave y valor
          String key = uploaded.getFieldName();
          String valor = uploaded.getString();
       }
      }
     request.getRequestDispatcher("/loadDocument.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(AddFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(AddFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
