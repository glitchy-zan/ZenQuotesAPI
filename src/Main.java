import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://zenquotes.io/api/quotes")).build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(Main::parse)
                .join();
    }

    public static String parse(String responseBody) {
        JSONArray quotes = new JSONArray(responseBody);
        int r = new Random().nextInt(quotes.length());
        JSONObject quote = quotes.getJSONObject(r);
        System.out.println(quote.getString("q") + "  -" + quote.getString("a"));
        return null;
    }
}
