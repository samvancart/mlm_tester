package mlm_tester.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import mlm_tester.domain.Group;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class MailerLiteApi {

    private String API_KEY = "";
    private String url = "https://api.mailerlite.com/api/v2/";

    public String getGroupsAsString() {
        String response = "";
        Header headerKey = new Header("X-MailerLite-ApiKey", API_KEY);

        HttpClient client = new HttpClient();

        GetMethod method = new GetMethod(url + "groups");

        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                new DefaultHttpMethodRetryHandler(3, false));
        method.addRequestHeader(headerKey);
        method.addRequestHeader("Content-Type", "application/json");

        try {
            int statusCode = client.executeMethod(method);

            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed: " + method.getStatusLine());
            }
            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(method.getResponseBodyAsStream()));

            response = rd.readLine();

        } catch (HttpException e) {
            System.err.println("Fatal protocol violation: " + e.getMessage());

        } catch (IOException e) {
            System.err.println("Fatal transport error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Release the connection.
            method.releaseConnection();
        }

        return response;
    }

    public List<Group> getParsedGroups(String response) {
        
        ArrayList<Group> groups = new ArrayList<>();
        
        ArrayList<String> data = new ArrayList<>();
        char[] c = new char[response.length()];
        c = response.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < c.length; i++) {
            char comma = ',';
            if (c[i] != '[' && c[i] != ']' && c[i] != '{' && c[i] != '}') {
                sb.append(c[i]);
            }
            if (c[i] == comma) {
                String text = sb.toString();
                sb = new StringBuilder();
                if (text.contains("id") || text.contains("name")) {
                    for (int j = 0; j < text.length(); j++) {
                        if (text.charAt(j) == ':') {
                            text = text.substring(j + 1, text.length());
                        }
                    }
                    data.add(text);
                }
            }
        }
        for (int i = 0; i < data.size(); i++) {
            groups.add(new Group(data.get(i),data.get(i+1)));
            i++;
        }

        return groups;
    }

}
