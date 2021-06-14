package test.Examples.APIExamples;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.annotations.Test;
import java.io.IOException;


public class Stage2APITestOkhttp {

    @Test
    public void testLogin() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\n\t\"isMasterCard\": false,\n\t\"username\": \"test2@test.com\",\n\t\"password\": \"test2@2021\"\n}");
        Request request = new Request.Builder()
                .url("https://missguidedus.poq.io/account/login/1/BDA9034A-6C55-4A19-90C4-A87FB3337B84")
                .method("POST", body)
                .addHeader("poq-app-id", "1")
                .addHeader("poq-app-identifier", "")
                .addHeader("poq-currency-identifier", "USD")
                .addHeader("version-code", "19.0")
                .addHeader("accept-language", "es-es")
                .addHeader("platform", "iphone")
                .addHeader("poq-user-id", "2CF7372D-C036-4ED5-A9F4-5A28BC1DE443")
                .addHeader("currency-code", "USD")
                .addHeader("appuseragent", "Poq-Native-iOS-App")
                .addHeader("poq-auth", "e0KzaoUmCUraKuIeazIS5Hzmw4KQRozyAqEkK2bco4M=")
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        String responseStr = response.body().string();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(responseStr);
        String prettyJsonString = gson.toJson(je);
        System.out.println(prettyJsonString);
    }
}
