/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.interfazcliente.d;

import com.mycompany.interfazcliente.d.HTTP.httpGet;
import java.io.IOException;
import java.io.InputStream;

import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.http.HttpResponse;
import java.util.Collection;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sun.net.www.http.HttpClient;

/**
 *
 * @author Javier
 */
public class Buscar extends HttpServlet {

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
            throws ServletException, IOException {
        
       String palabra = request.getParameter("txt_buscar");
        String ts = request.getParameter("typeSearch");
        String ar =  request.getParameter("amountRetrive");
        System.out.println("typeSearch = " +ts + " amountRetrive = " + ar );
        String urlEncoded = URLEncoder.encode(palabra, "UTF-8");
       
        if(ts.isBlank() || ts.isEmpty() || ar.isBlank() || ar.isEmpty()){
            ts = "true";
            ar = "10";
        }
        
        httpGet.ListObject[] m = httpGet.get("http://localhost:8080/WebApplication5/webresources/results/"+urlEncoded+"&"+ar+"&"+ts);
        
        request.setAttribute("palabra", palabra);
       //Object lista = m.get("lista");
        

        request.setAttribute("lista", m);
        
        
        //This servlet control the view flows.
        request.getRequestDispatcher("/results.jsp").forward(request, response);
        
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
        processRequest(request, response);
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
        processRequest(request, response);
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
