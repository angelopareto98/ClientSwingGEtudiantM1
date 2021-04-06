/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tableau;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;

/**
 *
 * @author anghack
 */
public class TutoTsyIlainaLotry {
    
    public static void tsydeIlaina() throws IOException {
               String url = "http://localhost/ApiM1/Etudiant/ListeEtudiantParNiveau.php";
       
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);
       
        HttpResponse response = client.execute(request);
        int actualResponseCode = response.getStatusLine().getStatusCode();
        Assert.assertEquals(200, actualResponseCode);
        
        
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        
        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {            
            result.append(line);
        }
        
        System.out.println(result);
    }
    
}
