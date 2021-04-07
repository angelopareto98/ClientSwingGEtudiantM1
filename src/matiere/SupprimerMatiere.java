
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
public class SupprimerMatiere {
    
    public static void main(String[] args) {
        try {
            SupprimerMatiere.supprimerMatiere();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void supprimerMatiere() throws MalformedURLException, JSONException, IOException{
    URL url = new URL("http://localhost/ApiM1/Matiere/supprimerMatiere.php");

        JSONObject params = new JSONObject();
        params.put("codeMat", "E005");

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
