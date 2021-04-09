package etudiant;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import static java.lang.System.in;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author anghack
 */
public class RecherherEtudiantNumNom {

    public static void main(String[] args) {
        try {
            RecherherEtudiantNumNom.recherherEtudiantNumNom();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void recherherEtudiantNumNom() throws IOException, JSONException {
        URL url = new URL("http://localhost/ApiM1/Etudiant/recherherEtudiantNumNom.php");
        
        System.out.println("Entrer le numero de l'Etudiant a chercher: ");
        Scanner sc = new Scanner(in);
        int n = sc.nextInt();

        JSONObject params = new JSONObject();
        params.put("numEt", n);

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

        JSONArray jsArr = (JSONArray) jsObj.getJSONArray("etudiant");
        System.out.println("\nLes infos sur ce numero sont: ");
        for (int i = 0; i < jsArr.length(); i++) {
            JSONObject etud = jsArr.getJSONObject(i);
            String numeroEtud = etud.getString("Numero d'Etudiant");
            System.out.println("Numero: " + numeroEtud);
            String nomEtud = etud.getString("Nom");
            System.out.println("Nom: " + nomEtud);
            String niveauEtud = etud.getString("Niveau");
            System.out.println("Niveau: " + niveauEtud);
        }
    }
}
