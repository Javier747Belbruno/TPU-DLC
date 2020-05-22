/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.interfazcliente.d.HTTP;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import scala.util.parsing.json.JSONArray;
import scala.util.parsing.json.JSONObject;




/**
 *
 * @author Javier
 */
public class httpGet {
    public static ListObject[] get(String urlString) throws IOException{
        URL url;
        try {
            url = new URL(urlString);
            URLConnection conn = url.openConnection();
            InputStream is = conn.getInputStream();
            ObjectMapper mapper = new ObjectMapper();
           // convert JSON array to Array objects
            ListObject[] pp1 = mapper.readValue(is, ListObject[].class);
            for (ListObject item : pp1) {
                System.out.println(item.toString());
            }
            return pp1;
        } catch (MalformedURLException ex) {
            Logger.getLogger(httpGet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }
    

    public static String httpGetResponseFile(String urlString) throws IOException{
        URL url;
        try {
            url = new URL(urlString);
            URLConnection conn = url.openConnection();
            InputStream is = conn.getInputStream();

           
            ObjectMapper mapper = new ObjectMapper();
      
            Map<String, Object> carMap = mapper.readValue(is, new TypeReference<Map<String, Object>>() {
            });

            for (Entry<String, Object> entry : carMap.entrySet()) {
                System.out.println("key=" + entry.getKey() + " and value=" + entry.getValue());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    


   
    public static class ListObject{
    
    private String document;
    private Double ir;

        public String getDocument() {
            return document;
        }

        public Double getIr() {
            return ir;
        }

        public void setDocument(String document) {
            this.document = document;
        }

        public void setIr(Double ir) {
            this.ir = ir;
        }

        @Override
        public String toString() {
            return "ListObject{" + "document=" + document + ", ir=" + ir + '}';
        }

   
      

       
        
    }

       
    
    
    
    }
    
    
   

