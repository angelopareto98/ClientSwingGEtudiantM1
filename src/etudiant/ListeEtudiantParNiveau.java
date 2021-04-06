package etudiant;

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

public class ListeEtudiantParNiveau {

    public static void main(String[] args) {
        try {
            ListeEtudiantParNiveau.listeEtudiantParNiveau();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void listeEtudiantParNiveau() throws MalformedURLException, IOException, JSONException {
        URL url = new URL("http://localhost/ApiM1/Etudiant/ListeEtudiantParNiveau.php");

        JSONObject params = new JSONObject();
        params.put("niveauEt", "M1");

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
        
        JSONArray jsArr = (JSONArray) jsObj.getJSONArray("M1");
        System.out.println("\nLes Etudiants de la classe sont: ");
        for (int i = 0; i < jsArr.length(); i++) {
            System.out.println(jsArr.get(i));
        }
        
        
    

    }
}
