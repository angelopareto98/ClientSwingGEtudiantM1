package etudiant;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author anghack
 */
public class ModifierEtudiant {

    public static void main(String[] args) {
        try {
            ModifierEtudiant.modifierEtudiant();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void modifierEtudiant() throws MalformedURLException, JSONException, IOException {

        URL url = new URL("http://localhost/ApiM1/Etudiant/modifierEtudiant.php");

        JSONObject params = new JSONObject();
        params.put("numEt", 5);
        params.put("nomEt", "Puyol");
        params.put("niveauEt", "L1");

        String valeur = params.toString();
        System.out.println(params);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("PUT");
        conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        conn.setRequestProperty("Accept", "application/json");
        conn.setDoInput(true); //
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
