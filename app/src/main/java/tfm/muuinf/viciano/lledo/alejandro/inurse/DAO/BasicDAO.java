package tfm.muuinf.viciano.lledo.alejandro.inurse.DAO;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Alex on 31/01/2017.
 */

public class BasicDAO {

    public JSONObject realizarPeticionHTTP(final URL url) throws Exception {

        final HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            final InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            return inputStreamToJSON(in);
        } finally {
            urlConnection.disconnect();
        }
    }

    private JSONObject inputStreamToJSON(final InputStream in) throws Exception {
        final BufferedReader streamReader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
        final StringBuilder responseStrBuilder = new StringBuilder();
        String inputStr;
        while ((inputStr = streamReader.readLine()) != null)
            responseStrBuilder.append(inputStr);
        return new JSONObject(responseStrBuilder.toString());
    }
}