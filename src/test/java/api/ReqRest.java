package api;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class ReqRest {
    RequestBody requestBody = new RequestBody();



    @Test
    public void FirstTest() {
        Response response = RestAssured.get("https://reqres.in/api/users/2");
        response.prettyPrint();
        String email = response.jsonPath().getString("data.email");
        System.out.println(email);
        Assert.assertTrue(email.endsWith("@reqres.in"));
        int status = response.statusCode();
        Assert.assertEquals(status, 200);
        String firstName = response.jsonPath().getString("data.first_name");
        Assert.assertEquals(firstName, "Janet");
    }


    @Test
    public void testName() {
        Response response = RestAssured.get("https://reqres.in/api/users/2");
        int status = response.statusCode();
        Assert.assertEquals(status, 200);
        String firstName = response.jsonPath().getString("data.first_name");
        Assert.assertEquals(firstName, "Janet");
    }

    @Test
    public void text() {
        Response response = RestAssured.get("https://reqres.in/api/users/2");
        String text = response.jsonPath().getString("support.text");
        System.out.println(text);
    }

    @Test
    public void generateToken() {

        Response response = RestAssured.given().contentType(ContentType.JSON).body(requestBody).
                post("/api/myaccount/auth/login");
        System.out.println(response.statusCode());
        String token = response.jsonPath().getString("jwt_token");
        System.out.println(token);
    }
}
