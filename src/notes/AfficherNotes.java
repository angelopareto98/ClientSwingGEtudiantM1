
package notes;

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
public class AfficherNotes {
    public static void main(String[] args) {
        try {
            AfficherNotes.afficherNotes();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    
    public static void afficherNotes() throws MalformedURLException, IOException, JSONException{
    
        String url = "http://localhost/ApiM1/Notes/afficherNotes.php";
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

    
        JSONArray jArray = (JSONArray) myResponse.getJSONArray("Notes des Etudiant");
        if (jArray != null) {
            int len = jArray.length();
            for (int i = 0; i < len; i++) {
                JSONObject notes = jArray.getJSONObject(i);
                String numInscription = notes.getString("numInscription");
                System.out.println("\n ******** Numero d'Inscription '" + numInscription + "' *********** ");
                String numEtud = notes.getString("numEt");
                System.out.println("Numero: "+numEtud);
                String codeMati = notes.getString("codeMat");
                System.out.println("Code Matiere: " + codeMati);
                double note = notes.getDouble("note");
                System.out.println("Note: " + note);
                

           } 
        }
    }
}
