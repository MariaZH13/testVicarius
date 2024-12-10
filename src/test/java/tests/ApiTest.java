package tests;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ApiTest {
    @Test
    public void verifyRepositoryIsPublic() {
        try {
            String response = RestAssured.given()
                    .accept(ContentType.JSON)
                    .when()
                    .get("https://api.github.com/repos/SeleniumHQ/seleniumhq.github.io")
                    .then()
                    .extract().asString();

            System.out.println("Response: " + response);

            JSONObject jsonResponse = new JSONObject(response);
            int count = jsonResponse.getInt("count");
            Assert.assertEquals(count, 1, "Expected 1 result, but found " + count);
            System.out.println("Count is 1");

            boolean isPublic = !jsonResponse.getBoolean("private");
            System.out.println("Repository is public: " + isPublic);
        } catch (JSONException e) {
            System.err.println("Error parsing JSON: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

}

