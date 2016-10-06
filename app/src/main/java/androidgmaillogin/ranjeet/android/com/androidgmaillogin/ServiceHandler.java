package androidgmaillogin.ranjeet.android.com.androidgmaillogin;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Ranjeet on 20-01-2016.
 */
public class ServiceHandler {

    HttpURLConnection httpURLConnection = null;
    BufferedReader bufferedReader = null;
    URL url = null;
    InputStream inputStream;
    Writer writer;
    String jsonData;

    public String getJsonData(String urlString) throws IOException {
        url = new URL(urlString);
        httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.connect();
        inputStream = httpURLConnection.getInputStream();
        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuffer buffer = new StringBuffer();
        String line = "";
        while ((line = bufferedReader.readLine()) != null) {
            buffer.append(line);
        }
        String finalJson = buffer.toString();
        Log.e("finalJson","finalJson"+finalJson);
        return finalJson;
    }
    public String postJsonData(String urlString, String jsonData) throws IOException {
        url = new URL(urlString);
        this.jsonData = jsonData;
        httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Content-Type", "application/json");
        httpURLConnection.setRequestProperty("Accept", "application/json");
        writer = new BufferedWriter(new OutputStreamWriter(httpURLConnection.getOutputStream(), "UTF-8"));
        writer.write(jsonData);
        writer.close();
        inputStream = httpURLConnection.getInputStream();
        if (inputStream == null) {
            return null;
        }
        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String inputLine;
        StringBuffer stringBuffer = new StringBuffer();
        while ((inputLine = bufferedReader.readLine()) != null) {
            stringBuffer.append(inputLine);
        }
        if (stringBuffer.length() == 0) {
            return null;
        }

        String jsonResponse = stringBuffer.toString();
        Log.i("Response Data is", jsonResponse);
        return jsonResponse;


    }


}
