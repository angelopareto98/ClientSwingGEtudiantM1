
package matiere;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author anghack
 */
public class AfficherMatiere {
    
    public static void main(String[] args) {
        try {
            AfficherMatiere.afficherMatiere();
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
    }
    
    
    public static void afficherMatiere() throws MalformedURLException, IOException, JSONException{
    
        String url = "http://localhost/ApiM1/Matiere/afficherMatiere.php";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        System.out.println("Response code : "+responseCode);
        
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
            
        }
        in.close();
        
        System.out.println("\n"+response.toString());
        
        
        
        System.out.println("\nJSON en Objet Java:");
        JSONObject myResponse = new JSONObject(response.toString());

    
        JSONArray jArray = (JSONArray) myResponse.getJSONArray("matiere");
        if (jArray != null) {
            int len = jArray.length();
            for (int i = 0; i < len; i++) {
                JSONObject mat = jArray.getJSONObject(i);
                String codeMati = mat.getString("codeMat");
                System.out.println("\n ******** Code matiere '" + codeMati + "' *********** ");
                String libelleMati = mat.getString("libelleMat");
                System.out.println("Libelle: " + libelleMati);
                int coefMati = mat.getInt("coefMat");
                System.out.println("Niveau: " + coefMati);
                

           } 
        }
    }
}
