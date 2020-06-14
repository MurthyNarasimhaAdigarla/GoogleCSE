import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.json.JSONObject;

import java.util.Scanner;


public class GoogleCustomSearch {

    public static String resp;
    public static Response response;

    public static void main(String args[]) throws Throwable {

        Scanner s = new Scanner(System.in);
        System.out.println("Enter any word");
        String word = s.nextLine();


        postCreateDefaultProfileXapiCall(word);
        parsing_the_json();

    }

    public static void postCreateDefaultProfileXapiCall(String word) throws Throwable {

        String baseURI = " https://www.googleapis.com/customsearch/v1?key=AIzaSyDlJJPBF3BSvq_eNDhmgZ7-bIUFj7PIMg0&cx=017576662512468239146:omuauf_lfve&q=";
        String ccfpService = baseURI + word;
        response = RestAssured.given().header("Content-Type", "application/json")
                .when().get(ccfpService)
                .then()
                .statusCode(200).extract().response();
        resp = response.getBody().prettyPrint();
        System.out.println("Response in String format " + resp);

    }


    public static void parsing_the_json() {

        JSONObject get_ProdStatus = new JSONObject(resp);
        for (int i = 0; i < 3; i++) {
            JSONObject all_Items = get_ProdStatus.getJSONArray("items").getJSONObject(i);
            System.out.println("Link no :" + i + " is " + all_Items.get("displayLink"));
        }


    }


}