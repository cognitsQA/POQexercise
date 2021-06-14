package test.Examples.APIExamples;

import auxiliar.coverage.WordFileSearch;
import auxiliar.tools.HttpConnect;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.http.HttpResponse;

public class HttpConnectTest {
    public static final String URL_PREFIX = "https://www.pivotaltracker.com/services/v5/projects/{idproject}/";
    public static final String filter = "stories?fields=id%2Cstory_type&with_story_type=feature";

    public static void main(String[] args){
        String apiToken="{token}";
        HttpResponse<String> response = HttpConnect.HttpSendRequest(URL_PREFIX+ filter,"X-TrackerToken",apiToken);
        JSONParser parser = new JSONParser();
        try{
            JSONArray jsonArray = (JSONArray) parser.parse(response.body());
            for(int i=0;i<jsonArray.size();i++){
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                System.out.print("ID: "+jsonObject.get("id"));
                System.out.print("| type: "+jsonObject.get("story_type"));
                WordFileSearch.contador(jsonObject.get("id")+"");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
