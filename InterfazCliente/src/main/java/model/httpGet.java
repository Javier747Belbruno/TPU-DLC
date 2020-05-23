
package model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;



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
        return pp1;
        } catch (MalformedURLException ex) {
            Logger.getLogger(httpGet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
 }
    

public static String httpGetResponseFile(String urlString) throws IOException{
    StringBuilder mensajeRespuesta = new StringBuilder();
    URL url;
    try {
        url = new URL(urlString);
        URLConnection conn = url.openConnection();
        InputStream is = conn.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> Map = mapper.readValue(is, new TypeReference<Map<String, Object>>() {
        });
        for (Entry<String, Object> entry : Map.entrySet()) {
           mensajeRespuesta.append(entry.getValue());
        }
    } catch (Exception e) {
        mensajeRespuesta.append(e.getMessage());
    }
    return mensajeRespuesta.toString();
}

public static List<String> getDocument(String urlString) throws IOException{
    URL url;
    try {
        url = new URL(urlString);
        URLConnection conn = url.openConnection();
        InputStream is = conn.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
       // convert JSON array to List
        List<String> a = mapper.readValue(is, List.class);
        return a;
        } catch (MalformedURLException ex) {
            Logger.getLogger(httpGet.class.getName()).log(Level.SEVERE, null, ex);
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
    
    
   

