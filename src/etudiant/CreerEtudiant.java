
package etudiant;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;


public class CreerEtudiant {
    
    public static void main(String[] args) {
        try {
            CreerEtudiant.creerEtudiant();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public static void creerEtudiant() throws MalformedURLException, UnsupportedEncodingException, IOException, JSONException{
    
        URL url = new URL ("http://localhost/ApiM1/Etudiant/creerEtudiant.php");
        
        JSONObject params = new JSONObject();
        params.put("numEt", 5);
        params.put("nomEt", "Numero cinq");
        params.put("niveauEt", "M1");
        
        String valeur = params.toString();
        System.out.println(params);
        
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        conn.setDoInput(true); //
        conn.setDoOutput(true);
        
        OutputStream os = conn.getOutputStream();
        os.write(valeur.getBytes("UTF-8"));
        os.close();
        
        
        // Permet d'envoyer les Donnees
        if (conn.getResponseCode() != 503) {
            InputStream in = new BufferedInputStream(conn.getInputStream());
            String result = IOUtils.toString(in, "UTF-8");
            System.out.println(result);
        } else System.err.println("Alllllerrrrt !!!!!  Cet etudiant existe deja");

    }
}
