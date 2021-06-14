package test.Examples.APIExamples;
import auxiliar.tools.RequestMethods;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;

public class Stage2APITest {

    @Test
    public void testLogin(){
        RequestMethods requestMethods = new RequestMethods();
        List<Header> headerList = new ArrayList<>();
        headerList.add(new Header("Content-Type", "application/json"));
        headerList.add(new Header("poq-app-id", "1"));
        headerList.add(new Header("poq-app-identifier", ""));
        headerList.add(new Header("poq-currency-identifier", "USD"));
        headerList.add(new Header("version-code", "19.0"));
        headerList.add(new Header("accept-language", "es-es"));
        headerList.add(new Header("platform", "iphone"));
        headerList.add(new Header("poq-user-id", "2CF7372D-C036-4ED5-A9F4-5A28BC1DE443"));
        headerList.add(new Header("currency-code", "USD"));
        headerList.add(new Header("appuseragent", "Poq-Native-iOS-App"));
        headerList.add(new Header("poq-auth", "e0KzaoUmCUraKuIeazIS5Hzmw4KQRozyAqEkK2bco4M="));
        Headers headers = new Headers(headerList);

        Map<String, Object> map2 = new LinkedHashMap<>();
        map2.put("isMasterCard", false);
        map2.put("username", "test2@test.com");
        map2.put("password", "test2@2021");

        Response response = requestMethods.makePostMultiheaders("https://missguidedus.poq.io/account/login/1/BDA9034A-6C55-4A19-90C4-A87FB3337B84", headers, map2);
        if(response.statusCode()!=200){
            Assert.fail("Status code: "+response.statusCode());
        }
        System.out.println("Status code:"+response.statusCode());

        JsonPath jsonPath = response.getBody().jsonPath();
        //System.out.println("Respuesta:"+jsonPath.prettyPrint());

        String respuesta = jsonPath.get("message");
        System.out.println("message:"+respuesta);
    }

    @Test
    public void testRegister(){
        RequestMethods requestMethods = new RequestMethods();
        List<Header> headerList = new ArrayList<>();
        headerList.add(new Header("Content-Type", "application/json"));
        headerList.add(new Header("poq-app-id", "1"));
        headerList.add(new Header("poq-app-identifier", ""));
        headerList.add(new Header("poq-currency-identifier", "USD"));
        headerList.add(new Header("version-code", "19.0"));
        headerList.add(new Header("accept-language", "es-es"));
        headerList.add(new Header("platform", "iphone"));
        headerList.add(new Header("poq-user-id", "FBFE5113-BD26-4DD5-B255-F7B5DCCE96BF"));
        headerList.add(new Header("currency-code", "USD"));
        headerList.add(new Header("appuseragent", "Poq-Native-iOS-App"));
        headerList.add(new Header("poq-auth", "OrNOna9YedqkWIdHRuriwr9PilE60HzasMuXZejW0ww="));
        Headers headers = new Headers(headerList);

        Map<String, Object> map2 = new LinkedHashMap<>();
        Map<String, Object> profile = new LinkedHashMap<>();
        profile.put("email", "test4@test.com");
        profile.put("firstName", "Test4");
        profile.put("lastName", "Test4");
        profile.put("isPromotion", false);
        profile.put("encryptedPassword", "test4@2021");
        Map<String, String> credentials = new LinkedHashMap<>();
        credentials.put("username", "test4@test.com");
        credentials.put("password", "test3@2021");

        map2.put("profile",profile);
        map2.put("credentials",credentials);
        map2.put("isPromotion", false);

        Response response = requestMethods.makePostMultiheaders("https://missguidedus.poq.io/account/register/1/FBFE5113-BD26-4DD5-B255-F7B5DCCE96BF", headers, map2);
        if(response.statusCode()!=400){
            Assert.fail("Status code: "+response.statusCode());
        }
        System.out.println("Status code:"+response.statusCode());
        JsonPath jsonPath = response.getBody().jsonPath();
        //System.out.println("Respuesta:"+jsonPath.prettyPrint());

        String respuesta = jsonPath.get("message");
        System.out.println("message:"+respuesta);
    }

    @Test
    public void testProductDetail(){
        RequestMethods requestMethods = new RequestMethods();
        List<Header> headerList = new ArrayList<>();
        headerList.add(new Header("Content-Type", "application/json"));
        Headers headers = new Headers(headerList);
        Response response = requestMethods.multiHeadersGetRequest("https://missguidedus.poq.io/products/detail/1/147825?externalId=1694824&poqUserId=2CF7372D-C036-4ED5-A9F4-5A28BC1DE443", headers);
        if(response.statusCode()!=200){
            Assert.fail("Status code: "+response.statusCode());
        }
        System.out.println("Status code:"+response.statusCode());
        JsonPath jsonPath = response.getBody().jsonPath();
        //System.out.println("Respuesta:"+jsonPath.prettyPrint());

        String externalID = jsonPath.get("externalID");
        System.out.println("ExternalID: "+externalID);
        List<Map<String, String>> expressions = jsonPath.getList("productPictures");
        for(Map<String, String> returnedLink : expressions){
            String url=returnedLink.get("url");
            System.out.println(url);
        }

    }
}

