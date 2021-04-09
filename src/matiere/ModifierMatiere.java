package matiere;

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
public class ModifierMatiere {

    public static void main(String[] args) {
        try {
            ModifierMatiere.modifierMatiere();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void modifierMatiere() throws MalformedURLException, IOException, JSONException {
        URL url = new URL("http://localhost/ApiM1/Matiere/modifierMatiere.php");

        JSONObject params = new JSONObject();
        params.put("codeMat", "E008");
        params.put("libelleMat", "Symfony");
        params.put("coefMat", 3);

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
