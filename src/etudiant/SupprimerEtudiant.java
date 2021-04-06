/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etudiant;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author anghack
 */
public class SupprimerEtudiant {
    
    public static void main(String[] args) {
        try {
            SupprimerEtudiant.supprimerEtudiant();
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
    }

    public static void supprimerEtudiant() throws MalformedURLException, JSONException, IOException {

        URL url = new URL("http://localhost/ApiM1/Etudiant/supprimerEtudiant.php");

        JSONObject params = new JSONObject();
        params.put("numEt", 4);

        String valeur = params.toString();
        System.out.println(params);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("DELETE");
        conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        conn.setRequestProperty("Accept", "application/json");
        conn.setDoInput(true); 
        conn.setDoOutput(true);

        OutputStream os = conn.getOutputStream();
        os.write(valeur.getBytes("UTF-8"));
        os.close();

        InputStream in = new BufferedInputStream(conn.getInputStream());
        String result = IOUtils.toString(in, "UTF-8");

        JSONObject jsObj = new JSONObject(result);
        System.out.println("Message: " + jsObj.getString("message"));
    }
}
