package tfm.muuinf.viciano.lledo.alejandro.inurse.dao;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;

class BasicDAO {

    static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    JSONObject getHTTP(final URL url) throws Exception {

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
            return inputStreamToJSON(inputStream);
        } finally {
            urlConnection.disconnect();
        }
    }

    String insertHTTP(final URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
            return IOUtils.toString(inputStream, "UTF-8");
        } finally {
            urlConnection.disconnect();
        }
    }

    private JSONObject inputStreamToJSON(final InputStream in) throws Exception {
        BufferedReader streamReader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
        StringBuilder responseStrBuilder = new StringBuilder();
        String inputStr;
        while ((inputStr = streamReader.readLine()) != null)
            responseStrBuilder.append(inputStr);
        return new JSONObject(responseStrBuilder.toString());
    }
}
