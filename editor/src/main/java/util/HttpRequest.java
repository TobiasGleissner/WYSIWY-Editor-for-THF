package util;

import gui.Config;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class HttpRequest {

    private static final String USER_AGENT = Config.name;
    private String url;
    private String response;
    Map<String,Object> URLParameters;

    public HttpRequest(String url,Map<String,Object> URLParameters){
        this.URLParameters = URLParameters;
        this.url = url;
    }

    public void sendPost() throws IOException {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //  header
        con.setRequestMethod("POST");
        con.setRequestProperty(Config.USER_AGENT, HttpRequest.USER_AGENT);
        String urlParameters = this.createParameters();

        //System.out.println(urlParameters);

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        // read response
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) response.append(inputLine);
        in.close();

        this.response = response.toString();

    }

    private String createParameters(){
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (String s : this.URLParameters.keySet()){
            i++;
            sb.append(s);
            sb.append("=");
            sb.append(this.URLParameters.get(s).toString());
            if (i != this.URLParameters.keySet().size()){
                sb.append("&");
            }
        }
        return sb.toString();
    }

    public String getResponse(){
        return this.response;
    }
}
