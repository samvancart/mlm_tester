package mlm_tester.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import mlm_tester.services.MailerLiteApi;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class main {

    public static void main(String[] args) throws IOException {

        MailerLiteApi mla = new MailerLiteApi();
        String response = mla.getGroupsAsString();
        ArrayList<Group> groups = new ArrayList<>();
        groups = (ArrayList<Group>) mla.getParsedGroups(response);
        for (int i = 0; i < groups.size(); i++) {
            System.out.println("id: "+groups.get(i).getId()+" name: "+groups.get(i).getName());
        }

//        String API_KEY = "";
//        String url = "https://api.mailerlite.com/api/v2/groups";
//
//        Header headerKey = new Header("X-MailerLite-ApiKey", API_KEY);
//
//        HttpClient client = new HttpClient();
//
//        // Create a method instance.
//        GetMethod method = new GetMethod(url);
//
//        // Provide custom retry handler is necessary
//        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
//                new DefaultHttpMethodRetryHandler(3, false));
//        method.addRequestHeader(headerKey);
//        method.addRequestHeader("Content-Type", "application/json");
//
//        try {
//            // Execute the method.
//            int statusCode = client.executeMethod(method);
//        
//            if (statusCode != HttpStatus.SC_OK) {
//                System.err.println("Method failed: " + method.getStatusLine());
//            }
//
//            // Read the response body.
////            byte[] responseBody = method.getResponseBody();
//            BufferedReader rd = new BufferedReader(
//                    new InputStreamReader(method.getResponseBodyAsStream()));
//
//            String response = rd.readLine();
//            
////            System.out.println(response);
////            ArrayList<String> al = new ArrayList<>();
////            String groupId;
//
//            
//
//            char[] c = new char[response.length()];
//            c = response.toCharArray();
//            StringBuilder sb = new StringBuilder();
//            for (int i = 0; i < c.length; i++) {
//                char comma = ',';
//                if (c[i] != '[' && c[i] != ']' && c[i] != '{' && c[i] != '}') {
//                    sb.append(c[i]);
//                }
//                if (c[i] == comma) {
//                    String text = sb.toString();
//                    sb = new StringBuilder();
//                    if (text.contains("id") || text.contains("name")) {
//                        for (int j = 0; j < text.length(); j++) {
//                            if (text.charAt(j)== ':') {
//                                text=text.substring(j+1, text.length());
//                            }
//                        }
//                        System.out.println(text);
//                    }
//
//                }
//            }
//
////            StringBuilder result = new StringBuilder();
////            String line = "";
////            while ((line = rd.readLine()) != null) {
////                System.out.println(result.append(line));
////            }
//            // Deal with the response.
//            // Use caution: ensure correct character encoding and is not binary data
////            System.out.println(new String(responseBody));
//        } catch (HttpException e) {
//            System.err.println("Fatal protocol violation: " + e.getMessage());
//
//        } catch (IOException e) {
//            System.err.println("Fatal transport error: " + e.getMessage());
//            e.printStackTrace();
//        } finally {
//            // Release the connection.
//            method.releaseConnection();
//        }
    }
}
