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
public class CreerMatiere {

    public static void main(String[] args) {
        try {
            CreerMatiere.creerMatiere();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void creerMatiere() throws MalformedURLException, JSONException, IOException {

        URL url = new URL("http://localhost/ApiM1/Matiere/creerMatiere.php");

        JSONObject params = new JSONObject();
        params.put("codeMat", "E008");
        params.put("libelleMat", "Symfony");
        params.put("coefMat", 2);

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
        if (conn.getResponseCode() != 503) {
            InputStream in = new BufferedInputStream(conn.getInputStream());

            String result = IOUtils.toString(in, "UTF-8");

            JSONObject jsObj = new JSONObject(result);
            System.out.println("Message: " + jsObj.getString("message"));
        } else {
            System.err.println("Alllllerrrrt !!!!!  Cet code matiere existe deja");
        }
    }
}
