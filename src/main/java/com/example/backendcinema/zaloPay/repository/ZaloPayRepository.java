package zalopay.repository;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ZaloPayRepository {

    public JSONObject createOrder(Map<String, String> params) throws Exception {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost("https://sb-openapi.zalopay.vn/v2/create");

        List<NameValuePair> parameters = params.entrySet().stream()
                .map(e -> new BasicNameValuePair(e.getKey(), e.getValue()))
                .collect(Collectors.toList());

        post.setEntity(new UrlEncodedFormEntity(parameters));

        try (CloseableHttpResponse res = client.execute(post);
             BufferedReader rd = new BufferedReader(new InputStreamReader(res.getEntity().getContent()))) {

            StringBuilder resultJsonStr = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                resultJsonStr.append(line);
            }

            return new JSONObject(resultJsonStr.toString());
        }
    }
}
