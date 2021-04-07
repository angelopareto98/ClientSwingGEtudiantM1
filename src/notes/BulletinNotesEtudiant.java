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
public class BulletinNotesEtudiant {

    public static void main(String[] args) {
        try {
            BulletinNotesEtudiant.bulletinNotesEtudiant();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void bulletinNotesEtudiant() throws MalformedURLException, JSONException, IOException {

        URL url = new URL("http://localhost/ApiM1/Notes/bulletinNotesEtudiant.php");

        JSONObject params = new JSONObject();
        params.put("numInscription", "686 H-F");  //  variable

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

        JSONArray jsArr = (JSONArray) jsObj.getJSONArray("686 H-F");  // variable
        System.out.println("\nLes notes de " + "686 H-F"); //variable
        for (int i = 0; i < jsArr.length(); i++) {
            JSONObject notes = jsArr.getJSONObject(i);
            System.out.println("\n ******** Note n '" + nb++ + "' *********** ");

            int numEtud = notes.getInt("Numero d'Etudiant");
            System.out.println("Numero: " + numEtud);
            String nomEtud = notes.getString("Nom");
            System.out.println("Nom: " + nomEtud);
            String niveauEtud = notes.getString("Niveau");
            System.out.println("Niveau: " + niveauEtud);
            String libelleMati = notes.getString("libelle matiere");
            System.out.println("Matiere: " + libelleMati);

            String coefMati = notes.getString("Coefficient matiere");
            System.out.println("Coefficient: " + coefMati);
            int note = notes.getInt("Notes");
            System.out.println("Note: " + note);
            int notePond = notes.getInt("Note Ponderee");
            System.out.println("Note ponderee: " + notePond);
        }

        JSONObject jsObjMoyenne = jsObj.getJSONObject("Moyenne");
        String moyenne = jsObjMoyenne.getString("moyenne");
        System.out.println("\n\n        Moyenne = " + moyenne);
    }
}
