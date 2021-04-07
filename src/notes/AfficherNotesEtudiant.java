package notes;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author anghack
 */
public class AfficherNotesEtudiant {

    public static void main(String[] args) {
        try {
            AfficherNotesEtudiant.afficherNotesEtudiant();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void afficherNotesEtudiant() throws MalformedURLException, JSONException, IOException {

        URL url = new URL("http://localhost/ApiM1/Notes/afficherNotesEtudiant.php");

        JSONObject params = new JSONObject();
        params.put("numInscription", "33 H-F");  // variable

        String valeur = params.toString();
        System.out.println(params);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        conn.setDoInput(true); //
        conn.setDoOutput(true);

        // Permet d'ecrire les donnees
        OutputStream os = conn.getOutputStream();
        os.write(valeur.getBytes("UTF-8"));
        os.close();

        // Permet d'envoyer les Donnees
        InputStream in = new BufferedInputStream(conn.getInputStream());
        String result = IOUtils.toString(in, "UTF-8");
        System.out.println(result);

        // JSON to Object Java
        JSONObject jsObj = new JSONObject(result);
        int nb = 1;
        
        System.out.println("\nEtudiant numero: "+jsObj.getInt("numEt"));
        System.out.println("Nom: "+jsObj.getString("nomEt"));
        System.out.println("Classe: "+jsObj.getString("niveauEt"));

        JSONArray jsArr = (JSONArray) jsObj.getJSONArray("33 H-F"); // variable
        System.out.println("\nLes notes de " + "33 H-F");
        for (int i = 0; i < jsArr.length(); i++) {
            JSONObject notes = jsArr.getJSONObject(i);
            System.out.println("\n ******** Note n '" + nb++ + "' *********** ");

            String libelleMati = notes.getString("libelle matiere");
            System.out.println("Matiere: " + libelleMati);
            int note = notes.getInt("Notes");
            System.out.println("Note: " + note);
            String coefMati = notes.getString("Coefficient matiere");
            System.out.println("Coefficient: " + coefMati);
        }
        

    }

}
