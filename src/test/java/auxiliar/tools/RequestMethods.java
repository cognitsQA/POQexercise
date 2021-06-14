package auxiliar.tools;

import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class RequestMethods {
    public Response multiHeadersGetRequest(String url, Headers headers) {
        return given()
                .headers(headers)
                .when()
                .get(url)
                .then()
                .extract()
                .response();
    }

    public Response makeGetRequest(String url) {
        return given()
                .header("Content=Type", "application/json")
                .when()
                .get(url)
                .then()
                .extract()
                .response();
    }

    public Response makeGetRequest(String url, HashMap params) {
        return given()
                .header("Content=Type", "application/json")
                .params(params)
                .get(url)
                .then()
                .extract()
                .response();
    }

    public Response makeDeleteRequest(String url) {
        return given()
                .header("Content=Type", "application/json")
                .when()
                .delete(url)
                .then()
                .extract()
                .response();
    }

    public Response makePostRequest(String url, Map<String,Object> body) {
        return given()
                .header("Content=Type", "application/json")
                .body(body)
                .when()
                .post(url)
                .then()
                .extract()
                .response();
    }
    public Response makePostMultiheaders(String url, Headers headers, Map<String,Object> body) {
        return given()
                //.urlEncodingEnabled(false)
                //.log()
                //.all()
                //.urlEncodingEnabled(true)
                .headers(headers)
                .body(body)
                .when()
                .post(url)
                .then()
                .extract()
                .response();
    }
}
