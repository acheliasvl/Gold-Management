import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.json.JSONObject;

public class GoldRateService {

    private static final String API_KEY =
            System.getenv("GOLD_API_KEY");

    public static String getCurrentGramGold() {

        try {
            double gram24k = getGramGold24K();
            return String.format("%.2f", gram24k);

        } catch (Exception e) {
            e.printStackTrace();
            return "—";
        }
    }

    private static double getGramGold24K() throws Exception {

        String urlStr = "https://www.goldapi.io/api/XAU/USD";
        URL url = new URL(urlStr);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setRequestMethod("GET");
        con.setRequestProperty("x-access-token", API_KEY);
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("User-Agent", "JavaFX-App");

        int status = con.getResponseCode();
        System.out.println("HTTP STATUS (XAU/USD): " + status);

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        status >= 400 ? con.getErrorStream() : con.getInputStream()
                )
        );

        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        String responseText = response.toString().trim();
        System.out.println("RAW RESPONSE = " + responseText);

        if (!responseText.startsWith("{")) {
            throw new RuntimeException("Invalid JSON response");
        }

        JSONObject json = new JSONObject(responseText);


        return json.getDouble("price_gram_24k");
    }
}
