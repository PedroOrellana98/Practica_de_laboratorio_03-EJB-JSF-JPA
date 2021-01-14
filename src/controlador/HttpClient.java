package controlador;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class HttpClient {

	private ArrayList<OnHttpRequestComplete> httpRequestComplete;

    public HttpClient(OnHttpRequestComplete o) {
        httpRequestComplete = new ArrayList<>();
        httpRequestComplete.add(o);
    }

    public void excecute(String urlHttp){
        Response r = getResponse(urlHttp);
        for (OnHttpRequestComplete http : httpRequestComplete) {
            http.onComplete(r);
        }
    }

    public void excecute(String urlHttp, int idResponse){
        Response r = getResponse(urlHttp);
        Response response = new Response(r.getResult(),r.isSuccess(),idResponse);
        for (OnHttpRequestComplete http : httpRequestComplete) {
            http.onComplete(response);
        }
    }

    private Response getResponse(String urlHttp){
      
        String text = "";
        boolean success = false;
        URL url;
        HttpURLConnection urlConnection = null;
        try {
            url = new URL(urlHttp);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = urlConnection.getInputStream();
            InputStreamReader isw = new InputStreamReader(in);
            int data = isw.read();
            while (data != -1) {
                char current = (char) data;
                data = isw.read();
                text += String.valueOf(current);
            }
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                urlConnection.disconnect();
            } catch (Exception e) {
                success = false;
            }
        }

        return new Response(text,success,0);
    }
}
