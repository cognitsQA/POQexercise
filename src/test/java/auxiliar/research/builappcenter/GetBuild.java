package auxiliar.research.builappcenter;

import auxiliar.tools.RequestMethods;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;

public class GetBuild {
    public static void main(String[] args) {
        String releaseBuscado="1922";
        obtenerRealese("DEV-Android",releaseBuscado);
        obtenerRealese("DEV-iOS",releaseBuscado);
    }

    public static String obtenerRealese(String os, String releaseBuscado){
        String respuesta=null;
        RequestMethods requestMethods = new RequestMethods();
        List<Header> headerlist = new ArrayList<Header>();
        headerlist.add(new Header("Content=Type", "application/json"));
        headerlist.add(new Header("X-Api-Token", "85b34583ada4e16da700f9e17d8357720866bd45"));
        Headers headers = new Headers(headerlist);
        Response response = requestMethods.multiHeadersGetRequest("https://api.appcenter.ms/v0.1/apps/GMF-Digital-Banking/"+os+"/releases/", headers);
        JsonPath jsonPath = response.jsonPath();
        List<String> text = jsonPath.get("id");

        requestMethods = new RequestMethods();
        headerlist = new ArrayList<Header>();
        headerlist.add(new Header("Content=Type", "application/json"));
        headerlist.add(new Header("X-Api-Token", "85b34583ada4e16da700f9e17d8357720866bd45"));
        headers = new Headers(headerlist);
        for (int i=0;i<50;i++){
            String idStr=String.valueOf(text.get(i));
            response = requestMethods.multiHeadersGetRequest("https://api.appcenter.ms/v0.1/apps/GMF-Digital-Banking/"+os+"/releases/"+idStr, headers);
            jsonPath = response.jsonPath();
            String branchCommit = jsonPath.get("release_notes");
            String url = jsonPath.get("download_url");
            String version = jsonPath.get("short_version");
            if(branchCommit.contains(releaseBuscado)){
                String branch=branchCommit.split("Branch: ")[1].split(" ")[0];
                branch=branch.replace("commit:","").replace("Commit:","");
                System.out.print(branch);
                System.out.println("realease:"+idStr);
                System.out.println("short_version:"+version);
                System.out.println("link:"+url);
                respuesta=idStr;
                break;
            }
        }
        return respuesta;
    }

}
