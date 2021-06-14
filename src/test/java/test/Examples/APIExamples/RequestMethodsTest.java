package test.Examples.APIExamples;

import auxiliar.tools.RequestMethods;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.*;

public class RequestMethodsTest {

    @Test
    public void getMultiHeaders() {
        RequestMethods requestMethods = new RequestMethods();
        List<Header> headerList = new ArrayList<>();
        headerList.add(new Header("Content=Type", "application/json"));
        headerList.add(new Header("X-Api-Token", "5f89541d69f78740a69e54df9b8fa32c7f7a1004"));
        Headers headers = new Headers(headerList);
        Response response = requestMethods.multiHeadersGetRequest("https://api.appcenter.ms/v0.1/apps/GMF-Digital-Banking/DEV-Android/releases/latest", headers);
        JsonPath jsonPath = response.jsonPath();
        String text = jsonPath.get("download_url");
        System.out.println(text);
    }

    @Test
    public void get() {
        String url = "https://reqres.in/api/users/2";
        RequestMethods requestMethods = new RequestMethods();
        Response response = requestMethods.makeGetRequest(url);
        JsonPath jsonPath = response.getBody().jsonPath();
        System.out.println(jsonPath.prettyPrint());
        System.out.println("------------------------------------");
        String text = jsonPath.get("support.url").toString();
        System.out.println(text);
    }

    @Test
    public void getParams() {
        String url = "https://reqres.in/api/users";
        HashMap<String, Integer> hash_map = new HashMap<>();
        hash_map.put("page", 2);
        RequestMethods requestMethods = new RequestMethods();
        Response response = requestMethods.makeGetRequest(url, hash_map);
        JsonPath jsonPath = response.getBody().jsonPath();
        System.out.println(jsonPath.prettyPrint());
        System.out.println("------------------------------------");
        String text = jsonPath.get("page").toString();
        System.out.println(text);
    }

    @Test
    public void post() {
        String url = "https://reqres.in/api/users";
        Map<String, Object> hash_map = new LinkedHashMap<>();
        hash_map.put("name", "Angel");
        hash_map.put("job", "QA");
        RequestMethods requestMethods = new RequestMethods();
        Response response = requestMethods.makePostRequest(url, hash_map);
        JsonPath jsonPath = response.getBody().jsonPath();
        System.out.println(jsonPath.prettyPrint());
        System.out.println("------------------------------------");
        String id = jsonPath.get("id").toString();
        System.out.println(id);
        String createdAt = jsonPath.get("createdAt").toString();
        System.out.println(createdAt);
    }
}
