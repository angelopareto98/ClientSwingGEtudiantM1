package etudiant;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author anghack
 */
public class AfficherEtudiant {

    public static void main(String[] args) {

        try {
            AfficherEtudiant.afficherEtudiant();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void afficherEtudiant() throws Exception {

        String url = "http://localhost/ApiM1/Etudiant/afficherEtudiant.php";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        System.out.println("Response code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);

        }
        in.close();

        System.out.println("\n" + response.toString());

        System.out.println("\nJSON en Objet Java:");
        JSONObject myResponse = new JSONObject(response.toString());
        int nb = 1;

//        ArrayList<Object> listdata = new ArrayList<Object>();     
        JSONArray jArray = (JSONArray) myResponse.getJSONArray("etudiant");
        if (jArray != null) {
            int len = jArray.length();
            for (int i = 0; i < len; i++) {
                JSONObject etud = jArray.getJSONObject(i);
                System.out.println("\n ******** Etudiant " + nb++ + " *********** ");
                String numeroEtud = etud.getString("numEt");
                System.out.println("Numero: " + numeroEtud);
                String nomEtud = etud.getString("nomEt");
                System.out.println("Nom: " + nomEtud);
                String niveauEtud = etud.getString("niveauEt");
                System.out.println("Niveau: " + niveauEtud);

                // miAjouter anaz agnaty liste fotsiny io ambany io
//            listdata.add(jArray.get(i).toString());
            }
        }
        // miAfficher an'io commentaire ambony io
//        System.out.println("\nJson en Objet:\n "+listdata);

    }

}
